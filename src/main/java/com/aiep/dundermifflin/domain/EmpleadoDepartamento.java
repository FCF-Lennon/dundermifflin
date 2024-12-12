package com.aiep.dundermifflin.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A EmpleadoDepartamento.
 */
@Entity
@Table(name = "empleado_departamento")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EmpleadoDepartamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion;

    @ManyToOne(fetch = FetchType.LAZY)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento departamento;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EmpleadoDepartamento id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaAsignacion() {
        return this.fechaAsignacion;
    }

    public EmpleadoDepartamento fechaAsignacion(LocalDate fechaAsignacion) {
        this.setFechaAsignacion(fechaAsignacion);
        return this;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadoDepartamento empleado(Empleado empleado) {
        this.setEmpleado(empleado);
        return this;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public EmpleadoDepartamento departamento(Departamento departamento) {
        this.setDepartamento(departamento);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmpleadoDepartamento)) {
            return false;
        }
        return getId() != null && getId().equals(((EmpleadoDepartamento) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmpleadoDepartamento{" +
            "id=" + getId() +
            ", fechaAsignacion='" + getFechaAsignacion() + "'" +
            "}";
    }
}
