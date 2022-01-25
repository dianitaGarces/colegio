package com.colegio.prueba.repository;

import com.colegio.prueba.domain.Asignatura;
import com.colegio.prueba.domain.Estudiante;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Asignatura entity.
 */
@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {
    @Query("select asignatura from Asignatura asignatura where id_profesor.id =:id")
    List<Asignatura> findAllId(@Param("id") Long id);
}
