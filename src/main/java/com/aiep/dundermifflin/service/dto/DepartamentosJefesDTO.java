package com.aiep.dundermifflin.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.aiep.dundermifflin.domain.DepartamentosJefes} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DepartamentosJefesDTO implements Serializable {

    private Long id;

    private DepartamentoDTO departamento;

    private JefesDTO jefe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
    }

    public JefesDTO getJefe() {
        return jefe;
    }

    public void setJefe(JefesDTO jefe) {
        this.jefe = jefe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepartamentosJefesDTO)) {
            return false;
        }

        DepartamentosJefesDTO departamentosJefesDTO = (DepartamentosJefesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, departamentosJefesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepartamentosJefesDTO{" +
            "id=" + getId() +
            ", departamento=" + getDepartamento() +
            ", jefe=" + getJefe() +
            "}";
    }
}
