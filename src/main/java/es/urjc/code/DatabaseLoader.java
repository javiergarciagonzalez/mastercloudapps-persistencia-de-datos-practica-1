package es.urjc.code;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import es.urjc.code.dtos.TripulantAccumulatedFlightTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import es.urjc.code.dtos.AirplaneDto;
import es.urjc.code.dtos.FlightDto;
import es.urjc.code.dtos.TripulantDto;
import es.urjc.code.models.Airplane;
import es.urjc.code.models.Airport;
import es.urjc.code.models.Flight;
import es.urjc.code.models.MechanicalEmployee;
import es.urjc.code.models.TechnicalReview;
import es.urjc.code.models.Tripulant;
import es.urjc.code.models.TripulantFlight;
import es.urjc.code.repository.AirplaneRepository;
import es.urjc.code.repository.AirportRepository;
import es.urjc.code.repository.FlightRepository;
import es.urjc.code.repository.MechanicalEmployeeRepository;
import es.urjc.code.repository.TechnicalReviewRepository;
import es.urjc.code.repository.TripulantRepository;

@Controller
public class DatabaseLoader implements CommandLineRunner {

    private AirplaneRepository airplaneRepository;
    private TechnicalReviewRepository technicalReviewRepository;
    private MechanicalEmployeeRepository mechanicalEmployeeRepository;
    private FlightRepository flightRepository;
    private AirportRepository airportRepository;
    private TripulantRepository tripulantRepository;

    long MINS_IN_MS = 1000 * 60 * 24;
    private Date now = new Date(System.currentTimeMillis());
    private Date twoHoursAndAHalfAgo = new Date(System.currentTimeMillis() - (150 * MINS_IN_MS));

    public DatabaseLoader(AirplaneRepository airplaneRepository, TechnicalReviewRepository technicalReviewRepository, MechanicalEmployeeRepository mechanicalEmployeeRepository, FlightRepository flightRepository, AirportRepository airportRepository, TripulantRepository tripulantRepository) {
        this.airplaneRepository = airplaneRepository;
        this.technicalReviewRepository = technicalReviewRepository;
        this.mechanicalEmployeeRepository = mechanicalEmployeeRepository;
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.tripulantRepository = tripulantRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Airplane airplane = Airplane.builder().flightHours(BigDecimal.valueOf(1000.508)).licensePlate("LP54125").manufacturer("Airbus").model("A380").build();

        MechanicalEmployee mechanicalEmployee1 = MechanicalEmployee.builder().code("EmployeeCode").name("Pedro").lastName("Picapiedra").companyName("URJC").education("Universidad").startingDate(new Date(System.currentTimeMillis())).build();

        Airport originAirport = Airport.builder().city("Madrid").country("Spain").IATACode("MAD").name("Barajas").build();

        TechnicalReview technicalReview = TechnicalReview.builder().reviewType("Periodical").spentHoursOnReview(10).startDate(new Date(System.currentTimeMillis()-50000000)).endDate(new Date(System.currentTimeMillis())).workDescription("Work description OK").checkedAirplane(airplane).mechanicalEmployee(mechanicalEmployee1).airport(originAirport).build();

        Airport destinationAirport = Airport.builder().city("Amsterdam").country("Netherlands").IATACode("AMS").name("Schiphol").build();
        Tripulant tripulant1 = Tripulant.builder().code("code01").name("John").lastName("Doe").role("Flight attendant").companyName("Iberia").build();

        Flight flight = new Flight("UX1094", "Iberia", airplane, originAirport, destinationAirport, twoHoursAndAHalfAgo, now, 2.5F);
        TripulantFlight tripulantFlight1 = TripulantFlight.builder().flight(flight).tripulant(tripulant1).build();
        flight.setTripulants(Arrays.asList(tripulantFlight1));

        mechanicalEmployeeRepository.save(mechanicalEmployee1);
        airplaneRepository.save(airplane);
        airportRepository.save(originAirport);
        airportRepository.save(destinationAirport);
        technicalReviewRepository.save(technicalReview);
        flightRepository.save(flight);

        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        String flightDtoDate = dateFormat.format(twoHoursAndAHalfAgo);

        List<AirplaneDto> airplaneDto = airplaneRepository.findAirplaneMechanicalReviewer();
        System.out.println(airplaneDto.get(0));

        // List<FlightDto> flightDto = flightRepository.findFlightsByCityAndDateOrderedByTime("Amsterdam", flightDtoDate);
        List<FlightDto> flightDto = flightRepository.findFlightsByCityAndDateOrderedByTime("Amsterdam");
        System.out.println(flightDto.get(0));

        List<TripulantDto> tripulantDto = tripulantRepository.getTripulantDestinationCitiesAndDatesByTripulantCode(tripulant1.getCode());
        System.out.println(tripulantDto.get(0));

        List<TripulantAccumulatedFlightTime> tripulantAccumulatedFlightTime = tripulantRepository.getTripulantFlightsAmountAndTotalFlightTime();
        System.out.println(tripulantAccumulatedFlightTime.get(0));

        System.out.println("Finish");

    }
}
