package dk.lyngby.routes;

import dk.lyngby.controller.ITrainLineController;
import dk.lyngby.controller.impl.TrainLineController;
import dk.lyngby.controller.impl.TrainLineControllerMock;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

public class TrainLineRoutes {
    private final ITrainLineController controller = new TrainLineController();

    protected EndpointGroup getRoutes() {

        return () -> {
            path("/lines", () -> {
                post("/", controller::create);
                get("/avgprice", controller::getTheAveragePrice);
                get("/amountconductor", controller::getNumberOfConductors);
                get("/", controller::readAll);
                get("/departure/{hour}/{min}", controller::readByDeparture);
                get("/{id}", controller::read);
                put("/{id}/conductor", controller::addConductor);
                put("/{id}", controller::update);
            });
        };
    }
}
