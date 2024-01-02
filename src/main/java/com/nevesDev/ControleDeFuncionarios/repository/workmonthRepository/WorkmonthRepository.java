package com.nevesDev.ControleDeFuncionarios.repository.workmonthRepository;

import com.nevesDev.ControleDeFuncionarios.model.workmonth.Workmonth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkmonthRepository extends JpaRepository<Workmonth, UUID> {
}
