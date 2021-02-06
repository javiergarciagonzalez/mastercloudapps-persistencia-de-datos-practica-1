package es.urjc.code.repository;

import es.urjc.code.models.CabinCrewFlight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabinCrewFlightRepository extends JpaRepository<CabinCrewFlight, Long> {
}
