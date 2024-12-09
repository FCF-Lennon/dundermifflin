package com.aiep.dundermifflin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A InformacionContactoEmpleados.
 */
@Entity
@Table(name = "informacion_contacto_empleados")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InformacionContactoEmpleados implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 15)
    @Column(name = "telefono", length = 15)
    private String telefono;

    @Size(max = 20)
    @Column(name = "tipo_fono", length = 20)
    private String tipoFono;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InformacionContactoEmpleados id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public InformacionContactoEmpleados telefono(String telefono) {
        this.setTelefono(telefono);
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoFono() {
        return this.tipoFono;
    }

    public InformacionContactoEmpleados tipoFono(String tipoFono) {
        this.setTipoFono(tipoFono);
        return this;
    }

    public void setTipoFono(String tipoFono) {
        this.tipoFono = tipoFono;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InformacionContactoEmpleados)) {
            return false;
        }
        return getId() != null && getId().equals(((InformacionContactoEmpleados) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InformacionContactoEmpleados{" +
            "id=" + getId() +
            ", telefono='" + getTelefono() + "'" +
            ", tipoFono='" + getTipoFono() + "'" +
            "}";
    }
}
