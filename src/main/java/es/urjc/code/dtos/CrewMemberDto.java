package es.urjc.code.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CrewMemberDto {
    private String name;
    private String lastName;
    private String originCity;
    private Date date;
}
