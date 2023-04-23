package co.parameta.soft.technical.test.service.impl;

import co.parameta.soft.technical.test.dto.employee.request.EmployeeDtoRequest;
import co.parameta.soft.technical.test.dto.employee.response.EmployeeDtoResponse;
import co.parameta.soft.technical.test.exception.BusinessRuleException;
import co.parameta.soft.technical.test.mapper.IEmployeeMapper;
import co.parameta.soft.technical.test.model.Employee;
import co.parameta.soft.technical.test.repository.IEmployeeRepository;
import co.parameta.soft.technical.test.service.IEmployeeService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.time.ZoneId;

@Service
@Primary
public class EmployeeServiceImpl implements IEmployeeService {

    // ~ Dependencies
    // ====================================================================
    private final IEmployeeRepository employeeRepository;
    private final IEmployeeMapper employeeMapper;

    // ~ Dependency Injection
    // ====================================================================
    public EmployeeServiceImpl(final IEmployeeRepository employeeRepository, IEmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDtoResponse saveEmployee(final EmployeeDtoRequest employeeDTORequest) {
        /*Business rules*/
        if(isInvalidDateFormat(employeeDTORequest.getHireDate())) throw new BusinessRuleException("bad.request.hireDate.format");
        if(isInvalidDateFormat(employeeDTORequest.getBirthdate())) throw new BusinessRuleException("bad.request.birthdate.format");
        if(calculateAge(employeeDTORequest.getBirthdate()) < 18) throw new BusinessRuleException(("bad.request.age"));

        Employee employeeModel = employeeMapper.toEntity(employeeDTORequest);
        Employee employeeSaved = this.employeeRepository.save(employeeModel);
        EmployeeDtoResponse employeeDtoResponse = employeeMapper.toDto(employeeSaved);
        employeeDtoResponse.setCurrentAge(calculateTimeDifference(employeeDtoResponse.getBirthdate()));
        employeeDtoResponse.setHiringTime(calculateTimeDifference(employeeDtoResponse.getHireDate()));
        return employeeDtoResponse;
    }


    /**
     * Check if de input date is in a valid format
     *
     * @param dateStr the input date
     * @return true if the format is invalid or false if the format is valid
     */
    private boolean isInvalidDateFormat(final String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return false;
        } catch (ParseException e) {
            return true;
        }
    }


    /**
     * Calculate the period of time between the input date and current date
     *
     * @param birthdate the input date
     * @return String format with years, months and days information
     */
    private String calculateTimeDifference(Date birthdate) {
        LocalDate birthdateLocal = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthdateLocal, today);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        return years + " años, " + months + " meses, y " + days + " días";
    }


    /**
     * Calculate the age from string input date
     *
     * @param birthdateStr the input date
     * @return The current age
     */
    private int calculateAge(final String birthdateStr) {
        /*Parse date*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate;
        try {
            birthdate = sdf.parse(birthdateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        /*Calendar instance*/
        Calendar calendar = Calendar.getInstance();
        /*Set calendar in birthdate*/
        calendar.setTime(birthdate);
        /*Get year, month and day of birthday*/
        int yearOfBirth = calendar.get(Calendar.YEAR);
        int monthOfBirth = calendar.get(Calendar.MONTH);
        int dayOfBirth = calendar.get(Calendar.DAY_OF_MONTH);
        /*Set calendar in current date*/
        calendar.setTime(new Date());
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        /*Get current age from birth year and current year*/
        int age = currentYear - yearOfBirth;
        /*If the current month is less than the month of birth, the person has not yet had a birthday*/
        if (currentMonth < monthOfBirth) {
            age--;
        /*If the current month is equal to month of birth and the current day is less than the day of birth, the person has not yet had a birthday*/
        } else if (currentMonth == monthOfBirth && currentDay < dayOfBirth) {
            age--;
        }
        /*Return age*/
        return age;
    }

}
