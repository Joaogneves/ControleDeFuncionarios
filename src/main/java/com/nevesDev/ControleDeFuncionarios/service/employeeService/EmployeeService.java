package com.nevesDev.ControleDeFuncionarios.service.employeeService;

import com.nevesDev.ControleDeFuncionarios.model.employee.Employee;
import com.nevesDev.ControleDeFuncionarios.model.employee.EmployeeDto;
import com.nevesDev.ControleDeFuncionarios.repository.employeeRepository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        employee.setWorkPlace(dto.workPlace());
        repository.save(employee);
        return employee;
    }
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public List<Employee> getByWorkplace(String workplace) {
        List<Employee> employees = repository.findAll();
        List<Employee> employeesWorkplaces = new ArrayList<>();
        for(Employee e : employees) {
            if(e.getWorkPlace().toLowerCase().contains(workplace.toLowerCase())) {
                employeesWorkplaces.add(e);
            }
        }
        return employeesWorkplaces;
    }
}
