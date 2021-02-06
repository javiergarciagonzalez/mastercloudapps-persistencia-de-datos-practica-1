package es.urjc.code.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String code;
    private String name;
    private String lastName;
    protected String role;
    private String companyName;

    public Employee(String code, String name, String lastName, String role, String companyName) {
        this.code = code;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.companyName = companyName;
    }
}
