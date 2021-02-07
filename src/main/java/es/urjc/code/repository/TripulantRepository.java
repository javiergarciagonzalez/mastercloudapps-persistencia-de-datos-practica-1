package es.urjc.code.repository;

import java.util.List;

import es.urjc.code.dtos.TripulantAccumulatedFlightTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.urjc.code.dtos.TripulantDto;
import es.urjc.code.models.Tripulant;
import org.springframework.data.repository.query.Param;

public interface TripulantRepository extends JpaRepository<Tripulant, Long> {

    @Query(value = "SELECT new es.urjc.code.dtos.TripulantDto(t.name, t.lastName, a.city, f.departureDate) " +
                   "FROM Tripulant t JOIN TripulantFlight tf ON t.id = tf.tripulant.id JOIN Flight f ON f.id = tf.flight.id join Airport a ON a.id = f.originAirport.id " +
                   "WHERE t.code = :tripulantCode")
    List<TripulantDto> getTripulantDestinationCitiesAndDatesByTripulantCode(@Param("tripulantCode") String tripulantCode);

    @Query(value = "SELECT new es.urjc.code.dtos.TripulantAccumulatedFlightTime(t.name, t.lastName, COUNT(tf), SUM(f.flightDuration)) " +
                   "FROM Tripulant t join TripulantFlight tf ON t.id = tf.tripulant.id JOIN Flight f ON f.id = tf.flight.id " +
                   "GROUP BY t.name, t.lastName")
    List<TripulantAccumulatedFlightTime> getTripulantFlightsAmountAndTotalFlightTime();

}
