package co.edu.uniquindio.service.interfaces;

import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserEntity {

    String createUserEntity(RecordUserDTO userDTO) throws Exception;

    UserEntity updateUserEntity(UpdateUserDTO userDTO) throws Exception;

    DetailsUserEntityDTO getUserEntity(int id) throws Exception;

    void deleteUserEntity(int id) throws Exception;

    List<ItemUserEntityDTO> userEntityList() throws Exception;

    void changePassword(ChangePasswordDTO passwordDTO) throws Exception;
}
