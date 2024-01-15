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
        for (Workhour h : hours) {
            if (h.getWorkDay().getMonth().toString().equals(month)) {
                if (getStatus(h, "HORAEXTRANORMAL")) {
                    normalHour += ChronoUnit.MINUTES.between(h.getEntry(), h.getBreakInit());
                    normalHour += ChronoUnit.MINUTES.between(h.getBreakEnd(), h.getLeave());
                    hour50percent += ChronoUnit.MINUTES.between(h.getStartExtra(), h.getEndExtra());
                }
                if (getStatus(h, "NORMAL")) {
                    normalHour += ChronoUnit.MINUTES.between(h.getEntry(), h.getBreakInit());
                    normalHour += ChronoUnit.MINUTES.between(h.getBreakEnd(), h.getLeave());
                }
                if (getStatus(h, "HORAEXTRA100")) {
                    hour100percent += ChronoUnit.MINUTES.between(h.getEntry(), h.getBreakInit());
                    hour100percent += ChronoUnit.MINUTES.between(h.getBreakEnd(), h.getLeave());
                }
                if (getStatus(h, "SABADO50")) {
                    hour50percent += ChronoUnit.MINUTES.between(h.getEntry(), h.getBreakInit());
                    hour50percent += ChronoUnit.MINUTES.between(h.getBreakEnd(), h.getLeave());
                }
                if (getStatus(h, "FALTA")) {
                    normalHour += ChronoUnit.MINUTES.between(h.getEntry(), h.getBreakInit());
                    normalHour += ChronoUnit.MINUTES.between(h.getBreakEnd(), h.getLeave());
                }
                if (getStatus(h, "SABADO")) {
                    normalHour += ChronoUnit.MINUTES.between(h.getEntry(), h.getBreakInit());
                    normalHour += ChronoUnit.MINUTES.between(h.getBreakEnd(), h.getLeave());
                }
                if (getStatus(h, "DOMINGO")) {
                    normalHour += ChronoUnit.MINUTES.between(h.getEntry(), h.getBreakInit());
                    normalHour += ChronoUnit.MINUTES.between(h.getBreakEnd(), h.getLeave());
                }
                if (getStatus(h, "FERIADO")) {
                    normalHour += ChronoUnit.MINUTES.between(h.getEntry(), h.getBreakInit());
                    normalHour += ChronoUnit.MINUTES.between(h.getBreakEnd(), h.getLeave());
                }
                if (getStatus(h, "FOLGA")) {
                    normalHour += ChronoUnit.MINUTES.between(h.getEntry(), h.getBreakInit());
                    normalHour += ChronoUnit.MINUTES.between(h.getBreakEnd(), h.getLeave());
                }
            }
        }
        Employee employee = service.getById(id);
        String monthPt = translateMonth(month);
        Workmonth workmonth = new Workmonth(monthPt, normalHour / 60, hour50percent / 60, hour100percent / 60, normalHour % 60, hour50percent % 60, hour100percent % 60, employee);
        workmonthRepository.save(workmonth);
        return workmonth;
    }

    public List<Workmonth> getAllHours(String month) {
        List<Employee> employees = service.getAll();
        List<Workmonth> workmonths = new ArrayList<>();
        for (Employee e : employees) {
            Workmonth workmonth = getHours(e.getId(), month);
            workmonths.add(workmonth);
            workmonthRepository.save(workmonth);
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
        workhour.setWorkhourStatus(dto.workhourStatus());
        repository.save(workhour);
        return workhour;
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private boolean getStatus(Workhour h, String status) {
        return h.getWorkhourStatus().toString().equals(status);
    }

    private String translateMonth(String month) {
        switch (month) {
            case "JANUARY": {
                return "Janeiro";
            }
            case "FEBRUARY": {
                return "Fevereiro";
            }
            case "MARCH": {
                return "Março";
            }
            case "APRIL": {
                return "Abril";
            }
            case "MAY": {
                return "Maio";
            }
            case "JUNE": {
                return "Junho";
            }
            case "JULY": {
                return "Julho";
            }
            case "AUGUST": {
                return "Agosto";
            }
            case "SEPTEMBER": {
                return "Setembro";
            }
            case "OCTOBER": {
                return "Outubro";
            }
            case "NOVEMBER": {
                return "Novembro";
            }
            case "DECEMBER": {
                return "Dezembro";
            }
        }
        return "";
    }
}
