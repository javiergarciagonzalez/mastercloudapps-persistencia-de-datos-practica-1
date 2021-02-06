package es.urjc.code.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MechanicalEmployee extends Employee {

    private Date startingDate;
    private String education;
    @OneToOne
    private TechnicalReview technicalReview;

    @Builder
    public MechanicalEmployee(String code, String name, String lastName, String role, String companyName, Date startingDate, String education) {
        super(code, name, lastName, role, companyName);
        this.startingDate = startingDate;
        this.education = education;
    }
}
