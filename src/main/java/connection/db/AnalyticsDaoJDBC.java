package connection.db;

import model.Analytics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    public List<Analytics> findByWebshop(int webshop){
        return null;
    }

    public List<Analytics> findByWebshopTime(int webshop, Timestamp start, Timestamp end){
        return null;
    }

    public List<Analytics> findByWebshopTim(int webshop, Timestamp start, Timestamp end){
        return null;
    }
//    public List<Analytics> findByTime(int webshopI) {
//        Analytics result = null;
//        try (Connection connection = JDBCConnect.getConnection();
//             Statement statement = connection.createStatement();
//             ResultSet rs = statement.executeQuery("SELECT * FROM webshop WHERE u_id ='" + id + "';");
//        ) {
//            if (rs.next()) {
//                boolean emailStatus = rs.getInt("welcomeEmail") == 1;
//                result = new User(rs.getString("u_name"), rs.getString("email"), rs.getString("password"), emailStatus);
//                result.setId(id);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public List<Analytics> findByWebshop()
}
