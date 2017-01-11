import model.Analytics;
import model.LocationModel;
import org.junit.Before;
import org.junit.Test;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestAnalytics {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Analytics model;

    @Before
    public void setup() throws ParseException {
        Date startDate = format.parse("2017-01-01 00:00:00");
        Date stopDate = format.parse("2017-01-02 00:00:00");
        LocationModel location = new LocationModel("Budapest", "Hungary", "HU");
        Float amount = (float)100.5;
        model = new Analytics(1, "sessionid", new Timestamp(startDate.getTime()),
                new Timestamp(stopDate.getTime()), location, amount, "HUF");
    }

    @Test
    public void testContructor() throws ParseException {
        Float amount = (float)100.5;
        assertEquals(model.getAmount(), amount);
    }

    @Test
    public void getSecondsSpent() {
        Integer seconds = model.secondsSpent();
        Integer solution = 86400;
        assertEquals(solution, seconds);
    }

    @Test
    public void testSeondsSpentWithFalseTest() {
        Integer seconds = model.secondsSpent();
        Integer solution = 86399;
        assertNotEquals(seconds, solution);
    }
}