package dk.lyngby.controller.impl;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.controller.ITrainLineController;
import dk.lyngby.dao.ITrainLineDao;
import dk.lyngby.dao.impl.TrainLineDAO;
import dk.lyngby.dao.impl.TrainLineDAOMock;
import dk.lyngby.dto.TrainConductorDTO;
import dk.lyngby.dto.TrainLineDTO;
import dk.lyngby.exception.ApiException;
import dk.lyngby.model.TrainConductor;
import dk.lyngby.model.TrainLine;
import io.javalin.http.Context;

import java.time.LocalTime;


public class TrainLineController implements ITrainLineController {
    private ITrainLineDao<TrainLine> dao;
    public TrainLineController(){
        var emf = HibernateConfig.getEntityManagerFactory();
        dao = TrainLineDAO.getInstance(emf);
    }

    @Override
    public void read(Context ctx) throws ApiException {
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        TrainLine created = dao.read(id);
        ctx.json(new TrainLineDTO(created));
    }

    @Override
    public void readAll(Context ctx) throws ApiException {
        ctx.json(TrainLineDTO.toTrainLineDTOList(dao.readAll()));
    }

    @Override
    public void readByDeparture(Context ctx) throws ApiException {
        int hour = ctx.pathParamAsClass("hour", Integer.class).get();
        int min = ctx.pathParamAsClass("min", Integer.class).get();
        LocalTime departureTime = LocalTime.of(hour, min);
        ctx.json(TrainLineDTO.toTrainLineDTOList(
                    dao.trainsByDeparture(departureTime)));
    }

    @Override
    public void addConductor(Context ctx) throws ApiException {
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        TrainConductorDTO request = ctx.bodyAsClass(TrainConductorDTO.class);
        TrainLine line = dao.addConductor(id, new TrainConductor(request));
        ctx.status(204).json(new TrainLineDTO(line));
    }

    @Override
    public void getTheAveragePrice(Context ctx) throws ApiException {
        ctx.json(dao.getTheAverageTicketPrice());
    }

    @Override
    public void getNumberOfConductors(Context ctx) throws ApiException {
        ctx.json(dao.getNumberOfConductors());
    }

    @Override
    public void create(Context ctx) throws ApiException {
        TrainLineDTO request = ctx.bodyAsClass(TrainLineDTO.class);
        TrainLine database = dao.create(new TrainLine(request));
        ctx.status(201)
                .json(new TrainLineDTO(database));
    }

    @Override
    public void update(Context ctx) throws ApiException {
        TrainLineDTO request = ctx.bodyAsClass(TrainLineDTO.class);
        int id = ctx.pathParamAsClass("id", Integer.class).get();

        TrainLine update = dao.update(id, new TrainLine(request));
        ctx.json(new TrainLineDTO(update));
    }

    @Override
    public void delete(Context ctx) throws ApiException {
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        dao.delete(id);
        ctx.status(201);
    }
}
