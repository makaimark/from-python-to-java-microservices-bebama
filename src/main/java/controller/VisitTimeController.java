package controller;

import connection.db.AnalyticsDaoJDBC;
import model.Analytics;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class VisitTimeController {

    public static String averageVisitTime(Integer webshop){
        return countAverage(new AnalyticsDaoJDBC().findByWebshop(webshop));
    }

    public static String averageVisitTimeByTime(Integer webshop, Timestamp startTime, Timestamp endTime){
        return countAverage(new AnalyticsDaoJDBC().findByWebshopTime(webshop, startTime, endTime));
    }

    public static String countAverage(List<Analytics> visits){
//        totalSeconds + averageSeconds to be reviewed!
        Double totalSeconds = visits.stream().map(v->v.getEndTime().getTime()-v.getEndTime().getTime()).collect(Collectors.summingDouble(d -> d));
//        Integer totalSeconds = visits.stream().map(v->v.getEndTime()-v.getEndTime()).mapToInt(Integer::intValue).sum();
        Long averageSeconds = Math.floorDiv((long) (double)totalSeconds, (long) ((Integer) visits.size()).intValue());
        Long hours = averageSeconds / 3600;
        Long minutes = (averageSeconds % 3600) / 60;
        Long seconds = averageSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
