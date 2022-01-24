package com.colegio.prueba.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ColegioMapperTest {

    private ColegioMapper colegioMapper;

    @BeforeEach
    public void setUp() {
        colegioMapper = new ColegioMapperImpl();
    }
}
