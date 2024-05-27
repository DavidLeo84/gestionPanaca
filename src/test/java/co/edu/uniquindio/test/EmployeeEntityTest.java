package co.edu.uniquindio.test;

import co.edu.uniquindio.dto.DetailsEmployeeDTO;
import co.edu.uniquindio.dto.ItemEmployeeDTO;
import co.edu.uniquindio.dto.RecordEmployeeDTO;
import co.edu.uniquindio.dto.UpdateEmployeeDTO;
import co.edu.uniquindio.model.Address;
import co.edu.uniquindio.model.EmployeeEntity;
import co.edu.uniquindio.model.enums.Estate;
import co.edu.uniquindio.model.enums.Municipalities;
import co.edu.uniquindio.service.EmployeeServiceImpl;
import co.edu.uniquindio.validations.EmployeeValidation;
import co.edu.uniquindio.validations.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class EmployeeEntityTest {

    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private EmployeeValidation employeeValidation;

    @Test
    //@Sql
    @DisplayName("Test para guardar o registrar un empleado")
    public void createEmployeeTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        RecordEmployeeDTO employeeDTO = new RecordEmployeeDTO(
                "3242353246",
                "David",
                "Romero",
                "3144653454",
                "6014532424",
                new Address(Municipalities.ARMENIA.name(), Estate.QUINDÍO.name(), "Los girasoles", "mza 10 casa 10"),
                LocalDate.of(1984, 07, 23),
                "davidromero@hotmail.com",
                2,
                "dev-junior",
                LocalDate.of(2024, 04, 23)
        );

        // When - Acción o el comportamiento que se va a probar
        EmployeeEntity employee = employeeService.createEmployee(employeeDTO);

        //Then - Verificar la salida
        //System.out.println("employee.toString() = " + employee.toString());
        assertThat(employee).isNotNull();
    }

    @Test
    //@Sql
    @DisplayName("Test para modificar un registro de un empleado")
    public void updateEmployeeTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        EmployeeEntity employeeEntity = employeeValidation.findEmployee("12345664332");
        UpdateEmployeeDTO employeeDTO = new UpdateEmployeeDTO(
                employeeEntity.getDni(),
                employeeEntity.getFirstName(),
                employeeEntity.getLastname(),
                "3182323423",
                employeeEntity.getLocalNumberPhone(),
                employeeEntity.getAddress()
        );
        // When - Acción o el comportamiento que se va a probar
        EmployeeEntity employee = employeeService.updateEmployee(employeeDTO);

        //Then - Verificar la salida
        EmployeeEntity finder = employeeValidation.findEmployee("12345664332");
        assertThat(finder.getNumberPhone()).isEqualTo("3182323423");
    }

    @Test
    //@Sql
    @DisplayName("Test para listar los registros de los empleados que existan")
    public void employeesListDepartmentTest() throws  Exception {

        // When - Acción o el comportamiento que se va a probar
        List<ItemEmployeeDTO> list = employeeService.employeesListDepartment("Recursos Humanos");

        //Then - Verificar la salida
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    //@Sql
    @DisplayName("Test para obtener los datos del registro de un empleado")
    public void getEmployeeTest() throws Exception {

        // Given - Dado o condicion previa o configuración

        // When - Acción o el comportamiento que se va a probar
        DetailsEmployeeDTO employeeDTO = employeeService.getEmployee("3243253242");

        //Then - Verificar la salida
        //System.out.println("employeeDTO = " + employeeDTO.toString());
        assertThat(employeeService).isNotNull();
    }

    @Test
    //@Sql
    @DisplayName("Test para eliminar el egistro de un empleado")
    public void deleteEmployeeTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        EmployeeEntity employeeEntity = employeeValidation.findEmployee("12345664332");

        // When - Acción o el comportamiento que se va a probar
        employeeService.deleteEmployee(employeeEntity.getDni());

        //Then - Verificar la salida
        assertThrows(ResourceNotFoundException.class, () ->  employeeValidation.findEmployee("12345664332"));
    }
}
