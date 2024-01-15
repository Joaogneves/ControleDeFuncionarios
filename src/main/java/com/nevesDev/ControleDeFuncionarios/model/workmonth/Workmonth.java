package com.nevesDev.ControleDeFuncionarios.model.workmonth;

import com.nevesDev.ControleDeFuncionarios.model.employee.Employee;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_workmonth")
public class Workmonth {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String month;

    long normalHour;
    long extraHour50;
    long extraHour100;
    long normalMinutes;
    long extraMinutes50;
    long extraMinutes100;

    @ManyToOne
    private Employee employee;

    public Workmonth() {}

    public Workmonth(UUID id, String month, long normalHour, long extraHour50, long extraHour100, long normalMinutes, long extraMinutes50, long extraMinutes100, Employee employee) {
        this.id = id;
        this.month = month;
        this.normalHour = normalHour;
        this.extraHour50 = extraHour50;
        this.extraHour100 = extraHour100;
        this.normalMinutes = normalMinutes;
        this.extraMinutes50 = extraMinutes50;
        this.extraMinutes100 = extraMinutes100;
        this.employee = employee;
    }

    public Workmonth(String month, long normalHour, long extraHour50, long extraHour100, long normalMinutes, long extraMinutes50, long extraMinutes100, Employee employee) {
        this.month = month;
        this.normalHour = normalHour;
        this.extraHour50 = extraHour50;
        this.extraHour100 = extraHour100;
        this.normalMinutes = normalMinutes;
        this.extraMinutes50 = extraMinutes50;
        this.extraMinutes100 = extraMinutes100;
        this.employee = employee;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public long getNormalHour() {
        return normalHour;
    }

    public void setNormalHour(long normalHour) {
        this.normalHour = normalHour;
    }

    public long getExtraHour50() {
        return extraHour50;
    }

    public void setExtraHour50(long extraHour50) {
        this.extraHour50 = extraHour50;
    }

    public long getExtraHour100() {
        return extraHour100;
    }

    public void setExtraHour100(long extraHour100) {
        this.extraHour100 = extraHour100;
    }

    public long getNormalMinutes() {
        return normalMinutes;
    }

    public void setNormalMinutes(long normalMinutes) {
        this.normalMinutes = normalMinutes;
    }

    public long getExtraMinutes50() {
        return extraMinutes50;
    }

    public void setExtraMinutes50(long extraMinutes50) {
        this.extraMinutes50 = extraMinutes50;
    }

    public long getExtraMinutes100() {
        return extraMinutes100;
    }

    public void setExtraMinutes100(long extraMinutes100) {
        this.extraMinutes100 = extraMinutes100;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workmonth workmonth = (Workmonth) o;
        return Objects.equals(id, workmonth.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
