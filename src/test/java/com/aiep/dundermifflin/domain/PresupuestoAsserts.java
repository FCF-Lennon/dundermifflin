package com.aiep.dundermifflin.domain;

import static com.aiep.dundermifflin.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class PresupuestoAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPresupuestoAllPropertiesEquals(Presupuesto expected, Presupuesto actual) {
        assertPresupuestoAutoGeneratedPropertiesEquals(expected, actual);
        assertPresupuestoAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPresupuestoAllUpdatablePropertiesEquals(Presupuesto expected, Presupuesto actual) {
        assertPresupuestoUpdatableFieldsEquals(expected, actual);
        assertPresupuestoUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPresupuestoAutoGeneratedPropertiesEquals(Presupuesto expected, Presupuesto actual) {
        assertThat(expected)
            .as("Verify Presupuesto auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPresupuestoUpdatableFieldsEquals(Presupuesto expected, Presupuesto actual) {
        assertThat(expected)
            .as("Verify Presupuesto relevant properties")
            .satisfies(e ->
                assertThat(e.getPresupuestoDepartamento())
                    .as("check presupuestoDepartamento")
                    .usingComparator(bigDecimalCompareTo)
                    .isEqualTo(actual.getPresupuestoDepartamento())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPresupuestoUpdatableRelationshipsEquals(Presupuesto expected, Presupuesto actual) {
        assertThat(expected)
            .as("Verify Presupuesto relationships")
            .satisfies(e -> assertThat(e.getDepartamento()).as("check departamento").isEqualTo(actual.getDepartamento()));
    }
}
