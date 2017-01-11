package controller;

import connection.db.AnalyticsDaoJDBC;
import model.Analytics;

import java.sql.Timestamp;
import java.util.List;

public class AvenueController {

    public static Float totalAvenue(Integer webshop){
        return countAvenue(new AnalyticsDaoJDBC().findByWebshop(webshop));
    }

    public static Float avenueByTime(Integer webshop, Timestamp startTime, Timestamp endTime){
        return countAvenue(new AnalyticsDaoJDBC().findByWebshopTime(webshop, startTime, endTime));
    }

    public static Float countAvenue(List<Analytics> purchases){
        Double avenue = purchases.stream().map(Analytics::getAmount).mapToDouble(Float::floatValue).sum();
        return avenue.floatValue();
    }
}
