package co.edu.uniquindio.repositories;

import co.edu.uniquindio.model.UserEntity;
import co.edu.uniquindio.model.enums.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findById(int id) throws Exception;

    List<UserEntity> findAllByStatus(Status status) throws Exception;

    @Query("select u from UserEntity u join u.department dt where dt.id = ?1")
    Optional<UserEntity> getUserDepartment(int idDepartment) throws Exception; // buscar el usuario responsable del departamento
}
