package co.edu.uniquindio.controllers;


import co.edu.uniquindio.dto.LoginDTO;
import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.dto.TokenDTO;
import co.edu.uniquindio.repositories.UserEntityRepository;
import co.edu.uniquindio.service.AuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
//@SecurityRequirement(name = "bearerAuth")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;
    private final UserEntityRepository userEntityRepo;

    @PostMapping("/sign-in")
    public ResponseEntity<MensajeDTO<TokenDTO>> signInUser(@Valid @RequestBody LoginDTO loginDTO) throws Exception {

        TokenDTO tokenDTO = authenticationService.signInUser(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));

    }
}
