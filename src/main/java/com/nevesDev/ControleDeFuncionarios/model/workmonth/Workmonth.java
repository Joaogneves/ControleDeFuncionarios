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

    long normalHour;
    long extraHour50;
    long extraHour100;

    @ManyToOne
    private Employee employee;

    public Workmonth() {}

    public Workmonth(long normalHour, long extraHour50, long extraHour100, Employee employee) {
        this.normalHour = normalHour;
        this.extraHour50 = extraHour50;
        this.extraHour100 = extraHour100;
        this.employee = employee;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
