package dk.lyngby.exception;

import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class ExceptionHandler
{
    public void validationExceptionHandler(ValidationException e, Context ctx){
        ctx.status(400);
        ctx.json(new Message(400,"There was an error validating path parameter.", LocalDateTime.now().toString(), "TrainConnect"));
    }
    public void apiExceptionHandler(ApiException e, Context ctx)
    {
        ctx.status(e.getStatusCode());
        ctx.json(new Message(e.getStatusCode(), e.getMessage(), LocalDateTime.now().toString(), "TrainConnect"));
    }

    public void exceptionHandler(Exception e, Context ctx)
    {
        ctx.status(500);
        ctx.json(new Message(500, e.getMessage(), LocalDateTime.now().toString(), "TrainConnect"));
    }
}
