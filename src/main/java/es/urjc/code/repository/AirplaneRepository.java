package es.urjc.code.repository;

import es.urjc.code.dtos.AirplaneDto;
import es.urjc.code.models.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    @Query(value = "select new es.urjc.code.dtos.AirplaneDto(a.id, m.name, m.lastName, a.licensePlate, a.manufacturer, a.model, a.flightHours) from Airplane a join TechnicalReview t on t.checkedAirplane.id = a.id join MechanicalEmployee m on m.id = t.mechanicalEmployee.id")
    List<AirplaneDto> findAirplaneMechanicalReviewer();

}
