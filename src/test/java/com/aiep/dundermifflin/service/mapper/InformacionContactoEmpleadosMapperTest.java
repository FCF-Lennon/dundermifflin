package com.aiep.dundermifflin.service.mapper;

import static com.aiep.dundermifflin.domain.InformacionContactoEmpleadosAsserts.*;
import static com.aiep.dundermifflin.domain.InformacionContactoEmpleadosTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InformacionContactoEmpleadosMapperTest {

    private InformacionContactoEmpleadosMapper informacionContactoEmpleadosMapper;

    @BeforeEach
    void setUp() {
        informacionContactoEmpleadosMapper = new InformacionContactoEmpleadosMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getInformacionContactoEmpleadosSample1();
        var actual = informacionContactoEmpleadosMapper.toEntity(informacionContactoEmpleadosMapper.toDto(expected));
        assertInformacionContactoEmpleadosAllPropertiesEquals(expected, actual);
    }
}
