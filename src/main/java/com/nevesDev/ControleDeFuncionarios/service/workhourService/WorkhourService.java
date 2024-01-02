package com.nevesDev.ControleDeFuncionarios.service.workhourService;

import com.nevesDev.ControleDeFuncionarios.model.employee.Employee;
import com.nevesDev.ControleDeFuncionarios.model.workhour.Workhour;
import com.nevesDev.ControleDeFuncionarios.repository.employeeRepository.EmployeeRepository;
import com.nevesDev.ControleDeFuncionarios.repository.workhourRepository.WorkhourRepository;
import com.nevesDev.ControleDeFuncionarios.service.employeeService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkhourService {

    @Autowired
    private WorkhourRepository repository;
    @Autowired
    private EmployeeService service;

    public void save(Workhour workhour, UUID employeeId) {
        workhour.setEmployee(service.getById(employeeId));
        repository.save(workhour);
    }

    public List<Workhour> getAll() {
        return repository.findAll();
    }

    public Workhour getById(UUID id) {
        return repository.findById(id).orElseThrow();
    }
}
