package com.colegio.prueba.service.impl;

import com.colegio.prueba.domain.Colegio;
import com.colegio.prueba.repository.ColegioRepository;
import com.colegio.prueba.service.ColegioService;
import com.colegio.prueba.service.dto.ColegioDTO;
import com.colegio.prueba.service.mapper.ColegioMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Colegio}.
 */
@Service
@Transactional
public class ColegioServiceImpl implements ColegioService {

    private final Logger log = LoggerFactory.getLogger(ColegioServiceImpl.class);

    private final ColegioRepository colegioRepository;

    private final ColegioMapper colegioMapper;

    public ColegioServiceImpl(ColegioRepository colegioRepository, ColegioMapper colegioMapper) {
        this.colegioRepository = colegioRepository;
        this.colegioMapper = colegioMapper;
    }

    @Override
    public ColegioDTO save(ColegioDTO colegioDTO) {
        log.debug("Request to save Colegio : {}", colegioDTO);
        Colegio colegio = colegioMapper.toEntity(colegioDTO);
        colegio = colegioRepository.save(colegio);
        return colegioMapper.toDto(colegio);
    }

    @Override
    public Optional<ColegioDTO> partialUpdate(ColegioDTO colegioDTO) {
        log.debug("Request to partially update Colegio : {}", colegioDTO);

        return colegioRepository
            .findById(colegioDTO.getId())
            .map(existingColegio -> {
                colegioMapper.partialUpdate(existingColegio, colegioDTO);

                return existingColegio;
            })
            .map(colegioRepository::save)
            .map(colegioMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ColegioDTO> findAll() {
        log.debug("Request to get all Colegios");
        return colegioRepository.findAll().stream().map(colegioMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ColegioDTO> findOne(Long id) {
        log.debug("Request to get Colegio : {}", id);
        return colegioRepository.findById(id).map(colegioMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Colegio : {}", id);
        colegioRepository.deleteById(id);
    }
}
