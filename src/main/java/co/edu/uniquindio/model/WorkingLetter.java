package co.edu.uniquindio.model;

import co.edu.uniquindio.repositories.EmployeeRepository;
import co.edu.uniquindio.service.interfaces.IDocument;
import co.edu.uniquindio.validations.exceptions.ResourceNotFoundException;
import lombok.*;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Setter
@Getter
@Builder
@Service
@Transactional
/*Carta laboral*/
public class WorkingLetter implements IDocument {

    private final EmployeeRepository employeeRepo;

    public WorkingLetter(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public void createDocument(String dni) throws Exception {
        try {
            Optional<EmployeeEntity> optionalEmployee = employeeRepo.findByDni(dni);
            if (optionalEmployee.isEmpty()) {
                throw new ResourceNotFoundException("No existe la persona ");
            }
            EmployeeEntity employee = optionalEmployee.get();

            String destinationPath = "src" + File.separator +
                    "main" + File.separator +
                    "resources" +
                    File.separator +
                    "static" + File.separator +
                    "Reporte.pdf";

            String filePath = "src" + File.separator +
                    "main" + File.separator +
                    "resources" + File.separator +
                    "templates" + File.separator +
                    "report" + File.separator +
                    "Report.jrxml";

            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("firstName",employee.getFirstName());
            parameters.put("last_name", employee.getLastname());
            parameters.put("dni", employee.getDni());
            parameters.put("appointment", employee.getAppointment());
            parameters.put("email", employee.getEmail());
            parameters.put("hireDate", employee.getHireDate());

            JasperReport report = JasperCompileManager.compileReport(filePath);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(print, destinationPath);
            System.out.println("Report Created Successfully");
        } catch (NullPointerException e) {
            throw new NullPointerException("Error con el repositorio de employees");
        }
    }

    @Override
    public void updateDocument() throws Exception {

    }
}
