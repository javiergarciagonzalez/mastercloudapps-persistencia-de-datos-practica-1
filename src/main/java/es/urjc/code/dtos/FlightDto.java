package es.urjc.code.dtos;

import es.urjc.code.models.Airport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private long id;

    private String flightCode;
    private String airline;
    private Airport originAirport;
    private Airport destinationAirport;
    private Date departureDate;
    private Date arrivalDate;
    private Float flightDuration;

    public String toString(){
      return flightCode + "-" + airline + "-" + originAirport.getIATACode() + "-" + destinationAirport.getIATACode() + "-" + departureDate + "-" + arrivalDate + "-" + flightDuration;
    };
}
