package es.urjc.code.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class TechnicalReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Airplane checkedAirplane;
    private Date startDate;
    private Date endDate;
    private Integer spentHoursOnReview;
    private String reviewType;
    private String workDescription;
    private Airport airport;

}
