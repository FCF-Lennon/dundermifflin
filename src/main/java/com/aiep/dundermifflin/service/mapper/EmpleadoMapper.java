package com.aiep.dundermifflin.service.mapper;

import com.aiep.dundermifflin.domain.Empleado;
import com.aiep.dundermifflin.service.dto.EmpleadoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Empleado} and its DTO {@link EmpleadoDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmpleadoMapper extends EntityMapper<EmpleadoDTO, Empleado> {}
