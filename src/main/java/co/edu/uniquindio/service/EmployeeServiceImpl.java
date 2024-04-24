package co.edu.uniquindio.service;

import co.edu.uniquindio.dto.DetailEmployeeDTO;
import co.edu.uniquindio.dto.ItemEmployeeDTO;
import co.edu.uniquindio.dto.RecordEmployeeDTO;
import co.edu.uniquindio.dto.UpdateEmployeeDTO;
import co.edu.uniquindio.model.Address;
import co.edu.uniquindio.model.Department;
import co.edu.uniquindio.model.EmployeeEntity;
import co.edu.uniquindio.model.enums.Status;
import co.edu.uniquindio.repositories.EmployeeRepository;
import co.edu.uniquindio.service.interfaces.IEmployeeService;
import co.edu.uniquindio.validations.EmployeeValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository employeeRepo;

    @Autowired
    private EmployeeValidation employeeValidation;


    @Override
    public EmployeeEntity recordEmployee(RecordEmployeeDTO employeeDTO) throws Exception {

        employeeValidation.existEmployee(employeeDTO.dni());
        EmployeeEntity employee = EmployeeEntity.builder()
                .dni(employeeDTO.dni())
                .firstName(employeeDTO.firstName())
                .lastname(employeeDTO.lastname())
                .numberPhone(employeeDTO.numberPhone())
                .localNumberPhone(employeeDTO.localNumberPhone())
                .address(Address.builder()
                        .city(employeeDTO.address().getCity())
                        .stateProvince(employeeDTO.address().getStateProvince())
                        .suburb(employeeDTO.address().getSuburb())
                        .streetAdress(employeeDTO.address().getStreetAdress())
                        .build())
                .birthDate(employeeValidation.dateFormatter(employeeDTO.birthDate()))
                .email(employeeDTO.email())
                .department(Department.builder()
                        .departmentName(employeeDTO.department().getDepartmentName())
                        .build())
                .appointment(employeeDTO.appointment())
                .hireDate(employeeValidation.dateFormatter(employeeDTO.hireDate()))
                .status(Status.ACTIVE)
                .build();
        employeeRepo.save(employee);
        return employee;
    }

    @Override
    public EmployeeEntity updateEmployee(UpdateEmployeeDTO employeeDTO) throws Exception {

        EmployeeEntity employee = employeeValidation.findEmployee(employeeDTO.dni());
        employee.setFirstName(employeeDTO.firstName());
        employee.setLastname(employeeDTO.lastname());
        employee.setNumberPhone(employeeDTO.numberPhone());
        employee.setNumberPhone(employeeDTO.localNumberPhone());
        employee.setAddress(employeeDTO.address());
        employeeRepo.save(employee);
        return employee;
    }

    @Override
    public void deleteEmployee(String dni) throws Exception {

        EmployeeEntity employee = employeeValidation.findEmployee(dni);
        employee.setStatus(Status.INACTIVE);
        employeeRepo.save(employee);
    }

    @Override
    public List<ItemEmployeeDTO> employeesListDepartment(String departmentName) throws Exception {

        employeeValidation.existDepartment(departmentName);
        List<EmployeeEntity> employeesList = employeeRepo.findAllByDepartment(departmentName);
        return employeesList.stream()
                .map(e -> new ItemEmployeeDTO(
                        e.getFirstName(),
                        e.getLastname(),
                        e.getAppointment()))
                .collect(Collectors.toList());
    }

    @Override
    public DetailEmployeeDTO getEmployee(String dni) throws Exception {

        EmployeeEntity employee = employeeValidation.findEmployee(dni);

        return new DetailEmployeeDTO(
                employee.getDni(), employee.getFirstName(),
                employee.getLastname(), employee.getAppointment(),
                employee.getNumberPhone(), employee.getLocalNumberPhone(),
                employee.getAddress(), employeeValidation.stringFormatter(employee.getBirthDate()),
                employee.getEmail(), employee.getDepartment(), employeeValidation.stringFormatter(employee.getHireDate()));

    }
}
