package com.aiep.dundermifflin.service.mapper;

import com.aiep.dundermifflin.domain.Departamento;
import com.aiep.dundermifflin.domain.Empleado;
import com.aiep.dundermifflin.domain.EmpleadoDepartamento;
import com.aiep.dundermifflin.service.dto.DepartamentoDTO;
import com.aiep.dundermifflin.service.dto.EmpleadoDTO;
import com.aiep.dundermifflin.service.dto.EmpleadoDepartamentoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EmpleadoDepartamento} and its DTO {@link EmpleadoDepartamentoDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmpleadoDepartamentoMapper extends EntityMapper<EmpleadoDepartamentoDTO, EmpleadoDepartamento> {
    @Mapping(target = "empleado", source = "empleado", qualifiedByName = "empleadoId")
    @Mapping(target = "departamento", source = "departamento", qualifiedByName = "departamentoId")
    EmpleadoDepartamentoDTO toDto(EmpleadoDepartamento s);

    @Named("empleadoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmpleadoDTO toDtoEmpleadoId(Empleado empleado);

    @Named("departamentoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DepartamentoDTO toDtoDepartamentoId(Departamento departamento);
}
