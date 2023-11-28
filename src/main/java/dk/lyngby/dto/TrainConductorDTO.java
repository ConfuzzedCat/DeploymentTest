package dk.lyngby.dto;

import dk.lyngby.model.TrainConductor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainConductorDTO {
    private int id;
    private String name;
    private String email;
    private float salary;
    private String phoneNumber;

    public TrainConductorDTO(String name, String email, float salary, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
    }

    public TrainConductorDTO(TrainConductor trainConductor){
        this.id = trainConductor.getConductorId();
        this.name = trainConductor.getName();
        this.email = trainConductor.getEmail();
        this.salary = trainConductor.getSalary();
        this.phoneNumber = trainConductor.getPhoneNumber();
    }

    public static Set<TrainConductorDTO> toTrainConductorDTOSet(Set<TrainConductor> trainConductors) {
        return trainConductors.stream().map(TrainConductorDTO::new).collect(Collectors.toSet());
    }
}
