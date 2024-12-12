package com.aiep.dundermifflin.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.aiep.dundermifflin.domain.Jefes} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class JefesDTO implements Serializable {

    private Long id;

    private String nombreJefe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreJefe() {
        return nombreJefe;
    }

    public void setNombreJefe(String nombreJefe) {
        this.nombreJefe = nombreJefe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JefesDTO)) {
            return false;
        }

        JefesDTO jefesDTO = (JefesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, jefesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JefesDTO{" +
            "id=" + getId() +
            ", nombreJefe='" + getNombreJefe() + "'" +
            "}";
    }
}
