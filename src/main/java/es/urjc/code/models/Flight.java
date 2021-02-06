package es.urjc.code.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
    //private Airplane airplane;
    //private Airport originAirport;
    //private Airport distinationAirport;
    private Date departureDate;
    private Date arrivalDate;
    private Float flightDuration;
    //private Employee[] cabinCrew;
}
