package co.parameta.soft.technical.test.dto.employee.response;
import lombok.*;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeDtoResponse {
    /*Attributes*/
    private Integer id;
    private String names;
    private String surnames;
    private String documentType;
    private String documentNumber;
    private Date birthdate;
    private Date hireDate;
    private String position;
    private String salary;
    private String hiringTime;
    private String currentAge;
}
