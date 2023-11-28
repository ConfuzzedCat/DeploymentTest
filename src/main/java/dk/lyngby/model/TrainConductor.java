package dk.lyngby.model;

import dk.lyngby.dto.TrainConductorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Table(name = "train_conductor")
public class TrainConductor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conductor_id")
    private int conductorId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private float salary;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    public TrainConductor(String name, String email, float salary, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
    }

    public TrainConductor(TrainConductorDTO dto){
        name = dto.getName();
        email = dto.getEmail();
        salary = dto.getSalary();
        phoneNumber = dto.getPhoneNumber();
    }

    public static Set<TrainConductor> toTrainConductorSet(Set<TrainConductorDTO> trainConductorDTOs) {
        return trainConductorDTOs.stream().map(TrainConductor::new).collect(Collectors.toSet());
    }
}
