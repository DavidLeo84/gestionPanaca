package co.edu.uniquindio.controllers;


import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.model.PayRollNew;
import co.edu.uniquindio.service.PayRollNewServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payRolls")
@SecurityRequirement(name = "bearerAuth")
public class PayRollNewController {

    private final PayRollNewServiceImpl payRollNewService;

    @PostMapping("/create-payroll")
    public ResponseEntity<MensajeDTO<String>> createPayRollNew(@Valid @RequestBody
                                                               RecordPayRollDTO payRollDTO) throws Exception {
        PayRollNew payRollNew = payRollNewService.createPayRollNew(payRollDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se creo satisfactoriamente la novedad"));
    }

    @PutMapping("/update-payroll")
    public ResponseEntity<MensajeDTO<String>> updatePayRollNew(@Valid @RequestBody
                                                               UpdatePayRollDTO payRollDTO) throws Exception {
        PayRollNew payRollNew = payRollNewService.updatePayRollNew(payRollDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se modificó satisfactoriamente la novedad"));
    }

    @GetMapping("/get-payroll/{id}")
    public ResponseEntity<MensajeDTO<DetailPayRollNewDTO>> getPayRollNew(@PathVariable int id) throws Exception {

        DetailPayRollNewDTO payRollNewDTO = payRollNewService.getPayRollNew(id);
        return ResponseEntity.ok().body(new MensajeDTO<>(true, payRollNewDTO));
    }

    @GetMapping("/get-list-payrolls/{idDepartment}")
    public ResponseEntity<MensajeDTO<List<ItemPayRollDTO>>> listPayRollNewsForDepartment(@PathVariable
                                                                                         int idDepartment) throws Exception {
        List<ItemPayRollDTO> listPayRoll = payRollNewService.listPayRollNewsForDepartment(idDepartment);
        return ResponseEntity.ok().body(new MensajeDTO<>(true, listPayRoll));
    }

    @GetMapping("/get-list-payrolls-dates/{dateStart}/{dateFinish}")
    public ResponseEntity<MensajeDTO<List<ItemPayRollDTO>>> listPayRollNewsForDates(@PathVariable LocalDateTime dateStart,
                                                                                    @PathVariable LocalDateTime dateFinish) throws Exception {
        List<ItemPayRollDTO> listPayrolls = payRollNewService.listPayRollNewsForDates(dateStart, dateFinish);
        return ResponseEntity.ok().body(new MensajeDTO<>(true, listPayrolls));
    }

    @GetMapping("/get-list-payrolls-employee/{dniEmployee}")
    public ResponseEntity<MensajeDTO<List<ItemPayRollDTO>>> listPayRollNewsForEmployee(@PathVariable String dniEmployee) throws Exception {

        List<ItemPayRollDTO> listPayrolls = payRollNewService.listPayRollNewsForEmployee(dniEmployee);
        return ResponseEntity.ok().body(new MensajeDTO<>(true, listPayrolls));
    }
}
