package com.aiep.dundermifflin.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.aiep.dundermifflin.domain.InformacionContactoEmpleados} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InformacionContactoEmpleadosDTO implements Serializable {

    private Long id;

    @Size(max = 15)
    private String telefono;

    @Size(max = 20)
    private String tipoFono;

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

    public String getTipoFono() {
        return tipoFono;
    }

    public void setTipoFono(String tipoFono) {
        this.tipoFono = tipoFono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InformacionContactoEmpleadosDTO)) {
            return false;
        }

        InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO = (InformacionContactoEmpleadosDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, informacionContactoEmpleadosDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InformacionContactoEmpleadosDTO{" +
            "id=" + getId() +
            ", telefono='" + getTelefono() + "'" +
            ", tipoFono='" + getTipoFono() + "'" +
            "}";
    }
}
