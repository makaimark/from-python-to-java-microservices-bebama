package controller;

import dao.JDBC.AnalyticsDaoJDBC;
import model.Analytics;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class VisitTimeController {

    /**
     * Counts the average visit time by webshop id
     * @param webshop The id of the webshop
     * @return A Map that contains the average visit times by webshopid
     */
    public static Map<String, String> averageVisitTime(Integer webshop) {
        return countAverage(new AnalyticsDaoJDBC().findByWebshop(webshop));
    }

    /**
     * Counts the average visit time by webshop id and by time
     * @param webshop The id of the webshop
     * @param startTime The start time of the statistic
     * @param endTime The end time of the statistic
     * @return Map
     */
    public static Map<String, String> averageVisitTimeByTime(Integer webshop, Timestamp startTime, Timestamp endTime) {
        return countAverage(new AnalyticsDaoJDBC().findByWebshopTime(webshop, startTime, endTime));
    }

    /**
     * Counts the average visit time from list of Analitycs
     * @param visits A list of visits
     * @return A Map that contains the number of visitors
     */
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

    /**
     * Converts IntStream from List of Analitycs
     * @param visits
     * @return IntStream
     */
    private static IntStream getStream(List<Analytics> visits){
        return visits.stream().map(Analytics::secondsSpent).mapToInt(Integer::intValue);
    }

    /**
     * Converts Integer to string
     * @param duration An integer that we would like to convert to string
     * @return String
     */
    private static String intToString(Integer duration) {
        Integer hours = duration / 3600;
        Integer minutes = (duration % 3600) / 60;
        Integer seconds = duration % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
