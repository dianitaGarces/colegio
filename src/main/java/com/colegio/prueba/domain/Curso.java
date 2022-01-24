package com.colegio.prueba.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Curso.
 */
@Entity
@Table(name = "curso")
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "salon", nullable = false)
    private String salon;

    @NotNull
    @Column(name = "grado", nullable = false)
    private Integer grado;

    @ManyToOne
    private Colegio id_colegio;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Curso id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalon() {
        return this.salon;
    }

    public Curso salon(String salon) {
        this.setSalon(salon);
        return this;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public Integer getGrado() {
        return this.grado;
    }

    public Curso grado(Integer grado) {
        this.setGrado(grado);
        return this;
    }

    public void setGrado(Integer grado) {
        this.grado = grado;
    }

    public Colegio getId_colegio() {
        return this.id_colegio;
    }

    public void setId_colegio(Colegio colegio) {
        this.id_colegio = colegio;
    }

    public Curso id_colegio(Colegio colegio) {
        this.setId_colegio(colegio);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Curso)) {
            return false;
        }
        return id != null && id.equals(((Curso) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Curso{" +
            "id=" + getId() +
            ", salon='" + getSalon() + "'" +
            ", grado=" + getGrado() +
            "}";
    }
}
