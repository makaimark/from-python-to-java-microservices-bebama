package controller;

import spark.Request;
import spark.Response;

public class APIController {

    private String sessionId;
    private String webShopId;

    public String api(Request request, Response response) {
        webShopId = request.queryParams("webshopId");
        sessionId = request.queryParams("sessionId");
        return "";
    }

    public String visitorCounter(Request request, Response response) {
        sessionId = request.queryParams("sessionId");
        return "";
    }

    public String countVisitTime(Request request, Response response) {
        Integer time = Integer.valueOf(request.queryParams("time"));
        sessionId = request.queryParams("sessionId");
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
