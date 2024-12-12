package com.aiep.dundermifflin.domain;

import static com.aiep.dundermifflin.domain.EmpleadoTestSamples.*;
import static com.aiep.dundermifflin.domain.InformacionContactoEmpleadoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.aiep.dundermifflin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InformacionContactoEmpleadoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InformacionContactoEmpleado.class);
        InformacionContactoEmpleado informacionContactoEmpleado1 = getInformacionContactoEmpleadoSample1();
        InformacionContactoEmpleado informacionContactoEmpleado2 = new InformacionContactoEmpleado();
        assertThat(informacionContactoEmpleado1).isNotEqualTo(informacionContactoEmpleado2);

        informacionContactoEmpleado2.setId(informacionContactoEmpleado1.getId());
        assertThat(informacionContactoEmpleado1).isEqualTo(informacionContactoEmpleado2);

        informacionContactoEmpleado2 = getInformacionContactoEmpleadoSample2();
        assertThat(informacionContactoEmpleado1).isNotEqualTo(informacionContactoEmpleado2);
    }

    @Test
    void empleadoTest() {
        InformacionContactoEmpleado informacionContactoEmpleado = getInformacionContactoEmpleadoRandomSampleGenerator();
        Empleado empleadoBack = getEmpleadoRandomSampleGenerator();

        informacionContactoEmpleado.setEmpleado(empleadoBack);
        assertThat(informacionContactoEmpleado.getEmpleado()).isEqualTo(empleadoBack);

        informacionContactoEmpleado.empleado(null);
        assertThat(informacionContactoEmpleado.getEmpleado()).isNull();
    }
}
