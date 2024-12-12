package com.aiep.dundermifflin.repository;

import com.aiep.dundermifflin.domain.EmpleadoDepartamento;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the EmpleadoDepartamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmpleadoDepartamentoRepository extends JpaRepository<EmpleadoDepartamento, Long> {}
