package com.aiep.dundermifflin.repository;

import com.aiep.dundermifflin.domain.Jefes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Jefes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JefesRepository extends JpaRepository<Jefes, Long> {}
