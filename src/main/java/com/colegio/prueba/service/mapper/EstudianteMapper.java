package com.colegio.prueba.service.mapper;

import com.colegio.prueba.domain.Estudiante;
import com.colegio.prueba.service.dto.EstudianteDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Estudiante} and its DTO {@link EstudianteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EstudianteMapper extends EntityMapper<EstudianteDTO, Estudiante> {
    @Named("nombreSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    Set<EstudianteDTO> toDtoNombreSet(Set<Estudiante> estudiante);
}
