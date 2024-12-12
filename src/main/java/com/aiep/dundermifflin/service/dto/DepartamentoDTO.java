package com.aiep.dundermifflin.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.aiep.dundermifflin.domain.Departamento} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DepartamentoDTO implements Serializable {

    private Long id;

    private String nombreDepartamento;

    private String ubicacionDepartamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getUbicacionDepartamento() {
        return ubicacionDepartamento;
    }

    public void setUbicacionDepartamento(String ubicacionDepartamento) {
        this.ubicacionDepartamento = ubicacionDepartamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepartamentoDTO)) {
            return false;
        }

        DepartamentoDTO departamentoDTO = (DepartamentoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, departamentoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepartamentoDTO{" +
            "id=" + getId() +
            ", nombreDepartamento='" + getNombreDepartamento() + "'" +
            ", ubicacionDepartamento='" + getUbicacionDepartamento() + "'" +
            "}";
    }
}
