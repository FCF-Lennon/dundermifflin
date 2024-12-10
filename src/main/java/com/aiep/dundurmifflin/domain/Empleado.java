package com.aiep.dundurmifflin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Empleado.
 */
@Entity
@Table(name = "empleado")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nombre_empleado", nullable = false)
    private String nombreEmpleado;

    @NotNull
    @Column(name = "apellido_empleado", nullable = false)
    private String apellidoEmpleado;

    @NotNull
    @Column(name = "correo", nullable = false)
    private String correo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_empleado__departamento",
        joinColumns = @JoinColumn(name = "empleado_id"),
        inverseJoinColumns = @JoinColumn(name = "departamento_id")
    )
    @JsonIgnoreProperties(value = { "empleados" }, allowSetters = true)
    private Set<Departamento> departamentos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Empleado id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpleado() {
        return this.nombreEmpleado;
    }

    public Empleado nombreEmpleado(String nombreEmpleado) {
        this.setNombreEmpleado(nombreEmpleado);
        return this;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return this.apellidoEmpleado;
    }

    public Empleado apellidoEmpleado(String apellidoEmpleado) {
        this.setApellidoEmpleado(apellidoEmpleado);
        return this;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public String getCorreo() {
        return this.correo;
    }

    public Empleado correo(String correo) {
        this.setCorreo(correo);
        return this;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Set<Departamento> getDepartamentos() {
        return this.departamentos;
    }

    public void setDepartamentos(Set<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public Empleado departamentos(Set<Departamento> departamentos) {
        this.setDepartamentos(departamentos);
        return this;
    }

    public Empleado addDepartamento(Departamento departamento) {
        this.departamentos.add(departamento);
        return this;
    }

    public Empleado removeDepartamento(Departamento departamento) {
        this.departamentos.remove(departamento);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Empleado)) {
            return false;
        }
        return getId() != null && getId().equals(((Empleado) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Empleado{" +
            "id=" + getId() +
            ", nombreEmpleado='" + getNombreEmpleado() + "'" +
            ", apellidoEmpleado='" + getApellidoEmpleado() + "'" +
            ", correo='" + getCorreo() + "'" +
            "}";
    }
}
