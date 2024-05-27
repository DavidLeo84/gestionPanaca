package co.edu.uniquindio.repositories;

import co.edu.uniquindio.model.PayRollNew;
import co.edu.uniquindio.model.UserEntity;
import co.edu.uniquindio.model.enums.StatusPayRollNew;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PayRollNewRepository extends CrudRepository<PayRollNew, Integer> {

    List<PayRollNew> findAllByStatusPayRollNew(StatusPayRollNew status) throws Exception;
    Optional<PayRollNew> findById(int id) throws Exception;

    List<PayRollNew> findAllByUserEntity(UserEntity userEntity) throws Exception;

    @Query("select p from PayRollNew p join p.userEntity u join u.department d where d.id = ?1")
    List<PayRollNew> getAllByDepartment(int id) throws Exception; // buscar todas las novedades por departamento especifico

    @Query("select p from PayRollNew p where p.dateStart between ?1 and ?2")
    List<PayRollNew> getAllByDates(LocalDateTime dateStart, LocalDateTime dateFinish) throws Exception;

    @Query("select p from PayRollNew p join p.employeeEntity em where em.dni = ?1")
    List<PayRollNew> getAllByEmployeeEntity(String dni) throws Exception;
}
