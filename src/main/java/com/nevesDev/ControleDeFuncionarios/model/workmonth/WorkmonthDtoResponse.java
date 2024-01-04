package com.nevesDev.ControleDeFuncionarios.model.workmonth;

import com.nevesDev.ControleDeFuncionarios.model.employee.EmployeeDtoResponse;

public record WorkmonthDtoResponse(long horasNormais, long horasExtras50, long horasExtras100, EmployeeDtoResponse dtoResponse) {
}
