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
public class CrewMemberFlight {

    @EmbeddedId
    private CrewMemberFlightId id;

    @ManyToOne
    @MapsId("flightId")
    private Flight flight;

    @ManyToOne
    @MapsId("crewMemberId")
    private CrewMember crewMember;

    @Builder
    public CrewMemberFlight(Flight flight, CrewMember crewMember) {
        this.flight = flight;
        this.crewMember = crewMember;
        this.id = new CrewMemberFlightId(flight.getId(), crewMember.getId());
    }
}
