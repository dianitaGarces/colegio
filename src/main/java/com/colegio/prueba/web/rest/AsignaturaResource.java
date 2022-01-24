package com.colegio.prueba.web.rest;

import com.colegio.prueba.repository.AsignaturaRepository;
import com.colegio.prueba.service.AsignaturaService;
import com.colegio.prueba.service.dto.AsignaturaDTO;
import com.colegio.prueba.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
 * REST controller for managing {@link com.colegio.prueba.domain.Asignatura}.
 */
@RestController
@RequestMapping("/api")
public class AsignaturaResource {

    private final Logger log = LoggerFactory.getLogger(AsignaturaResource.class);

    private static final String ENTITY_NAME = "asignatura";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AsignaturaService asignaturaService;

    private final AsignaturaRepository asignaturaRepository;

    public AsignaturaResource(AsignaturaService asignaturaService, AsignaturaRepository asignaturaRepository) {
        this.asignaturaService = asignaturaService;
        this.asignaturaRepository = asignaturaRepository;
    }

    /**
     * {@code GET  /asignaturas} : get all the asignaturas.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of asignaturas in body.
     */
    @GetMapping("/asignaturas/{id}")
    public List<AsignaturaDTO> getAllAsignaturas(@PathVariable Long id) {
        System.out.print("********************* " + id);
        log.debug("REST request to get all Asignaturas");
        List<AsignaturaDTO> asignaturaDTO = asignaturaService.findAllId(id);
        return asignaturaDTO;
    }
}
