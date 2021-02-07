package es.urjc.code.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    // @Builder.Default
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
