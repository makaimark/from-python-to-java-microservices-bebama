package controller;

import connection.db.AnalyticsDaoJDBC;
import model.Analytics;
import model.LocationModel;
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
    private String webShopId;
    private Timestamp startTime;
    private Timestamp stopTime;


    public ModelAndView renderTest(Request req, Response res) throws Exception {
        startSession(req, res);
        Map<Object, Object> params = new HashMap<>();
        return new ModelAndView(params, "test");
    }

    public String api(Request request, Response response) {
        webShopId = request.queryParams("webshopId");
        sessionId = request.queryParams("sessionId");
        return "";
    }

    public String visitTimeCounter(Request request, Response response) throws ParseException {
        webShopId = request.queryParams("webshopId");
        sessionId = request.queryParams("sessionId");

        if ( request.queryParams().size() == 3 ) {
            String start = request.queryParams("startTime");
            String stop = request.queryParams("stopTime");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            VisitTimeController.averageVisitTimeByTime(Integer.parseInt(webShopId), convertToTimeStamp(format.parse(start)), convertToTimeStamp(format.parse(stop)));
        } else {
            VisitTimeController.averageVisitTime(Integer.parseInt(webShopId));
        }
        return "";
    }

    public String visitorCounter(Request request, Response response) throws ParseException {
        webShopId = request.queryParams("webshopId");
        sessionId = request.queryParams("sessionId");

        if ( request.queryParams().size() == 3 ) {
            String start = request.queryParams("startTime");
            String stop = request.queryParams("stopTime");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            VisitorController.visitorsByTime(Integer.parseInt(webShopId), convertToTimeStamp(format.parse(start)), convertToTimeStamp(format.parse(stop)));
        } else {
            VisitorController.visitors(Integer.parseInt(webShopId));
        }
        return "";
    }

    public String stopSession(Request request, Response response) throws ParseException {
        String time = request.queryParams("time");
        Date date = new Date(Long.parseLong(time));
        String formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("Stop" + formatted);
        this.stopTime = convertToTimeStamp(date);
        try {
            analitycs();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String startSession(Request request, Response response) {
        Date date = new Date();
        String formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("Start" + formatted);
        this.startTime = convertToTimeStamp(date);
        return "";
    }

    public String countRevenue(Request request, Response response) throws ParseException {
        sessionId = request.queryParams("sessionId");
        webShopId = request.queryParams("webshopId");

        if ( request.queryParams().size() == 3 ) {
            String start = request.queryParams("startTime");
            String stop = request.queryParams("stopTime");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            RevenueController.avenueByTime(Integer.parseInt(webShopId), convertToTimeStamp(format.parse(start)), convertToTimeStamp(format.parse(stop)));
        } else {
            RevenueController.totalAvenue(Integer.parseInt(webShopId));
        }
        return "";
    }

    private void analitycs() throws org.json.simple.parser.ParseException {
        LocationModel location = LocationModel.getAllLocations().get(0);
        float amount = 10;
        Currency currency = Currency.getInstance(Locale.US);
        Analytics model = new Analytics(1, sessionId, this.startTime, this.stopTime, location, amount, String.valueOf(currency));
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
