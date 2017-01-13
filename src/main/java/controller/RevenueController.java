package controller;

import dao.JDBC.AnalyticsDaoJDBC;
import model.Analytics;

import java.sql.Timestamp;
import java.util.List;

public class RevenueController {

    public static Float totalRevenue(Integer webshop) {
        return countRevenue(new AnalyticsDaoJDBC().findByWebshop(webshop));
    }

    public static Float revenueByTime(Integer webshop, Timestamp startTime, Timestamp endTime){
        return countRevenue(new AnalyticsDaoJDBC().findByWebshopTime(webshop, startTime, endTime));
    }

    private static Float countRevenue(List<Analytics> purchases){
        Double avenue = purchases.stream().map(Analytics::getAmount).mapToDouble(Float::floatValue).sum();
        return avenue.floatValue();
    }
}
