package dk.lyngby.controller.impl;

import dk.lyngby.controller.ITrainLineController;
import dk.lyngby.dao.ITrainLineDao;
import dk.lyngby.dao.impl.TrainLineDAOMock;
import dk.lyngby.dto.TrainLineDTO;
import dk.lyngby.exception.ApiException;
import io.javalin.http.Context;

import java.time.LocalTime;

public class TrainLineControllerMock implements ITrainLineController {

    private ITrainLineDao<TrainLineDTO> dao;
    public TrainLineControllerMock(){
        dao = TrainLineDAOMock.getInstance(null);
    }

    @Override
    public void read(Context ctx) throws ApiException {
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        TrainLineDTO created = dao.read(id);
        ctx.json(created);
    }

    @Override
    public void readAll(Context ctx) throws ApiException {
        ctx.json(dao.readAll());
    }

    @Override
    public void create(Context ctx) throws ApiException {
        TrainLineDTO request = ctx.bodyAsClass(TrainLineDTO.class);
        ctx.status(201)
                .json(dao.create(request));
    }

    @Override
    public void update(Context ctx) throws ApiException {
        TrainLineDTO request = ctx.bodyAsClass(TrainLineDTO.class);
        int id = ctx.pathParamAsClass("id", Integer.class).get();

        TrainLineDTO update = dao.update(id, request);
        ctx.json(update);
    }

    @Override
    public void delete(Context ctx) throws ApiException {
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        dao.delete(id);
        ctx.status(201);
    }

    @Override
    public void readByDeparture(Context ctx) throws ApiException {
        int hour = ctx.pathParamAsClass("hour", Integer.class).get();
        int min = ctx.pathParamAsClass("min", Integer.class).get();
        LocalTime departureTime = LocalTime.of(hour, min);
        ctx.json(dao.trainsByDeparture(departureTime));
    }

    @Override
    public void addConductor(Context ctx) throws ApiException {

    }

    @Override
    public void getTheAveragePrice(Context ctx) throws ApiException {

    }

    @Override
    public void getNumberOfConductors(Context ctx) throws ApiException {

    }
}
