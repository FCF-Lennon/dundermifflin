package com.aiep.dundermifflin.service.mapper;

import com.aiep.dundermifflin.domain.Departamento;
import com.aiep.dundermifflin.domain.Presupuesto;
import com.aiep.dundermifflin.service.dto.DepartamentoDTO;
import com.aiep.dundermifflin.service.dto.PresupuestoDTO;
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
