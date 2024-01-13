package com.nevesDev.ControleDeFuncionarios.controller.workhourController;

import com.nevesDev.ControleDeFuncionarios.model.employee.Employee;
import com.nevesDev.ControleDeFuncionarios.model.workhour.Workhour;
import com.nevesDev.ControleDeFuncionarios.model.workhour.WorkhourDto;
import com.nevesDev.ControleDeFuncionarios.model.workmonth.Workmonth;
import com.nevesDev.ControleDeFuncionarios.service.workhourService.WorkhourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/workhour")
@CrossOrigin("*")
public class WorkhourController {

    @Autowired
    private WorkhourService service;

    @PostMapping
    public ResponseEntity<Workhour> save(@RequestBody Workhour workhour, @RequestParam UUID employeeId) {
        System.out.println(workhour);
        service.save(workhour, employeeId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(workhour.getId()).toUri();
        return ResponseEntity.created(uri).body(workhour);
    }

    @GetMapping
    public ResponseEntity<List<Workhour>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Workhour> getAll(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @GetMapping(value = "employee/{id}")
    public ResponseEntity<List<Workhour>> getAllByEmployee(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.getAllByEmployee(id));
    }

    @GetMapping(value = "endmonth/{id}")
    public ResponseEntity<Workmonth> endMonth(@PathVariable UUID id, @RequestParam String month) {
        return ResponseEntity.ok().body(service.getHours(id, month));
    }

    @GetMapping(value = "endmonth")
    public ResponseEntity<List<Workmonth>> endMonthForAll(@RequestParam String month) {
        return ResponseEntity.ok().body(service.getAllHours(month));
    }


    @PutMapping
    public ResponseEntity<Workhour> update(@RequestBody WorkhourDto dto) {
        return ResponseEntity.ok().body(service.update(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Workhour> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }

}
