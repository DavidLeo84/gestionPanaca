package co.edu.uniquindio.service;

import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.model.*;
import co.edu.uniquindio.model.enums.Status;
import co.edu.uniquindio.repositories.DepartmentRepository;
import co.edu.uniquindio.repositories.UserEntityRepository;
import co.edu.uniquindio.service.interfaces.IDepartmentService;
import co.edu.uniquindio.validations.DepartmentValidation;
import co.edu.uniquindio.validations.EmployeeValidation;
import co.edu.uniquindio.validations.UserEntityValidation;
import co.edu.uniquindio.validations.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements IDepartmentService {

    private final DepartmentRepository departmentRepo;
    private final  UserEntityRepository userEntityRepo;
    private final DepartmentValidation departmentValidation;
    private final EmployeeValidation employeeValidation;
    private final UserEntityValidation userEntityValidation;


    @Override
    public Department createDepartment(RecordDepartmentDTO departmentDTO) throws Exception {

        departmentValidation.existDepartment(departmentDTO.departmentName());
        Department department = Department.builder()
                .departmentName(departmentDTO.departmentName())
                .listEmployees(new HashSet<>())
                .userEntity(null)
                .status(Status.ACTIVE)
                .build();
        departmentRepo.save(department);
        return department;
    }

    @Override
    public Department updateDepartment(UpdateDepartmentDTO departmentDTO) throws Exception {

        Department department = departmentValidation.findDepartment(departmentDTO.idDepartment());
        department.setDepartmentName(departmentDTO.departmentName());
        departmentRepo.save(department);
        return department;
    }

    @Override
    public void deleteDepartment(int id) throws Exception {

        Department department = departmentValidation.findDepartment(id);
        UserEntity userEntity = userEntityValidation.findUserEntity(department.getUserEntity().getId());
        userEntity.setStatus(Status.INACTIVE);
        userEntityRepo.save(userEntity);
        department.setStatus(Status.INACTIVE);
        departmentRepo.save(department);
    }

    @Override
    public DetailsDepartmentDTO getDepartment(int id) throws Exception {

        Department department = departmentValidation.findDepartment(id);
        return new DetailsDepartmentDTO(
                department.getId(),
                department.getDepartmentName(),
                department.getUserEntity().getFirstName().concat(department.getUserEntity().getLastname()),
                department.getListEmployees().stream()
                        .map(String -> String.getFirstName().concat(" ").concat(String.getLastname()))
                        .collect(Collectors.toSet()));
    }

    @Override
    public Set<ItemDepartmentDTO> getListDepartment() throws Exception {

        List<Department> departmentList = departmentRepo.findAllByStatus(Status.ACTIVE);
        if (departmentList.isEmpty()) {
            throw new ResourceNotFoundException("No hay departamentos creados");
        }
        return departmentList.stream()
                .map(d -> new ItemDepartmentDTO(d.getId(), d.getDepartmentName()))
                .collect(Collectors.toSet());
    }
}
