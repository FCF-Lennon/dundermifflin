package com.aiep.dundermifflin.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A InformacionContactoJefe.
 */
@Entity
@Table(name = "informacion_contacto_jefe")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InformacionContactoJefe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "tipo_fono")
    private String tipoFono;

    @ManyToOne(fetch = FetchType.LAZY)
    private Jefes jefe;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InformacionContactoJefe id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public InformacionContactoJefe telefono(String telefono) {
        this.setTelefono(telefono);
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoFono() {
        return this.tipoFono;
    }

    public InformacionContactoJefe tipoFono(String tipoFono) {
        this.setTipoFono(tipoFono);
        return this;
    }

    public void setTipoFono(String tipoFono) {
        this.tipoFono = tipoFono;
    }

    public Jefes getJefe() {
        return this.jefe;
    }

    public void setJefe(Jefes jefes) {
        this.jefe = jefes;
    }

    public InformacionContactoJefe jefe(Jefes jefes) {
        this.setJefe(jefes);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InformacionContactoJefe)) {
            return false;
        }
        return getId() != null && getId().equals(((InformacionContactoJefe) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InformacionContactoJefe{" +
            "id=" + getId() +
            ", telefono='" + getTelefono() + "'" +
            ", tipoFono='" + getTipoFono() + "'" +
            "}";
    }
}
