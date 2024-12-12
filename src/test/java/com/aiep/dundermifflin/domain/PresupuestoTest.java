package com.aiep.dundermifflin.domain;

import static com.aiep.dundermifflin.domain.DepartamentoTestSamples.*;
import static com.aiep.dundermifflin.domain.PresupuestoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.aiep.dundermifflin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PresupuestoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Presupuesto.class);
        Presupuesto presupuesto1 = getPresupuestoSample1();
        Presupuesto presupuesto2 = new Presupuesto();
        assertThat(presupuesto1).isNotEqualTo(presupuesto2);

        presupuesto2.setId(presupuesto1.getId());
        assertThat(presupuesto1).isEqualTo(presupuesto2);

        presupuesto2 = getPresupuestoSample2();
        assertThat(presupuesto1).isNotEqualTo(presupuesto2);
    }

    @Test
    void departamentoTest() {
        Presupuesto presupuesto = getPresupuestoRandomSampleGenerator();
        Departamento departamentoBack = getDepartamentoRandomSampleGenerator();

        presupuesto.setDepartamento(departamentoBack);
        assertThat(presupuesto.getDepartamento()).isEqualTo(departamentoBack);

        presupuesto.departamento(null);
        assertThat(presupuesto.getDepartamento()).isNull();
    }
}
