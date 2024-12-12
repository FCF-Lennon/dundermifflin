package com.aiep.dundermifflin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.aiep.dundermifflin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JefesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JefesDTO.class);
        JefesDTO jefesDTO1 = new JefesDTO();
        jefesDTO1.setId(1L);
        JefesDTO jefesDTO2 = new JefesDTO();
        assertThat(jefesDTO1).isNotEqualTo(jefesDTO2);
        jefesDTO2.setId(jefesDTO1.getId());
        assertThat(jefesDTO1).isEqualTo(jefesDTO2);
        jefesDTO2.setId(2L);
        assertThat(jefesDTO1).isNotEqualTo(jefesDTO2);
        jefesDTO1.setId(null);
        assertThat(jefesDTO1).isNotEqualTo(jefesDTO2);
    }
}
