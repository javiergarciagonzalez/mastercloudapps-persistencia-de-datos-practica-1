package es.urjc.code.repository;

import es.urjc.code.dtos.FlightDto;
import es.urjc.code.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "select new es.urjc.code.dtos.FlightDto(a.id, f.flightCode, f.airline, f.originAirport, f.destinationAirport, f.departureDate, f.arrivalDate, f.flightDuration) " +
                   "from Flight f join Airport a on a.id = f.destinationAirport.id " +
                   "where a.city = :city " +
                   "order by f.arrivalDate")
    List<FlightDto> findFlightsByCityAndDateOrderedByTime(@Param("city") String city);

}
