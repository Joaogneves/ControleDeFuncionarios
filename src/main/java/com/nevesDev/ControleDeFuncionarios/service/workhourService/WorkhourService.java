package com.nevesDev.ControleDeFuncionarios.service.workhourService;

import com.nevesDev.ControleDeFuncionarios.model.employee.Employee;
import com.nevesDev.ControleDeFuncionarios.model.workhour.Workhour;
import com.nevesDev.ControleDeFuncionarios.model.workmonth.Workmonth;
import com.nevesDev.ControleDeFuncionarios.repository.employeeRepository.EmployeeRepository;
import com.nevesDev.ControleDeFuncionarios.repository.workhourRepository.WorkhourRepository;
import com.nevesDev.ControleDeFuncionarios.repository.workmonthRepository.WorkmonthRepository;
import com.nevesDev.ControleDeFuncionarios.service.employeeService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class WorkhourService {

    @Autowired
    private WorkhourRepository repository;
    @Autowired
    private EmployeeService service;

    @Autowired
    private WorkmonthRepository workmonthRepository;

    public void save(Workhour workhour, UUID employeeId) {
        workhour.setEmployee(service.getById(employeeId));
        repository.save(workhour);
    }

    public List<Workhour> getAll() {
        return repository.findAll();
    }

    public Workhour getById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Workhour> getAllByEmployee(UUID id) {
        return repository.findAllByEmployeeId(id);
    }

    public Workmonth getHours(UUID id, String month) {
        long horasNormais = 0;
        long horasExtra100 = 0;
        long horasExtra50 = 0;
        List<Workhour> hours = repository.findAllByEmployeeId(id);
        for(Workhour h : hours) {
            if(h.getWorkDay().getMonth().toString().equals(month)) {
                if(!h.getItsHolliday()) {
                    horasNormais += ChronoUnit.HOURS.between(h.getEntry(), h.getBreakInit());
                    horasNormais += ChronoUnit.HOURS.between(h.getBreakEnd(), h.getLeave());
                    if(h.getStartExtra() != null && h.getEndExtra() != null) {
                        horasExtra50 += ChronoUnit.HOURS.between(h.getStartExtra(), h.getEndExtra());
                    }
                }
                if(h.getItsHolliday()) {
                    horasExtra100 += ChronoUnit.HOURS.between(h.getEntry(), h.getBreakInit());
                    horasExtra100 += ChronoUnit.HOURS.between(h.getBreakEnd(), h.getLeave());
                }
            }
        }
        Employee employee = service.getById(id);
        Workmonth workmonth = new Workmonth(horasNormais, horasExtra50, horasExtra100, employee);
        workmonthRepository.save(workmonth);
        return workmonth;
    }
}
