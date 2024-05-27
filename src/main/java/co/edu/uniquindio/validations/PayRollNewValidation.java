package co.edu.uniquindio.validations;

import co.edu.uniquindio.dto.ItemPayRollDTO;
import co.edu.uniquindio.model.PayRollNew;
import co.edu.uniquindio.repositories.EmployeeRepository;
import co.edu.uniquindio.repositories.PayRollNewRepository;
import co.edu.uniquindio.validations.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PayRollNewValidation {

    private final PayRollNewRepository payRollNewRepo;
    private final EmployeeRepository employeeRepo;

    public void existPayRollNew(int id) throws Exception {

        Optional<PayRollNew> optional = payRollNewRepo.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("No existe la novedad de nómina");
        }
    }

    public PayRollNew findPayRollNew(int id) throws Exception {

        Optional<PayRollNew> optional = payRollNewRepo.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("No existe la novedad de nómina");
        }
        PayRollNew payRollNew = optional.get();
        return  payRollNew;
    }

    public List<PayRollNew> listValidate(List<PayRollNew> list) throws Exception {

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("aca hay un error");
        }
        return list;
    }

    public LocalDateTime formatterDateString(String date) throws Exception {
        try {
            String dateModified = date.replaceAll(" ", "T");
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            System.out.println("dateModified = " + dateModified);
            LocalDateTime dateFormatted = LocalDateTime.parse(dateModified, formatter);
            //TemporalAccessor dateFormatted = formatter.parse(dateModified);
            //return LocalDateTime.from(dateFormatted);
            return dateFormatted;
        }
        catch (Exception ex) {
            throw new Exception("La fecha no cumple con el formato requerido");
        }
    }


}
