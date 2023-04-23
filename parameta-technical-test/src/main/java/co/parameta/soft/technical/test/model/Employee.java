package co.parameta.soft.technical.test.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Employee {
    /*Attributes*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String names;
    @Column(length = 70)
    private String surnames;
    @Column(length = 30)
    private String documentType;
    @Column(length = 25)
    private String documentNumber;
    private Date birthdate;
    private Date hireDate;
    @Column(length = 40)
    private String position;
    private String salary;
}

