package controller;


import dao.JDBC.AnalyticsDaoJDBC;

import java.sql.Timestamp;

public class VisitorCountController {

    public static int totalVisitors(Integer webshop) {
         return new AnalyticsDaoJDBC().findByWebshop(webshop).size();
    }

    public static int visitorsByTime(Integer webshop, Timestamp startTime, Timestamp endTime){
        return new AnalyticsDaoJDBC().findByWebshopTime(webshop, startTime, endTime).size();
    }
}
