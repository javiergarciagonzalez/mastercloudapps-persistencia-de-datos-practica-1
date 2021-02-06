package es.urjc.code.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@Data
@NoArgsConstructor
public class CabinCrewFlight {

    @EmbeddedId
    private CabinCrewFlightId id;

    @ManyToOne
    @MapsId("flightId")
    private Flight flight;

    @ManyToOne
    @MapsId("employeeId")
    private Employee employee;

    public CabinCrewFlight(Flight flight, Employee employee) {
        this.flight = flight;
        this.employee = employee;
        this.id = new CabinCrewFlightId(flight.getId(), employee.getId());
    }
}
