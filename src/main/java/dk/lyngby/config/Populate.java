package dk.lyngby.config;


import dk.lyngby.enums.Station;
import dk.lyngby.model.Hotel;
import dk.lyngby.model.Room;
import dk.lyngby.model.TrainConductor;
import dk.lyngby.model.TrainLine;
import jakarta.persistence.EntityManagerFactory;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;

public class Populate {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        TrainConductor tc1 = new TrainConductor("John Smith1", "john@example.com",5000f, "123-456-7890");
        TrainConductor tc2 = new TrainConductor("Alice Johnson", "alice@example.com",4800f, "987-654-3210");
        TrainConductor tc3 = new TrainConductor("Bob Anderson", "bob@example.com",5200.75f, "555-555-5555");
        TrainConductor tc4 = new TrainConductor("John Smith2", "john@example.com",5000f, "123-456-7890");
        TrainConductor tc5 = new TrainConductor("John Smith3", "john@example.com",5000f, "123-456-7890");
        TrainConductor tc6 = new TrainConductor("John Smith4", "john@example.com",5000f, "123-456-7890");
        TrainConductor tc7 = new TrainConductor("John Smith5", "john@example.com",5000f, "123-456-7890");
        TrainConductor tc8 = new TrainConductor("John Smith6", "john@example.com",5000f, "123-456-7890");


        TrainLine tl1 =new TrainLine("PA-KI", Station.Paddington, Station.KingsCross, LocalTime.of(8,0), LocalTime.of(8,30), 25f);
        TrainLine tl2 =new TrainLine("KI-PA", Station.KingsCross, Station.Paddington, LocalTime.of(8,0), LocalTime.of(10,00), 25f);
        TrainLine tl3 =new TrainLine("VI-BR", Station.Victoria, Station.Bridge, LocalTime.of(9,0), LocalTime.of(11,30), 10.5f);
        TrainLine tl4 =new TrainLine("BR-VI", Station.Bridge, Station.Victoria, LocalTime.of(11,30), LocalTime.of(12,30), 10.5f);
        TrainLine tl5 =new TrainLine("EU-LI", Station.Euston, Station.LiverpoolStreet, LocalTime.of(11,45), LocalTime.of(15,15), 35.75f);
        TrainLine tl6 =new TrainLine("BL-LI", Station.Blackfriars, Station.LiverpoolStreet, LocalTime.of(13,15), LocalTime.of(14,0), 15.25f);
        TrainLine tl7 =new TrainLine("LI-BL", Station.LiverpoolStreet, Station.Blackfriars, LocalTime.of(13,15), LocalTime.of(13,45), 5.25f);
        TrainLine tl8 =new TrainLine("BR-PA", Station.Bridge, Station.Paddington, LocalTime.of(15,30), LocalTime.of(17,0), 20.75f);

        tl1.addConductor(tc1);
        tl2.addConductor(tc2);
        tl3.addConductor(tc3);
        tl4.addConductor(tc4);
        tl5.addConductor(tc5);
        tl6.addConductor(tc6);
        tl7.addConductor(tc7);
        tl8.addConductor(tc8);

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(tl1);
            em.persist(tl2);
            em.persist(tl3);
            em.persist(tl4);
            em.persist(tl5);
            em.persist(tl6);
            em.persist(tl7);
            em.persist(tl8);
            em.getTransaction().commit();
        }
    }
}
