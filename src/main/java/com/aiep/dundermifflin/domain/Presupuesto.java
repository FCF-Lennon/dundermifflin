package com.aiep.dundermifflin.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Presupuesto.
 */
@Entity
@Table(name = "presupuesto")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Presupuesto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "presupuesto_departamento", precision = 21, scale = 2)
    private BigDecimal presupuestoDepartamento;

    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento departamento;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Presupuesto id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPresupuestoDepartamento() {
        return this.presupuestoDepartamento;
    }

    public Presupuesto presupuestoDepartamento(BigDecimal presupuestoDepartamento) {
        this.setPresupuestoDepartamento(presupuestoDepartamento);
        return this;
    }

    public void setPresupuestoDepartamento(BigDecimal presupuestoDepartamento) {
        this.presupuestoDepartamento = presupuestoDepartamento;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Presupuesto departamento(Departamento departamento) {
        this.setDepartamento(departamento);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Presupuesto)) {
            return false;
        }
        return getId() != null && getId().equals(((Presupuesto) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Presupuesto{" +
            "id=" + getId() +
            ", presupuestoDepartamento=" + getPresupuestoDepartamento() +
            "}";
    }
}
