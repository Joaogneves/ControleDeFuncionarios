package com.nevesDev.ControleDeFuncionarios.controller.workmonthController;

import com.nevesDev.ControleDeFuncionarios.model.workmonth.Workmonth;
import com.nevesDev.ControleDeFuncionarios.model.workmonth.WorkmonthDtoResponse;
import com.nevesDev.ControleDeFuncionarios.service.workmonth.WorkMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/workmonth")
public class WorkmonthController {

    @Autowired
    private WorkMonthService service;

    @GetMapping
    public ResponseEntity<List<WorkmonthDtoResponse>> findAllByEmployee(@RequestParam UUID employeeId) {
        return ResponseEntity.ok().body(service.findAllByEmployee(employeeId));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Workmonth> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }

}
