import connection.db.JDBCConnect;
import connection.db.PropertiesConfig;
import controller.APIController;
import controller.LocationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.io.IOException;
import java.net.URISyntaxException;

import static spark.Spark.*;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    private static final int PORT = 60000;


    private static APIController controller;

    public static void main(String[] args) throws IOException {

        logger.debug("Starting server...");

        // --- EXCEPTION HANDLING ---
        exception(URISyntaxException.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("URI building error, maybe wrong format? : %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("Unexpected error occurred: %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });

        // --- SERVER SETUP ---
        staticFileLocation("/public");
        port(PORT);

        // --- DB CONNECTION SETUP ---
        PropertiesConfig.config();
        JDBCConnect.setConnection("connection.properties");

        // --- EXCEPTION HANDLING ---
        exception(URISyntaxException.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("URI building error, maybe wrong format? : %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("Unexpected error occurred: %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });

        controller = new APIController();

        // --- TEMPLATE ENGINE ---
        TemplateResolver templateResolver = new TemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setResourceResolver(new ClassLoaderResourceResolver());

        // --- ROUTES ---
        get("/", controller::renderMain, new ThymeleafTemplateEngine(templateResolver));
        get("/api", controller::api);
        get("/api/visitor_count", controller::visitorCounter);
        get("/api/visit_time_count", controller::visitTimeCounter);
        get("stopTime", controller::stopSession);
        get("/api/revenue", controller::countRevenue);
        get("/startTime", controller::startSession);
        post("/get_location", LocationController::getData);

        logger.info("Server started on port " + PORT);

    }
}
