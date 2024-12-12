package com.aiep.dundermifflin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.aiep.dundermifflin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EmpleadoDepartamentoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmpleadoDepartamentoDTO.class);
        EmpleadoDepartamentoDTO empleadoDepartamentoDTO1 = new EmpleadoDepartamentoDTO();
        empleadoDepartamentoDTO1.setId(1L);
        EmpleadoDepartamentoDTO empleadoDepartamentoDTO2 = new EmpleadoDepartamentoDTO();
        assertThat(empleadoDepartamentoDTO1).isNotEqualTo(empleadoDepartamentoDTO2);
        empleadoDepartamentoDTO2.setId(empleadoDepartamentoDTO1.getId());
        assertThat(empleadoDepartamentoDTO1).isEqualTo(empleadoDepartamentoDTO2);
        empleadoDepartamentoDTO2.setId(2L);
        assertThat(empleadoDepartamentoDTO1).isNotEqualTo(empleadoDepartamentoDTO2);
        empleadoDepartamentoDTO1.setId(null);
        assertThat(empleadoDepartamentoDTO1).isNotEqualTo(empleadoDepartamentoDTO2);
    }
}
