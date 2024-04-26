package co.edu.uniquindio.test;

import co.edu.uniquindio.dto.RecordDepartmentDTO;
import co.edu.uniquindio.model.Department;
import co.edu.uniquindio.model.UserEntity;
import co.edu.uniquindio.service.DepartmentServiceImpl;
import co.edu.uniquindio.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DepartmentEntityTest {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @Test
    //@Sql
    public void createDepartmentTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        UserEntity userEntity = new UserEntity();
        RecordDepartmentDTO departmentDTO = new RecordDepartmentDTO(
                "Recursos Humanos"
        );

        // When - Acción o el comportamiento que se va a probar
        Department department = departmentService.createDepartment(departmentDTO);

        //Then - Verificar la salida
        System.out.println("department.toString() = " + department.toString());
        assertThat(department).isNotNull();
    }
}
