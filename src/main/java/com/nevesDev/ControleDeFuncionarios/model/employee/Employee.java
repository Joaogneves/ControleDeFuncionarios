package com.nevesDev.ControleDeFuncionarios.model.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nevesDev.ControleDeFuncionarios.model.workhour.Workhour;
import com.nevesDev.ControleDeFuncionarios.model.workmonth.Workmonth;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private String cnpj;
    @Column(nullable = false)
    private String funcao;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "employee", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Workhour> workhours;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "employee", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Workmonth> workmonths;

    public Employee(){}

    public Employee(UUID id, String firstName, String lastName, String cpf, String cnpj, String funcao, List<Workhour> workhours, List<Workmonth> workmonths) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.funcao = funcao;
        this.workhours = workhours;
        this.workmonths = workmonths;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public List<Workhour> getWorkhours() {
        return workhours;
    }

    public void setWorkhours(List<Workhour> workhours) {
        this.workhours = workhours;
    }

    public List<Workmonth> getWorkmonths() {
        return workmonths;
    }

    public void setWorkmonths(List<Workmonth> workmonths) {
        this.workmonths = workmonths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
