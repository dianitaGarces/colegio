package com.colegio.prueba.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.colegio.prueba.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ColegioTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Colegio.class);
        Colegio colegio1 = new Colegio();
        colegio1.setId(1L);
        Colegio colegio2 = new Colegio();
        colegio2.setId(colegio1.getId());
        assertThat(colegio1).isEqualTo(colegio2);
        colegio2.setId(2L);
        assertThat(colegio1).isNotEqualTo(colegio2);
        colegio1.setId(null);
        assertThat(colegio1).isNotEqualTo(colegio2);
    }
}
