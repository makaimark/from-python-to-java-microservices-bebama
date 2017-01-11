package controller;

import connection.db.LocationVisitorDaoJDBC;
import model.LocationVisitor;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class LocationVisitorController {

    public static List<String> topLocations(Integer webshop) throws SQLException {
        return LocationVisitorDaoJDBC.locationsByWebshop(webshop)
                .stream().map(LocationVisitor::toString).collect(Collectors.toList());
    }

    public static List<String> topLocationsByTime(Integer webshop, Timestamp startTime, Timestamp endTime) {
        return LocationVisitorDaoJDBC.locationsByWebshopTime(webshop, startTime, endTime)
                .stream().map(LocationVisitor::toString).collect(Collectors.toList());
    }
}
