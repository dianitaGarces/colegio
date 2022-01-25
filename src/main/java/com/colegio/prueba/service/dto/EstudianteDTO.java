package com.colegio.prueba.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.colegio.prueba.domain.Estudiante} entity.
 */
public class EstudianteDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    private Set<AsignaturaDTO> id_asignaturas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<AsignaturaDTO> getId_asignaturas() {
        return id_asignaturas;
    }

    public void setId_asignaturas(Set<AsignaturaDTO> id_asignaturas) {
        this.id_asignaturas = id_asignaturas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EstudianteDTO)) {
            return false;
        }

        EstudianteDTO estudianteDTO = (EstudianteDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, estudianteDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EstudianteDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", id_asignaturas=" + getId_asignaturas() +
            "}";
    }
}
