package es.urjc.code.services;

import es.urjc.code.models.*;
import es.urjc.code.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class DatabaseLoader {
    private AirplaneLoader airplaneLoader;
    private AirportLoader airportLoader;
    private TechnicalReviewLoader technicalReviewLoader;
    private MechanicalEmployeeLoader mechanicalEmployeeLoader;
    private CrewMemberLoader crewMemberLoader;

    private FlightRepository flightRepository;

    long MINUTES_IN_MS = 1000 * 60 * 24;
    private Date now = new Date(System.currentTimeMillis());
    private Date twoHoursAndAHalfAgo = new Date(System.currentTimeMillis() - (150 * MINUTES_IN_MS));
    private Date arrivalDate = new Date(1612701233000L);

    public DatabaseLoader(FlightRepository flightRepository, AirplaneLoader airplaneLoader, AirportLoader airportLoader, TechnicalReviewLoader technicalReviewLoader, MechanicalEmployeeLoader mechanicalEmployeeLoader, CrewMemberLoader crewMemberLoader) {
        this.flightRepository = flightRepository;

        this.airplaneLoader = airplaneLoader;
        this.airportLoader = airportLoader;
        this.technicalReviewLoader = technicalReviewLoader;
        this.mechanicalEmployeeLoader = mechanicalEmployeeLoader;
        this.crewMemberLoader = crewMemberLoader;
    }

    public void load() {
        System.out.println("=========================================== LOADING ===========================================");

        List<Airplane> airplanes = airplaneLoader.load();
        List<Airport> airports = airportLoader.load();
        List<MechanicalEmployee> mechanicalEmployees = mechanicalEmployeeLoader.load();
        List<TechnicalReview> technicalReviews = technicalReviewLoader.load(airports, mechanicalEmployees, airplanes);
        List<CrewMember> crewMembers = crewMemberLoader.load();


        Flight flight = Flight.builder().flightCode("UX1094").airline("Iberia").airplane(airplanes.get(0)).originAirport(airports.get(0)).destinationAirport(airports.get(1)).departureDate(twoHoursAndAHalfAgo).arrivalDate(arrivalDate).flightDuration(2.5F).build();
        CrewMemberFlight crewMemberFlight1 = CrewMemberFlight.builder().flight(flight).crewMember(crewMembers.get(0)).build();
        flight.setCrewMembers(Collections.singletonList(crewMemberFlight1));

        flightRepository.saveAndFlush(flight);
        System.out.println("========================================= END LOADING =========================================");
    }

}
