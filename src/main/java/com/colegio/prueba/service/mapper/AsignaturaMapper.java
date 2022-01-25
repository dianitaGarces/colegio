package com.colegio.prueba.service.mapper;

import com.colegio.prueba.domain.Asignatura;
import com.colegio.prueba.service.dto.AsignaturaDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Asignatura} and its DTO {@link AsignaturaDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProfesorMapper.class, CursoMapper.class })
public interface AsignaturaMapper extends EntityMapper<AsignaturaDTO, Asignatura> {
    @Mapping(target = "id_profesor", source = "id_profesor", qualifiedByName = "nombre")
    @Mapping(target = "id_curso", source = "id_curso", qualifiedByName = "grado")
    AsignaturaDTO toDto(Asignatura s);

    @Named("nombreSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    Set<AsignaturaDTO> toDtoNombreSet(Set<Asignatura> asignatura);
}
