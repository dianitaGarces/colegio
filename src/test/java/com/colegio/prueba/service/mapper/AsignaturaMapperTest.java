package com.colegio.prueba.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AsignaturaMapperTest {

    private AsignaturaMapper asignaturaMapper;

    @BeforeEach
    public void setUp() {
        asignaturaMapper = new AsignaturaMapperImpl();
    }
}
