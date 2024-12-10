package com.aiep.dundurmifflin.service.mapper;

import static com.aiep.dundurmifflin.domain.DepartamentosJefesAsserts.*;
import static com.aiep.dundurmifflin.domain.DepartamentosJefesTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepartamentosJefesMapperTest {

    private DepartamentosJefesMapper departamentosJefesMapper;

    @BeforeEach
    void setUp() {
        departamentosJefesMapper = new DepartamentosJefesMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDepartamentosJefesSample1();
        var actual = departamentosJefesMapper.toEntity(departamentosJefesMapper.toDto(expected));
        assertDepartamentosJefesAllPropertiesEquals(expected, actual);
    }
}
