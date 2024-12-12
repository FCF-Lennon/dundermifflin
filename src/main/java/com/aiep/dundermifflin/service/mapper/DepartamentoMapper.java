package com.aiep.dundermifflin.service.mapper;

import com.aiep.dundermifflin.domain.Departamento;
import com.aiep.dundermifflin.service.dto.DepartamentoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Departamento} and its DTO {@link DepartamentoDTO}.
 */
@Mapper(componentModel = "spring")
public interface DepartamentoMapper extends EntityMapper<DepartamentoDTO, Departamento> {}
