package dk.lyngby.dto;

import dk.lyngby.enums.Station;
import dk.lyngby.model.TrainLine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainLineDTO {
    private int id;
    private String routeName;
    private Station departureStation;
    private Station arrivalStation;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private float price;
    private Set<TrainConductorDTO> conductors = new HashSet<>();

    public TrainLineDTO(String routeName, Station departureStation, Station arrivalStation, LocalTime departureTime, LocalTime arrivalTime, float price) {
        this.routeName = routeName;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public TrainLineDTO(TrainLine trainLine) {
        this.id = trainLine.getTrainLineId();
        this.routeName = trainLine.getRouteName();
        this.departureStation = trainLine.getDepartureStation();
        this.arrivalStation = trainLine.getArrivalStation();
        this.departureTime = trainLine.getDepartureTime();
        this.arrivalTime = trainLine.getArrivalTime();
        this.price = trainLine.getPrice();
        this.conductors = TrainConductorDTO.toTrainConductorDTOSet(trainLine.getConductors());
    }

    public void addConductor(TrainConductorDTO conductorDTO){
        conductors.add(conductorDTO);
    }

    public static List<TrainLineDTO> toTrainLineDTOList(List<TrainLine> resellers) {
        return resellers.stream().map(TrainLineDTO::new).collect(Collectors.toList());
    }
}
