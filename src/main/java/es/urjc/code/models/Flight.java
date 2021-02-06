package es.urjc.code.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String flightCode;
    private String airline;
    @ManyToOne
    private Airplane airplane;
    @ManyToOne
    private Airport originAirport;
    @ManyToOne
    private Airport destinationAirport;
    private Date departureDate;
    private Date arrivalDate;
    private Float flightDuration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flight")
    private List<CabinCrewFlight> employees = new ArrayList<>();
}
