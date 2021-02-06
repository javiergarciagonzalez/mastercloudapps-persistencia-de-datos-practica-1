package es.urjc.code;

import es.urjc.code.models.Airplane;
import es.urjc.code.models.MechanicalEmployee;
import es.urjc.code.models.TechnicalReview;
import es.urjc.code.repository.AirplaneRepository;
import es.urjc.code.repository.MechanicalEmployeeRepository;
import es.urjc.code.repository.TechnicalReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.Date;

@Controller
public class DatabaseLoader implements CommandLineRunner {

    private AirplaneRepository airplaneRepository;
    private TechnicalReviewRepository technicalReviewRepository;
    private MechanicalEmployeeRepository mechanicalEmployeeRepository;

    public DatabaseLoader(AirplaneRepository airplaneRepository, TechnicalReviewRepository technicalReviewRepository, MechanicalEmployeeRepository mechanicalEmployeeRepository) {
        this.airplaneRepository = airplaneRepository;
        this.technicalReviewRepository = technicalReviewRepository;
        this.mechanicalEmployeeRepository = mechanicalEmployeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Airplane airplane = Airplane.builder().flightHours(BigDecimal.valueOf(1000.508)).licensePlate("LP54125").manufacturer("Airbus").model("A380").build();
        airplaneRepository.save(airplane);
        TechnicalReview technicalReview = TechnicalReview.builder().reviewType("Periodical").spentHoursOnReview(10).startDate(new Date(System.currentTimeMillis()-50000000)).endDate(new Date(System.currentTimeMillis())).workDescription("Work description OK").build();
        technicalReviewRepository.save(technicalReview);
        MechanicalEmployee mechanicalEmployee = MechanicalEmployee.builder().code("EployeeCode").name("Pedro").lastName("Picapiedra").companyName("URJC").education("Universidad").startingDate(new Date(System.currentTimeMillis())).build();
        mechanicalEmployeeRepository.save(mechanicalEmployee);

        System.out.println("Finish");

    }
}
