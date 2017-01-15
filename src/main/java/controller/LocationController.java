package controller;

import model.LocationModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.Request;
import spark.Response;

/**
 * Created by cickib on 2017.01.09..
 */

public class LocationController {

    /**
     * Gets location data from the request
     * @param req
     * @param res
     * @return a response object
     * @throws ParseException
     */
    //   JSON keys: {city, country, countryCode}
    public static Response getData(Request req, Response res) throws ParseException {
        JSONObject jsonLocation = (JSONObject) new JSONParser().parse(req.queryParams().iterator().next());
        new LocationModel(jsonLocation.get("city").toString(),
                jsonLocation.get("country").toString(), jsonLocation.get("countryCode").toString());
        return res;
    }

}
