package com.aiep.dundermifflin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.aiep.dundermifflin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InformacionContactoJefeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InformacionContactoJefeDTO.class);
        InformacionContactoJefeDTO informacionContactoJefeDTO1 = new InformacionContactoJefeDTO();
        informacionContactoJefeDTO1.setId(1L);
        InformacionContactoJefeDTO informacionContactoJefeDTO2 = new InformacionContactoJefeDTO();
        assertThat(informacionContactoJefeDTO1).isNotEqualTo(informacionContactoJefeDTO2);
        informacionContactoJefeDTO2.setId(informacionContactoJefeDTO1.getId());
        assertThat(informacionContactoJefeDTO1).isEqualTo(informacionContactoJefeDTO2);
        informacionContactoJefeDTO2.setId(2L);
        assertThat(informacionContactoJefeDTO1).isNotEqualTo(informacionContactoJefeDTO2);
        informacionContactoJefeDTO1.setId(null);
        assertThat(informacionContactoJefeDTO1).isNotEqualTo(informacionContactoJefeDTO2);
    }
}
