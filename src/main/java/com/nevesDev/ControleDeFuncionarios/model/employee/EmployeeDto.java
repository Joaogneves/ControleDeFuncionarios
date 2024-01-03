package com.nevesDev.ControleDeFuncionarios.model.employee;

import java.util.UUID;

public record EmployeeDto(UUID id, String firstName, String lastName, String cpf, String cnpj, String funcao) {
}
