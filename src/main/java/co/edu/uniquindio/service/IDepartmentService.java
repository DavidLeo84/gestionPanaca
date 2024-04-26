package co.edu.uniquindio.service;

import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.model.Department;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface IDepartmentService {

    Department createDepartment(RecordDepartmentDTO departmentDTO ) throws Exception;

    Department updateDepartment(UpdateDepartmentDTO departmentDTO ) throws Exception;

    void deleteDepartment(int id) throws Exception;

    DetailsDepartmentDTO getDepartment(int id) throws Exception;

    Set<ItemDepartmentDTO> getListDepartment() throws Exception;
}
