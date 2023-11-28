package dk.lyngby.controller;

import dk.lyngby.controller.IController;
import dk.lyngby.exception.ApiException;
import io.javalin.http.Context;

public interface ITrainLineController extends IController {
    void readByDeparture(Context ctx) throws ApiException;
    void addConductor(Context ctx) throws ApiException;
    void getTheAveragePrice(Context ctx) throws ApiException;
    void getNumberOfConductors(Context ctx) throws ApiException;
}
