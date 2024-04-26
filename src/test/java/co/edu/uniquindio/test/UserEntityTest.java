package co.edu.uniquindio.test;

import co.edu.uniquindio.dto.RecordUserDTO;
import co.edu.uniquindio.model.Address;
import co.edu.uniquindio.model.Role;
import co.edu.uniquindio.model.UserEntity;
import co.edu.uniquindio.model.enums.ERole;
import co.edu.uniquindio.model.enums.Estate;
import co.edu.uniquindio.model.enums.Municipalities;
import co.edu.uniquindio.service.UserEntityServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserEntityTest {

    @Autowired
    private UserEntityServiceImpl userEntityService;


    @Test
    //@Sql
    @DisplayName("Test para registrar un nuevo usuario")
    public void createUserEntityTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        Role admin = Role.builder()
                .name(ERole.ADMINISTRATOR)
                .build();

        Set<Role> roles = new HashSet<>();
        roles.add(admin);
        RecordUserDTO userDTO = new RecordUserDTO(
                1,
                "Pepito",
                "Perez",
                "2343242354",
                "pepe",
                "123456",
                roles,
                "3272432434",
                "6012334234",
                new Address(Municipalities.ARMENIA.name(), Estate.QUINDÍO.name(), "San Jose", "mza F casa 20"),
                "2001-08-20"
        );

        // When - Acción o el comportamiento que se va a probar
        String userEntity = userEntityService.createUserEntity(userDTO);

        //Then - Verificar la salida
        System.out.println("userEntity = " + userEntity);
        assertThat(userEntity).isNotNull();

    }
}
