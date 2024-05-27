package co.edu.uniquindio.validations;

import co.edu.uniquindio.model.Department;
import co.edu.uniquindio.model.EmployeeEntity;
import co.edu.uniquindio.model.enums.Status;
import co.edu.uniquindio.repositories.DepartmentRepository;
import co.edu.uniquindio.repositories.EmployeeRepository;
import co.edu.uniquindio.validations.exceptions.ResourceInvalidException;
import co.edu.uniquindio.validations.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeValidation {

    private final EmployeeRepository employeeRepo;
    private final DepartmentRepository departmentRepo;

    public EmployeeEntity findEmployee(String dni) throws Exception {

        Optional<EmployeeEntity> optionalEmployee = employeeRepo.findByDni(dni);

        if (optionalEmployee.isEmpty() || optionalEmployee.get().getStatus().equals(Status.INACTIVE)) {
            throw new ResourceNotFoundException("No existe la persona el número de identificación " + dni);
        }
        EmployeeEntity employee = optionalEmployee.get();
        return employee;
    }

    public void existEmployee(String dni) throws Exception {

        Optional<EmployeeEntity> optionalEmployee = employeeRepo.findByDni(dni);
        if (optionalEmployee.isPresent()) {
            throw new Exception("Ya se encuentra registrado un empleado con el numero de identificacion " + dni);
        }
    }

    public void existDepartment(String departmentName) throws Exception{

        Optional<Department> departmentOptional = departmentRepo.findByDepartmentName(departmentName);
        if (departmentOptional.isEmpty()) {
            throw new ResourceNotFoundException("No existe el departamente con el nombre " + departmentName);
        }
    }

    public String dateFormatter(LocalDate date) throws Exception {

        if (date.isAfter(LocalDate.now())) {
            throw new ResourceInvalidException("Fecha no válida");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String dateFormat = formatter.format(date);
        return dateFormat;
    }

    public LocalDate stringFormatter(String date) throws Exception {

        try {
            String dateModificated = date.replaceAll("/", "-");
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            TemporalAccessor accessor = formatter.parse(dateModificated);
            return LocalDate.from(accessor);
        }
        catch (RuntimeException ex){
            throw new RuntimeException("Error. La fecha de nacimiento o de contratación no son válidas");
        }
    }

}
