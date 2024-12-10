package com.aiep.dundurmifflin.repository;

import com.aiep.dundurmifflin.domain.Presupuesto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Presupuesto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PresupuestoRepository extends JpaRepository<Presupuesto, Long> {}
