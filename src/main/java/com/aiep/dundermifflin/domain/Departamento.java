package com.aiep.dundermifflin.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Departamento.
 */
@Entity
@Table(name = "departamento")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_departamento")
    private String nombreDepartamento;

    @Column(name = "ubicacion_departamento")
    private String ubicacionDepartamento;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Departamento id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreDepartamento() {
        return this.nombreDepartamento;
    }

    public Departamento nombreDepartamento(String nombreDepartamento) {
        this.setNombreDepartamento(nombreDepartamento);
        return this;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getUbicacionDepartamento() {
        return this.ubicacionDepartamento;
    }

    public Departamento ubicacionDepartamento(String ubicacionDepartamento) {
        this.setUbicacionDepartamento(ubicacionDepartamento);
        return this;
    }

    public void setUbicacionDepartamento(String ubicacionDepartamento) {
        this.ubicacionDepartamento = ubicacionDepartamento;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Departamento)) {
            return false;
        }
        return getId() != null && getId().equals(((Departamento) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Departamento{" +
            "id=" + getId() +
            ", nombreDepartamento='" + getNombreDepartamento() + "'" +
            ", ubicacionDepartamento='" + getUbicacionDepartamento() + "'" +
            "}";
    }
}
