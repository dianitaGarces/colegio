package com.colegio.prueba.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.colegio.prueba.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AsignaturaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AsignaturaDTO.class);
        AsignaturaDTO asignaturaDTO1 = new AsignaturaDTO();
        asignaturaDTO1.setId(1L);
        AsignaturaDTO asignaturaDTO2 = new AsignaturaDTO();
        assertThat(asignaturaDTO1).isNotEqualTo(asignaturaDTO2);
        asignaturaDTO2.setId(asignaturaDTO1.getId());
        assertThat(asignaturaDTO1).isEqualTo(asignaturaDTO2);
        asignaturaDTO2.setId(2L);
        assertThat(asignaturaDTO1).isNotEqualTo(asignaturaDTO2);
        asignaturaDTO1.setId(null);
        assertThat(asignaturaDTO1).isNotEqualTo(asignaturaDTO2);
    }
}
