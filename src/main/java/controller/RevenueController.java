package controller;

import dao.JDBC.AnalyticsDaoJDBC;
import model.Analytics;

import java.sql.Timestamp;
import java.util.List;

public class RevenueController {

    /**
     * Counts the revenue by webshop id
     * @param webshop The id of the webshop
     * @return Float, the sum of revenues
     */
    public static Float totalRevenue(Integer webshop) {
        return countRevenue(new AnalyticsDaoJDBC().findByWebshop(webshop));
    }

    /**
     * Counts the revenue by webshop id and by time
     * @param webshop The id of the webshop
     * @param startTime the start time of the statistic
     * @param endTime The end time of the statistic
     * @return Float, the sum of the revenues by time
     */
    public static Float revenueByTime(Integer webshop, Timestamp startTime, Timestamp endTime){
        return countRevenue(new AnalyticsDaoJDBC().findByWebshopTime(webshop, startTime, endTime));
    }

    /**
     * Counts the revenue from list of Analityc objects
     * @param purchases A List, that contains the purchases
     * @return Float, the sum of the revenues
     */
    private static Float countRevenue(List<Analytics> purchases){
        Double avenue = purchases.stream().map(Analytics::getAmount).mapToDouble(Float::floatValue).sum();
        return avenue.floatValue();
    }
}
