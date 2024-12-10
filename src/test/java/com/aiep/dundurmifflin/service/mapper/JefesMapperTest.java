package com.aiep.dundurmifflin.service.mapper;

import static com.aiep.dundurmifflin.domain.JefesAsserts.*;
import static com.aiep.dundurmifflin.domain.JefesTestSamples.*;

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
