package es.urjc.code.services;

import es.urjc.code.dtos.AirplaneDto;
import es.urjc.code.dtos.FlightDto;
import es.urjc.code.dtos.TripulantAccumulatedFlightTime;
import es.urjc.code.dtos.TripulantDto;
import es.urjc.code.repository.AirplaneRepository;
import es.urjc.code.repository.FlightRepository;
import es.urjc.code.repository.TripulantRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DatabaseQueryRunner {
    private AirplaneRepository airplaneRepository;
    private FlightRepository flightRepository;
    private TripulantRepository tripulantRepository;

    long MINUTES_IN_MS = 1000 * 60 * 24;
    private Date twoHoursAndAHalfAgo = new Date(System.currentTimeMillis() - (150 * MINUTES_IN_MS));

    public DatabaseQueryRunner(AirplaneRepository airplaneRepository, FlightRepository flightRepository, TripulantRepository tripulantRepository) {
        this.airplaneRepository = airplaneRepository;
        this.flightRepository = flightRepository;
        this.tripulantRepository = tripulantRepository;
    }

    public void run() {
        System.out.println("=========================================== QUERING ===========================================");
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        String flightDtoDate = dateFormat.format(twoHoursAndAHalfAgo);

        List<AirplaneDto> airplaneDto = airplaneRepository.findAirplaneMechanicalReviewer();
        System.out.println(airplaneDto.get(0));

        List<FlightDto> flightDto = flightRepository.findFlightsByCityAndDateOrderedByTime("Amsterdam");
        System.out.println(flightDto.get(0));

        List<TripulantDto> tripulantDto = tripulantRepository.getTripulantDestinationCitiesAndDatesByTripulantCode("code01");
        System.out.println(tripulantDto.get(0));

        List<TripulantAccumulatedFlightTime> tripulantAccumulatedFlightTime = tripulantRepository.getTripulantFlightsAmountAndTotalFlightTime();
        System.out.println(tripulantAccumulatedFlightTime.get(0));

        System.out.println("=========================================== STOP QUERING ===========================================");
    }
}
