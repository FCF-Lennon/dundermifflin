package com.aiep.dundermifflin.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class EmpleadoDepartamentoAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEmpleadoDepartamentoAllPropertiesEquals(EmpleadoDepartamento expected, EmpleadoDepartamento actual) {
        assertEmpleadoDepartamentoAutoGeneratedPropertiesEquals(expected, actual);
        assertEmpleadoDepartamentoAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEmpleadoDepartamentoAllUpdatablePropertiesEquals(EmpleadoDepartamento expected, EmpleadoDepartamento actual) {
        assertEmpleadoDepartamentoUpdatableFieldsEquals(expected, actual);
        assertEmpleadoDepartamentoUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEmpleadoDepartamentoAutoGeneratedPropertiesEquals(EmpleadoDepartamento expected, EmpleadoDepartamento actual) {
        assertThat(expected)
            .as("Verify EmpleadoDepartamento auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEmpleadoDepartamentoUpdatableFieldsEquals(EmpleadoDepartamento expected, EmpleadoDepartamento actual) {
        assertThat(expected)
            .as("Verify EmpleadoDepartamento relevant properties")
            .satisfies(e -> assertThat(e.getFechaAsignacion()).as("check fechaAsignacion").isEqualTo(actual.getFechaAsignacion()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEmpleadoDepartamentoUpdatableRelationshipsEquals(EmpleadoDepartamento expected, EmpleadoDepartamento actual) {
        assertThat(expected)
            .as("Verify EmpleadoDepartamento relationships")
            .satisfies(e -> assertThat(e.getEmpleado()).as("check empleado").isEqualTo(actual.getEmpleado()))
            .satisfies(e -> assertThat(e.getDepartamento()).as("check departamento").isEqualTo(actual.getDepartamento()));
    }
}
