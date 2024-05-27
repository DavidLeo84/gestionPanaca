package co.edu.uniquindio.controllers;

import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.model.UserEntity;
import co.edu.uniquindio.service.UserEntityServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserEntityServiceImpl userEntityService;


    @PostMapping("/create-user")
    public ResponseEntity<MensajeDTO<String>> createUserEntity(@Valid @RequestBody RecordUserDTO recordUserDTO) throws Exception {

        return ResponseEntity.ok().body(new MensajeDTO<>(false, userEntityService.createUserEntity(recordUserDTO)));
    }

    @PutMapping("/update-user")
    public ResponseEntity<MensajeDTO<String>> updateUserEntity(@Valid @RequestBody UpdateUserDTO userDTO) throws Exception {

        UserEntity user = userEntityService.updateUserEntity(userDTO);

        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se ha nodificado la información del usuario"));
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<MensajeDTO<DetailsUserEntityDTO>> getUserEntity(@PathVariable int id) throws Exception {

        DetailsUserEntityDTO userEntityDTO = userEntityService.getUserEntity(id);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, userEntityDTO));
    }

    @DeleteMapping("delete-user/{id}")
    public ResponseEntity<MensajeDTO<String>> deleteUserEntity(@PathVariable int id) throws Exception {

        userEntityService.deleteUserEntity(id);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "El usuario ha sido eliminado"));
    }

    @GetMapping("/list-users")
    public ResponseEntity<MensajeDTO<List<ItemUserEntityDTO>>> userEntityList() throws Exception {

        List<ItemUserEntityDTO> userEntityDTO = userEntityService.userEntityList();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, userEntityDTO));
    }

    @PutMapping("/change-pass")
    public ResponseEntity<MensajeDTO<String>> changePassword(@Valid @RequestBody
                                                             ChangePasswordDTO changePasswordDTO) throws Exception {
        userEntityService.changePassword(changePasswordDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se ha nodificado el password"));
    }


}
