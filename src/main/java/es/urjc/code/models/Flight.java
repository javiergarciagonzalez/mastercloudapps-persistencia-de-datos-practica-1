package es.urjc.code.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String flightCode;
    private String airline;
    private Airplane airplane;
    private Airport originAirport;
    private Airport distinationAirport;
    private Date departureDate;
    private Date arrivalDate;
    private Float flightDuration;
    private Employee[] cabinCrew;
}
