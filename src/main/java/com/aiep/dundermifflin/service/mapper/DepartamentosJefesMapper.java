package com.aiep.dundermifflin.service.mapper;

import com.aiep.dundermifflin.domain.Departamento;
import com.aiep.dundermifflin.domain.DepartamentosJefes;
import com.aiep.dundermifflin.domain.Jefes;
import com.aiep.dundermifflin.service.dto.DepartamentoDTO;
import com.aiep.dundermifflin.service.dto.DepartamentosJefesDTO;
import com.aiep.dundermifflin.service.dto.JefesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DepartamentosJefes} and its DTO {@link DepartamentosJefesDTO}.
 */
@Mapper(componentModel = "spring")
public interface DepartamentosJefesMapper extends EntityMapper<DepartamentosJefesDTO, DepartamentosJefes> {
    @Mapping(target = "departamento", source = "departamento", qualifiedByName = "departamentoId")
    @Mapping(target = "jefe", source = "jefe", qualifiedByName = "jefesId")
    DepartamentosJefesDTO toDto(DepartamentosJefes s);

    @Named("departamentoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DepartamentoDTO toDtoDepartamentoId(Departamento departamento);

    @Named("jefesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    JefesDTO toDtoJefesId(Jefes jefes);
}
