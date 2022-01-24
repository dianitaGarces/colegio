package com.colegio.prueba.repository;

import com.colegio.prueba.domain.Colegio;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Colegio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ColegioRepository extends JpaRepository<Colegio, Long> {}
