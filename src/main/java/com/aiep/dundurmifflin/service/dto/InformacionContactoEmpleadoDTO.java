package com.aiep.dundurmifflin.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.aiep.dundurmifflin.domain.InformacionContactoEmpleado} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InformacionContactoEmpleadoDTO implements Serializable {

    private Long id;

    @NotNull
    private String telefono;

    private EmpleadoDTO empleado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InformacionContactoEmpleadoDTO)) {
            return false;
        }

        InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO = (InformacionContactoEmpleadoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, informacionContactoEmpleadoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InformacionContactoEmpleadoDTO{" +
            "id=" + getId() +
            ", telefono='" + getTelefono() + "'" +
            ", empleado=" + getEmpleado() +
            "}";
    }
}
