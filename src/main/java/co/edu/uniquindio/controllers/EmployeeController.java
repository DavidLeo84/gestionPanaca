package co.edu.uniquindio.controllers;


import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.model.EmployeeEntity;
import co.edu.uniquindio.service.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @PostMapping("/create-employee")
    public ResponseEntity<MensajeDTO<String>> createEmployee(@Valid @RequestBody
                                                             RecordEmployeeDTO employeeDTO) throws Exception {
        EmployeeEntity employeeEntity = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se ha creado el empleado con exito"));
    }

    @PutMapping("/update-employee")
    public ResponseEntity<MensajeDTO<String>> updateEmployee(@Valid @RequestBody
                                                             UpdateEmployeeDTO employeeDTO) throws Exception {
        EmployeeEntity employeeEntity = employeeService.updateEmployee(employeeDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se ha modificado el empleado con exito"));
    }

    @DeleteMapping("/delete-employee/{dni}")
    public ResponseEntity<MensajeDTO<String>> deleteEmployee(@PathVariable String dni) throws Exception {

        employeeService.deleteEmployee(dni);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se ha eliminado el empleado con exito"));
    }

    @GetMapping("/get-list-employees-dpt/{departmentName}")
    public ResponseEntity<MensajeDTO<List<ItemEmployeeDTO>>> employeesListDepartment(@PathVariable
                                                                                     String departmentName) throws Exception {
        List<ItemEmployeeDTO> listEmployees = employeeService.employeesListDepartment(departmentName);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, listEmployees));
    }

    @GetMapping("/get-employee/{dni}")
    public ResponseEntity<MensajeDTO<DetailsEmployeeDTO>> getEmployee(@PathVariable String dni) throws Exception {

        DetailsEmployeeDTO employeeDTO = employeeService.getEmployee(dni);
        return ResponseEntity.ok().body(new MensajeDTO<>(true, employeeDTO));
    }
}
