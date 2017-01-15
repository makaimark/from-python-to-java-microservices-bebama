package analyticsService.dao.JDBC;

import analyticsService.SaltHasher;
import analyticsService.dao.WebshopDao;
import analyticsService.model.Analytics;
import analyticsService.model.LocationModel;
import analyticsService.model.Webshop;

import java.sql.*;

public class WebshopDaoJDBC extends AbstractDaoJDBC implements WebshopDao {

    @Override
    public void add(Webshop webshop) throws Exception {
        try (Connection connection = AbstractDaoJDBC.getConnection()) {
            PreparedStatement query;
            query = connection.prepareStatement("INSERT INTO webshop (ws_name, apiKey) VALUES (?, ?);");
            query.setString(1, webshop.getName());
            query.setString(2, SaltHasher.hashString(webshop.getName()));
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Webshop findByApyKey(String apiKey) {
       return getWebshop("SELECT * FROM webshop WHERE apikey ='" + apiKey + "';");
    }

    private Webshop getWebshop(String query){
        Webshop result = null;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query);
        ) {
            while (rs.next()) {
                result = new Webshop(rs.getString("ws_name"));
                result.setId(rs.getInt("ws_id"));
                result.setAnalyticsList(new AnalyticsDaoJDBC().findByWebshop(rs.getString("apikey")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static void main(String[] args) throws Exception {
//        AbstractDaoJDBC.setConnection("connection.properties");
//        Webshop webshop = new Webshop("codecoolwebshop");
//        new WebshopDaoJDBC().add(webshop);
//        String apiKey = SaltHasher.hashString(webshop.getName());
//        webshop = new WebshopDaoJDBC().findByApyKey(apiKey);
//        System.out.println(new WebshopDaoJDBC().findByApyKey(apiKey));
//        LocationModel location = new LocationModel("Budapes", "Hungary", "HU");
//        Analytics analytics = new Analytics(
//                webshop, "efwefweefw",
//                new Timestamp(System.currentTimeMillis()),
//                new Timestamp(System.currentTimeMillis()),
//                location,
//                2345.3525f,
//                "HUF");
//        new AnalyticsDaoJDBC().add(analytics);
//        System.out.println(new AnalyticsDaoJDBC().findByWebshop(apiKey).get(0));
//        System.out.println(new LocationVisitorDaoJDBC().locationsByWebshop(apiKey));
//    }
}
