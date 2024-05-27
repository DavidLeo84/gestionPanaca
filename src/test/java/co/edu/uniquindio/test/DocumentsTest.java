package co.edu.uniquindio.test;

import co.edu.uniquindio.dto.DetailsEmployeeDTO;
import co.edu.uniquindio.dto.TicketDTO;
import co.edu.uniquindio.model.enums.EnumPay;
import co.edu.uniquindio.service.DocumentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DocumentsTest {

    @Autowired
    private DocumentServiceImpl document;

    /*public DocumentsTest(DocumentServiceImpl document) {
        this.document = document;
    }*/

    LocalDateTime dateTimeNow = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    //@Sql
    @Test
    @DisplayName("Test para crear un documeto")
    public void createDocumentTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        TicketDTO ticketDTO = new TicketDTO(
                "3243253242",
                "David",
                "Romero",
                "2024/05/25",
                "leoromero141@gmail.com",
                "Desarrollador"
        );
        // When - Acción o el comportamiento que se va a probar
        document.getDocument("carta laboral", ticketDTO);

        //Then - Verificar la salida
//        System.out.println("department.toString() = " + department.toString());
//        assertThat(department).isNotNull();
    }
}
