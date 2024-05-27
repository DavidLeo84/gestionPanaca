package co.edu.uniquindio.service.interfaces;

import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.model.PayRollNew;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface IPayRollNewService {

    PayRollNew createPayRollNew(RecordPayRollDTO payRollDTO) throws Exception;

    PayRollNew updatePayRollNew(UpdatePayRollDTO payRollDTO) throws Exception;

    DetailPayRollNewDTO getPayRollNew(int id) throws Exception;

    List<ItemPayRollDTO> listPayRollNewsForDepartment(int idDepartment) throws Exception;

    List<ItemPayRollDTO> listPayRollNewsForDates(LocalDateTime dateStart, LocalDateTime dateFinish) throws Exception;

    List<ItemPayRollDTO> listPayRollNewsForEmployee(String dniEmployee) throws Exception;

    void payRollNewReview(ReviewPayRolNewDTO review) throws Exception;

}
