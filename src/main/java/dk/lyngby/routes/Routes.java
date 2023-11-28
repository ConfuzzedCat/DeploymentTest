package dk.lyngby.routes;

import dk.lyngby.exception.ApiException;
import dk.lyngby.exception.ExceptionHandler;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {

    private final ExceptionHandler exceptionController = new ExceptionHandler();
    private int count = 0;
    private final TrainLineRoutes trainLineRoutes = new TrainLineRoutes();

    private final Logger LOGGER = LoggerFactory.getLogger(Routes.class);

    private void requestInfoHandler(Context ctx) {
        String requestInfo = ctx.req().getMethod() + " " + ctx.req().getRequestURI();
        ctx.attribute("requestInfo", requestInfo);
    }

    public EndpointGroup getRoutes(Javalin app) {
        return () -> {
            app.before(this::requestInfoHandler);
            app.before(ctx ->{
                ctx.header("Access-Control-Allow-Origin", "*");
                ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
                ctx.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
                ctx.header("Access-Control-Allow-Credentials", "true");

                if (ctx.method().equals("OPTIONS"))
                    ctx.status(200).result("OK");
            });

            app.routes(() -> {
                // path("/", hotelRoute.getRoutes());
                path("/", trainLineRoutes.getRoutes());

            });

            app.after(ctx -> LOGGER.info(" Request {} - {} was handled with status code {}", count++, ctx.attribute("requestInfo"), ctx.status()));

            app.exception(ApiException.class, exceptionController::apiExceptionHandler);
            app.exception(ValidationException.class, exceptionController::validationExceptionHandler);
            app.exception(Exception.class, exceptionController::exceptionHandler);
        };
    }
}
