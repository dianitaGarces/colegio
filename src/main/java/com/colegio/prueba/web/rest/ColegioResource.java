package com.colegio.prueba.web.rest;

import com.colegio.prueba.repository.ColegioRepository;
import com.colegio.prueba.service.ColegioService;
import com.colegio.prueba.service.dto.ColegioDTO;
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
 * REST controller for managing {@link com.colegio.prueba.domain.Colegio}.
 */
@RestController
@RequestMapping("/api")
public class ColegioResource {

    private final Logger log = LoggerFactory.getLogger(ColegioResource.class);

    private static final String ENTITY_NAME = "colegio";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ColegioService colegioService;

    private final ColegioRepository colegioRepository;

    public ColegioResource(ColegioService colegioService, ColegioRepository colegioRepository) {
        this.colegioService = colegioService;
        this.colegioRepository = colegioRepository;
    }

    /**
     * {@code POST  /colegios} : Create a new colegio.
     *
     * @param colegioDTO the colegioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new colegioDTO, or with status {@code 400 (Bad Request)} if the colegio has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/colegios")
    public ResponseEntity<ColegioDTO> createColegio(@Valid @RequestBody ColegioDTO colegioDTO) throws URISyntaxException {
        log.debug("REST request to save Colegio : {}", colegioDTO);
        if (colegioDTO.getId() != null) {
            throw new BadRequestAlertException("A new colegio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ColegioDTO result = colegioService.save(colegioDTO);
        return ResponseEntity
            .created(new URI("/api/colegios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /colegios/:id} : Updates an existing colegio.
     *
     * @param id the id of the colegioDTO to save.
     * @param colegioDTO the colegioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated colegioDTO,
     * or with status {@code 400 (Bad Request)} if the colegioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the colegioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/colegios/{id}")
    public ResponseEntity<ColegioDTO> updateColegio(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ColegioDTO colegioDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Colegio : {}, {}", id, colegioDTO);
        if (colegioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, colegioDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!colegioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ColegioDTO result = colegioService.save(colegioDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, colegioDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /colegios/:id} : Partial updates given fields of an existing colegio, field will ignore if it is null
     *
     * @param id the id of the colegioDTO to save.
     * @param colegioDTO the colegioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated colegioDTO,
     * or with status {@code 400 (Bad Request)} if the colegioDTO is not valid,
     * or with status {@code 404 (Not Found)} if the colegioDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the colegioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/colegios/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ColegioDTO> partialUpdateColegio(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ColegioDTO colegioDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Colegio partially : {}, {}", id, colegioDTO);
        if (colegioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, colegioDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!colegioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ColegioDTO> result = colegioService.partialUpdate(colegioDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, colegioDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /colegios} : get all the colegios.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of colegios in body.
     */
    @GetMapping("/colegios")
    public List<ColegioDTO> getAllColegios() {
        log.debug("REST request to get all Colegios");
        return colegioService.findAll();
    }

    /**
     * {@code GET  /colegios/:id} : get the "id" colegio.
     *
     * @param id the id of the colegioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the colegioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/colegios/{id}")
    public ResponseEntity<ColegioDTO> getColegio(@PathVariable Long id) {
        log.debug("REST request to get Colegio : {}", id);
        Optional<ColegioDTO> colegioDTO = colegioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(colegioDTO);
    }

    /**
     * {@code DELETE  /colegios/:id} : delete the "id" colegio.
     *
     * @param id the id of the colegioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/colegios/{id}")
    public ResponseEntity<Void> deleteColegio(@PathVariable Long id) {
        log.debug("REST request to delete Colegio : {}", id);
        colegioService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
