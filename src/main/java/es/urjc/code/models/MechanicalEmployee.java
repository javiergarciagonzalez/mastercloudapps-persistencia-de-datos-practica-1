package es.urjc.code.models;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class MechanicalEmployee extends Employee {

    private Date startingDate;
    private String education;

    public MechanicalEmployee(
            Long id,
            String code,
            String name,
            String lastName,
            String role,
            String companyName,
            Date startDate,
            String education) {

        super(id, code, name, lastName, role, companyName);
        this.role = "Mechanical";
    }

}
