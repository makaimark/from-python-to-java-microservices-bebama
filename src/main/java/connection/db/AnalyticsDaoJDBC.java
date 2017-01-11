package connection.db;

import model.Analytics;
import model.LocationModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static connection.db.JDBCConnect.getConnection;

/**
 * Created by makaimark on 2017.01.10..
 */
public class AnalyticsDaoJDBC {

    public static void add(Analytics model) throws Exception {
        try (Connection connection = JDBCConnect.getConnection()) {
            PreparedStatement query = connection.prepareStatement("INSERT INTO webshopAnalytics (webshop_id," +
                    " session_id, visit_start, visit_end, location, amount, currency) VALUES (?, ?, ?, ?, ?, ?, ?);");
            query.setInt(1, model.getWebshopId());
            query.setString(2, model.getSessionId());
            query.setTimestamp(3, model.getStartTime());
            query.setTimestamp(4, model.getEndTime());
            query.setString(5, model.getLocation().toString());
            query.setFloat(6, model.getAmount());
            query.setString(7, String.valueOf(model.getCurrency()));
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Analytics> findByWebshop(int webshop) throws SQLException {
        return getAnalyticsList("SELECT * FROM webshopAnalytics WHERE webshop_id ='" + webshop + "';");

    }

    public static List<Analytics> findByWebshopTime(int webshop, Timestamp start, Timestamp end) {
        return getAnalyticsList("SELECT * FROM webshopAnalytics" +
                " WHERE webshop_id ='" + webshop +
                "' AND visit_start >='" + start +
                "' AND visit_end <='" + end + "';");
    }


    private static List<Analytics> getAnalyticsList(String query) {
        List<Analytics> result = new ArrayList<>();
        System.out.println(query);
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query);
        ) {
            if (rs.next()) {
                Analytics analytics = new Analytics(rs.getInt("webshop_id"),
                        rs.getString("session_id"),
                        rs.getTimestamp("visit_start"),
                        rs.getTimestamp("visit_end"),
                        stringToLocation(rs.getString("location")),
                        rs.getFloat("amount"),
                        rs.getString("currency"));
                analytics.setId(rs.getInt("an_id"));
                result.add(analytics);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static LocationModel stringToLocation(String location) {
        String[] details = location.split(",");
        return new LocationModel(details[0],
                details[1].substring(1),
                details[2].substring(1));
    }
}
