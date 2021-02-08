package es.urjc.code.services;

import es.urjc.code.models.*;
import es.urjc.code.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

@Service
public class DatabaseLoader {
    private AirplaneRepository airplaneRepository;
    private TechnicalReviewRepository technicalReviewRepository;
    private MechanicalEmployeeRepository mechanicalEmployeeRepository;
    private FlightRepository flightRepository;
    private AirportRepository airportRepository;
    private CrewMemberRepository crewMemberRepository;

    long MINUTES_IN_MS = 1000 * 60 * 24;
    private Date now = new Date(System.currentTimeMillis());
    private Date twoHoursAndAHalfAgo = new Date(System.currentTimeMillis() - (150 * MINUTES_IN_MS));
    private Date arrivalDate = new Date(1612701233000L);

    public DatabaseLoader(AirplaneRepository airplaneRepository, TechnicalReviewRepository technicalReviewRepository, MechanicalEmployeeRepository mechanicalEmployeeRepository, FlightRepository flightRepository, AirportRepository airportRepository, CrewMemberRepository crewMemberRepository) {
        this.airplaneRepository = airplaneRepository;
        this.technicalReviewRepository = technicalReviewRepository;
        this.mechanicalEmployeeRepository = mechanicalEmployeeRepository;
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.crewMemberRepository = crewMemberRepository;
    }

    public void load() {
        System.out.println("=========================================== LOADING ===========================================");
        Airplane airplane = Airplane.builder().flightHours(BigDecimal.valueOf(1000.508)).licensePlate("LP54125").manufacturer("Airbus").model("A380").build();

        MechanicalEmployee mechanicalEmployee1 = MechanicalEmployee.builder().code("EmployeeCode").name("Pedro").lastName("Picapiedra").companyName("URJC").education("Universidad").startingDate(new Date(System.currentTimeMillis())).build();

        Airport originAirport = Airport.builder().city("Madrid").country("Spain").iataCode("MAD").name("Barajas").build();

        TechnicalReview technicalReview = TechnicalReview.builder().reviewType("Periodical").spentHoursOnReview(10).startDate(new Date(System.currentTimeMillis() - 50000000)).endDate(new Date(System.currentTimeMillis())).workDescription("Work description OK").checkedAirplane(airplane).mechanicalEmployee(mechanicalEmployee1).airport(originAirport).build();

        Airport destinationAirport = Airport.builder().city("Amsterdam").country("Netherlands").iataCode("AMS").name("Schiphol").build();
        CrewMember crewMember1 = CrewMember.builder().code("code01").name("John").lastName("Doe").role("Flight attendant").companyName("Iberia").build();

        Flight flight = Flight.builder().flightCode("UX1094").airline("Iberia").airplane(airplane).originAirport(originAirport).destinationAirport(destinationAirport).departureDate(twoHoursAndAHalfAgo).arrivalDate(arrivalDate).flightDuration(2.5F).build();
        CrewMemberFlight crewMemberFlight1 = CrewMemberFlight.builder().flight(flight).crewMember(crewMember1).build();
        flight.setCrewMembers(Arrays.asList(crewMemberFlight1));

        mechanicalEmployeeRepository.save(mechanicalEmployee1);
        airplaneRepository.save(airplane);
        airportRepository.save(originAirport);
        airportRepository.save(destinationAirport);
        technicalReviewRepository.save(technicalReview);
        flightRepository.save(flight);
        System.out.println("========================================= END LOADING =========================================");
    }

}
