package es.urjc.code.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<CabinCrewFlight> flights = new ArrayList<>();

    public Employee(String code, String name, String lastName, String role, String companyName) {
        this.code = code;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.companyName = companyName;
    }
}
