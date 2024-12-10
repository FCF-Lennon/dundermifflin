package com.aiep.dundurmifflin.domain;

import static com.aiep.dundurmifflin.domain.DepartamentoTestSamples.*;
import static com.aiep.dundurmifflin.domain.EmpleadoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.aiep.dundurmifflin.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class EmpleadoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Empleado.class);
        Empleado empleado1 = getEmpleadoSample1();
        Empleado empleado2 = new Empleado();
        assertThat(empleado1).isNotEqualTo(empleado2);

        empleado2.setId(empleado1.getId());
        assertThat(empleado1).isEqualTo(empleado2);

        empleado2 = getEmpleadoSample2();
        assertThat(empleado1).isNotEqualTo(empleado2);
    }

    @Test
    void departamentoTest() {
        Empleado empleado = getEmpleadoRandomSampleGenerator();
        Departamento departamentoBack = getDepartamentoRandomSampleGenerator();

        empleado.addDepartamento(departamentoBack);
        assertThat(empleado.getDepartamentos()).containsOnly(departamentoBack);

        empleado.removeDepartamento(departamentoBack);
        assertThat(empleado.getDepartamentos()).doesNotContain(departamentoBack);

        empleado.departamentos(new HashSet<>(Set.of(departamentoBack)));
        assertThat(empleado.getDepartamentos()).containsOnly(departamentoBack);

        empleado.setDepartamentos(new HashSet<>());
        assertThat(empleado.getDepartamentos()).doesNotContain(departamentoBack);
    }
}
