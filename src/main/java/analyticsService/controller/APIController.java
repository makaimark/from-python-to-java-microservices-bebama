package analyticsService.controller;

import analyticsService.dao.JDBC.AnalyticsDaoJDBC;
import analyticsService.dao.JDBC.WebshopDaoJDBC;
import analyticsService.model.Analytics;
import analyticsService.model.LocationModel;
import analyticsService.model.Webshop;
import org.json.simple.JSONObject;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class APIController {

    private String sessionId;
    private String webShopId = "1";
    private Timestamp startTime;
    private Timestamp endTime;
    private Date start;
    private Date stop;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Map<String, Integer> topLocations;

    public String getWebShopId() {
        return webShopId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public ModelAndView renderMain(Request req, Response res) {
        Map<Object, Object> params = new HashMap<>();
        startSession(req, res);
        return new ModelAndView(params, "time_location");
    }

    private void getTimes(Request req, Response res) throws ParseException {
        start = customDateParser(req.queryParams("startTime"));
        stop = customDateParser(req.queryParams("endTime"));
    }

    public String stopSession(Request req, Response res) {
        String time = req.queryParams("time");
        Date date = new Date(Long.parseLong(time));
        this.endTime = convertToTimeStamp(date);
        analytics(req, res);
        return "";
    }

    public String startSession(Request req, Response res) {
        sessionId = req.session().id();
        Date date = new Date();
        this.startTime = convertToTimeStamp(date);
        return "";
    }

    public void analytics(Request req, Response res) {
        LocationModel location = LocationModel.getAllLocations().get(0);
        float amount = 10;
        Currency currency = Currency.getInstance(Locale.US);
        Analytics model = new Analytics(new WebshopDaoJDBC().findByApyKey(getWebShopId()), getSessionId(), this.startTime, this.endTime, location, amount, String.valueOf(currency));
        try {
            new AnalyticsDaoJDBC().add(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String api(Request req, Response res) throws ParseException {
        webShopId = req.queryParams("webshopId");
        topLocations = LocationVisitorController.topLocations(req.queryParams("webshopId"));
        int highestVisitorCount = Collections.max(topLocations.values());
        String topLocation = topLocations.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), highestVisitorCount))
                .map(Map.Entry::getKey).findFirst().orElse(null);
        Map<String, Object> analytic = new HashMap<>();
        analytic.put("visitors", VisitorCountController.totalVisitors(webShopId));
        analytic.put("average_visit_time", VisitTimeController.averageVisitTime(webShopId));
        analytic.put("most_visited_from", topLocation);
        analytic.put("revenue", RevenueController.totalRevenue(webShopId));
        return convertMapToJSONString(analytic);
    }

    public String visitorCounter(Request req, Response res) throws ParseException {
        webShopId = req.queryParams("webshopId");
        sessionId = req.queryParams("sessionId");
        Map<String, Integer> counter = new HashMap<>();
        if (req.queryParams().size() == 3) {
            getTimes(req, res);
            counter.put("visitors", VisitorCountController.visitorsByTime(webShopId, convertToTimeStamp(start), convertToTimeStamp(stop)));
        } else {
            counter.put("visitors", VisitorCountController.totalVisitors(webShopId));
        }
        return convertMapToJSONString(counter);
    }

    public String visitTimeCounter(Request req, Response res) throws ParseException {
        webShopId = req.queryParams("webshopId");
        sessionId = req.queryParams("sessionId");
        if (req.queryParams().size() == 3) {
            getTimes(req, res);
            return convertMapToJSONString(VisitTimeController.averageVisitTimeByTime(webShopId, convertToTimeStamp(start), convertToTimeStamp(stop)));
        } else {
            return convertMapToJSONString(VisitTimeController.averageVisitTime(webShopId));
        }
    }

    public String locationVisits(Request req, Response res) throws ParseException {
        sessionId = req.queryParams("sessionId");
        webShopId = req.queryParams("webshopId");
        if (req.queryParams().size() == 3) {
            getTimes(req, res);
            return convertMapToJSONString(LocationVisitorController.topLocationsByTime(webShopId, convertToTimeStamp(start), convertToTimeStamp(stop)));
        } else return convertMapToJSONString(LocationVisitorController.topLocations(webShopId));
    }

    public String countRevenue(Request req, Response res) throws ParseException {
        sessionId = req.queryParams("sessionId");
        webShopId = req.queryParams("webshopId");
        Map<String, Float> revenue = new HashMap<>();
        if (req.queryParams().size() == 3) {
            getTimes(req, res);
            revenue.put("revenue", RevenueController.revenueByTime(webShopId, convertToTimeStamp(start), convertToTimeStamp(stop)));
        } else {
            revenue.put("revenue", RevenueController.totalRevenue(webShopId));
        }
        return convertMapToJSONString(revenue);
    }

    private Date customDateParser(String inputDate) throws ParseException {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date tempDate = inputFormat.parse(inputDate);
            String formattedDate = format.format(tempDate);
            return format.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Timestamp convertToTimeStamp(Date date) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.MILLISECOND, 0);
            return new java.sql.Timestamp(date.getTime());
        } else {
            return null;
        }
    }

    private String convertMapToJSONString(Map map){
        return (new JSONObject(){{putAll(map);}}).toString();
    }

}