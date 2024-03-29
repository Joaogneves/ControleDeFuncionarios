package com.nevesDev.ControleDeFuncionarios.model.workhour;

import com.nevesDev.ControleDeFuncionarios.model.employee.Employee;
import com.nevesDev.ControleDeFuncionarios.model.workhour.enums.WorkhourStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_workhour")
public class Workhour {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate workDay;

    @Column(nullable = false)
    private LocalTime entry;
    @Column(nullable = false)
    private LocalTime leave;
    @Column(nullable = false)
    private LocalTime breakInit;
    @Column(nullable = false)
    private LocalTime breakEnd;
    private LocalTime startExtra;
    private LocalTime endExtra;

    @Enumerated(EnumType.STRING)
    private WorkhourStatus workhourStatus;
    @ManyToOne
    private Employee employee;

    public Workhour(){}

    public Workhour(UUID id, LocalDate workDay, LocalTime entry, LocalTime leave, LocalTime breakInit, LocalTime breakEnd, LocalTime startExtra, LocalTime endExtra, WorkhourStatus workhourStatus, Employee employee) {
        this.id = id;
        this.workDay = workDay;
        this.entry = entry;
        this.leave = leave;
        this.breakInit = breakInit;
        this.breakEnd = breakEnd;
        this.startExtra = startExtra;
        this.endExtra = endExtra;
        this.workhourStatus = workhourStatus;
        this.employee = employee;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getWorkDay() {
        return workDay;
    }

    public void setWorkDay(LocalDate workDay) {
        this.workDay = workDay;
    }

    public LocalTime getEntry() {
        return entry;
    }

    public void setEntry(LocalTime entry) {
        this.entry = entry;
    }

    public LocalTime getLeave() {
        return leave;
    }

    public void setLeave(LocalTime leave) {
        this.leave = leave;
    }

    public LocalTime getBreakInit() {
        return breakInit;
    }

    public void setBreakInit(LocalTime breakInit) {
        this.breakInit = breakInit;
    }

    public LocalTime getBreakEnd() {
        return breakEnd;
    }

    public void setBreakEnd(LocalTime breakEnd) {
        this.breakEnd = breakEnd;
    }

    public LocalTime getStartExtra() {
        return startExtra;
    }

    public void setStartExtra(LocalTime startExtra) {
        this.startExtra = startExtra;
    }

    public LocalTime getEndExtra() {
        return endExtra;
    }

    public void setEndExtra(LocalTime endExtra) {
        this.endExtra = endExtra;
    }

    public Employee getEmployee() {
        return employee;
    }

    public WorkhourStatus getWorkhourStatus() {
        return workhourStatus;
    }

    public void setWorkhourStatus(WorkhourStatus workhourStatus) {
        this.workhourStatus = workhourStatus;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workhour workhour = (Workhour) o;
        return Objects.equals(id, workhour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
