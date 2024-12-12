package com.aiep.dundermifflin.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.aiep.dundermifflin.domain.EmpleadoDepartamento} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EmpleadoDepartamentoDTO implements Serializable {

    private Long id;

    private LocalDate fechaAsignacion;

    private EmpleadoDTO empleado;

    private DepartamentoDTO departamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmpleadoDepartamentoDTO)) {
            return false;
        }

        EmpleadoDepartamentoDTO empleadoDepartamentoDTO = (EmpleadoDepartamentoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, empleadoDepartamentoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmpleadoDepartamentoDTO{" +
            "id=" + getId() +
            ", fechaAsignacion='" + getFechaAsignacion() + "'" +
            ", empleado=" + getEmpleado() +
            ", departamento=" + getDepartamento() +
            "}";
    }
}
