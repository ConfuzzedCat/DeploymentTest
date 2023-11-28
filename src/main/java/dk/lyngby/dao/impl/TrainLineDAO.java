package dk.lyngby.dao.impl;

import dk.lyngby.dao.ITrainLineDao;
import dk.lyngby.exception.ApiException;
import dk.lyngby.model.TrainConductor;
import dk.lyngby.model.TrainLine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalTime;
import java.util.List;

public class TrainLineDAO implements ITrainLineDao<TrainLine> {
    private static EntityManagerFactory emf;
    private static TrainLineDAO instance;
    public static TrainLineDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new TrainLineDAO();
        }
        return instance;
    }
    private TrainLineDAO() {}

    @Override
    public TrainLine read(Integer id) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            TrainLine trainLine = em.find(TrainLine.class, id);
            if(trainLine == null){
                throw new ApiException(404, "Train line with id: " + id + " couldn't be found.");
            }
            return trainLine;
        }
    }

    @Override
    public List<TrainLine> readAll() throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            var query = em.createQuery("SELECT tl FROM TrainLine tl", TrainLine.class);
            return query.getResultList();
        }
    }

    @Override
    public TrainLine create(TrainLine trainLine) throws ApiException {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(trainLine);
            em.getTransaction().commit();
            return trainLine;
        }
    }

    @Override
    public TrainLine update(Integer id, TrainLine trainLine) throws ApiException {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            TrainLine old = em.find(TrainLine.class, id);
            if(old == null)
            {
                throw new ApiException(404, "Train line with id: " + id + " couldn't be found.");
            }
            old.setDepartureTime(trainLine.getDepartureTime());
            old.setArrivalTime(trainLine.getArrivalTime());
            old.setPrice(trainLine.getPrice());
            TrainLine merge = em.merge(old);
            em.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public void delete(Integer id) throws ApiException {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            TrainLine exists = em.find(TrainLine.class, id);
            if(exists == null){
                throw new ApiException(404, "Train line with id: " + id + " couldn't be found.");
            }
            em.remove(exists);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<TrainLine> trainsByDeparture(LocalTime departureTime) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            var query = em.createQuery("SELECT tl from TrainLine tl WHERE tl.departureTime = :departureTime", TrainLine.class);
            query.setParameter("departureTime", departureTime);
            return query.getResultList();
        }
    }

    @Override
    public TrainLine addConductor(Integer routeId, TrainConductor conductor) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            TrainLine route = em.find(TrainLine.class, routeId);
            route.addConductor(conductor);
            TrainLine merge = em.merge(route);
            em.getTransaction().commit();
            return merge;
        }
    }

    @Override
    public float getTheAverageTicketPrice() {

        try (EntityManager em = emf.createEntityManager()) {
            var query = em.createQuery(
                    "SELECT AVG(tl.price) FROM TrainLine tl", Float.class);
            return query.getSingleResult();
        }
    }

    @Override
    public long getNumberOfConductors() {
        try (EntityManager em = emf.createEntityManager()) {
            var query = em.createQuery(
                    "SELECT tl.id, COUNT(c) " +
                            "FROM TrainLine tl " +
                            "LEFT JOIN tl.conductors c " +
                            "GROUP BY tl.id", Object[].class);

            List<Object[]> results = query.getResultList();
            long sum = 0;
            for(Object[] o : results){
                sum += (long)o[1];
            }
            return sum;
        }
    }
}
