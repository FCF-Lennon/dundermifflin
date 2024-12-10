package com.aiep.dundurmifflin.service.mapper;

import com.aiep.dundurmifflin.domain.Jefes;
import com.aiep.dundurmifflin.service.dto.JefesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Jefes} and its DTO {@link JefesDTO}.
 */
@Mapper(componentModel = "spring")
public interface JefesMapper extends EntityMapper<JefesDTO, Jefes> {}
