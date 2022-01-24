package com.colegio.prueba.service;

import com.colegio.prueba.service.dto.AsignaturaDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.colegio.prueba.domain.Asignatura}.
 */
public interface AsignaturaService {
    /**
     * Get all the asignaturas.
     *
     * @return the list of entities.
     */
    List<AsignaturaDTO> findAllId(Long id);
}
