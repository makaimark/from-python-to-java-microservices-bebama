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
     * @param webshop This is the id of the webshop
     * @return A Map that contains the number of visitors from the locations
     */
    public static Map<String, Integer> topLocations(Integer webshop) {
        return converter(new LocationVisitorDaoJDBC().locationsByWebshop(webshop));
    }

    /**
     * Founds the location with the most visitors by time and by webhop id
     * @param webshop This is the id of the webshop
     * @param startTime The start time of the statistic
     * @param endTime The end time of the statistic
     * @return A Map that contains the number of visitors from the locations by time
     */
    public static Map<String, Integer> topLocationsByTime(Integer webshop, Timestamp startTime, Timestamp endTime) {
        return converter(new LocationVisitorDaoJDBC().locationsByWebshopTime(webshop, startTime, endTime));
    }

    /**
     * converts list of location to Map
     * @param locations A List that contains the locations
     * @return A Map that contains the number of visitors from the locations
     */
    public static Map<String, Integer> converter(List<LocationVisitor> locations) {
        Map<String, Integer> locationMap = new HashMap<>();
        for (LocationVisitor location : locations) {
            locationMap.put(location.getLocation().toString(), location.getVisitors());
        }
        return locationMap;
    }
}
