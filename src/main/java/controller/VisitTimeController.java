package controller;

import connection.db.AnalyticsDaoJDBC;
import model.Analytics;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class VisitTimeController {

    public static String averageVisitTime(Integer webshop) throws SQLException {
        return countAverage(new AnalyticsDaoJDBC().findByWebshop(webshop));
    }

    public static String averageVisitTimeByTime(Integer webshop, Timestamp startTime, Timestamp endTime){
        return countAverage(new AnalyticsDaoJDBC().findByWebshopTime(webshop, startTime, endTime));
    }

    public static String countAverage(List<Analytics> visits){
        Integer averageSeconds = visits.stream().mapToInt(Analytics::secondsSpent).sum()/visits.size();
        Integer hours = averageSeconds / 3600;
        Integer minutes = (averageSeconds % 3600) / 60;
        Integer seconds = averageSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
