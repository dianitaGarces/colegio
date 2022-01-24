package com.colegio.prueba.service;

import com.colegio.prueba.service.dto.EstudianteDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.colegio.prueba.domain.Estudiante}.
 */
public interface EstudianteService {
    /**
     * Get all the estudiantes.
     *
     * @return the list of entities.
     */
    List<EstudianteDTO> findAllId(Long id);
}
