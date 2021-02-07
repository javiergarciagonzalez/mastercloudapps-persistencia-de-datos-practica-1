package es.urjc.code.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
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
    private List<TripulantFlight> tripulants;
}
