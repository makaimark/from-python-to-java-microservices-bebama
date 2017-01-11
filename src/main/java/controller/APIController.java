package controller;

import connection.db.AnalyticsDaoJDBC;
import model.Analytics;
import model.LocationModel;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class APIController {

    private String sessionId;
    private int webShopId = 1;
    private Timestamp startTime;
    private Timestamp stopTime;
    String start;
    String stop;
    DateFormat format;


    public ModelAndView renderMain(Request req, Response res) throws Exception {
        Map<Object, Object> params = new HashMap<>();
        startSession(req, res);
        return new ModelAndView(params, "time_location");
    }

    private void getTimes(Request req, Response res) {
        start = req.queryParams("startTime");
        stop = req.queryParams("stopTime");
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public String api(Request req, Response res) {
        sessionId = req.session().id();
        startSession(req, res);
        return "";
    }

    public String visitTimeCounter(Request request, Response response) throws ParseException, SQLException {
        webShopId = Integer.parseInt(request.queryParams("webshopId"));
        sessionId = request.queryParams("sessionId");
        if (request.queryParams().size() == 3) {
            getTimes(request, response);
            return VisitTimeController.averageVisitTimeByTime(webShopId, convertToTimeStamp(format.parse(start)), convertToTimeStamp(format.parse(stop)));
        } else {
            return VisitTimeController.averageVisitTime(webShopId);
        }
    }

    public int visitorCounter(Request request, Response response) throws ParseException, SQLException {
        webShopId = Integer.parseInt(request.queryParams("webshopId"));
        sessionId = request.queryParams("sessionId");

        if (request.queryParams().size() == 3) {
            getTimes(request, response);
            return VisitorController.visitorsByTime(webShopId, convertToTimeStamp(format.parse(start)), convertToTimeStamp(format.parse(stop)));
        } else {
            return VisitorController.visitors(webShopId);
        }
    }

    public String stopSession(Request req, Response res) throws ParseException, org.json.simple.parser.ParseException {
        String time = req.queryParams("time");
        Date date = new Date(Long.parseLong(time));
        String formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("Stop " + formatted);
        this.stopTime = convertToTimeStamp(date);
        analytics(req, res);
        return "";
    }

    public String startSession(Request req, Response res) {
        sessionId = req.session().id();
        Date date = new Date();
        String formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("Start " + formatted);
        this.startTime = convertToTimeStamp(date);
        return "";
    }

    public Float countRevenue(Request request, Response response) throws ParseException, SQLException {
        sessionId = request.queryParams("sessionId");
        webShopId = Integer.parseInt(request.queryParams("webshopId"));

        if (request.queryParams().size() == 3) {
            getTimes(request, response);
            return RevenueController.revenueByTime(webShopId, convertToTimeStamp(format.parse(start)), convertToTimeStamp(format.parse(stop)));
        } else {
            return RevenueController.totalRevenue(webShopId);
        }
    }

    public int getWebShopId() {
        return webShopId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void analytics(Request req, Response res) throws org.json.simple.parser.ParseException {
        LocationModel location = LocationModel.getAllLocations().get(0);
        float amount = 10;
        Currency currency = Currency.getInstance(Locale.US);
        Analytics model = new Analytics(getWebShopId(), getSessionId(), this.startTime, this.stopTime, location, amount, String.valueOf(currency));
        try {
            AnalyticsDaoJDBC.add(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Timestamp convertToTimeStamp(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        return new java.sql.Timestamp(date.getTime());
    }
}
