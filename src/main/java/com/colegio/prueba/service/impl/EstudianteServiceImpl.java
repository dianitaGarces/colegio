package com.colegio.prueba.service.impl;

import com.colegio.prueba.domain.Estudiante;
import com.colegio.prueba.repository.EstudianteRepository;
import com.colegio.prueba.service.EstudianteService;
import com.colegio.prueba.service.dto.AsignaturaDTO;
import com.colegio.prueba.service.dto.EstudianteDTO;
import com.colegio.prueba.service.mapper.EstudianteMapper;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Estudiante}.
 */
@Service
@Transactional
public class EstudianteServiceImpl implements EstudianteService {

    private final Logger log = LoggerFactory.getLogger(EstudianteServiceImpl.class);

    private final EstudianteRepository estudianteRepository;

    private final EstudianteMapper estudianteMapper;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository, EstudianteMapper estudianteMapper) {
        this.estudianteRepository = estudianteRepository;
        this.estudianteMapper = estudianteMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstudianteDTO> findAllId(Long id) {
        log.debug("Request to get all Asignaturas");

        List<EstudianteDTO> estud = estudianteRepository
            .findAllId()
            .stream()
            .map(estudianteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        List<EstudianteDTO> estudiantesLista = new ArrayList<>();
        for (EstudianteDTO p : estud) {
            List<AsignaturaDTO> id_asignaturas = new ArrayList<>(p.getId_asignaturas());
            for (AsignaturaDTO asig : id_asignaturas) {
                if (asig.getId() == id) {
                    estudiantesLista.add(p);
                }
            }
        }
        return estudiantesLista;
    }
}
