package co.edu.uniquindio.test;

import co.edu.uniquindio.dto.DetailsUserEntityDTO;
import co.edu.uniquindio.dto.ItemUserEntityDTO;
import co.edu.uniquindio.dto.RecordUserDTO;
import co.edu.uniquindio.dto.UpdateUserDTO;
import co.edu.uniquindio.model.Address;
import co.edu.uniquindio.model.Role;
import co.edu.uniquindio.model.UserEntity;
import co.edu.uniquindio.model.enums.ERole;
import co.edu.uniquindio.model.enums.Estate;
import co.edu.uniquindio.model.enums.Municipalities;
import co.edu.uniquindio.service.UserEntityServiceImpl;
import co.edu.uniquindio.validations.UserEntityValidation;
import co.edu.uniquindio.validations.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserEntityTest {

    @Autowired
    private UserEntityServiceImpl userEntityService;

    @Autowired
    private UserEntityValidation userEntityValidation;


    @Test
    //@Sql
    @DisplayName("Test para registrar un nuevo usuario")
    public void createUserEntityTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        Role admin = Role.builder()
                .name(ERole.AUXILIAR)
                .build();
        Set<Role> roles = new HashSet<>();
        roles.add(admin);
        RecordUserDTO userDTO = new RecordUserDTO(
                2,
                "Jose",
                "Hernandez",
                "987675434",
                "josehdez",
                "123456",
                roles,
                "3217667456",
                "6043432426",
                new Address(Municipalities.PASTO.name(), Estate.NARIÑO.name(), "Las candelarias", "mza h casa 15"),
                "1993-11-08"
        );

        // When - Acción o el comportamiento que se va a probar
        String userEntity = userEntityService.createUserEntity(userDTO);

        //Then - Verificar la salida
        System.out.println("userEntity = " + userEntity);
        assertThat(userEntity).isNotNull();
    }

    @Test
    //@Sql
    @DisplayName("Test para modificar un registro de un usuario")
    public void updateUserEntityTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        UserEntity userEntity = userEntityValidation.findUserEntity(1);

        UpdateUserDTO userDTO = new UpdateUserDTO(
                userEntity.getId(),
                userEntity.getFirstName(),
                "Zapata",
                userEntity.getDni(),
                userEntity.getNumberPhone(),
                userEntity.getLocalNumberPhone(),
                userEntity.getAddress(),
                userEntity.getBirthDate()
        );
        // When - Acción o el comportamiento que se va a probar
        UserEntity user = userEntityService.updateUserEntity(userDTO);

        //Then - Verificar la salida
        assertThat(user).isNotNull();
    }

    @Test
    //@Sql
    @DisplayName("Test para obtener un registro de un usuario")
    public void getUserEntityTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        UserEntity userEntity = userEntityValidation.findUserEntity(1);

        // When - Acción o el comportamiento que se va a probar
        DetailsUserEntityDTO entityDTO = userEntityService.getUserEntity(userEntity.getId());

        //Then - Verificar la salida
        System.out.println("entityDTO = " + entityDTO.toString());
        assertThat(entityDTO).isNotNull();
    }

    @Test
    //@Sql
    @DisplayName("Test para listar los usuarios que esten registrados")
    public void userEntityListTest() throws Exception {

        // Given - Dado o condicion previa o configuración

        // When - Acción o el comportamiento que se va a probar
        List<ItemUserEntityDTO> list = userEntityService.userEntityList();

        //Then - Verificar la salida
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    //@Sql
    @DisplayName("Test para listar los usuarios que esten registrados")
    public void deleteUserEntityTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        UserEntity userEntity = userEntityValidation.findUserEntity(1);

        // When - Acción o el comportamiento que se va a probar
        userEntityService.deleteUserEntity(userEntity.getId());

        //Then - Verificar la salida
        assertThrows(ResourceNotFoundException.class, () -> userEntityValidation.findUserEntity(1));
    }

}
