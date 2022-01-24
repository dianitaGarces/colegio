package com.colegio.prueba.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.colegio.prueba.domain.Curso} entity.
 */
public class CursoDTO implements Serializable {

    private Long id;

    @NotNull
    private String salon;

    @NotNull
    private Integer grado;

    private ColegioDTO id_colegio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public Integer getGrado() {
        return grado;
    }

    public void setGrado(Integer grado) {
        this.grado = grado;
    }

    public ColegioDTO getId_colegio() {
        return id_colegio;
    }

    public void setId_colegio(ColegioDTO id_colegio) {
        this.id_colegio = id_colegio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CursoDTO)) {
            return false;
        }

        CursoDTO cursoDTO = (CursoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cursoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CursoDTO{" +
            "id=" + getId() +
            ", salon='" + getSalon() + "'" +
            ", grado=" + getGrado() +
            ", id_colegio=" + getId_colegio() +
            "}";
    }
}
