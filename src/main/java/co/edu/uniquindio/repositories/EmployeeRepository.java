package co.edu.uniquindio.repositories;

import co.edu.uniquindio.model.EmployeeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {

    Optional<EmployeeEntity> findByDni(String dni) throws Exception;
    List<EmployeeEntity> findByLastname(String lastName)throws Exception;
    List<EmployeeEntity> findByAppointment(String appointment)throws Exception;
    Optional<EmployeeEntity> findByEmail(String email)throws Exception;
    @Query("select em from EmployeeEntity em join em.department dt where dt.departmentName = ?1")
    List<EmployeeEntity> findAllByDepartment(String departmentName)throws Exception;



}
