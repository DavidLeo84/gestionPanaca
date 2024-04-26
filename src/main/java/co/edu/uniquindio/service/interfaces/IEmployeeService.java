package co.edu.uniquindio.service.interfaces;

import co.edu.uniquindio.dto.DetailsEmployeeDTO;
import co.edu.uniquindio.dto.ItemEmployeeDTO;
import co.edu.uniquindio.dto.RecordEmployeeDTO;
import co.edu.uniquindio.dto.UpdateEmployeeDTO;
import co.edu.uniquindio.model.EmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEmployeeService {

    EmployeeEntity recordEmployee(RecordEmployeeDTO employeeDTO) throws Exception;
    EmployeeEntity updateEmployee(UpdateEmployeeDTO employeeDTO) throws Exception;
    void deleteEmployee(String dni) throws Exception;
    List<ItemEmployeeDTO> employeesListDepartment(String departmentName) throws Exception;

    DetailsEmployeeDTO getEmployee(String dni) throws Exception;


}
