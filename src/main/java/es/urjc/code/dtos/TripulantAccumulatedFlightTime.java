package es.urjc.code.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TripulantAccumulatedFlightTime {

    private String name;
    private String lastName;
    private Long numberOfFlights;
    private Double totalTime;
}
