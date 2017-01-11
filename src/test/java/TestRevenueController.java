import connection.db.AnalyticsDaoJDBC;
import connection.db.JDBCConnect;
import model.Analytics;
import model.LocationModel;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


import static controller.RevenueController.countRevenue;

public class TestRevenueController {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date startDate;
    Date stopDate;

    @Before
    public void setUp() throws ParseException, IOException {
        JDBCConnect.setConnection("connection.properties");
        startDate = format.parse("2017-01-10 00:00:00");
        stopDate = format.parse("2017-01-12 14:45:00");
    }

    @Test
    public void testTotalRevenue() throws SQLException {
        Float result = countRevenue(new AnalyticsDaoJDBC().findByWebshop(1));
        Float expected = (float) 20;
        assertEquals(expected, result);
    }

    @Test
    public void testRevenueByTime() {
        Float result = countRevenue(new AnalyticsDaoJDBC().findByWebshopTime(1,
                new Timestamp(startDate.getTime()), new Timestamp(stopDate.getTime())));
        Float expected = (float) 20;
        assertEquals(expected, result);
    }

    @Test
    public void testCountRevenue() {
        LocationModel location = new LocationModel("Budapest", "Hungary", "HU");
        Float amount = (float)100.5;
        Float amount2 = (float)110.5;
        Analytics model = new Analytics(1, "sessionid", new Timestamp(startDate.getTime()),
                new Timestamp(stopDate.getTime()), location, amount, "HUF");
        Analytics model2 = new Analytics(1, "sessionid", new Timestamp(startDate.getTime()),
                new Timestamp(stopDate.getTime()), location, amount2, "HUF");

        List<Analytics> listOfAnalytics = new ArrayList<>();
        listOfAnalytics.add(model);
        listOfAnalytics.add(model2);
        Float result = countRevenue(listOfAnalytics);
        Float expected = (float) 211.0;
        assertEquals(expected, result);
    }
}
