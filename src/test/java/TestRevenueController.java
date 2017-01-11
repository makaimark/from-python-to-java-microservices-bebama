import connection.db.AnalyticsDaoJDBC;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.assertEquals;


import static controller.RevenueController.countRevenue;

public class TestRevenueController {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date startDate;
    Date stopDate;

    @Before
    public void setUp() throws ParseException {
        startDate = format.parse("2017-01-11 00:00:00");
        stopDate = format.parse("2017-01-11 14:45:00");
    }

    @Test
    public void testTotalRevenue() throws SQLException {
        Float result = countRevenue(new AnalyticsDaoJDBC().findByWebshop(1));
        Float number = (float) 1;
        assertEquals(number, result);
    }

    @Test
    public void testRevenueByTime() {
        Float result = countRevenue(new AnalyticsDaoJDBC().findByWebshopTime(1,
                new Timestamp(startDate.getTime()), new Timestamp(stopDate.getTime())));
        Float number = (float) 1;
        assertEquals(number, result);
    }

    @Test
    public void testCountRevenue() {

    }
}
