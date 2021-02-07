package es.urjc.code.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Flight(String flightCode, String airline, Airplane airplane, Airport originAirport, Airport destinationAirport, Date departureDate, Date arrivalDate, Float flightDuration) {
        this.flightCode = flightCode;
        this.airline = airline;
        this.airplane = airplane;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.flightDuration = flightDuration;
    }
}
