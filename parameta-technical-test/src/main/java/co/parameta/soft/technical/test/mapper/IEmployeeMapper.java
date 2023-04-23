package co.parameta.soft.technical.test.mapper;

import co.parameta.soft.technical.test.dto.employee.request.EmployeeDtoRequest;
import co.parameta.soft.technical.test.dto.employee.response.EmployeeDtoResponse;
import co.parameta.soft.technical.test.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IEmployeeMapper{
    @Mappings({
            @Mapping(target = "birthdate", source = "birthdate", dateFormat = "yyyy-MM-dd"),
            @Mapping(target = "hireDate", source = "hireDate", dateFormat = "yyyy-MM-dd")
    })
    EmployeeDtoResponse toDto(final Employee employee);

    @Mappings({
            @Mapping(target = "birthdate", source = "birthdate", dateFormat = "yyyy-MM-dd"),
            @Mapping(target = "hireDate", source = "hireDate", dateFormat = "yyyy-MM-dd")
    })
    Employee toEntity(final EmployeeDtoRequest requestDto);

}
