package co.edu.uniquindio.controllers;

import co.edu.uniquindio.dto.CreateUserDTO;
import co.edu.uniquindio.model.*;
import co.edu.uniquindio.model.enums.ERole;
import co.edu.uniquindio.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

//@RestController
//@RequestMapping("/api")
public class UserController {

   /* @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO userDTO){

        //Se recupera los roles que vienen del dto en string y se convierten a Role
        Set<Role> roles = userDTO.roles()
                .stream()
                .map(role -> Role
                        .builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());


        UserEntity userEntity = UserEntity
                .builder()
                .firstName("David Leonardo")
                .lastname("Romero Marulanda")
                .dni("9774676")
                .numberPhone("3172345678")
                .localNumberPhone("7374054")
                .address(new Address("Armenia", "Guaduales de la villa", "mza 5 casa 29", "QUINDIO"))
                .birthDate(LocalDate.of(1984,10,02))
                .username(userDTO.username())
                .password(passwordEncoder.encode(userDTO.password()))
                .roles(roles)
                .build();

        userRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam int id){

        userRepository.deleteById(id);
        return "Se ha borrado el usuario con id " + id;
    }*/
}
