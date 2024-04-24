package co.edu.uniquindio.service.interfaces;

import co.edu.uniquindio.dto.LoginDTO;
import co.edu.uniquindio.dto.TokenDTO;
import org.springframework.stereotype.Service;


@Service

public interface IAuthenticationService {

    //metodo para iniciar sesion
    TokenDTO signInUser(LoginDTO loginDTO) throws Exception;
}
