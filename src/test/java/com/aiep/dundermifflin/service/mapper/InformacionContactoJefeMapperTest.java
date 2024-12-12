package com.aiep.dundermifflin.service.mapper;

import static com.aiep.dundermifflin.domain.InformacionContactoJefeAsserts.*;
import static com.aiep.dundermifflin.domain.InformacionContactoJefeTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InformacionContactoJefeMapperTest {

    private InformacionContactoJefeMapper informacionContactoJefeMapper;

    @BeforeEach
    void setUp() {
        informacionContactoJefeMapper = new InformacionContactoJefeMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getInformacionContactoJefeSample1();
        var actual = informacionContactoJefeMapper.toEntity(informacionContactoJefeMapper.toDto(expected));
        assertInformacionContactoJefeAllPropertiesEquals(expected, actual);
    }
}
