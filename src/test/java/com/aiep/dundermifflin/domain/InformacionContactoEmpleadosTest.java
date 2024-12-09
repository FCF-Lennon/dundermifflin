package com.aiep.dundermifflin.domain;

import static com.aiep.dundermifflin.domain.InformacionContactoEmpleadosTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.aiep.dundermifflin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InformacionContactoEmpleadosTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InformacionContactoEmpleados.class);
        InformacionContactoEmpleados informacionContactoEmpleados1 = getInformacionContactoEmpleadosSample1();
        InformacionContactoEmpleados informacionContactoEmpleados2 = new InformacionContactoEmpleados();
        assertThat(informacionContactoEmpleados1).isNotEqualTo(informacionContactoEmpleados2);

        informacionContactoEmpleados2.setId(informacionContactoEmpleados1.getId());
        assertThat(informacionContactoEmpleados1).isEqualTo(informacionContactoEmpleados2);

        informacionContactoEmpleados2 = getInformacionContactoEmpleadosSample2();
        assertThat(informacionContactoEmpleados1).isNotEqualTo(informacionContactoEmpleados2);
    }
}
