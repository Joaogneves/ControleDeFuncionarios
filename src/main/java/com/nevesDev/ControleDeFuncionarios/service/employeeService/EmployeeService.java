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

    public List<Employee> getByWorkplace(String args) {
        List<Employee> employees = repository.findAll();
        List<Employee> employeesWorkplaces = new ArrayList<>();
        for(Employee e : employees) {
            if(checkEmployee(e, args)) {
                employeesWorkplaces.add(e);
            }
        }
        return employeesWorkplaces;
    }

    private boolean checkEmployee(Employee e, String args) {
        return e.getWorkPlace().toLowerCase().contains(args.toLowerCase()) || e.getFirstName().toLowerCase().contains(args.toLowerCase()) || e.getLastName().toLowerCase().contains(args.toLowerCase()) || e.getCpf().toLowerCase().contains(args.toLowerCase());
    }
}
