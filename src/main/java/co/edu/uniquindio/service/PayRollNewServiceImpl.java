package co.edu.uniquindio.service;

import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.model.Department;
import co.edu.uniquindio.model.EmployeeEntity;
import co.edu.uniquindio.model.PayRollNew;
import co.edu.uniquindio.model.UserEntity;
import co.edu.uniquindio.model.enums.StatusPayRollNew;
import co.edu.uniquindio.repositories.EmployeeRepository;
import co.edu.uniquindio.repositories.PayRollNewRepository;
import co.edu.uniquindio.service.interfaces.IPayRollNewService;
import co.edu.uniquindio.validations.DepartmentValidation;
import co.edu.uniquindio.validations.EmployeeValidation;
import co.edu.uniquindio.validations.PayRollNewValidation;
import co.edu.uniquindio.validations.UserEntityValidation;
import co.edu.uniquindio.validations.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PayRollNewServiceImpl implements IPayRollNewService {

    private final PayRollNewRepository payRollNewRepo;
    private final EmployeeRepository employeeRepo;
    private final EmployeeValidation employeeValidation;
    private final UserEntityValidation userEntityValidation;
    private final PayRollNewValidation payRollNewValidation;
    private final DepartmentValidation departmentValidation;


    @Override
    public PayRollNew createPayRollNew(RecordPayRollDTO payRollDTO) throws Exception {

        EmployeeEntity employee = employeeValidation.findEmployee(payRollDTO.dniEmployee());
        UserEntity user = userEntityValidation.findUserEntity(payRollDTO.idUserEntity());
        PayRollNew payRollNew = PayRollNew.builder()
                .typePayRollNew(payRollDTO.typePayRollNew())
                .dateStart(payRollDTO.dateStart())
                .dateFinish(payRollDTO.dateFinish())
                .price(payRollDTO.price())
                .amountHours(payRollDTO.amountHours())
                .description(payRollDTO.description())
                .statusPayRollNew(StatusPayRollNew.EN_PROCESO)
                .employeeEntity(employee)
                .userEntity(user)
                .build();
        payRollNewRepo.save(payRollNew);
        return payRollNew;
    }

    @Override
    public PayRollNew updatePayRollNew(UpdatePayRollDTO payRollDTO) throws Exception {

        EmployeeEntity employee = employeeValidation.findEmployee(payRollDTO.dniEmployee());
        PayRollNew payRollNew = payRollNewValidation.findPayRollNew(payRollDTO.idPayRollNew());
        payRollNew.setTypePayRollNew(payRollDTO.typePayRollNew());
        payRollNew.setPrice(payRollDTO.price());
        payRollNew.setDateStart(payRollDTO.dateStart());
        payRollNew.setDateFinish(payRollDTO.dateFinish());
        payRollNew.setAmountHours(payRollDTO.amountHours());
        payRollNew.setDescription(payRollDTO.description());
        payRollNewRepo.save(payRollNew);
        return payRollNew;
    }

    @Override
    public DetailPayRollNewDTO getPayRollNew(int id) throws Exception {

        PayRollNew payRollNew = payRollNewValidation.findPayRollNew(id);
        return new DetailPayRollNewDTO(
                payRollNew.getEmployeeEntity().getDni(),
                payRollNew.getEmployeeEntity().getFirstName(),
                payRollNew.getEmployeeEntity().getLastname(),
                payRollNew.getDateStart(),
                payRollNew.getPrice(),
                payRollNew.getAmountHours()
        );
    }

    @Override
    public List<ItemPayRollDTO> listPayRollNewsForDepartment(int idDepartment) throws Exception {

        Department department = departmentValidation.findDepartment(idDepartment);
        List<PayRollNew> list = payRollNewRepo.getAllByDepartment(department.getId());
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No hay novedades de nómina en este departamento");
        }
        return list.stream()
                .map(p -> new ItemPayRollDTO(p.getDateStart(), p.getEmployeeEntity().getDni()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemPayRollDTO> listPayRollNewsForDates(LocalDateTime dateStart, LocalDateTime dateFinish) throws Exception {

        try {
            //LocalDateTime date1 = payRollNewValidation.formatterDateString(dateStart);
            //LocalDateTime date2 = payRollNewValidation.formatterDateString(dateFinish);
            List<PayRollNew> list = payRollNewValidation.listValidate(payRollNewRepo.getAllByDates(dateStart, dateFinish));
            return list.stream()
                    .map(p -> new ItemPayRollDTO(p.getDateStart(), p.getEmployeeEntity().getDni()))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new Exception("No hay novedades de nómina creadas en en rango de fechas");
        }
    }

    @Override
    public List<ItemPayRollDTO> listPayRollNewsForEmployee(String dniEmployee) throws Exception {

        List<PayRollNew> list = payRollNewRepo.getAllByEmployeeEntity(dniEmployee);
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("El empleado con cédula " + dniEmployee + " no tiene novedades creadas");
        }
        return list.stream()
                .map(p -> new ItemPayRollDTO(p.getDateStart(), p.getUserEntity().getDni()))
                .collect(Collectors.toList());
    }

    @Override
    public void payRollNewReview(ReviewPayRolNewDTO review) throws Exception {

        PayRollNew payRollNew = payRollNewValidation.findPayRollNew(review.idPayRoll());
        payRollNew.setStatusPayRollNew(review.status());
        String descriptionReview = payRollNew.getDescription();
        payRollNew.setDescription(descriptionReview.concat(" Response: ").concat(review.descriptionReview()));
        payRollNewRepo.save(payRollNew);

    }
}
