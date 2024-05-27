package co.edu.uniquindio.model;

import co.edu.uniquindio.dto.TicketDTO;
import co.edu.uniquindio.repositories.EmployeeRepository;
import co.edu.uniquindio.service.interfaces.IDocument;
import lombok.*;
import net.sf.jasperreports.engine.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Builder
@Data
@Service
@NoArgsConstructor
public class Ticket implements IDocument {


    @Override
    public void createDocument(TicketDTO ticketDTO) throws Exception {

        String destinationPath = "src" + File.separator +
                "main" + File.separator +
                "resources" +
                File.separator +
                "static" + File.separator +
                "ReporteGenerado.pdf";

        String filePath = "src" + File.separator +
                "main" + File.separator +
                "resources" + File.separator +
                "templates" + File.separator +
                "report" + File.separator +
                "Report.jrxml";

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstName",ticketDTO.firstName());
        parameters.put("last_name", ticketDTO.lastName());
        parameters.put("dni", ticketDTO.dni());
        parameters.put("appointment", ticketDTO.appointment());
        parameters.put("email", ticketDTO.email());
        parameters.put("hireDate", ticketDTO.hireDate());

        JasperReport report = JasperCompileManager.compileReport(filePath);
        JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(print, destinationPath);
        System.out.println("Report Created Successfully");
    }

    @Override
    public void updateDocument() throws Exception {

    }


}
