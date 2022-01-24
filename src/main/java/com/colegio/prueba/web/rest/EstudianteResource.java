package com.colegio.prueba.web.rest;

import com.colegio.prueba.repository.EstudianteRepository;
import com.colegio.prueba.service.EstudianteQueryService;
import com.colegio.prueba.service.EstudianteService;
import com.colegio.prueba.service.criteria.EstudianteCriteria;
import com.colegio.prueba.service.dto.EstudianteDTO;
import com.colegio.prueba.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.colegio.prueba.domain.Estudiante}.
 */
@RestController
@RequestMapping("/api")
public class EstudianteResource {

    private final Logger log = LoggerFactory.getLogger(EstudianteResource.class);

    private static final String ENTITY_NAME = "estudiante";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstudianteService estudianteService;

    private final EstudianteRepository estudianteRepository;

    private final EstudianteQueryService estudianteQueryService;

    public EstudianteResource(
        EstudianteService estudianteService,
        EstudianteRepository estudianteRepository,
        EstudianteQueryService estudianteQueryService
    ) {
        this.estudianteService = estudianteService;
        this.estudianteRepository = estudianteRepository;
        this.estudianteQueryService = estudianteQueryService;
    }

    /**
     * {@code GET  /estudiantes} : get all the estudiantes by id.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of asignaturas in body.
     */
    @GetMapping("/estudiantes/{id}")
    public List<EstudianteDTO> getAllEstudiantesId(@PathVariable Long id) {
        System.out.print("********************* getAllEstudiantesId ****************** " + id);
        log.debug("REST request to get all getAllEstudiantesId ");
        List<EstudianteDTO> entityList = estudianteService.findAllId(id);
        return entityList;
    }
}
