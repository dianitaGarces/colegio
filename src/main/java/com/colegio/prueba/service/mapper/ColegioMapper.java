package com.colegio.prueba.service.mapper;

import com.colegio.prueba.domain.Colegio;
import com.colegio.prueba.service.dto.ColegioDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Colegio} and its DTO {@link ColegioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ColegioMapper extends EntityMapper<ColegioDTO, Colegio> {
    @Named("nombre")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    ColegioDTO toDtoNombre(Colegio colegio);
}
