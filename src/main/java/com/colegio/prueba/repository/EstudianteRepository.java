package com.colegio.prueba.repository;

import com.colegio.prueba.domain.Asignatura;
import com.colegio.prueba.domain.Estudiante;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Estudiante entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long>, JpaSpecificationExecutor<Estudiante> {
    @Query("select estudiante from Estudiante estudiante  left join fetch estudiante.id_asignaturas ")
    List<Estudiante> findAllId();
}
