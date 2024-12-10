package com.aiep.dundurmifflin.domain;

import static com.aiep.dundurmifflin.domain.DepartamentoTestSamples.*;
import static com.aiep.dundurmifflin.domain.DepartamentosJefesTestSamples.*;
import static com.aiep.dundurmifflin.domain.JefesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.aiep.dundurmifflin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DepartamentosJefesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepartamentosJefes.class);
        DepartamentosJefes departamentosJefes1 = getDepartamentosJefesSample1();
        DepartamentosJefes departamentosJefes2 = new DepartamentosJefes();
        assertThat(departamentosJefes1).isNotEqualTo(departamentosJefes2);

        departamentosJefes2.setId(departamentosJefes1.getId());
        assertThat(departamentosJefes1).isEqualTo(departamentosJefes2);

        departamentosJefes2 = getDepartamentosJefesSample2();
        assertThat(departamentosJefes1).isNotEqualTo(departamentosJefes2);
    }

    @Test
    void departamentoTest() {
        DepartamentosJefes departamentosJefes = getDepartamentosJefesRandomSampleGenerator();
        Departamento departamentoBack = getDepartamentoRandomSampleGenerator();

        departamentosJefes.setDepartamento(departamentoBack);
        assertThat(departamentosJefes.getDepartamento()).isEqualTo(departamentoBack);

        departamentosJefes.departamento(null);
        assertThat(departamentosJefes.getDepartamento()).isNull();
    }

    @Test
    void jefeTest() {
        DepartamentosJefes departamentosJefes = getDepartamentosJefesRandomSampleGenerator();
        Jefes jefesBack = getJefesRandomSampleGenerator();

        departamentosJefes.setJefe(jefesBack);
        assertThat(departamentosJefes.getJefe()).isEqualTo(jefesBack);

        departamentosJefes.jefe(null);
        assertThat(departamentosJefes.getJefe()).isNull();
    }
}
