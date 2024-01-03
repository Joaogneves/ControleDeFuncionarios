package com.nevesDev.ControleDeFuncionarios.service.employeeService;

import com.nevesDev.ControleDeFuncionarios.model.employee.Employee;
import com.nevesDev.ControleDeFuncionarios.model.employee.EmployeeDto;
import com.nevesDev.ControleDeFuncionarios.repository.employeeRepository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public void save(Employee employee) {
        repository.save(employee);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public Employee update(EmployeeDto dto) {
        Employee employee = repository.findById(dto.id()).orElseThrow();
        employee.setFirstName(dto.firstName());
        employee.setLastName(dto.lastName());
        employee.setCpf(dto.cpf());
        employee.setCnpj(dto.cnpj());
        employee.setFuncao(dto.funcao());
        repository.save(employee);
        return employee;
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
