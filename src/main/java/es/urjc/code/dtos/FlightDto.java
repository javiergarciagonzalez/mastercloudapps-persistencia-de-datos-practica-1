package es.urjc.code.dtos;

import es.urjc.code.models.Airport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlightDto {

    private long id;

    private String flightCode;
    private String airline;
    private Airport originAirport;
    private Airport destinationAirport;
    private Date departureDate;
    private Date arrivalDate;
    private Float flightDuration;
}
