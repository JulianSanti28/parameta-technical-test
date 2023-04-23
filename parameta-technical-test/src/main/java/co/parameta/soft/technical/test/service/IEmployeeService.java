package co.parameta.soft.technical.test.service;

import co.parameta.soft.technical.test.dto.employee.request.EmployeeDtoRequest;
import co.parameta.soft.technical.test.dto.employee.response.EmployeeDtoResponse;

public interface IEmployeeService {
    EmployeeDtoResponse saveEmployee(final EmployeeDtoRequest employeeDTORequest);
}
