package com.aiep.dundermifflin.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class InformacionContactoJefeAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertInformacionContactoJefeAllPropertiesEquals(InformacionContactoJefe expected, InformacionContactoJefe actual) {
        assertInformacionContactoJefeAutoGeneratedPropertiesEquals(expected, actual);
        assertInformacionContactoJefeAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertInformacionContactoJefeAllUpdatablePropertiesEquals(
        InformacionContactoJefe expected,
        InformacionContactoJefe actual
    ) {
        assertInformacionContactoJefeUpdatableFieldsEquals(expected, actual);
        assertInformacionContactoJefeUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertInformacionContactoJefeAutoGeneratedPropertiesEquals(
        InformacionContactoJefe expected,
        InformacionContactoJefe actual
    ) {
        assertThat(expected)
            .as("Verify InformacionContactoJefe auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertInformacionContactoJefeUpdatableFieldsEquals(
        InformacionContactoJefe expected,
        InformacionContactoJefe actual
    ) {
        assertThat(expected)
            .as("Verify InformacionContactoJefe relevant properties")
            .satisfies(e -> assertThat(e.getTelefono()).as("check telefono").isEqualTo(actual.getTelefono()))
            .satisfies(e -> assertThat(e.getTipoFono()).as("check tipoFono").isEqualTo(actual.getTipoFono()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertInformacionContactoJefeUpdatableRelationshipsEquals(
        InformacionContactoJefe expected,
        InformacionContactoJefe actual
    ) {
        assertThat(expected)
            .as("Verify InformacionContactoJefe relationships")
            .satisfies(e -> assertThat(e.getJefe()).as("check jefe").isEqualTo(actual.getJefe()));
    }
}
