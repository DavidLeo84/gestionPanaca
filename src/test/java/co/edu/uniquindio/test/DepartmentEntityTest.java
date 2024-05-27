package co.edu.uniquindio.test;

import co.edu.uniquindio.dto.DetailsDepartmentDTO;
import co.edu.uniquindio.dto.ItemDepartmentDTO;
import co.edu.uniquindio.dto.RecordDepartmentDTO;
import co.edu.uniquindio.dto.UpdateDepartmentDTO;
import co.edu.uniquindio.model.Department;
import co.edu.uniquindio.model.UserEntity;
import co.edu.uniquindio.service.DepartmentServiceImpl;
import co.edu.uniquindio.validations.DepartmentValidation;
import co.edu.uniquindio.validations.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DepartmentEntityTest {

    @Autowired
    private DepartmentServiceImpl departmentService;
    @Autowired
    private DepartmentValidation departmentValidation;

    //@Sql
    @Test
    @DisplayName("Test para crear un registro de un departamento")
    public void createDepartmentTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        UserEntity userEntity = new UserEntity();
        RecordDepartmentDTO departmentDTO = new RecordDepartmentDTO(
                "VENTAS"
        );

        // When - Acción o el comportamiento que se va a probar
        Department department = departmentService.createDepartment(departmentDTO);

        //Then - Verificar la salida
        System.out.println("department.toString() = " + department.toString());
        assertThat(department).isNotNull();
    }

    //@Sql
    @Test
    @DisplayName("Test para modificar un registro de un departamento")
    public void updateDepartmentTest() throws Exception {

        // Given - Dado o condicion previa o configuración
        Department department = departmentValidation.findDepartment(1);
        UpdateDepartmentDTO departmentDTO = new UpdateDepartmentDTO(
                department.getId(),
                "RR-HH"
        );
        // When - Acción o el comportamiento que se va a probar
        Department updated = departmentService.updateDepartment(departmentDTO);

        //Then - Verificar la salida
        Department finder = departmentValidation.findDepartment(1);
        assertThat(finder.getDepartmentName()).isEqualTo("RR-HH");
    }

    //@Sql
    @Test
    @DisplayName("Test para obtener un registro de un departamento")
    public void getDepartmentTest() throws Exception {

        // Given - Dado o condicion previa o configuración

        // When - Acción o el comportamiento que se va a probar
        DetailsDepartmentDTO departmentDTO = departmentService.getDepartment(1);

        //Then - Verificar la salida
        System.out.println("departmentDTO = " + departmentDTO.toString());
        assertThat(departmentDTO).isNotNull();

    }

    //@Sql
    @Test
    @DisplayName("Test para obtener un listado de los registros de los departamentos")
    public void getListDepartmentTest() throws Exception {

        // When - Acción o el comportamiento que se va a probar
        Set<ItemDepartmentDTO> lista = departmentService.getListDepartment();

        //Then - Verificar la salida
        assertThat(lista.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("Test para eliminar el registro de un departamento")
    public void deleteDepartmentTest() throws Exception {

        // When - Acción o el comportamiento que se va a probar
        departmentService.deleteDepartment(1);

        //Then - Verificar la salida
        assertThrows(ResourceNotFoundException.class, () -> departmentValidation.findDepartment(1));
    }
}
