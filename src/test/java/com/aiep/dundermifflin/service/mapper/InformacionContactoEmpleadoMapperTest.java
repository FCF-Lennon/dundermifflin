package com.aiep.dundermifflin.service.mapper;

import static com.aiep.dundermifflin.domain.InformacionContactoEmpleadoAsserts.*;
import static com.aiep.dundermifflin.domain.InformacionContactoEmpleadoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InformacionContactoEmpleadoMapperTest {

    private InformacionContactoEmpleadoMapper informacionContactoEmpleadoMapper;

    @BeforeEach
    void setUp() {
        informacionContactoEmpleadoMapper = new InformacionContactoEmpleadoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getInformacionContactoEmpleadoSample1();
        var actual = informacionContactoEmpleadoMapper.toEntity(informacionContactoEmpleadoMapper.toDto(expected));
        assertInformacionContactoEmpleadoAllPropertiesEquals(expected, actual);
    }
}
