package controller;

import dao.JDBC.LocationVisitorDaoJDBC;
import model.LocationVisitor;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationVisitorController {

    /**
     * Founds the location with the most visitors by webhop id
     * @param webshop
     * @return Map
     */
    public static Map<String, Integer> topLocations(Integer webshop) {
        return converter(new LocationVisitorDaoJDBC().locationsByWebshop(webshop));
    }

    /**
     * Founds the location with the most visitors by time and by webhop id
     * @param webshop
     * @param startTime
     * @param endTime
     * @return Map
     */
    public static Map<String, Integer> topLocationsByTime(Integer webshop, Timestamp startTime, Timestamp endTime) {
        return converter(new LocationVisitorDaoJDBC().locationsByWebshopTime(webshop, startTime, endTime));
    }

    /**
     * converts list of location to Map
     * @param locations
     * @return Map
     */
    public static Map<String, Integer> converter(List<LocationVisitor> locations) {
        Map<String, Integer> locationMap = new HashMap<>();
        for (LocationVisitor location : locations) {
            locationMap.put(location.getLocation().toString(), location.getVisitors());
        }
        return locationMap;
    }
}
