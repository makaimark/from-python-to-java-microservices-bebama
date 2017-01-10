package controller;

import dao.implementation.AnalyticsDaoJDBC;

public class VisitorController {

    public static int visitors(Integer webshop){
         return new AnalyticsDaoJDBC().findByWebshop(webshop).size();
    }

    public static int visitorsByTime(Integer webshop, Integer startTime, Integer endTime){
        return new AnalyticsDaoJDBC().findByWebshopTime(webshop, startTime, endTime).size();
    }
}
