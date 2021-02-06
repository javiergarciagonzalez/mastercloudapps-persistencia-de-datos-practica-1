package es.urjc.code.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AirplaneDto {

    private long id;
    private String mechanicalEmployeeName;
    private String mechanicalEmployeeLastName;
    private String licensePlate;
    private String manufacturer;
    private String model;
    private BigDecimal flightHours;

}
