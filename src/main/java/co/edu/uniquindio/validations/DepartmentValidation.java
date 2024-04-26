package co.edu.uniquindio.validations;

import co.edu.uniquindio.model.Department;
import co.edu.uniquindio.repositories.DepartmentRepository;
import co.edu.uniquindio.validations.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DepartmentValidation {

    private final DepartmentRepository departmentRepo;

    public Department findDepartment(int id) throws Exception {

        Optional<Department> departmentOptional = departmentRepo.findById(id);

        if (departmentOptional.isEmpty()) {
            throw new ResourceNotFoundException("No existe departamento");
        }
        Department department = departmentOptional.get();
        return department;
    }

    public void existDepartment(String departmentName) throws Exception {

        Optional<Department> departmentOptional = departmentRepo.findByDepartmentName(departmentName);
        if (departmentOptional.isPresent()) {
            throw new Exception("Ya se encuentra registrado el departamento con el nombre " + departmentName);
        }
    }
}
