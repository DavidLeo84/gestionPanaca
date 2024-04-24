package co.edu.uniquindio.service;

import co.edu.uniquindio.dto.LoginDTO;
import co.edu.uniquindio.dto.TokenDTO;
import co.edu.uniquindio.model.UserEntity;
import co.edu.uniquindio.repositories.UserRepository;
import co.edu.uniquindio.security.jwt.JwtUtils;
import co.edu.uniquindio.service.interfaces.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public TokenDTO signInUser(LoginDTO loginDTO) throws Exception {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(loginDTO.username());
        if (optionalUserEntity.isEmpty()) {
            throw new Exception("El usuario no se encuentra registrado");
        }
        UserEntity userEntity = optionalUserEntity.get();
        if(!passwordEncoder.matches(loginDTO.password(), userEntity.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "ADMIN");
        map.put("nombre", userEntity.getUsername());
        map.put("id", userEntity.getId());
        return new TokenDTO( jwtUtils.generarToken(userEntity.getUsername(), map) );
    }
}
