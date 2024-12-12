package com.aiep.dundermifflin.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.aiep.dundermifflin.domain.Presupuesto} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PresupuestoDTO implements Serializable {

    private Long id;

    private BigDecimal presupuestoDepartamento;

    private DepartamentoDTO departamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPresupuestoDepartamento() {
        return presupuestoDepartamento;
    }

    public void setPresupuestoDepartamento(BigDecimal presupuestoDepartamento) {
        this.presupuestoDepartamento = presupuestoDepartamento;
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
        if (!(o instanceof PresupuestoDTO)) {
            return false;
        }

        PresupuestoDTO presupuestoDTO = (PresupuestoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, presupuestoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PresupuestoDTO{" +
            "id=" + getId() +
            ", presupuestoDepartamento=" + getPresupuestoDepartamento() +
            ", departamento=" + getDepartamento() +
            "}";
    }
}
