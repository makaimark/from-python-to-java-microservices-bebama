import connection.db.AnalyticsDaoJDBC;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
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
    public void setUp() throws ParseException {
        startDate = format.parse("2017-01-11 00:00:00");
        stopDate = format.parse("2017-01-11 14:45:00");
    }

    @Test
    public void testVisitors() throws SQLException {
        Integer visitors = new AnalyticsDaoJDBC().findByWebshop(1).size();
        Integer number = 2;
        assertEquals(number, visitors);
    }

    @Test
    public void testVisitorsByTime() {

    }
}
