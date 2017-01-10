import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cickib on 2017.01.09..
 */

public class Location {

    public static ModelAndView renderLocator(Request req, Response res) {

        Map params = new HashMap<>();
        return new ModelAndView(params, "location");

    }

    //   JSON keys: {city, country, countryCode}
    public static Response getData(Request req, Response res) throws ParseException {
        JSONObject jsonLocation = (JSONObject) new JSONParser().parse(req.queryParams().iterator().next());
        return res;
    }

}
