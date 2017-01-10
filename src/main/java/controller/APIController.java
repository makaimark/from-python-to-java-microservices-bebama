package controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class APIController {

    private String sessionId;
    private String webShopId;

    public ModelAndView renderTest(Request req, Response res) throws Exception {
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

    public String stopSession(Request request, Response response) {
        String time = request.queryParams("time");
        return "";
    }

    public String countRevenue(Request request, Response response) {
        sessionId = request.queryParams("sessionId");
        return "";
    }

    public String startSession(Request request, Response response){
        sessionId = request.session().id();
        return "";
    }

    public String getWebShopId() {
        return webShopId;
    }

    public String getSessionId() {
        return sessionId;
    }
}
