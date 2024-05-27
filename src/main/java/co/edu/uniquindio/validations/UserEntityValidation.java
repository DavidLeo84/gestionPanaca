package co.edu.uniquindio.validations;

import co.edu.uniquindio.model.UserEntity;
import co.edu.uniquindio.model.enums.Status;
import co.edu.uniquindio.repositories.UserEntityRepository;
import co.edu.uniquindio.validations.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserEntityValidation {

    private final UserEntityRepository userEntityRepo;

    public UserEntity findUserEntity(int id) throws Exception {

        Optional<UserEntity> userEntityOptional = userEntityRepo.findById(id);
        if (userEntityOptional.isEmpty() || userEntityOptional.get().getStatus().equals(Status.INACTIVE)) {
            throw new ResourceNotFoundException("No existe el usuario");
        }
        UserEntity userEntity = userEntityOptional.get();
        return userEntity;
    }

    public void existUserEntity(String username) throws Exception {

        Optional<UserEntity> userEntityOptional = userEntityRepo.findByUsername(username);
        if (userEntityOptional.isPresent()) {
            throw new Exception("Ya se encuentra registrado el usuario con el username " + username);
        }
    }
}
