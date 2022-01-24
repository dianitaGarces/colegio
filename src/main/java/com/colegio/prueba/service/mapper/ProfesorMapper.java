package com.colegio.prueba.service.mapper;

import com.colegio.prueba.domain.Profesor;
import com.colegio.prueba.service.dto.ProfesorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Profesor} and its DTO {@link ProfesorDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProfesorMapper extends EntityMapper<ProfesorDTO, Profesor> {
    @Named("nombre")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    ProfesorDTO toDtoNombre(Profesor profesor);
}
