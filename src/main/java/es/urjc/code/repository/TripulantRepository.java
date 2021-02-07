package es.urjc.code.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.urjc.code.dtos.TripulantDto;
import es.urjc.code.models.Tripulant;

public interface TripulantRepository extends JpaRepository<Tripulant, Long> {

    @Query(value = "select new es.urjc.code.dtos.TripulantDto(t.name, t.lastName, a.city, f.departureDate) from Tripulant t join TripulantFlight tf on t.id = tf.tripulant join Flight f on f.id = tf.flight join Airport a on a.id = f.originAirport.id where t.code = ?1")
    List<TripulantDto> getTripulantDestinationCitiesAndDatesByTripulantCode(String tripulantCode);

}
