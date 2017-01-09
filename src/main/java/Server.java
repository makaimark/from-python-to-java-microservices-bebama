import controller.APIController;

import static spark.Spark.*;

public class Server {

    private static APIController controller;

    public static void main(String[] args) {

        controller = new APIController();
        port(60000);

        get("/api", controller::api);
        get("/api/visitor_count", controller::visitorCounter);
        get("/api/visit_time", controller::countVisitTime);
        get("/api/revenue", controller::countRevenue);
    }
}
