package com.nevesDev.ControleDeFuncionarios.model.workhour;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record WorkhourDto(UUID id, LocalDate workDay, LocalTime entry, LocalTime leave, LocalTime breakInit, LocalTime breakEnd, LocalTime startExtra, LocalTime endExtra, Boolean itsHoliday) {
}
