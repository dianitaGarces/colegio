package com.colegio.prueba.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.colegio.prueba.IntegrationTest;
import com.colegio.prueba.domain.Colegio;
import com.colegio.prueba.repository.ColegioRepository;
import com.colegio.prueba.service.dto.ColegioDTO;
import com.colegio.prueba.service.mapper.ColegioMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ColegioResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ColegioResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/colegios";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ColegioRepository colegioRepository;

    @Autowired
    private ColegioMapper colegioMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restColegioMockMvc;

    private Colegio colegio;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Colegio createEntity(EntityManager em) {
        Colegio colegio = new Colegio().nombre(DEFAULT_NOMBRE);
        return colegio;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Colegio createUpdatedEntity(EntityManager em) {
        Colegio colegio = new Colegio().nombre(UPDATED_NOMBRE);
        return colegio;
    }

    @BeforeEach
    public void initTest() {
        colegio = createEntity(em);
    }

    @Test
    @Transactional
    void createColegio() throws Exception {
        int databaseSizeBeforeCreate = colegioRepository.findAll().size();
        // Create the Colegio
        ColegioDTO colegioDTO = colegioMapper.toDto(colegio);
        restColegioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(colegioDTO)))
            .andExpect(status().isCreated());

        // Validate the Colegio in the database
        List<Colegio> colegioList = colegioRepository.findAll();
        assertThat(colegioList).hasSize(databaseSizeBeforeCreate + 1);
        Colegio testColegio = colegioList.get(colegioList.size() - 1);
        assertThat(testColegio.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    void createColegioWithExistingId() throws Exception {
        // Create the Colegio with an existing ID
        colegio.setId(1L);
        ColegioDTO colegioDTO = colegioMapper.toDto(colegio);

        int databaseSizeBeforeCreate = colegioRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restColegioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(colegioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Colegio in the database
        List<Colegio> colegioList = colegioRepository.findAll();
        assertThat(colegioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = colegioRepository.findAll().size();
        // set the field null
        colegio.setNombre(null);

        // Create the Colegio, which fails.
        ColegioDTO colegioDTO = colegioMapper.toDto(colegio);

        restColegioMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(colegioDTO)))
            .andExpect(status().isBadRequest());

        List<Colegio> colegioList = colegioRepository.findAll();
        assertThat(colegioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllColegios() throws Exception {
        // Initialize the database
        colegioRepository.saveAndFlush(colegio);

        // Get all the colegioList
        restColegioMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(colegio.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }

    @Test
    @Transactional
    void getColegio() throws Exception {
        // Initialize the database
        colegioRepository.saveAndFlush(colegio);

        // Get the colegio
        restColegioMockMvc
            .perform(get(ENTITY_API_URL_ID, colegio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(colegio.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }

    @Test
    @Transactional
    void getNonExistingColegio() throws Exception {
        // Get the colegio
        restColegioMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewColegio() throws Exception {
        // Initialize the database
        colegioRepository.saveAndFlush(colegio);

        int databaseSizeBeforeUpdate = colegioRepository.findAll().size();

        // Update the colegio
        Colegio updatedColegio = colegioRepository.findById(colegio.getId()).get();
        // Disconnect from session so that the updates on updatedColegio are not directly saved in db
        em.detach(updatedColegio);
        updatedColegio.nombre(UPDATED_NOMBRE);
        ColegioDTO colegioDTO = colegioMapper.toDto(updatedColegio);

        restColegioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, colegioDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(colegioDTO))
            )
            .andExpect(status().isOk());

        // Validate the Colegio in the database
        List<Colegio> colegioList = colegioRepository.findAll();
        assertThat(colegioList).hasSize(databaseSizeBeforeUpdate);
        Colegio testColegio = colegioList.get(colegioList.size() - 1);
        assertThat(testColegio.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    void putNonExistingColegio() throws Exception {
        int databaseSizeBeforeUpdate = colegioRepository.findAll().size();
        colegio.setId(count.incrementAndGet());

        // Create the Colegio
        ColegioDTO colegioDTO = colegioMapper.toDto(colegio);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restColegioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, colegioDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(colegioDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Colegio in the database
        List<Colegio> colegioList = colegioRepository.findAll();
        assertThat(colegioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchColegio() throws Exception {
        int databaseSizeBeforeUpdate = colegioRepository.findAll().size();
        colegio.setId(count.incrementAndGet());

        // Create the Colegio
        ColegioDTO colegioDTO = colegioMapper.toDto(colegio);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restColegioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(colegioDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Colegio in the database
        List<Colegio> colegioList = colegioRepository.findAll();
        assertThat(colegioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamColegio() throws Exception {
        int databaseSizeBeforeUpdate = colegioRepository.findAll().size();
        colegio.setId(count.incrementAndGet());

        // Create the Colegio
        ColegioDTO colegioDTO = colegioMapper.toDto(colegio);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restColegioMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(colegioDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Colegio in the database
        List<Colegio> colegioList = colegioRepository.findAll();
        assertThat(colegioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateColegioWithPatch() throws Exception {
        // Initialize the database
        colegioRepository.saveAndFlush(colegio);

        int databaseSizeBeforeUpdate = colegioRepository.findAll().size();

        // Update the colegio using partial update
        Colegio partialUpdatedColegio = new Colegio();
        partialUpdatedColegio.setId(colegio.getId());

        restColegioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedColegio.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedColegio))
            )
            .andExpect(status().isOk());

        // Validate the Colegio in the database
        List<Colegio> colegioList = colegioRepository.findAll();
        assertThat(colegioList).hasSize(databaseSizeBeforeUpdate);
        Colegio testColegio = colegioList.get(colegioList.size() - 1);
        assertThat(testColegio.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    void fullUpdateColegioWithPatch() throws Exception {
        // Initialize the database
        colegioRepository.saveAndFlush(colegio);

        int databaseSizeBeforeUpdate = colegioRepository.findAll().size();

        // Update the colegio using partial update
        Colegio partialUpdatedColegio = new Colegio();
        partialUpdatedColegio.setId(colegio.getId());

        partialUpdatedColegio.nombre(UPDATED_NOMBRE);

        restColegioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedColegio.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedColegio))
            )
            .andExpect(status().isOk());

        // Validate the Colegio in the database
        List<Colegio> colegioList = colegioRepository.findAll();
        assertThat(colegioList).hasSize(databaseSizeBeforeUpdate);
        Colegio testColegio = colegioList.get(colegioList.size() - 1);
        assertThat(testColegio.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    void patchNonExistingColegio() throws Exception {
        int databaseSizeBeforeUpdate = colegioRepository.findAll().size();
        colegio.setId(count.incrementAndGet());

        // Create the Colegio
        ColegioDTO colegioDTO = colegioMapper.toDto(colegio);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restColegioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, colegioDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(colegioDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Colegio in the database
        List<Colegio> colegioList = colegioRepository.findAll();
        assertThat(colegioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchColegio() throws Exception {
        int databaseSizeBeforeUpdate = colegioRepository.findAll().size();
        colegio.setId(count.incrementAndGet());

        // Create the Colegio
        ColegioDTO colegioDTO = colegioMapper.toDto(colegio);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restColegioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(colegioDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Colegio in the database
        List<Colegio> colegioList = colegioRepository.findAll();
        assertThat(colegioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamColegio() throws Exception {
        int databaseSizeBeforeUpdate = colegioRepository.findAll().size();
        colegio.setId(count.incrementAndGet());

        // Create the Colegio
        ColegioDTO colegioDTO = colegioMapper.toDto(colegio);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restColegioMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(colegioDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Colegio in the database
        List<Colegio> colegioList = colegioRepository.findAll();
        assertThat(colegioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteColegio() throws Exception {
        // Initialize the database
        colegioRepository.saveAndFlush(colegio);

        int databaseSizeBeforeDelete = colegioRepository.findAll().size();

        // Delete the colegio
        restColegioMockMvc
            .perform(delete(ENTITY_API_URL_ID, colegio.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Colegio> colegioList = colegioRepository.findAll();
        assertThat(colegioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
