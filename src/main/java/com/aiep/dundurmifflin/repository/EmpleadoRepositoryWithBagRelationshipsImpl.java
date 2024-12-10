package com.aiep.dundurmifflin.repository;

import com.aiep.dundurmifflin.domain.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class EmpleadoRepositoryWithBagRelationshipsImpl implements EmpleadoRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String EMPLEADOS_PARAMETER = "empleados";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Empleado> fetchBagRelationships(Optional<Empleado> empleado) {
        return empleado.map(this::fetchDepartamentos);
    }

    @Override
    public Page<Empleado> fetchBagRelationships(Page<Empleado> empleados) {
        return new PageImpl<>(fetchBagRelationships(empleados.getContent()), empleados.getPageable(), empleados.getTotalElements());
    }

    @Override
    public List<Empleado> fetchBagRelationships(List<Empleado> empleados) {
        return Optional.of(empleados).map(this::fetchDepartamentos).orElse(Collections.emptyList());
    }

    Empleado fetchDepartamentos(Empleado result) {
        return entityManager
            .createQuery(
                "select empleado from Empleado empleado left join fetch empleado.departamentos where empleado.id = :id",
                Empleado.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Empleado> fetchDepartamentos(List<Empleado> empleados) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, empleados.size()).forEach(index -> order.put(empleados.get(index).getId(), index));
        List<Empleado> result = entityManager
            .createQuery(
                "select empleado from Empleado empleado left join fetch empleado.departamentos where empleado in :empleados",
                Empleado.class
            )
            .setParameter(EMPLEADOS_PARAMETER, empleados)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
