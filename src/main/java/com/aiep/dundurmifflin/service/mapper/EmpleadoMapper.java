package com.aiep.dundurmifflin.service.mapper;

import com.aiep.dundurmifflin.domain.Departamento;
import com.aiep.dundurmifflin.domain.Empleado;
import com.aiep.dundurmifflin.service.dto.DepartamentoDTO;
import com.aiep.dundurmifflin.service.dto.EmpleadoDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Empleado} and its DTO {@link EmpleadoDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmpleadoMapper extends EntityMapper<EmpleadoDTO, Empleado> {
    @Mapping(target = "departamentos", source = "departamentos", qualifiedByName = "departamentoIdSet")
    EmpleadoDTO toDto(Empleado s);

    @Mapping(target = "removeDepartamento", ignore = true)
    Empleado toEntity(EmpleadoDTO empleadoDTO);

    @Named("departamentoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DepartamentoDTO toDtoDepartamentoId(Departamento departamento);

    @Named("departamentoIdSet")
    default Set<DepartamentoDTO> toDtoDepartamentoIdSet(Set<Departamento> departamento) {
        return departamento.stream().map(this::toDtoDepartamentoId).collect(Collectors.toSet());
    }
}
