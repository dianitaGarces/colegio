package com.colegio.prueba.service;

import com.colegio.prueba.service.dto.ColegioDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.colegio.prueba.domain.Colegio}.
 */
public interface ColegioService {
    /**
     * Save a colegio.
     *
     * @param colegioDTO the entity to save.
     * @return the persisted entity.
     */
    ColegioDTO save(ColegioDTO colegioDTO);

    /**
     * Partially updates a colegio.
     *
     * @param colegioDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ColegioDTO> partialUpdate(ColegioDTO colegioDTO);

    /**
     * Get all the colegios.
     *
     * @return the list of entities.
     */
    List<ColegioDTO> findAll();

    /**
     * Get the "id" colegio.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ColegioDTO> findOne(Long id);

    /**
     * Delete the "id" colegio.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
