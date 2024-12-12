package com.aiep.dundermifflin.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.aiep.dundermifflin.domain.InformacionContactoJefe} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InformacionContactoJefeDTO implements Serializable {

    private Long id;

    private String telefono;

    private String tipoFono;

    private JefesDTO jefe;

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
        if (!(o instanceof InformacionContactoJefeDTO)) {
            return false;
        }

        InformacionContactoJefeDTO informacionContactoJefeDTO = (InformacionContactoJefeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, informacionContactoJefeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InformacionContactoJefeDTO{" +
            "id=" + getId() +
            ", telefono='" + getTelefono() + "'" +
            ", tipoFono='" + getTipoFono() + "'" +
            ", jefe=" + getJefe() +
            "}";
    }
}
