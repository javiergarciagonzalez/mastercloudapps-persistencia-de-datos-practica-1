package es.urjc.code.repository;

import java.util.List;

import es.urjc.code.dtos.TripulantAccumulatedFlightTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.urjc.code.dtos.TripulantDto;
import es.urjc.code.models.Tripulant;
import org.springframework.data.repository.query.Param;

public interface TripulantRepository extends JpaRepository<Tripulant, Long> {

    @Query(value = "select new es.urjc.code.dtos.TripulantDto(t.name, t.lastName, a.city, f.departureDate) " +
                   "from Tripulant t join TripulantFlight tf on t.id = tf.tripulant.id join Flight f on f.id = tf.flight.id join Airport a on a.id = f.originAirport.id " +
                   "where t.code = :tripulantCode")
    List<TripulantDto> getTripulantDestinationCitiesAndDatesByTripulantCode(@Param("tripulantCode") String tripulantCode);

    @Query(value = "select new es.urjc.code.dtos.TripulantAccumulatedFlightTime(t.name, t.lastName, count(tf), sum(f.flightDuration)) " +
                   "from Tripulant t join TripulantFlight tf on t.id = tf.tripulant.id join Flight f on f.id = tf.flight.id " +
                   "group by t.name, t.lastName")
    List<TripulantAccumulatedFlightTime> getTripulantFlightsAmountAndTotalFlightTime();

}
