package com.aiep.dundermifflin.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A DepartamentosJefes.
 */
@Entity
@Table(name = "departamentos_jefes")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DepartamentosJefes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    private Jefes jefe;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DepartamentosJefes id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public DepartamentosJefes departamento(Departamento departamento) {
        this.setDepartamento(departamento);
        return this;
    }

    public Jefes getJefe() {
        return this.jefe;
    }

    public void setJefe(Jefes jefes) {
        this.jefe = jefes;
    }

    public DepartamentosJefes jefe(Jefes jefes) {
        this.setJefe(jefes);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepartamentosJefes)) {
            return false;
        }
        return getId() != null && getId().equals(((DepartamentosJefes) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepartamentosJefes{" +
            "id=" + getId() +
            "}";
    }
}
