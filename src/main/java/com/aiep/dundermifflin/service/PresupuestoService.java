package com.aiep.dundermifflin.service;

import com.aiep.dundermifflin.domain.Presupuesto;
import com.aiep.dundermifflin.repository.PresupuestoRepository;
import com.aiep.dundermifflin.service.dto.PresupuestoDTO;
import com.aiep.dundermifflin.service.mapper.PresupuestoMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.aiep.dundermifflin.domain.Presupuesto}.
 */
@Service
@Transactional
public class PresupuestoService {

    private static final Logger LOG = LoggerFactory.getLogger(PresupuestoService.class);

    private final PresupuestoRepository presupuestoRepository;

    private final PresupuestoMapper presupuestoMapper;

    public PresupuestoService(PresupuestoRepository presupuestoRepository, PresupuestoMapper presupuestoMapper) {
        this.presupuestoRepository = presupuestoRepository;
        this.presupuestoMapper = presupuestoMapper;
    }

    /**
     * Save a presupuesto.
     *
     * @param presupuestoDTO the entity to save.
     * @return the persisted entity.
     */
    public PresupuestoDTO save(PresupuestoDTO presupuestoDTO) {
        LOG.debug("Request to save Presupuesto : {}", presupuestoDTO);
        Presupuesto presupuesto = presupuestoMapper.toEntity(presupuestoDTO);
        presupuesto = presupuestoRepository.save(presupuesto);
        return presupuestoMapper.toDto(presupuesto);
    }

    /**
     * Update a presupuesto.
     *
     * @param presupuestoDTO the entity to save.
     * @return the persisted entity.
     */
    public PresupuestoDTO update(PresupuestoDTO presupuestoDTO) {
        LOG.debug("Request to update Presupuesto : {}", presupuestoDTO);
        Presupuesto presupuesto = presupuestoMapper.toEntity(presupuestoDTO);
        presupuesto = presupuestoRepository.save(presupuesto);
        return presupuestoMapper.toDto(presupuesto);
    }

    /**
     * Partially update a presupuesto.
     *
     * @param presupuestoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PresupuestoDTO> partialUpdate(PresupuestoDTO presupuestoDTO) {
        LOG.debug("Request to partially update Presupuesto : {}", presupuestoDTO);

        return presupuestoRepository
            .findById(presupuestoDTO.getId())
            .map(existingPresupuesto -> {
                presupuestoMapper.partialUpdate(existingPresupuesto, presupuestoDTO);

                return existingPresupuesto;
            })
            .map(presupuestoRepository::save)
            .map(presupuestoMapper::toDto);
    }

    /**
     * Get all the presupuestos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PresupuestoDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Presupuestos");
        return presupuestoRepository.findAll(pageable).map(presupuestoMapper::toDto);
    }

    /**
     * Get one presupuesto by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PresupuestoDTO> findOne(Long id) {
        LOG.debug("Request to get Presupuesto : {}", id);
        return presupuestoRepository.findById(id).map(presupuestoMapper::toDto);
    }

    /**
     * Delete the presupuesto by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Presupuesto : {}", id);
        presupuestoRepository.deleteById(id);
    }
}
