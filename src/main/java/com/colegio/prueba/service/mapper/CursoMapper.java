package com.colegio.prueba.service.mapper;

import com.colegio.prueba.domain.Curso;
import com.colegio.prueba.service.dto.CursoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Curso} and its DTO {@link CursoDTO}.
 */
@Mapper(componentModel = "spring", uses = { ColegioMapper.class })
public interface CursoMapper extends EntityMapper<CursoDTO, Curso> {
    @Mapping(target = "id_colegio", source = "id_colegio", qualifiedByName = "nombre")
    CursoDTO toDto(Curso s);

    @Named("grado")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "grado", source = "grado")
    CursoDTO toDtoGrado(Curso curso);
}
