import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by makaimark on 2017.01.11..
 */
public class TestVisitTimeController {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date startDate;
    Date stopDate;

    @Before
    public void setUp() throws ParseException {
        startDate = format.parse("2017-01-11 00:00:00");
        stopDate = format.parse("2017-01-11 14:45:00");
    }

    @Test
    public void testAverageVisitTime() {

    }

    @Test
    public void testAverageVisitTimeByTime() {

    }

    @Test
    public void testCountAverage() {

    }
}
