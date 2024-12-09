package com.aiep.dundermifflin.service;

import com.aiep.dundermifflin.service.dto.InformacionContactoEmpleadosDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.aiep.dundermifflin.domain.InformacionContactoEmpleados}.
 */
public interface InformacionContactoEmpleadosService {
    /**
     * Save a informacionContactoEmpleados.
     *
     * @param informacionContactoEmpleadosDTO the entity to save.
     * @return the persisted entity.
     */
    InformacionContactoEmpleadosDTO save(InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO);

    /**
     * Updates a informacionContactoEmpleados.
     *
     * @param informacionContactoEmpleadosDTO the entity to update.
     * @return the persisted entity.
     */
    InformacionContactoEmpleadosDTO update(InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO);

    /**
     * Partially updates a informacionContactoEmpleados.
     *
     * @param informacionContactoEmpleadosDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InformacionContactoEmpleadosDTO> partialUpdate(InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO);

    /**
     * Get all the informacionContactoEmpleados.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InformacionContactoEmpleadosDTO> findAll(Pageable pageable);

    /**
     * Get the "id" informacionContactoEmpleados.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InformacionContactoEmpleadosDTO> findOne(Long id);

    /**
     * Delete the "id" informacionContactoEmpleados.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
