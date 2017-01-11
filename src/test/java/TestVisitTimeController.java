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

import static controller.VisitTimeController.countAverage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by makaimark on 2017.01.11..
 */
public class TestVisitTimeController {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date startDate;
    Date stopDate;

    @Before
    public void setUp() throws ParseException, IOException {
        JDBCConnect.setConnection("connection.properties");
        startDate = format.parse("2017-01-11 00:00:00");
        stopDate = format.parse("2017-01-11 15:45:00");
    }

    @Test
    public void testAverageVisitTime() throws SQLException {
        String result = countAverage(new AnalyticsDaoJDBC().findByWebshop(1));
        assertFalse(result.equals("0:0:0"));
    }

    @Test
    public void testAverageVisitTimeByTime() {
        String result = countAverage(new AnalyticsDaoJDBC().findByWebshopTime(1, new Timestamp(startDate.getTime()),
                new Timestamp(stopDate.getTime())));
        assertFalse(result.equals("0:0:0"));
    }

    @Test
    public void testCountAverage() {
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

        String result = countAverage(listOfAnalytics);
        String expected = "15:45:00";
        assertTrue(expected.equals(result));
    }
}
