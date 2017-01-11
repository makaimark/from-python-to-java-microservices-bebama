package controller;

import connection.db.AnalyticsDaoJDBC;
import model.Analytics;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class VisitTimeController {

    public static Map<String, String> averageVisitTime(Integer webshop) throws SQLException {
        return countAverage(new AnalyticsDaoJDBC().findByWebshop(webshop));
    }

    public static Map<String, String> averageVisitTimeByTime(Integer webshop, Timestamp startTime, Timestamp endTime) {
        return countAverage(new AnalyticsDaoJDBC().findByWebshopTime(webshop, startTime, endTime));
    }

    private static Map<String, String> countAverage(List<Analytics> visits) {
        Map<String, String> statistics = new HashMap<String, String>(){{
            put("average", "00:00:00");
            put("min", "00:00:00");
            put("max", "00:00:00");
        }};
        if (visits.size() > 0) {
            IntStream visitSeconds = visits.stream()
                    .map(Analytics::secondsSpent).mapToInt(Integer::intValue);
            statistics.put("average", intToString(visitSeconds.sum() / visits.size()));
            statistics.put("min", intToString(visitSeconds.min().getAsInt()));
            statistics.put("max", intToString(visitSeconds.max().getAsInt()));
        }
        return statistics;
    }

    private static String intToString(Integer duration) {
        Integer hours = duration / 3600;
        Integer minutes = (duration % 3600) / 60;
        Integer seconds = duration % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
