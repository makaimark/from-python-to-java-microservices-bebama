package analyticsService.controller;

import analyticsService.dao.JDBC.AnalyticsDaoJDBC;
import analyticsService.model.Analytics;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class VisitTimeController {

    public static Map<String, String> averageVisitTime(String apiKey) {
        return countAverage(new AnalyticsDaoJDBC().findByWebshop(apiKey));
    }

    public static Map<String, String> averageVisitTimeByTime(String apiKey, Timestamp startTime, Timestamp endTime) {
        return countAverage(new AnalyticsDaoJDBC().findByWebshopTime(apiKey, startTime, endTime));
    }

    private static Map<String, String> countAverage(List<Analytics> visits) {
        Map<String, String> statistics = new HashMap<String, String>(){{
            put("average", "00:00:00");
            put("min", "00:00:00");
            put("max", "00:00:00");
        }};
        if (visits.size() > 0) {
            statistics.put("average", intToString(getStream(visits).sum() / visits.size()));
            statistics.put("min", intToString(getStream(visits).min().getAsInt()));
            statistics.put("max", intToString(getStream(visits).max().getAsInt()));
        }
        return statistics;
    }

    private static IntStream getStream(List<Analytics> visits){
        return visits.stream().map(Analytics::secondsSpent).mapToInt(Integer::intValue);
    }

    private static String intToString(Integer duration) {
        Integer hours = duration / 3600;
        Integer minutes = (duration % 3600) / 60;
        Integer seconds = duration % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
