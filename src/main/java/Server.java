import controller.APIController;

import static spark.Spark.*;

public class Server {

    public static void main(String[] args) {

        port(60000);

        get("/api", APIController::api);
        get("/api/visitor_count", APIController::visitorCounter);
        get("/api/visit_time", APIController::countVisitTime);
        get("/api/revenue", APIController::countRevenue);
    }
}
