package com.aiep.dundermifflin.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A InformacionContactoEmpleado.
 */
@Entity
@Table(name = "informacion_contacto_empleado")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InformacionContactoEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "telefono")
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    private Empleado empleado;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InformacionContactoEmpleado id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public InformacionContactoEmpleado telefono(String telefono) {
        this.setTelefono(telefono);
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public InformacionContactoEmpleado empleado(Empleado empleado) {
        this.setEmpleado(empleado);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InformacionContactoEmpleado)) {
            return false;
        }
        return getId() != null && getId().equals(((InformacionContactoEmpleado) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InformacionContactoEmpleado{" +
            "id=" + getId() +
            ", telefono='" + getTelefono() + "'" +
            "}";
    }
}
