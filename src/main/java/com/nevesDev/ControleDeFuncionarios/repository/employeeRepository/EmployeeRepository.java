package com.nevesDev.ControleDeFuncionarios.repository.employeeRepository;

import com.nevesDev.ControleDeFuncionarios.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
