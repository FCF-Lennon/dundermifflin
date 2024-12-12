package com.aiep.dundermifflin.service.mapper;

import com.aiep.dundermifflin.domain.InformacionContactoJefe;
import com.aiep.dundermifflin.domain.Jefes;
import com.aiep.dundermifflin.service.dto.InformacionContactoJefeDTO;
import com.aiep.dundermifflin.service.dto.JefesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InformacionContactoJefe} and its DTO {@link InformacionContactoJefeDTO}.
 */
@Mapper(componentModel = "spring")
public interface InformacionContactoJefeMapper extends EntityMapper<InformacionContactoJefeDTO, InformacionContactoJefe> {
    @Mapping(target = "jefe", source = "jefe", qualifiedByName = "jefesId")
    InformacionContactoJefeDTO toDto(InformacionContactoJefe s);

    @Named("jefesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    JefesDTO toDtoJefesId(Jefes jefes);
}
