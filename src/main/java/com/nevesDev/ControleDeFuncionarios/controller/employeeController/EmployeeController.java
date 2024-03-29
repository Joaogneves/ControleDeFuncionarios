package com.nevesDev.ControleDeFuncionarios.controller.employeeController;

import com.nevesDev.ControleDeFuncionarios.model.employee.Employee;
import com.nevesDev.ControleDeFuncionarios.model.employee.EmployeeDto;
import com.nevesDev.ControleDeFuncionarios.repository.employeeRepository.EmployeeRepository;
import com.nevesDev.ControleDeFuncionarios.service.employeeService.EmployeeService;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        service.save(employee);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.created(uri).body(employee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> getById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @PutMapping()
    public ResponseEntity<Employee> update(@RequestBody EmployeeDto dto) {
        return ResponseEntity.ok().body(service.update(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Employee> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Employee>> getAllByWorkplace(@RequestParam String workplace) {
        System.out.println(workplace);
        return ResponseEntity.ok().body(service.getByWorkplace(workplace));
    }
}
