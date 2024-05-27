package co.edu.uniquindio.test;

import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.model.Department;
import co.edu.uniquindio.model.PayRollNew;
import co.edu.uniquindio.model.enums.Reason;
import co.edu.uniquindio.model.enums.StatusPayRollNew;
import co.edu.uniquindio.service.PayRollNewServiceImpl;
import co.edu.uniquindio.validations.DepartmentValidation;
import co.edu.uniquindio.validations.PayRollNewValidation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class PayRollNewTest {

    @Autowired
    private PayRollNewServiceImpl payRollNewService;
    @Autowired
    private PayRollNewValidation payRollNewValidation;
    @Autowired
    private DepartmentValidation departmentValidation;

    @Test
    //@Sql
    @DisplayName("Test para guardar o registrar una novedad de nomina")
    public void createPayRollNewTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        RecordPayRollDTO payRollDTO = new RecordPayRollDTO(
                "3242353246",
                1,
                0.0,
                2,
                Reason.HORAS_EXTRAS,
                LocalDateTime.of(2024,03,29, 8,00,00),
                LocalDateTime.now(),
                "Actividades varias"
        );
        // When - Acción o el comportamiento que se va a probar
        PayRollNew payRollNew = payRollNewService.createPayRollNew(payRollDTO);

        //Then - Verificar la salida
        //System.out.println("payRollNew = " + payRollNew.toString());
        assertThat(payRollNew).isNotNull();
    }

    @Test
    //@Sql
    @DisplayName("Test para modificar un registro de una novedad de nomina")
    public void updatePayRollNewTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        PayRollNew finder = payRollNewValidation.findPayRollNew(2);

        UpdatePayRollDTO payRollDTO = new UpdatePayRollDTO(
                finder.getTypePayRollNew(),
                finder.getId(),
                finder.getEmployeeEntity().getDni(),
                finder.getPrice(),
                3,
                finder.getDateStart(),
                finder.getDateFinish().plusHours(1),
                finder.getDescription()
        );
        // When - Acción o el comportamiento que se va a probar
        PayRollNew payRollNew = payRollNewService.updatePayRollNew(payRollDTO);

        //Then - Verificar la salida
        assertThat(payRollNew.getAmountHours()).isEqualTo(3);
    }

    @Test
    //@Sql
    @DisplayName("Test para obtener los datos de un registro de una novedad de nomina")
    public void getPayRollNewTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        PayRollNew finder = payRollNewValidation.findPayRollNew(1);

        // When - Acción o el comportamiento que se va a probar
        DetailPayRollNewDTO payRollNewDTO = payRollNewService.getPayRollNew(finder.getId());
        //Then - Verificar la salida
        System.out.println("payRollNewDTO = " + payRollNewDTO.toString());
        assertThat(payRollNewDTO).isNotNull();
    }

    @Test
    //@Sql
    @DisplayName("Test para listar los registros de las novedades de nomina de un departamento")
    public void listPayRollNewsForDepartmentTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        Department department = departmentValidation.findDepartment(1);

        // When - Acción o el comportamiento que se va a probar
        List<ItemPayRollDTO> list = payRollNewService.listPayRollNewsForDepartment(department.getId());

        //Then - Verificar la salida
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    //@Sql
    @DisplayName("Test para listar los registros de las novedades de nomina segun un intervalo de fechas")
    public void listPayRollNewsForDatesTest() throws Exception {

        // Given - Dado o condicion previa o configuración

        // When - Acción o el comportamiento que se va a probar
        List<ItemPayRollDTO> list = payRollNewService.listPayRollNewsForDates(LocalDateTime.now().minusDays(10), LocalDateTime.now());

        //Then - Verificar la salida
        assertThat(list.size()).isEqualTo(2);

    }

    @Test
    //@Sql
    @DisplayName("Test para listar los registros de las novedades de nomina por empleado")
    public void listPayRollNewsForEmployeeTest() throws Exception {

        // Given - Dado o condicion previa o configuración

        // When - Acción o el comportamiento que se va a probar
        List<ItemPayRollDTO> list = payRollNewService.listPayRollNewsForEmployee("3243253242");

        //Then - Verificar la salida
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    //@Sql
    @DisplayName("Test para revisar y cambiar el estado de la novedad de nómina")
    public void payRollNewReviewTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        PayRollNew payRollNew = payRollNewValidation.findPayRollNew(1);
        ReviewPayRolNewDTO payRolNewDTO = new ReviewPayRolNewDTO(
                payRollNew.getId(),
                StatusPayRollNew.APROBADO,
                "Cumple con los requisitos"
        );

        // When - Acción o el comportamiento que se va a probar
        payRollNewService.payRollNewReview(payRolNewDTO);

        //Then - Verificar la salida
        PayRollNew finder = payRollNewValidation.findPayRollNew(1);
        assertThat(finder.getStatusPayRollNew()).isEqualTo(StatusPayRollNew.APROBADO);



    }
}
