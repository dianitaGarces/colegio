package com.colegio.prueba.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.colegio.prueba.domain.Asignatura} entity.
 */
public class AsignaturaDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    private ProfesorDTO id_profesor;

    private CursoDTO id_curso;

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

    public ProfesorDTO getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(ProfesorDTO id_profesor) {
        this.id_profesor = id_profesor;
    }

    public CursoDTO getId_curso() {
        return id_curso;
    }

    public void setId_curso(CursoDTO id_curso) {
        this.id_curso = id_curso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AsignaturaDTO)) {
            return false;
        }

        AsignaturaDTO asignaturaDTO = (AsignaturaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, asignaturaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AsignaturaDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", id_profesor=" + getId_profesor() +
            ", id_curso=" + getId_curso() +
            "}";
    }
}
