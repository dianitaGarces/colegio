package com.colegio.prueba.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.colegio.prueba.domain.Estudiante} entity. This class is used
 * in {@link com.colegio.prueba.web.rest.EstudianteResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /estudiantes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EstudianteCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombre;

    private LongFilter id_asignaturaId;

    private Boolean distinct;

    public EstudianteCriteria() {}

    public EstudianteCriteria(EstudianteCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nombre = other.nombre == null ? null : other.nombre.copy();
        this.id_asignaturaId = other.id_asignaturaId == null ? null : other.id_asignaturaId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public EstudianteCriteria copy() {
        return new EstudianteCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNombre() {
        return nombre;
    }

    public StringFilter nombre() {
        if (nombre == null) {
            nombre = new StringFilter();
        }
        return nombre;
    }

    public void setNombre(StringFilter nombre) {
        this.nombre = nombre;
    }

    public LongFilter getId_asignaturaId() {
        return id_asignaturaId;
    }

    public LongFilter id_asignaturaId() {
        if (id_asignaturaId == null) {
            id_asignaturaId = new LongFilter();
        }
        return id_asignaturaId;
    }

    public void setId_asignaturaId(LongFilter id_asignaturaId) {
        this.id_asignaturaId = id_asignaturaId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EstudianteCriteria that = (EstudianteCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(id_asignaturaId, that.id_asignaturaId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, id_asignaturaId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EstudianteCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (nombre != null ? "nombre=" + nombre + ", " : "") +
            (id_asignaturaId != null ? "id_asignaturaId=" + id_asignaturaId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
