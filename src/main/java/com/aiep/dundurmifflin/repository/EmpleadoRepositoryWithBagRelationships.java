package com.aiep.dundurmifflin.repository;

import com.aiep.dundurmifflin.domain.Empleado;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface EmpleadoRepositoryWithBagRelationships {
    Optional<Empleado> fetchBagRelationships(Optional<Empleado> empleado);

    List<Empleado> fetchBagRelationships(List<Empleado> empleados);

    Page<Empleado> fetchBagRelationships(Page<Empleado> empleados);
}
