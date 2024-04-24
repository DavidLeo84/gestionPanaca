package co.edu.uniquindio.repositories;

import co.edu.uniquindio.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

    Optional<Department> findById(int id) throws Exception;
    Optional<Department> findByDepartmentName(String departmentName) throws Exception;
}
