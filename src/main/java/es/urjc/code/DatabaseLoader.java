package es.urjc.code;

import es.urjc.code.dtos.AirplaneDto;
import es.urjc.code.dtos.FlightDto;
import es.urjc.code.models.*;
import es.urjc.code.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
public class DatabaseLoader implements CommandLineRunner {

    private AirplaneRepository airplaneRepository;
    private TechnicalReviewRepository technicalReviewRepository;
    private MechanicalEmployeeRepository mechanicalEmployeeRepository;
    private FlightRepository flightRepository;
    private CabinCrewFlightRepository cabinCrewFlightRepository;
    private AirportRepository airportRepository;

    public DatabaseLoader(AirplaneRepository airplaneRepository, TechnicalReviewRepository technicalReviewRepository, MechanicalEmployeeRepository mechanicalEmployeeRepository, FlightRepository flightRepository, CabinCrewFlightRepository cabinCrewFlightRepository, AirportRepository airportRepository) {
        this.airplaneRepository = airplaneRepository;
        this.technicalReviewRepository = technicalReviewRepository;
        this.mechanicalEmployeeRepository = mechanicalEmployeeRepository;
        this.flightRepository = flightRepository;
        this.cabinCrewFlightRepository = cabinCrewFlightRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Airplane airplane = Airplane.builder().flightHours(BigDecimal.valueOf(1000.508)).licensePlate("LP54125").manufacturer("Airbus").model("A380").build();
        airplaneRepository.save(airplane);
        MechanicalEmployee mechanicalEmployee = MechanicalEmployee.builder().code("EployeeCode").name("Pedro").lastName("Picapiedra").companyName("URJC").education("Universidad").startingDate(new Date(System.currentTimeMillis())).build();
        mechanicalEmployeeRepository.save(mechanicalEmployee);
        TechnicalReview technicalReview = TechnicalReview.builder().reviewType("Periodical").spentHoursOnReview(10).startDate(new Date(System.currentTimeMillis()-50000000)).endDate(new Date(System.currentTimeMillis())).workDescription("Work description OK").checkedAirplane(airplane).mechanicalEmployee(mechanicalEmployee).build();
        technicalReviewRepository.save(technicalReview);

        List<AirplaneDto> airplaneDto = airplaneRepository.findAirplaneMechanicalReviewer();
        System.out.println(airplaneDto.get(0));

        Airport originAirport = Airport.builder().city("Madrid").country("Spain").IATACode("MAD").name("Barajas").build();
        Airport destinationAirport = Airport.builder().city("Barcelona").country("Spain").IATACode("BAR").name("Prat").build();
        airportRepository.save(originAirport);
        airportRepository.save(destinationAirport);

        //  1612134000000 01/02/2021
        Flight flight = Flight.builder().flightCode("ERSDA").flightDuration(1.5f).airline("IBERIA").airplane(airplane).originAirport(originAirport).destinationAirport(destinationAirport).departureDate(new Date(1612134000000L)).arrivalDate(new Date(1612134000000L)).build();
        flightRepository.save(flight);

        CabinCrewFlight cabinCrewFlight = new CabinCrewFlight(flight, mechanicalEmployee);
        cabinCrewFlightRepository.save(cabinCrewFlight);

        List<FlightDto> flightDto = flightRepository.findFlightsByCityAndDateOrderedByTime("Barcelona");
        System.out.println(flightDto.get(0));

        System.out.println("Finish");

    }
}
