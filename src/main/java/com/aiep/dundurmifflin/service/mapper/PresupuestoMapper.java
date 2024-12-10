package com.aiep.dundurmifflin.service.mapper;

import com.aiep.dundurmifflin.domain.Departamento;
import com.aiep.dundurmifflin.domain.Presupuesto;
import com.aiep.dundurmifflin.service.dto.DepartamentoDTO;
import com.aiep.dundurmifflin.service.dto.PresupuestoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Presupuesto} and its DTO {@link PresupuestoDTO}.
 */
@Mapper(componentModel = "spring")
public interface PresupuestoMapper extends EntityMapper<PresupuestoDTO, Presupuesto> {
    @Mapping(target = "departamento", source = "departamento", qualifiedByName = "departamentoId")
    PresupuestoDTO toDto(Presupuesto s);

    @Named("departamentoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DepartamentoDTO toDtoDepartamentoId(Departamento departamento);
}
