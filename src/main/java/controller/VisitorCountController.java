package controller;


import dao.JDBC.AnalyticsDaoJDBC;

import java.sql.Timestamp;

public class VisitorCountController {

    /**
     * Counts the number of visitors by webshop id
     * @param webshop The id of the webshop
     * @return Integer, the sum of the visitors
     */
    public static int totalVisitors(Integer webshop) {
         return new AnalyticsDaoJDBC().findByWebshop(webshop).size();
    }

    /**
     * Counts the number of visitors by webshop id and by time
     * @param webshop The id of the webshop
     * @param startTime The start time of the statistic
     * @param endTime The end time of the statistic
     * @return Integer, the sum of the visitors
     */
    public static int visitorsByTime(Integer webshop, Timestamp startTime, Timestamp endTime){
        return new AnalyticsDaoJDBC().findByWebshopTime(webshop, startTime, endTime).size();
    }
}
