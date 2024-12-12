package com.aiep.dundermifflin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.aiep.dundermifflin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InformacionContactoEmpleadoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InformacionContactoEmpleadoDTO.class);
        InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO1 = new InformacionContactoEmpleadoDTO();
        informacionContactoEmpleadoDTO1.setId(1L);
        InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO2 = new InformacionContactoEmpleadoDTO();
        assertThat(informacionContactoEmpleadoDTO1).isNotEqualTo(informacionContactoEmpleadoDTO2);
        informacionContactoEmpleadoDTO2.setId(informacionContactoEmpleadoDTO1.getId());
        assertThat(informacionContactoEmpleadoDTO1).isEqualTo(informacionContactoEmpleadoDTO2);
        informacionContactoEmpleadoDTO2.setId(2L);
        assertThat(informacionContactoEmpleadoDTO1).isNotEqualTo(informacionContactoEmpleadoDTO2);
        informacionContactoEmpleadoDTO1.setId(null);
        assertThat(informacionContactoEmpleadoDTO1).isNotEqualTo(informacionContactoEmpleadoDTO2);
    }
}
