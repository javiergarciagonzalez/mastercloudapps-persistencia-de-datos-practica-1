package es.urjc.code.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String code;
    private String name;
    private String lastName;

    public Employee(String code, String name, String lastName) {
        this.code = code;
        this.name = name;
        this.lastName = lastName;
    }
}
