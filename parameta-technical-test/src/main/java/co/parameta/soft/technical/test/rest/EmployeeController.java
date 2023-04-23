package co.parameta.soft.technical.test.rest;

import co.parameta.soft.technical.test.dto.employee.request.EmployeeDtoRequest;
import co.parameta.soft.technical.test.dto.employee.response.EmployeeDtoResponse;
import co.parameta.soft.technical.test.service.IEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    // ~ Dependencies
    // ====================================================================
    private final IEmployeeService employeeService;

    // ~ Dependency Injection
    // ====================================================================
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDtoResponse> saveEmployee(@Valid @RequestBody final EmployeeDtoRequest employeeDtoRequest){
        return new ResponseEntity<>(this.employeeService.saveEmployee(employeeDtoRequest), HttpStatus.CREATED);
    }
}
