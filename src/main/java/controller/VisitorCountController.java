package controller;


import dao.JDBC.AnalyticsDaoJDBC;

import java.sql.Timestamp;

public class VisitorCountController {

    /**
     * Counts the number of visitors by webshop id
     * @param webshop
     * @return Integer
     */
    public static int totalVisitors(Integer webshop) {
         return new AnalyticsDaoJDBC().findByWebshop(webshop).size();
    }

    /**
     * Counts the number of visitors by webshop id and by time
     * @param webshop
     * @param startTime
     * @param endTime
     * @return Integer
     */
    public static int visitorsByTime(Integer webshop, Timestamp startTime, Timestamp endTime){
        return new AnalyticsDaoJDBC().findByWebshopTime(webshop, startTime, endTime).size();
    }
}
