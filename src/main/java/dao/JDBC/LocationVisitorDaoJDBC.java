package dao.JDBC;

import dao.LocationVisitorDao;
import model.LocationVisitor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationVisitorDaoJDBC extends AnalyticsDaoJDBC implements LocationVisitorDao{

    /**
     * Finds the visitors location by webshop id
     * @param webshop
     * @return List of LocationVisitor models
     */
    public List<LocationVisitor> locationsByWebshop(int webshop) {
        return getLocationList("SELECT count(*) AS totalVisitors, location FROM webshopAnalytics " +
                "WHERE webshop_id ='" + webshop + "' GROUP BY location ORDER BY totalVisitors LIMIT 10;");
    }

    /**
     * Finds the visitors location by webshop id and by time
     * @param webshop
     * @param start
     * @param end
     * @return List of LocationVisitor models
     */
    public List<LocationVisitor> locationsByWebshopTime(int webshop, Timestamp start, Timestamp end) {
        return getLocationList("SELECT count(*) AS totalVisitors, location FROM webshopAnalytics " +
                " WHERE webshop_id ='" + webshop +
                "' AND visit_start >='" + start +
                "' AND visit_end <='" + end + "' GROUP BY location ORDER BY totalVisitors LIMIT 10;");
    }

    /**
     * Collects the locations from the JDBC by he query
     * @param query
     * @return List of LocationVisitor models
     */
    private List<LocationVisitor> getLocationList(String query) {
        List<LocationVisitor> result = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query);
        ) {
            while (rs.next()) {
                LocationVisitor location = new LocationVisitor(stringToLocation(rs.getString("location")),
                        rs.getInt("totalVisitors"));
                result.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
