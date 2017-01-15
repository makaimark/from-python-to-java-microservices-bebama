package controller;

import dao.JDBC.AnalyticsDaoJDBC;
import model.Analytics;

import java.sql.Timestamp;
import java.util.List;

public class RevenueController {

    /**
     * Counts the revenue by webshop id
     * @param webshop
     * @return Float
     */
    public static Float totalRevenue(Integer webshop) {
        return countRevenue(new AnalyticsDaoJDBC().findByWebshop(webshop));
    }

    /**
     * Counts the revenue by webshop id and by time
     * @param webshop
     * @param startTime
     * @param endTime
     * @return Float
     */
    public static Float revenueByTime(Integer webshop, Timestamp startTime, Timestamp endTime){
        return countRevenue(new AnalyticsDaoJDBC().findByWebshopTime(webshop, startTime, endTime));
    }

    /**
     * Counts the revenue from list of Analityc objects
     * @param purchases
     * @return Float
     */
    private static Float countRevenue(List<Analytics> purchases){
        Double avenue = purchases.stream().map(Analytics::getAmount).mapToDouble(Float::floatValue).sum();
        return avenue.floatValue();
    }
}
