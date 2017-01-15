package analyticsService.controller;

import analyticsService.dao.JDBC.AnalyticsDaoJDBC;

import java.sql.Timestamp;

public class VisitorCountController {

    public static int totalVisitors(String apiKey) {
         return new AnalyticsDaoJDBC().findByWebshop(apiKey).size();
    }

    public static int visitorsByTime(String apiKey, Timestamp startTime, Timestamp endTime){
        return new AnalyticsDaoJDBC().findByWebshopTime(apiKey, startTime, endTime).size();
    }
}
