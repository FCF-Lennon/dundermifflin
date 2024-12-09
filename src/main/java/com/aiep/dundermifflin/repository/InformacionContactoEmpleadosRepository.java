package com.aiep.dundermifflin.repository;

import com.aiep.dundermifflin.domain.InformacionContactoEmpleados;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InformacionContactoEmpleados entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InformacionContactoEmpleadosRepository extends JpaRepository<InformacionContactoEmpleados, Long> {}
