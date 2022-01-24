package com.colegio.prueba.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Asignatura.
 */
@Entity
@Table(name = "asignatura")
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @ManyToOne
    private Profesor id_profesor;

    @ManyToMany
    @JoinTable(
        name = "rel_asignatura__id_estudiante",
        joinColumns = @JoinColumn(name = "asignatura_id"),
        inverseJoinColumns = @JoinColumn(name = "id_estudiante_id")
    )
    private Set<Estudiante> id_estudiantes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "id_colegio" }, allowSetters = true)
    private Curso id_curso;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Asignatura id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Asignatura nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Profesor getId_profesor() {
        return this.id_profesor;
    }

    public void setId_profesor(Profesor profesor) {
        this.id_profesor = profesor;
    }

    public Asignatura id_profesor(Profesor profesor) {
        this.setId_profesor(profesor);
        return this;
    }

    public Set<Estudiante> getId_estudiantes() {
        return this.id_estudiantes;
    }

    public void setId_estudiantes(Set<Estudiante> estudiantes) {
        this.id_estudiantes = estudiantes;
    }

    public Asignatura id_estudiantes(Set<Estudiante> estudiantes) {
        this.setId_estudiantes(estudiantes);
        return this;
    }

    public Asignatura addId_estudiante(Estudiante estudiante) {
        this.id_estudiantes.add(estudiante);
        return this;
    }

    public Asignatura removeId_estudiante(Estudiante estudiante) {
        this.id_estudiantes.remove(estudiante);
        return this;
    }

    public Curso getId_curso() {
        return this.id_curso;
    }

    public void setId_curso(Curso curso) {
        this.id_curso = curso;
    }

    public Asignatura id_curso(Curso curso) {
        this.setId_curso(curso);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Asignatura)) {
            return false;
        }
        return id != null && id.equals(((Asignatura) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Asignatura{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
