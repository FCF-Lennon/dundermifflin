package com.aiep.dundermifflin.service;

import com.aiep.dundermifflin.domain.InformacionContactoEmpleado;
import com.aiep.dundermifflin.repository.InformacionContactoEmpleadoRepository;
import com.aiep.dundermifflin.service.dto.InformacionContactoEmpleadoDTO;
import com.aiep.dundermifflin.service.mapper.InformacionContactoEmpleadoMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.aiep.dundermifflin.domain.InformacionContactoEmpleado}.
 */
@Service
@Transactional
public class InformacionContactoEmpleadoService {

    private static final Logger LOG = LoggerFactory.getLogger(InformacionContactoEmpleadoService.class);

    private final InformacionContactoEmpleadoRepository informacionContactoEmpleadoRepository;

    private final InformacionContactoEmpleadoMapper informacionContactoEmpleadoMapper;

    public InformacionContactoEmpleadoService(
        InformacionContactoEmpleadoRepository informacionContactoEmpleadoRepository,
        InformacionContactoEmpleadoMapper informacionContactoEmpleadoMapper
    ) {
        this.informacionContactoEmpleadoRepository = informacionContactoEmpleadoRepository;
        this.informacionContactoEmpleadoMapper = informacionContactoEmpleadoMapper;
    }

    /**
     * Save a informacionContactoEmpleado.
     *
     * @param informacionContactoEmpleadoDTO the entity to save.
     * @return the persisted entity.
     */
    public InformacionContactoEmpleadoDTO save(InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO) {
        LOG.debug("Request to save InformacionContactoEmpleado : {}", informacionContactoEmpleadoDTO);
        InformacionContactoEmpleado informacionContactoEmpleado = informacionContactoEmpleadoMapper.toEntity(
            informacionContactoEmpleadoDTO
        );
        informacionContactoEmpleado = informacionContactoEmpleadoRepository.save(informacionContactoEmpleado);
        return informacionContactoEmpleadoMapper.toDto(informacionContactoEmpleado);
    }

    /**
     * Update a informacionContactoEmpleado.
     *
     * @param informacionContactoEmpleadoDTO the entity to save.
     * @return the persisted entity.
     */
    public InformacionContactoEmpleadoDTO update(InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO) {
        LOG.debug("Request to update InformacionContactoEmpleado : {}", informacionContactoEmpleadoDTO);
        InformacionContactoEmpleado informacionContactoEmpleado = informacionContactoEmpleadoMapper.toEntity(
            informacionContactoEmpleadoDTO
        );
        informacionContactoEmpleado = informacionContactoEmpleadoRepository.save(informacionContactoEmpleado);
        return informacionContactoEmpleadoMapper.toDto(informacionContactoEmpleado);
    }

    /**
     * Partially update a informacionContactoEmpleado.
     *
     * @param informacionContactoEmpleadoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<InformacionContactoEmpleadoDTO> partialUpdate(InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO) {
        LOG.debug("Request to partially update InformacionContactoEmpleado : {}", informacionContactoEmpleadoDTO);

        return informacionContactoEmpleadoRepository
            .findById(informacionContactoEmpleadoDTO.getId())
            .map(existingInformacionContactoEmpleado -> {
                informacionContactoEmpleadoMapper.partialUpdate(existingInformacionContactoEmpleado, informacionContactoEmpleadoDTO);

                return existingInformacionContactoEmpleado;
            })
            .map(informacionContactoEmpleadoRepository::save)
            .map(informacionContactoEmpleadoMapper::toDto);
    }

    /**
     * Get all the informacionContactoEmpleados.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InformacionContactoEmpleadoDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all InformacionContactoEmpleados");
        return informacionContactoEmpleadoRepository.findAll(pageable).map(informacionContactoEmpleadoMapper::toDto);
    }

    /**
     * Get one informacionContactoEmpleado by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InformacionContactoEmpleadoDTO> findOne(Long id) {
        LOG.debug("Request to get InformacionContactoEmpleado : {}", id);
        return informacionContactoEmpleadoRepository.findById(id).map(informacionContactoEmpleadoMapper::toDto);
    }

    /**
     * Delete the informacionContactoEmpleado by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete InformacionContactoEmpleado : {}", id);
        informacionContactoEmpleadoRepository.deleteById(id);
    }
}
