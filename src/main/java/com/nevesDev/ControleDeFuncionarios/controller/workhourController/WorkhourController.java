package com.nevesDev.ControleDeFuncionarios.controller.workhourController;

import com.nevesDev.ControleDeFuncionarios.model.workhour.Workhour;
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
public class WorkhourController {

    @Autowired
    private WorkhourService service;

    @PostMapping
    public ResponseEntity<Workhour> save(@RequestBody Workhour workhour, @RequestParam UUID employeeId) {
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



}
