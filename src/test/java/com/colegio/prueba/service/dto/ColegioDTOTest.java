package com.colegio.prueba.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.colegio.prueba.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ColegioDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ColegioDTO.class);
        ColegioDTO colegioDTO1 = new ColegioDTO();
        colegioDTO1.setId(1L);
        ColegioDTO colegioDTO2 = new ColegioDTO();
        assertThat(colegioDTO1).isNotEqualTo(colegioDTO2);
        colegioDTO2.setId(colegioDTO1.getId());
        assertThat(colegioDTO1).isEqualTo(colegioDTO2);
        colegioDTO2.setId(2L);
        assertThat(colegioDTO1).isNotEqualTo(colegioDTO2);
        colegioDTO1.setId(null);
        assertThat(colegioDTO1).isNotEqualTo(colegioDTO2);
    }
}
