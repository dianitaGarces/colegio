package com.colegio.prueba.service.impl;

import com.colegio.prueba.domain.Asignatura;
import com.colegio.prueba.repository.AsignaturaRepository;
import com.colegio.prueba.service.AsignaturaService;
import com.colegio.prueba.service.dto.AsignaturaDTO;
import com.colegio.prueba.service.mapper.AsignaturaMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Asignatura}.
 */
@Service
@Transactional
public class AsignaturaServiceImpl implements AsignaturaService {

    private final Logger log = LoggerFactory.getLogger(AsignaturaServiceImpl.class);

    private final AsignaturaRepository asignaturaRepository;

    private final AsignaturaMapper asignaturaMapper;

    public AsignaturaServiceImpl(AsignaturaRepository asignaturaRepository, AsignaturaMapper asignaturaMapper) {
        this.asignaturaRepository = asignaturaRepository;
        this.asignaturaMapper = asignaturaMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AsignaturaDTO> findAllId(Long id) {
        log.debug("Request to get all Asignaturas");
        return asignaturaRepository.findAllId(id).stream().map(asignaturaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }
}
