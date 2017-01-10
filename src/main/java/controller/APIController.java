package controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class APIController {

    private String sessionId;
    private String webShopId;

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

    public String visitorCounter(Request request, Response response) {
        sessionId = request.queryParams("sessionId");
        return "";
    }

    public String stopSession(Request request, Response response) throws ParseException {
        String time = request.queryParams("time");
        Date date = new Date(Long.parseLong(time));
        String formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("Stop" + formatted);
        return "";
    }

    public String startSession(Request request, Response response){
        sessionId = request.session().id();
        Date date = new Date();
        String formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("Start" + formatted);
        return "";
    }

    public String countRevenue(Request request, Response response) {
        sessionId = request.queryParams("sessionId");
        return "";
    }

    public String getWebShopId() {
        return webShopId;
    }

    public String getSessionId() {
        return sessionId;
    }
}
