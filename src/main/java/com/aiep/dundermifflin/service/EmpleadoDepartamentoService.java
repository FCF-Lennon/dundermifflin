package com.aiep.dundermifflin.service;

import com.aiep.dundermifflin.domain.EmpleadoDepartamento;
import com.aiep.dundermifflin.repository.EmpleadoDepartamentoRepository;
import com.aiep.dundermifflin.service.dto.EmpleadoDepartamentoDTO;
import com.aiep.dundermifflin.service.mapper.EmpleadoDepartamentoMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.aiep.dundermifflin.domain.EmpleadoDepartamento}.
 */
@Service
@Transactional
public class EmpleadoDepartamentoService {

    private static final Logger LOG = LoggerFactory.getLogger(EmpleadoDepartamentoService.class);

    private final EmpleadoDepartamentoRepository empleadoDepartamentoRepository;

    private final EmpleadoDepartamentoMapper empleadoDepartamentoMapper;

    public EmpleadoDepartamentoService(
        EmpleadoDepartamentoRepository empleadoDepartamentoRepository,
        EmpleadoDepartamentoMapper empleadoDepartamentoMapper
    ) {
        this.empleadoDepartamentoRepository = empleadoDepartamentoRepository;
        this.empleadoDepartamentoMapper = empleadoDepartamentoMapper;
    }

    /**
     * Save a empleadoDepartamento.
     *
     * @param empleadoDepartamentoDTO the entity to save.
     * @return the persisted entity.
     */
    public EmpleadoDepartamentoDTO save(EmpleadoDepartamentoDTO empleadoDepartamentoDTO) {
        LOG.debug("Request to save EmpleadoDepartamento : {}", empleadoDepartamentoDTO);
        EmpleadoDepartamento empleadoDepartamento = empleadoDepartamentoMapper.toEntity(empleadoDepartamentoDTO);
        empleadoDepartamento = empleadoDepartamentoRepository.save(empleadoDepartamento);
        return empleadoDepartamentoMapper.toDto(empleadoDepartamento);
    }

    /**
     * Update a empleadoDepartamento.
     *
     * @param empleadoDepartamentoDTO the entity to save.
     * @return the persisted entity.
     */
    public EmpleadoDepartamentoDTO update(EmpleadoDepartamentoDTO empleadoDepartamentoDTO) {
        LOG.debug("Request to update EmpleadoDepartamento : {}", empleadoDepartamentoDTO);
        EmpleadoDepartamento empleadoDepartamento = empleadoDepartamentoMapper.toEntity(empleadoDepartamentoDTO);
        empleadoDepartamento = empleadoDepartamentoRepository.save(empleadoDepartamento);
        return empleadoDepartamentoMapper.toDto(empleadoDepartamento);
    }

    /**
     * Partially update a empleadoDepartamento.
     *
     * @param empleadoDepartamentoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EmpleadoDepartamentoDTO> partialUpdate(EmpleadoDepartamentoDTO empleadoDepartamentoDTO) {
        LOG.debug("Request to partially update EmpleadoDepartamento : {}", empleadoDepartamentoDTO);

        return empleadoDepartamentoRepository
            .findById(empleadoDepartamentoDTO.getId())
            .map(existingEmpleadoDepartamento -> {
                empleadoDepartamentoMapper.partialUpdate(existingEmpleadoDepartamento, empleadoDepartamentoDTO);

                return existingEmpleadoDepartamento;
            })
            .map(empleadoDepartamentoRepository::save)
            .map(empleadoDepartamentoMapper::toDto);
    }

    /**
     * Get all the empleadoDepartamentos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EmpleadoDepartamentoDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all EmpleadoDepartamentos");
        return empleadoDepartamentoRepository.findAll(pageable).map(empleadoDepartamentoMapper::toDto);
    }

    /**
     * Get one empleadoDepartamento by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmpleadoDepartamentoDTO> findOne(Long id) {
        LOG.debug("Request to get EmpleadoDepartamento : {}", id);
        return empleadoDepartamentoRepository.findById(id).map(empleadoDepartamentoMapper::toDto);
    }

    /**
     * Delete the empleadoDepartamento by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete EmpleadoDepartamento : {}", id);
        empleadoDepartamentoRepository.deleteById(id);
    }
}
