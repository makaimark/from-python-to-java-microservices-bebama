import controller.APIController;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;

public class Server {

    private static APIController controller;

    public static void main(String[] args) {

        controller = new APIController();
        port(60000);

        staticFileLocation("/public");


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
        get("stopTime", controller::stopSession);
        get("/api/revenue", controller::countRevenue);
        get("/startTime", controller::startSession);
    }
}
