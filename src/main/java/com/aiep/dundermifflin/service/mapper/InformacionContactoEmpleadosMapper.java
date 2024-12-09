package com.aiep.dundermifflin.service.mapper;

import com.aiep.dundermifflin.domain.InformacionContactoEmpleados;
import com.aiep.dundermifflin.service.dto.InformacionContactoEmpleadosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InformacionContactoEmpleados} and its DTO {@link InformacionContactoEmpleadosDTO}.
 */
@Mapper(componentModel = "spring")
public interface InformacionContactoEmpleadosMapper extends EntityMapper<InformacionContactoEmpleadosDTO, InformacionContactoEmpleados> {}
