package com.aiep.dundurmifflin.repository;

import com.aiep.dundurmifflin.domain.DepartamentosJefes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DepartamentosJefes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepartamentosJefesRepository extends JpaRepository<DepartamentosJefes, Long> {}
