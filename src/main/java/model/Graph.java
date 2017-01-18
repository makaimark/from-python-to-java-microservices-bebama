package model;

import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

/**
 * Created by makaimark on 2017.01.11..
 */
public class Graph {
    public static final String API_URL = "http://chart.apis.google.com/chart?";

    /**
     * This function communicates with an API that generate an url with a grapg from the parameters
     * @param size The size of the graph (200x400)
     * @param name The name of the graph
     * @param values The values of the graph (eeded format: Asleep|Awake|randomthing)
     * @param partition The number and sizes of the partitions (needed format: 1,89,10 ( 100 max))
     * @param colors The colors of the partitions (needed format: 7D858F,586F8E,7D858F)
     * @return Return with the URL of the generated graph
     * @throws URISyntaxException
     */
    public String buildGraphURL(String size, String name, String values, String partition, String colors) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(API_URL);
        builder.addParameter("chs", size); // 200x400
        builder.addParameter("chdlp", "b"); // must have :D
        builder.addParameter("chtt", name);
        builder.addParameter("chdl", values); // needed format: Asleep|Awake|randomthing
        builder.addParameter("chd", "t:" + partition); // needed format: 1,89,10 ( 100 max)
        builder.addParameter("cht", "p"); // must have :D
        builder.addParameter("chco", colors); // needed format: 7D858F,586F8E,7D858F

        return String.valueOf(builder.build());
    }
}
