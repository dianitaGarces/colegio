package com.colegio.prueba.service;

import com.colegio.prueba.service.dto.CursoDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.colegio.prueba.domain.Curso}.
 */
public interface CursoService {
    /**
     * Save a curso.
     *
     * @param cursoDTO the entity to save.
     * @return the persisted entity.
     */
    CursoDTO save(CursoDTO cursoDTO);

    /**
     * Partially updates a curso.
     *
     * @param cursoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CursoDTO> partialUpdate(CursoDTO cursoDTO);

    /**
     * Get all the cursos.
     *
     * @return the list of entities.
     */
    List<CursoDTO> findAll();

    /**
     * Get the "id" curso.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CursoDTO> findOne(Long id);

    /**
     * Delete the "id" curso.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
