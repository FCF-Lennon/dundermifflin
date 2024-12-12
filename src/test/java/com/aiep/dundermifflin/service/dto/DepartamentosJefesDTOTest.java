package com.aiep.dundermifflin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.aiep.dundermifflin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DepartamentosJefesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepartamentosJefesDTO.class);
        DepartamentosJefesDTO departamentosJefesDTO1 = new DepartamentosJefesDTO();
        departamentosJefesDTO1.setId(1L);
        DepartamentosJefesDTO departamentosJefesDTO2 = new DepartamentosJefesDTO();
        assertThat(departamentosJefesDTO1).isNotEqualTo(departamentosJefesDTO2);
        departamentosJefesDTO2.setId(departamentosJefesDTO1.getId());
        assertThat(departamentosJefesDTO1).isEqualTo(departamentosJefesDTO2);
        departamentosJefesDTO2.setId(2L);
        assertThat(departamentosJefesDTO1).isNotEqualTo(departamentosJefesDTO2);
        departamentosJefesDTO1.setId(null);
        assertThat(departamentosJefesDTO1).isNotEqualTo(departamentosJefesDTO2);
    }
}
