package com.colegio.prueba.service.mapper;

import com.colegio.prueba.domain.Asignatura;
import com.colegio.prueba.service.dto.AsignaturaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Asignatura} and its DTO {@link AsignaturaDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProfesorMapper.class, EstudianteMapper.class, CursoMapper.class })
public interface AsignaturaMapper extends EntityMapper<AsignaturaDTO, Asignatura> {
    @Mapping(target = "id_profesor", source = "id_profesor", qualifiedByName = "nombre")
    @Mapping(target = "id_estudiantes", source = "id_estudiantes", qualifiedByName = "nombreSet")
    @Mapping(target = "id_curso", source = "id_curso", qualifiedByName = "grado")
    AsignaturaDTO toDto(Asignatura s);

    @Mapping(target = "removeId_estudiante", ignore = true)
    Asignatura toEntity(AsignaturaDTO asignaturaDTO);
}
