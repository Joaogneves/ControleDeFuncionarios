package com.nevesDev.ControleDeFuncionarios.repository.workhourRepository;

import com.nevesDev.ControleDeFuncionarios.model.workhour.Workhour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WorkhourRepository extends JpaRepository<Workhour, UUID> {
    List<Workhour> findAllByEmployeeId(UUID id);
}
