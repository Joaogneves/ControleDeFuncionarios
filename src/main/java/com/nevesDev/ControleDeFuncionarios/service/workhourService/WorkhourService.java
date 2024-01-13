package com.nevesDev.ControleDeFuncionarios.service.workhourService;

import com.nevesDev.ControleDeFuncionarios.model.employee.Employee;
import com.nevesDev.ControleDeFuncionarios.model.workhour.Workhour;
import com.nevesDev.ControleDeFuncionarios.model.workhour.WorkhourDto;
import com.nevesDev.ControleDeFuncionarios.model.workmonth.Workmonth;
import com.nevesDev.ControleDeFuncionarios.repository.workhourRepository.WorkhourRepository;
import com.nevesDev.ControleDeFuncionarios.repository.workmonthRepository.WorkmonthRepository;
import com.nevesDev.ControleDeFuncionarios.service.employeeService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
        long normalHour = 0;
        long hour100percent = 0;
        long hour50percent = 0;
        List<Workhour> hours = repository.findAllByEmployeeId(id);
        for(Workhour h : hours) {
            /*
            if(h.getWorkDay().getMonth().toString().equals(month)) {
                if(!h.getIsHoliday()) {
                    normalHour += ChronoUnit.MINUTES.between(h.getEntry(), h.getBreakInit());
                    normalHour += ChronoUnit.MINUTES.between(h.getBreakEnd(), h.getLeave());
                    if(h.getStartExtra() != null && h.getEndExtra() != null) {
                        hour50percent += ChronoUnit.MINUTES.between(h.getStartExtra(), h.getEndExtra());
                    }
                }
                if(h.getIsHoliday()) {
                    hour100percent += ChronoUnit.MINUTES.between(h.getEntry(), h.getBreakInit());
                    hour100percent += ChronoUnit.MINUTES.between(h.getBreakEnd(), h.getLeave());
                }
            } */
        }
        Employee employee = service.getById(id);
        Workmonth workmonth = new Workmonth(normalHour, hour50percent, hour100percent, employee);
        //workmonthRepository.save(workmonth);
        return workmonth;
    }

    public List<Workmonth> getAllHours(String month) {
        List<Employee> employees = service.getAll();
        List<Workmonth> workmonths = new ArrayList<>();
        for(Employee e : employees) {
            long normalHour = 0;
            long hour100percent = 0;
            long hour50percent = 0;
            List<Workhour> hours = repository.findAllByEmployeeId(e.getId());
            for(Workhour h : hours) {
                if(h.getWorkDay().getMonth().toString().equals(month)) {
                    /*
                    if(!h.getIsHoliday()) {
                        normalHour += ChronoUnit.MINUTES.between(h.getEntry(), h.getBreakInit());
                        normalHour += ChronoUnit.MINUTES.between(h.getBreakEnd(), h.getLeave());
                        if(h.getStartExtra() != null && h.getEndExtra() != null) {
                            hour50percent += ChronoUnit.MINUTES.between(h.getStartExtra(), h.getEndExtra());
                        }
                    }
                    if(h.getIsHoliday()) {
                        hour100percent += ChronoUnit.MINUTES.between(h.getEntry(), h.getBreakInit());
                        hour100percent += ChronoUnit.MINUTES.between(h.getBreakEnd(), h.getLeave());
                    } */
                }
            }
            Workmonth workmonth = new Workmonth(normalHour, hour50percent, hour100percent, e);
            workmonths.add(workmonth);
            //workmonthRepository.save(workmonth);
        }
        return workmonths;
    }

    public Workhour update(WorkhourDto dto) {
        Workhour workhour = repository.findById(dto.id()).orElseThrow();
        workhour.setWorkDay(dto.workDay());
        workhour.setEntry(dto.entry());
        workhour.setLeave(dto.leave());
        workhour.setBreakInit(dto.breakInit());
        workhour.setBreakEnd(dto.breakEnd());
        workhour.setStartExtra(dto.startExtra());
        workhour.setEndExtra(dto.endExtra());
        //workhour.setIsHoliday(dto.itsHoliday());
        return workhour;
    }
    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
