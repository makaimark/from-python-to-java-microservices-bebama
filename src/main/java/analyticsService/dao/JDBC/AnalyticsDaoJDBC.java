package analyticsService.dao.JDBC;

import analyticsService.dao.AnalyticsDao;
import analyticsService.dao.WebshopDao;
import analyticsService.model.Analytics;
import analyticsService.model.Webshop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnalyticsDaoJDBC extends AbstractDaoJDBC implements AnalyticsDao {

    public void add(Analytics model) {
        try (Connection connection = AbstractDaoJDBC.getConnection()) {
            PreparedStatement query;
            if (findSessionId(model.getSessionId()).size() > 0) {
                query = connection.prepareStatement("UPDATE webshopAnalytics SET visit_end = ?, amount = ?, currency  = ? WHERE session_id = ?");
                query.setTimestamp(1, model.getEndTime());
                query.setFloat(2, model.getAmount());
                query.setString(3, String.valueOf(model.getCurrency()));
                query.setString(4, model.getSessionId());
            } else {
                query = connection.prepareStatement("INSERT INTO webshopAnalytics (webshop_id," +
                        " session_id, visit_start, visit_end, location, amount, currency) VALUES (?, ?, ?, ?, ?, ?, ?);");
                query.setInt(1, model.getWebshop().getId());
                query.setString(2, model.getSessionId());
                query.setTimestamp(3, model.getStartTime());
                query.setTimestamp(4, model.getEndTime());
                query.setString(5, model.getLocation().toString());
                query.setFloat(6, model.getAmount());
                query.setString(7, String.valueOf(model.getCurrency()));
            }
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Analytics> findByWebshop(String apiKey) {
        return getAnalyticsList("SELECT * FROM webshopAnalytics " +
                "LEFT JOIN webshop ON webshop_id = ws_id " +
                "WHERE webshop_id = (SELECT ws_id FROM webshop WHERE apikey ='" + apiKey + "');");
    }

    public List<Analytics> findByWebshopTime(String apiKey, Timestamp start, Timestamp end) {
        return getAnalyticsList("SELECT * FROM webshopAnalytics " +
                "LEFT JOIN webshop ON webshop_id = ws_id " +
                "WHERE webshop_id = (SELECT ws_id FROM webshop WHERE apikey ='" + apiKey + "') " +
                "AND visit_start >='" + start + "' " +
                "AND visit_end <='" + end + "';");
    }


    private List<Analytics> getAnalyticsList(String query) {
        List<Analytics> result = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query);
        ) {
            while (rs.next()) {
                Webshop webshop = new Webshop(rs.getString("ws_name"));
                webshop.setId(rs.getInt("ws_id"));
                Analytics analytics = new Analytics(
                        webshop,
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

    public List<Analytics> findSessionId(String sessionId) {
        return getAnalyticsList("SELECT * FROM webshopAnalytics WHERE session_id ='" + sessionId + "';");
    }

}
