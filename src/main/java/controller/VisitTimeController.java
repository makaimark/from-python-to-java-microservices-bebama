package controller;

import dao.implementation.AnalyticsDaoJDBC;
import model.Analytics;

import java.util.List;

public class VisitTimeController {

    public static String averageVisitTime(Integer webshop){
        return countAverage(new AnalyticsDaoJDBC().findByWebshop(webshop));
    }

    public static String averageVisitTimeByTime(Integer webshop, Integer startTime, Integer endTime){
        return countAverage(new AnalyticsDaoJDBC().findByWebshopTime(webshop, startTime, endTime));
    }

    public static String countAverage(List<Analytics> visits){
        Integer totalSeconds = visits.stream().map(v->v.getEndTime()-v.getEndTime()).mapToInt(Integer::intValue).sum();
        Integer averageSeconds = Math.floorDiv(totalSeconds, visits.size());
        Integer hours = averageSeconds / 3600;
        Integer minutes = (averageSeconds % 3600) / 60;
        Integer seconds = averageSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
