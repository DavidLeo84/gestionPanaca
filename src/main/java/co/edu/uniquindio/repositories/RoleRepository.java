package co.edu.uniquindio.repositories;

import co.edu.uniquindio.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findById(int id) throws Exception;

    Optional<Role> findByName(String roleName) throws Exception;
}
