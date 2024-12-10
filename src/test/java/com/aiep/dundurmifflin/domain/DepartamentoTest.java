package com.aiep.dundurmifflin.domain;

import static com.aiep.dundurmifflin.domain.DepartamentoTestSamples.*;
import static com.aiep.dundurmifflin.domain.EmpleadoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.aiep.dundurmifflin.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DepartamentoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Departamento.class);
        Departamento departamento1 = getDepartamentoSample1();
        Departamento departamento2 = new Departamento();
        assertThat(departamento1).isNotEqualTo(departamento2);

        departamento2.setId(departamento1.getId());
        assertThat(departamento1).isEqualTo(departamento2);

        departamento2 = getDepartamentoSample2();
        assertThat(departamento1).isNotEqualTo(departamento2);
    }

    @Test
    void empleadosTest() {
        Departamento departamento = getDepartamentoRandomSampleGenerator();
        Empleado empleadoBack = getEmpleadoRandomSampleGenerator();

        departamento.addEmpleados(empleadoBack);
        assertThat(departamento.getEmpleados()).containsOnly(empleadoBack);
        assertThat(empleadoBack.getDepartamentos()).containsOnly(departamento);

        departamento.removeEmpleados(empleadoBack);
        assertThat(departamento.getEmpleados()).doesNotContain(empleadoBack);
        assertThat(empleadoBack.getDepartamentos()).doesNotContain(departamento);

        departamento.empleados(new HashSet<>(Set.of(empleadoBack)));
        assertThat(departamento.getEmpleados()).containsOnly(empleadoBack);
        assertThat(empleadoBack.getDepartamentos()).containsOnly(departamento);

        departamento.setEmpleados(new HashSet<>());
        assertThat(departamento.getEmpleados()).doesNotContain(empleadoBack);
        assertThat(empleadoBack.getDepartamentos()).doesNotContain(departamento);
    }
}
