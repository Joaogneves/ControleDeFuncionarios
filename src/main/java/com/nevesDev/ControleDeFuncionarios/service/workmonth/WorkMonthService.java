package com.nevesDev.ControleDeFuncionarios.service.workmonth;

import com.nevesDev.ControleDeFuncionarios.model.workmonth.Workmonth;
import com.nevesDev.ControleDeFuncionarios.repository.workmonthRepository.WorkmonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkMonthService {

    @Autowired
    private WorkmonthRepository repository;

    public List<Workmonth> findAllByEmployee(UUID id) {
        return repository.findAllByEmployeeId(id);
    }

    public Workmonth findById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }


}
