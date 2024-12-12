package com.aiep.dundermifflin.repository;

import com.aiep.dundermifflin.domain.InformacionContactoJefe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InformacionContactoJefe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InformacionContactoJefeRepository extends JpaRepository<InformacionContactoJefe, Long> {}
