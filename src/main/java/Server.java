import connection.db.JDBCConnect;
import connection.db.PropertiesConfig;
import controller.APIController;
import controller.LocationController;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.io.IOException;

import static spark.Spark.*;

public class Server {

    private static APIController controller;

    public static void main(String[] args) throws IOException {

        controller = new APIController();
        port(60000);

        staticFileLocation("/public");
        PropertiesConfig.config();
        JDBCConnect.setConnection("connection.properties");


        // --- TEMPLATE ENGINE ---
        TemplateResolver templateResolver = new TemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setResourceResolver(new ClassLoaderResourceResolver());

        get("/", controller::renderTest, new ThymeleafTemplateEngine(templateResolver));
        get("/api", controller::api);
        get("/api/visitor_count", controller::visitorCounter);
        get("/api/visit_time_count", controller::visitTimeCounter);
        get("stopTime", controller::stopSession);
        get("/api/revenue", controller::countRevenue);
        get("/startTime", controller::startSession);
        get("/get_location", LocationController::renderLocator, new ThymeleafTemplateEngine());
        post("/get_location", LocationController::getData);
    }
}
