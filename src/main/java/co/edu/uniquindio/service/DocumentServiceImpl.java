package co.edu.uniquindio.service;

import co.edu.uniquindio.dto.TicketDTO;
import co.edu.uniquindio.model.ProofOfEmployment;
import co.edu.uniquindio.model.Ticket;
import co.edu.uniquindio.model.WorkersReport;
import co.edu.uniquindio.model.WorkingLetter;
import co.edu.uniquindio.repositories.EmployeeRepository;
import co.edu.uniquindio.service.interfaces.IDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DocumentServiceImpl implements Serializable {

    @Autowired
    private EmployeeRepository employeeRepo;

    public IDocument getDocument(String type, TicketDTO ticketDTO) throws Exception {

        if (type.equals("carta laboral")) {
            WorkingLetter workingLetter = WorkingLetter.builder()
                    .employeeRepo(employeeRepo)
                    .build();
            workingLetter.createDocument(ticketDTO);
            return workingLetter;
        } else if (type.equals("constancia laboral")) {
            return new ProofOfEmployment();
        } else if (type.equals("reporte de empleados")) {
            return new WorkersReport();
        }
        Ticket ticket = Ticket.builder().build();
        return ticket;

    }
}
