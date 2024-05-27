package co.edu.uniquindio.controllers;


import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.model.Department;
import co.edu.uniquindio.service.DepartmentServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
@SecurityRequirement(name = "bearerAuth")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    @PostMapping("/create-dpt")
    public ResponseEntity<MensajeDTO<String>> createDepartment(@Valid @RequestBody
                                                               RecordDepartmentDTO departmentDTO) throws Exception {

        Department department = departmentService.createDepartment(departmentDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,
                "Se ha creado satisfactoriamente el departamento " + departmentDTO.departmentName()));
    }

    @PutMapping("update-dpt")
    public ResponseEntity<MensajeDTO<String>> updateDepartment(@Valid @RequestBody
                                                               UpdateDepartmentDTO departmentDTO) throws Exception {
        Department department = departmentService.updateDepartment(departmentDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se ha modificado el departamento " + departmentDTO.departmentName()));
    }

    @DeleteMapping("/delete-dpt/{id}")
    public ResponseEntity<MensajeDTO<String>> deleteDepartment(@PathVariable int id) throws Exception {

        departmentService.deleteDepartment(id);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se ha borrado el departamento " + id));
    }

    @GetMapping("/get-dpt/{id}")
    public ResponseEntity<MensajeDTO<DetailsDepartmentDTO>> getDepartment(@PathVariable int id) throws Exception {

        DetailsDepartmentDTO departmentDTO = departmentService.getDepartment(id);
        return ResponseEntity.ok().body(new MensajeDTO<>(true, departmentDTO));
    }

    @GetMapping("/get-list-dpt")
    public ResponseEntity<MensajeDTO<Set<ItemDepartmentDTO>>> getListDepartment() throws Exception {

        Set<ItemDepartmentDTO> departmentDTOList = departmentService.getListDepartment();
        return ResponseEntity.ok().body(new MensajeDTO<>(true, departmentDTOList));
    }
}
