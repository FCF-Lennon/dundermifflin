package com.aiep.dundurmifflin.repository;

import com.aiep.dundurmifflin.domain.Jefes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Jefes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JefesRepository extends JpaRepository<Jefes, Long> {}
