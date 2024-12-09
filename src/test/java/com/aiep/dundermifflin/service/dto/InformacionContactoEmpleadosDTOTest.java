package com.aiep.dundermifflin.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.aiep.dundermifflin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InformacionContactoEmpleadosDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InformacionContactoEmpleadosDTO.class);
        InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO1 = new InformacionContactoEmpleadosDTO();
        informacionContactoEmpleadosDTO1.setId(1L);
        InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO2 = new InformacionContactoEmpleadosDTO();
        assertThat(informacionContactoEmpleadosDTO1).isNotEqualTo(informacionContactoEmpleadosDTO2);
        informacionContactoEmpleadosDTO2.setId(informacionContactoEmpleadosDTO1.getId());
        assertThat(informacionContactoEmpleadosDTO1).isEqualTo(informacionContactoEmpleadosDTO2);
        informacionContactoEmpleadosDTO2.setId(2L);
        assertThat(informacionContactoEmpleadosDTO1).isNotEqualTo(informacionContactoEmpleadosDTO2);
        informacionContactoEmpleadosDTO1.setId(null);
        assertThat(informacionContactoEmpleadosDTO1).isNotEqualTo(informacionContactoEmpleadosDTO2);
    }
}
