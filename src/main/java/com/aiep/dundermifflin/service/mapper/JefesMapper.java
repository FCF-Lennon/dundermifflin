package com.aiep.dundermifflin.service.mapper;

import com.aiep.dundermifflin.domain.Jefes;
import com.aiep.dundermifflin.service.dto.JefesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Jefes} and its DTO {@link JefesDTO}.
 */
@Mapper(componentModel = "spring")
public interface JefesMapper extends EntityMapper<JefesDTO, Jefes> {}
