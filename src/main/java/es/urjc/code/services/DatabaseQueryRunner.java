package es.urjc.code.services;

import es.urjc.code.dtos.AirplaneDto;
import es.urjc.code.dtos.FlightDto;
import es.urjc.code.dtos.CrewMemberAccumulatedFlightTime;
import es.urjc.code.dtos.CrewMemberDto;
import es.urjc.code.repository.AirplaneRepository;
import es.urjc.code.repository.FlightRepository;
import es.urjc.code.repository.CrewMemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class DatabaseQueryRunner {
    private AirplaneRepository airplaneRepository;
    private FlightRepository flightRepository;
    private CrewMemberRepository crewMemberRepository;

    long MINUTES_IN_MS = 1000 * 60 * 24;
    private Date twoHoursAndAHalfAgo = new Date(System.currentTimeMillis() - (150 * MINUTES_IN_MS));

    public DatabaseQueryRunner(AirplaneRepository airplaneRepository, FlightRepository flightRepository, CrewMemberRepository crewMemberRepository) {
        this.airplaneRepository = airplaneRepository;
        this.flightRepository = flightRepository;
        this.crewMemberRepository = crewMemberRepository;
    }

    public void run() {
        System.out.println("=========================================== QUERING ===========================================");

        this.query1();
        this.query2();




        List<CrewMemberDto> crewMemberDto = crewMemberRepository.getCrewMemberDestinationCitiesAndDatesByCrewMemberCode("code01");
        System.out.println(crewMemberDto.get(0));

        List<CrewMemberAccumulatedFlightTime> crewMemberAccumulatedFlightTime = crewMemberRepository.getCrewMemberFlightsAmountAndTotalFlightTime();
        System.out.println(crewMemberAccumulatedFlightTime.get(0));

        System.out.println("=========================================== STOP QUERING ===========================================");
    }

    private void query1() {
        System.out.println("=========================================== QUERY 1 ===========================================");
        System.out.println("Para cada avión, mostrar el nombre y apellidos de los mecánicos responsables de sus revisiones.");

        List<AirplaneDto> airplaneDtos = airplaneRepository.findAirplaneMechanicalReviewer();
        for (AirplaneDto airplaneDto : airplaneDtos) {
            System.out.println(airplaneDto);
        }
    }

    private void query2() {
        System.out.println("=========================================== QUERY 2 ===========================================");
        System.out.println("Dado el nombre de una ciudad y una fecha, listado de los vuelos que han aterrizado (destino) en los aeropuertos de esa ciudad en esa fecha, ordenados por hora.");

        LocalDate flightDtoDate = LocalDate.parse(
            "2021-02-07" ,
            DateTimeFormatter.ofPattern( "yyyy-MM-dd" )
        );

        List<FlightDto> flightDtos = flightRepository.findFlightsByCityAndDateOrderedByTime("Amsterdam", flightDtoDate.toString());
        for (FlightDto flightDto : flightDtos) {
            System.out.println(flightDto);
        }
    }
}
