package co.edu.uniquindio.test;

import co.edu.uniquindio.dto.LoginDTO;
import co.edu.uniquindio.dto.TokenDTO;
import co.edu.uniquindio.service.AuthenticationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthenticationTest {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Test
    //@Sql
    @DisplayName("Test para iniciar sesion con un usuario userEntity")
    public void signInUserTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        LoginDTO loginDTO = new LoginDTO(
                "mariaperez",
                "123456"
        );

        // When - Acción o el comportamiento que se va a probar
        TokenDTO tokenDTO = authenticationService.signInUser(loginDTO);

        //Then - Verificar la salida
        System.out.println("tokenDTO = " + tokenDTO);
        assertThat(tokenDTO).isNotNull();

    }
}
