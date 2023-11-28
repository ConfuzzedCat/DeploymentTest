package dk.lyngby.dao.impl;

import dk.lyngby.dao.ITrainLineDao;
import dk.lyngby.dto.TrainLineDTO;
import dk.lyngby.enums.Station;
import dk.lyngby.exception.ApiException;
import dk.lyngby.model.TrainConductor;
import dk.lyngby.model.TrainLine;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TrainLineDAOMock implements ITrainLineDao<TrainLineDTO> {

    private static TrainLineDAOMock instance;

    public static TrainLineDAOMock getInstance(EntityManagerFactory _emf){
        if(instance == null){
            instance = new TrainLineDAOMock();
        }
        return instance;
    }

    private static List<TrainLineDTO> mockDB;

    private TrainLineDAOMock(){
        mockDB = populateMockDB();
    }

    private List<TrainLineDTO> populateMockDB() {
        List<TrainLineDTO> db = new ArrayList<>();
        /*
        db.add(new TrainLineDTO(1, "PA-KI", Station.Paddington, Station.KingsCross, LocalTime.of(8,0), LocalTime.of(8,30), 25f));
        db.add(new TrainLineDTO(2, "KI-PA", Station.KingsCross, Station.Paddington, LocalTime.of(8,0), LocalTime.of(10,00), 25f));
        db.add(new TrainLineDTO(3, "VI-BR", Station.Victoria, Station.Bridge, LocalTime.of(9,0), LocalTime.of(11,30), 10.5f));
        db.add(new TrainLineDTO(4, "BR-VI", Station.Bridge, Station.Victoria, LocalTime.of(11,30), LocalTime.of(12,30), 10.5f));
        db.add(new TrainLineDTO(5, "EU-LI", Station.Euston, Station.LiverpoolStreet, LocalTime.of(11,45), LocalTime.of(15,15), 35.75f));
        db.add(new TrainLineDTO(6, "BL-LI", Station.Blackfriars, Station.LiverpoolStreet, LocalTime.of(13,15), LocalTime.of(14,0), 15.25f));
        db.add(new TrainLineDTO(7, "LI-BL", Station.LiverpoolStreet, Station.Blackfriars, LocalTime.of(13,15), LocalTime.of(13,45), 5.25f));
        db.add(new TrainLineDTO(8, "BR-PA", Station.Bridge, Station.Paddington, LocalTime.of(15,30), LocalTime.of(17,0), 20.75f));
        */
        return db;
    }

    @Override
    public TrainLineDTO read(Integer id) throws ApiException {
        for (TrainLineDTO trainLine:mockDB) {
            if(trainLine.getId() == id){
                return trainLine;
            }
        }
        throw new ApiException(404, "Train line with id: " + id + " couldn't be found.");
    }

    @Override
    public List<TrainLineDTO> readAll() throws ApiException {
        return mockDB;
    }

    @Override
    public TrainLineDTO create(TrainLineDTO trainLineDTO) throws ApiException {
        trainLineDTO.setId(mockDB.size()+1);
        mockDB.add(trainLineDTO);
        return trainLineDTO;
    }

    @Override
    public TrainLineDTO update(Integer id, TrainLineDTO trainLineDTO) throws ApiException {
        for (int i = 0; i < mockDB.size(); i++) {
            TrainLineDTO trainLine = mockDB.get(i);
            if (trainLine.getId() == id) {
                trainLineDTO.setId(id);
                mockDB.set(i, trainLineDTO);
                return trainLineDTO;
            }
        }
        throw new ApiException(404, "Train line with id: " + id + " couldn't be found.");
    }

    @Override
    public void delete(Integer id) throws ApiException {
        for (int i = 0; i < mockDB.size(); i++) {
            TrainLineDTO trainLine = mockDB.get(i);
            if (trainLine.getId() == id) {
                mockDB.remove(i);
                return;
            }
        }
        throw new ApiException(404, "Train line with id: " + id + " couldn't be found.");
    }

    @Override
    public List<TrainLineDTO> trainsByDeparture(LocalTime departureTime) throws ApiException {
        List<TrainLineDTO> trainLineDTOS = new ArrayList<>();
        for (TrainLineDTO trainLine :
                mockDB) {
            if (trainLine.getDepartureTime().equals(departureTime)){
                trainLineDTOS.add(trainLine);
            }
        }
        return trainLineDTOS;
    }

    @Override
    public TrainLine addConductor(Integer routeId, TrainConductor conductor) {
        return null;
    }

    @Override
    public float getTheAverageTicketPrice() {
        return 0;
    }

    @Override
    public long getNumberOfConductors() {
        return 0;
    }
}
