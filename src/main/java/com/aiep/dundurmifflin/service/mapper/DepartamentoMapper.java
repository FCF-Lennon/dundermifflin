package com.aiep.dundurmifflin.service.mapper;

import com.aiep.dundurmifflin.domain.Departamento;
import com.aiep.dundurmifflin.domain.Empleado;
import com.aiep.dundurmifflin.service.dto.DepartamentoDTO;
import com.aiep.dundurmifflin.service.dto.EmpleadoDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Departamento} and its DTO {@link DepartamentoDTO}.
 */
@Mapper(componentModel = "spring")
public interface DepartamentoMapper extends EntityMapper<DepartamentoDTO, Departamento> {
    @Mapping(target = "empleados", source = "empleados", qualifiedByName = "empleadoIdSet")
    DepartamentoDTO toDto(Departamento s);

    @Mapping(target = "empleados", ignore = true)
    @Mapping(target = "removeEmpleados", ignore = true)
    Departamento toEntity(DepartamentoDTO departamentoDTO);

    @Named("empleadoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmpleadoDTO toDtoEmpleadoId(Empleado empleado);

    @Named("empleadoIdSet")
    default Set<EmpleadoDTO> toDtoEmpleadoIdSet(Set<Empleado> empleado) {
        return empleado.stream().map(this::toDtoEmpleadoId).collect(Collectors.toSet());
    }
}
