package co.edu.uniquindio.service;

import co.edu.uniquindio.model.ProofOfEmployment;
import co.edu.uniquindio.model.WorkersReport;
import co.edu.uniquindio.model.WorkingLetter;
import co.edu.uniquindio.repositories.EmployeeRepository;
import co.edu.uniquindio.service.interfaces.IDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@RequiredArgsConstructor
public class DocumentServiceImpl implements Serializable {

    private final EmployeeRepository employeeRepo;

    public IDocument getDocument(String type, String dni) throws Exception {


        if (type.equals("carta laboral")) {
            WorkingLetter workingLetter = WorkingLetter.builder()
                    .employeeRepo(employeeRepo).build();
            workingLetter.createDocument(dni);
            return workingLetter;
        } else if (type.equals("constancia laboral")) {
            ProofOfEmployment proofOfEmployment = ProofOfEmployment.builder().build();
            return proofOfEmployment;

        } else {
            WorkersReport workersReport = WorkersReport.builder().build();
            return workersReport;
        }
    }
}
