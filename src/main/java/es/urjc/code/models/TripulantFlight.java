package es.urjc.code.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@Data
@NoArgsConstructor
public class TripulantFlight {

    @EmbeddedId
    private TripulantFlightId id;

    @ManyToOne
    @MapsId("flightId")
    private Flight flight;

    @ManyToOne
    @MapsId("tripulantId")
    private Tripulant tripulant;

    @Builder
    public TripulantFlight(Flight flight, Tripulant tripulant) {
        this.flight = flight;
        this.tripulant = tripulant;
        this.id = new TripulantFlightId(flight.getId(), tripulant.getId());
    }
}
