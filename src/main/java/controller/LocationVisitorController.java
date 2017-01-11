package controller;

import connection.db.LocationVisitorDaoJDBC;
import model.LocationVisitor;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationVisitorController {

    public static Map<String, Integer> topLocations(Integer webshop) throws SQLException {
        return converter(LocationVisitorDaoJDBC.locationsByWebshop(webshop));
    }

    public static Map<String, Integer> topLocationsByTime(Integer webshop, Timestamp startTime, Timestamp endTime) {
        return converter(LocationVisitorDaoJDBC.locationsByWebshopTime(webshop, startTime, endTime));
    }

    public static Map<String, Integer> converter(List<LocationVisitor> locations) {
        Map<String, Integer> locationMap = new HashMap<>();
        for (LocationVisitor location : locations) {
            locationMap.put(location.getLocation().toString(), location.getVisitors());
        }
        return locationMap;
    }
}
