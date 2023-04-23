package co.parameta.soft.technical.test.dto.employee.request;
import lombok.*;
import javax.validation.constraints.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeDtoRequest {
    /*Attributes*/
    @NotNull(message = "{names.not.null}")
    @NotEmpty(message = "{names.not.empty}")
    private String names;
    @NotNull(message = "{surnames.not.null}")
    @NotEmpty(message = "{surnames.not.empty}")
    private String surnames;
    @NotNull(message = "{documentType.not.null}")
    @NotEmpty(message = "{documentType.not.empty}")
    private String documentType;
    @NotNull(message = "{documentNumber.not.null}")
    @NotEmpty(message = "{documentNumber.not.empty}")
    private String documentNumber;
    @NotNull(message = "{birthdate.not.null}")
    @NotEmpty(message = "{birthdate.not.empty}")
    private String birthdate;
    @NotNull(message = "{hireDate.not.null}")
    @NotEmpty(message = "{hireDate.not.empty}")
    private String hireDate;
    @NotNull(message = "{position.not.null}")
    @NotEmpty(message = "{position.not.empty}")
    private String position;
    @NotNull(message = "{salary.not.null}")
    @PositiveOrZero(message = "{salary.positive.or.zero}")
    private String salary;

}
