package com.aiep.dundermifflin.service.mapper;

import static com.aiep.dundermifflin.domain.JefesAsserts.*;
import static com.aiep.dundermifflin.domain.JefesTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JefesMapperTest {

    private JefesMapper jefesMapper;

    @BeforeEach
    void setUp() {
        jefesMapper = new JefesMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getJefesSample1();
        var actual = jefesMapper.toEntity(jefesMapper.toDto(expected));
        assertJefesAllPropertiesEquals(expected, actual);
    }
}
