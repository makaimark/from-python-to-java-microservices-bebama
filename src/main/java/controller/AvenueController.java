package controller;

import dao.implementation.AnalyticsDaoJDBC;
import model.Analytics;

import java.util.List;

public class AvenueController {

    public static Float totalAvenue(Integer webshop){
        return countAvenue(new AnalyticsDaoJDBC().findByWebshop(webshop));
    }

    public static Float avenueByTime(Integer webshop, Integer startTime, Integer endTime){
        return countAvenue(new AnalyticsDaoJDBC().findByWebshopTime(webshop, startTime, endTime));
    }

    public static Float countAvenue(List<Analytics> purchases){
        Double avenue = purchases.stream().map(Analytics::getAmount).mapToDouble(Float::floatValue).sum();
        return avenue.floatValue();
    }
}
