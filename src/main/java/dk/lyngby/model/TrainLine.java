package dk.lyngby.model;

import dk.lyngby.dto.TrainConductorDTO;
import dk.lyngby.dto.TrainLineDTO;
import dk.lyngby.enums.Station;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "train_line")
public class TrainLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_line_id")
    private int trainLineId;
    @Column(name = "route_name", nullable = false)
    private String routeName;
    @Column(name = "departure_station", nullable = false)
    @Enumerated(EnumType.STRING)
    private Station departureStation;
    @Column(name = "arrival_station", nullable = false)
    @Enumerated(EnumType.STRING)
    private Station arrivalStation;
    @Column(name = "departure_time", nullable = false)
    @Temporal(value = TemporalType.TIME)
    private LocalTime departureTime;
    @Column(name = "arrival_time", nullable = false)
    @Temporal(value = TemporalType.TIME)
    private LocalTime arrivalTime;
    @Column(nullable = false)
    private float price;


    @Column(name = "created_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "train_line_conductors",
            joinColumns = @JoinColumn(name = "train_line_id"),
            inverseJoinColumns = @JoinColumn(name = "conductor_id"))
    private Set<TrainConductor> conductors = new HashSet<>();

    public TrainLine(String routeName, Station departureStation, Station arrivalStation, LocalTime departureTime, LocalTime arrivalTime, float price) {
        this.routeName = routeName;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public TrainLine(TrainLineDTO dto){
        routeName = dto.getRouteName();
        departureStation = dto.getDepartureStation();
        arrivalStation = dto.getArrivalStation();
        departureTime = dto.getDepartureTime();
        arrivalTime = dto.getArrivalTime();
        price = dto.getPrice();
        conductors = TrainConductor.toTrainConductorSet(dto.getConductors());
    }
    public void addConductor(TrainConductor conductor){
        conductors.add(conductor);
    }
    @PrePersist
    void onPrePresist(){
        createdAt = LocalDateTime.now();
        modifiedAt = LocalDateTime.now();
    }
    @PreUpdate
    void onPreUpdate(){
        modifiedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainLine trainLine = (TrainLine) o;

        if (Float.compare(price, trainLine.price) != 0) return false;
        if (!routeName.equals(trainLine.routeName)) return false;
        if (departureStation != trainLine.departureStation) return false;
        if (arrivalStation != trainLine.arrivalStation) return false;
        if (!departureTime.equals(trainLine.departureTime)) return false;
        return arrivalTime.equals(trainLine.arrivalTime);
    }

    @Override
    public int hashCode() {
        int result = routeName.hashCode();
        result = 31 * result + departureStation.hashCode();
        result = 31 * result + arrivalStation.hashCode();
        result = 31 * result + departureTime.hashCode();
        result = 31 * result + arrivalTime.hashCode();
        result = 31 * result + (price != 0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }
}
