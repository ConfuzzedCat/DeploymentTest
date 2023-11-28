package dk.lyngby;

import dk.lyngby.model.TrainConductor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TrainStreams {
    public static List<TrainConductor> getConductorsWithSalaryOver(float bound, Set<TrainConductor> conductors){
        return conductors.stream().filter((c) -> {
            return c.getSalary() > bound;
        }).toList();
    }
    public static double getTotalSalary(Set<TrainConductor> conductors){
        return conductors.stream()
                .mapToDouble(TrainConductor::getSalary) // Assuming TrainConductor has a getSalary method
                .sum();
    }


}
