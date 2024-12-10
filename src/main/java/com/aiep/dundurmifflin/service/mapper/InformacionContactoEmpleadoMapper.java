package com.aiep.dundurmifflin.service.mapper;

import com.aiep.dundurmifflin.domain.Empleado;
import com.aiep.dundurmifflin.domain.InformacionContactoEmpleado;
import com.aiep.dundurmifflin.service.dto.EmpleadoDTO;
import com.aiep.dundurmifflin.service.dto.InformacionContactoEmpleadoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InformacionContactoEmpleado} and its DTO {@link InformacionContactoEmpleadoDTO}.
 */
@Mapper(componentModel = "spring")
public interface InformacionContactoEmpleadoMapper extends EntityMapper<InformacionContactoEmpleadoDTO, InformacionContactoEmpleado> {
    @Mapping(target = "empleado", source = "empleado", qualifiedByName = "empleadoId")
    InformacionContactoEmpleadoDTO toDto(InformacionContactoEmpleado s);

    @Named("empleadoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmpleadoDTO toDtoEmpleadoId(Empleado empleado);
}
