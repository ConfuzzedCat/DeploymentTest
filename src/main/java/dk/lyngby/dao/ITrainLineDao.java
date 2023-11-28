package dk.lyngby.dao;

import dk.lyngby.exception.ApiException;
import dk.lyngby.model.TrainConductor;
import dk.lyngby.model.TrainLine;

import java.time.LocalTime;
import java.util.List;

public interface ITrainLineDao<T> extends IDao<T, Integer>{
    List<T> trainsByDeparture(LocalTime departureTime) throws ApiException;
    TrainLine addConductor(Integer routeId, TrainConductor conductor);
    float getTheAverageTicketPrice();
    long getNumberOfConductors();
}
