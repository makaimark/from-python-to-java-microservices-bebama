import connection.db.AnalyticsDaoJDBC;
import connection.db.JDBCConnect;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by makaimark on 2017.01.11..
 */
public class TestVisitorController {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date startDate;
    Date stopDate;

    @Before
    public void setUp() throws ParseException, IOException {
        JDBCConnect.setConnection("connection.properties");
        startDate = format.parse("2017-01-11 00:00:00");
        stopDate = format.parse("2017-01-11 16:45:00");
    }

    @Test
    public void testVisitors() throws SQLException {
        Integer visitors = new AnalyticsDaoJDBC().findByWebshop(1).size();
        Integer expected = 2;
        assertEquals(expected, visitors);
    }

    @Test
    public void testVisitorsByTime() {
        Integer result = new AnalyticsDaoJDBC().findByWebshopTime(1, new Timestamp(startDate.getTime()),
                new Timestamp(stopDate.getTime())).size();
        Integer expected = 2;
        assertEquals(expected, result);
    }
}
