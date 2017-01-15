package analyticsService.controller;

import analyticsService.dao.JDBC.LocationVisitorDaoJDBC;
import analyticsService.model.LocationVisitor;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationVisitorController {

    public static Map<String, Integer> topLocations(String apiKey) {
        return converter(new LocationVisitorDaoJDBC().locationsByWebshop(apiKey));
    }

    public static Map<String, Integer> topLocationsByTime(String apiKey, Timestamp startTime, Timestamp endTime) {
        return converter(new LocationVisitorDaoJDBC().locationsByWebshopTime(apiKey, startTime, endTime));
    }

    public static Map<String, Integer> converter(List<LocationVisitor> locations) {
        Map<String, Integer> locationMap = new HashMap<>();
        for (LocationVisitor location : locations) {
            locationMap.put(location.getLocation().toString(), location.getVisitors());
        }
        return locationMap;
    }
}
