package com.colegio.prueba.service.mapper;

import com.colegio.prueba.domain.Estudiante;
import com.colegio.prueba.service.dto.EstudianteDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Estudiante} and its DTO {@link EstudianteDTO}.
 */
@Mapper(componentModel = "spring", uses = { AsignaturaMapper.class })
public interface EstudianteMapper extends EntityMapper<EstudianteDTO, Estudiante> {
    @Mapping(target = "id_asignaturas", source = "id_asignaturas", qualifiedByName = "nombreSet")
    EstudianteDTO toDto(Estudiante s);

    @Mapping(target = "removeId_asignatura", ignore = true)
    Estudiante toEntity(EstudianteDTO estudianteDTO);
}
