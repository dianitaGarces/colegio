package com.colegio.prueba.service;

import com.colegio.prueba.domain.*; // for static metamodels
import com.colegio.prueba.domain.Estudiante;
import com.colegio.prueba.repository.EstudianteRepository;
import com.colegio.prueba.service.criteria.EstudianteCriteria;
import com.colegio.prueba.service.dto.EstudianteDTO;
import com.colegio.prueba.service.mapper.EstudianteMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Estudiante} entities in the database.
 * The main input is a {@link EstudianteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EstudianteDTO} or a {@link Page} of {@link EstudianteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EstudianteQueryService extends QueryService<Estudiante> {

    private final Logger log = LoggerFactory.getLogger(EstudianteQueryService.class);

    private final EstudianteRepository estudianteRepository;

    private final EstudianteMapper estudianteMapper;

    public EstudianteQueryService(EstudianteRepository estudianteRepository, EstudianteMapper estudianteMapper) {
        this.estudianteRepository = estudianteRepository;
        this.estudianteMapper = estudianteMapper;
    }

    /**
     * Return a {@link List} of {@link EstudianteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EstudianteDTO> findByCriteria(EstudianteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Estudiante> specification = createSpecification(criteria);
        return estudianteMapper.toDto(estudianteRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EstudianteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EstudianteDTO> findByCriteria(EstudianteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Estudiante> specification = createSpecification(criteria);
        return estudianteRepository.findAll(specification, page).map(estudianteMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EstudianteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Estudiante> specification = createSpecification(criteria);
        return estudianteRepository.count(specification);
    }

    /**
     * Function to convert {@link EstudianteCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Estudiante> createSpecification(EstudianteCriteria criteria) {
        Specification<Estudiante> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Estudiante_.id));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), Estudiante_.nombre));
            }
        }
        return specification;
    }
}
