package co.edu.uniquindio.service;

import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.model.Address;
import co.edu.uniquindio.model.Department;
import co.edu.uniquindio.model.UserEntity;
import co.edu.uniquindio.model.enums.Status;
import co.edu.uniquindio.repositories.DepartmentRepository;
import co.edu.uniquindio.repositories.UserEntityRepository;
import co.edu.uniquindio.service.interfaces.IUserEntity;
import co.edu.uniquindio.validations.DepartmentValidation;
import co.edu.uniquindio.validations.UserEntityValidation;
import co.edu.uniquindio.validations.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserEntityServiceImpl implements IUserEntity {

    private final UserEntityRepository userEntityRepo;
    private final DepartmentRepository departmentRepo;
    private final UserEntityValidation userEntityValidation;
    private final DepartmentValidation departmentValidation;

    @Override
    public String createUserEntity(RecordUserDTO userDTO) throws Exception {

        userEntityValidation.existUserEntity(userDTO.username());
        Department department = departmentValidation.findDepartment(userDTO.idDepartment());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity userEntity = UserEntity.builder()
                .firstName(userDTO.firstName()).lastname(userDTO.lastname())
                .dni(userDTO.dni()).username(userDTO.username())
                .password(passwordEncoder.encode(userDTO.password())).roles(userDTO.roles())
                .numberPhone(userDTO.numberPhone()).localNumberPhone(userDTO.localNumberPhone())
                .address(new Address()).birthDate(userDTO.birthDate())
                .status(Status.ACTIVE).department(department).build();
        userEntityRepo.save(userEntity);
        department.setUserEntity(userEntity);
        departmentRepo.save(department);
        return "Se creo el usuario con éxito";
    }

    @Override
    public UserEntity updateUserEntity(UpdateUserDTO userDTO) throws Exception {

        UserEntity userEntity = userEntityValidation.findUserEntity(userDTO.id());
        userEntity.setFirstName(userDTO.firstName());
        userEntity.setLastname(userDTO.lastname());
        userEntity.setDni(userDTO.dni());
        userEntity.setNumberPhone(userDTO.numberPhone());
        userEntity.setLocalNumberPhone(userDTO.localNumberPhone());
        userEntity.setAddress(userDTO.address());
        userEntity.setBirthDate(userDTO.birthDate());
        userEntityRepo.save(userEntity);
        return userEntity;
    }

    @Override
    public DetailsUserEntityDTO getUserEntity(int id) throws Exception {

        UserEntity userEntity = userEntityValidation.findUserEntity(id);
        return new DetailsUserEntityDTO(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastname(),
                userEntity.getUsername(),
                userEntity.getRoles());
    }

    @Override
    public void deleteUserEntity(int id) throws Exception {

        UserEntity userEntity = userEntityValidation.findUserEntity(id);
        userEntity.setStatus(Status.INACTIVE);
        userEntityRepo.save(userEntity);

    }

    @Override
    public List<ItemUserEntityDTO> userEntityList() throws Exception {

        List<UserEntity> userEntityList = userEntityRepo.findAllByStatus(Status.ACTIVE);
        if (userEntityList.isEmpty()) {
            throw new ResourceNotFoundException("No hay usuarios registrados");
        }
        return userEntityList.stream()
                .map(u -> new ItemUserEntityDTO(
                        u.getId(),
                        u.getUsername()))
                .collect(Collectors.toList());
    }

    @Override
    public void changePassword(ChangePasswordDTO passwordDTO) throws Exception {

    }
}
