package analyticsService.controller;

import analyticsService.dao.JDBC.AnalyticsDaoJDBC;
import analyticsService.model.Analytics;

import java.sql.Timestamp;
import java.util.List;

public class RevenueController {

    public static Float totalRevenue(String apiKey) {
        return countRevenue(new AnalyticsDaoJDBC().findByWebshop(apiKey));
    }

    public static Float revenueByTime(String apiKey, Timestamp startTime, Timestamp endTime){
        return countRevenue(new AnalyticsDaoJDBC().findByWebshopTime(apiKey, startTime, endTime));
    }

    private static Float countRevenue(List<Analytics> purchases){
        Double avenue = purchases.stream().map(Analytics::getAmount).mapToDouble(Float::floatValue).sum();
        return avenue.floatValue();
    }
}
