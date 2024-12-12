package com.aiep.dundermifflin.service.mapper;

import static com.aiep.dundermifflin.domain.EmpleadoDepartamentoAsserts.*;
import static com.aiep.dundermifflin.domain.EmpleadoDepartamentoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmpleadoDepartamentoMapperTest {

    private EmpleadoDepartamentoMapper empleadoDepartamentoMapper;

    @BeforeEach
    void setUp() {
        empleadoDepartamentoMapper = new EmpleadoDepartamentoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getEmpleadoDepartamentoSample1();
        var actual = empleadoDepartamentoMapper.toEntity(empleadoDepartamentoMapper.toDto(expected));
        assertEmpleadoDepartamentoAllPropertiesEquals(expected, actual);
    }
}
