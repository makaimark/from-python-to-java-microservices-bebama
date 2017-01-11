package connection.db;

import model.LocationVisitor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationVisitorDaoJDBC extends AnalyticsDaoJDBC{

    public static List<LocationVisitor> locationsByWebshop(int webshop) throws SQLException {
        return getLocationList("SELECT count(*) AS visitors, location FROM webshopAnalytics " +
                "WHERE webshop_id ='" + webshop + "' GROUP BY location ORDER BY visitors LIMIT 10;");
    }

    public static List<LocationVisitor> locationsByWebshopTime(int webshop, Timestamp start, Timestamp end) {
        return getLocationList("SELECT count(*) AS visitors, location FROM webshopAnalytics " +
                " WHERE webshop_id ='" + webshop +
                "' AND visit_start >='" + start +
                "' AND visit_end <='" + end + "' GROUP BY location ORDER BY visitors LIMIT 10;");
    }


    private static List<LocationVisitor> getLocationList(String query) {
        List<LocationVisitor> result = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query);
        ) {
            while (rs.next()) {
                LocationVisitor location = new LocationVisitor(stringToLocation(rs.getString("location")),
                        rs.getInt("visitors"));
                result.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
