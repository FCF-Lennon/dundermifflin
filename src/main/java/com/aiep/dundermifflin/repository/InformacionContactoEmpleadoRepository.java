package com.aiep.dundermifflin.repository;

import com.aiep.dundermifflin.domain.InformacionContactoEmpleado;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InformacionContactoEmpleado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InformacionContactoEmpleadoRepository extends JpaRepository<InformacionContactoEmpleado, Long> {}
