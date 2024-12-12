package com.aiep.dundermifflin.service;

import com.aiep.dundermifflin.domain.InformacionContactoJefe;
import com.aiep.dundermifflin.repository.InformacionContactoJefeRepository;
import com.aiep.dundermifflin.service.dto.InformacionContactoJefeDTO;
import com.aiep.dundermifflin.service.mapper.InformacionContactoJefeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.aiep.dundermifflin.domain.InformacionContactoJefe}.
 */
@Service
@Transactional
public class InformacionContactoJefeService {

    private static final Logger LOG = LoggerFactory.getLogger(InformacionContactoJefeService.class);

    private final InformacionContactoJefeRepository informacionContactoJefeRepository;

    private final InformacionContactoJefeMapper informacionContactoJefeMapper;

    public InformacionContactoJefeService(
        InformacionContactoJefeRepository informacionContactoJefeRepository,
        InformacionContactoJefeMapper informacionContactoJefeMapper
    ) {
        this.informacionContactoJefeRepository = informacionContactoJefeRepository;
        this.informacionContactoJefeMapper = informacionContactoJefeMapper;
    }

    /**
     * Save a informacionContactoJefe.
     *
     * @param informacionContactoJefeDTO the entity to save.
     * @return the persisted entity.
     */
    public InformacionContactoJefeDTO save(InformacionContactoJefeDTO informacionContactoJefeDTO) {
        LOG.debug("Request to save InformacionContactoJefe : {}", informacionContactoJefeDTO);
        InformacionContactoJefe informacionContactoJefe = informacionContactoJefeMapper.toEntity(informacionContactoJefeDTO);
        informacionContactoJefe = informacionContactoJefeRepository.save(informacionContactoJefe);
        return informacionContactoJefeMapper.toDto(informacionContactoJefe);
    }

    /**
     * Update a informacionContactoJefe.
     *
     * @param informacionContactoJefeDTO the entity to save.
     * @return the persisted entity.
     */
    public InformacionContactoJefeDTO update(InformacionContactoJefeDTO informacionContactoJefeDTO) {
        LOG.debug("Request to update InformacionContactoJefe : {}", informacionContactoJefeDTO);
        InformacionContactoJefe informacionContactoJefe = informacionContactoJefeMapper.toEntity(informacionContactoJefeDTO);
        informacionContactoJefe = informacionContactoJefeRepository.save(informacionContactoJefe);
        return informacionContactoJefeMapper.toDto(informacionContactoJefe);
    }

    /**
     * Partially update a informacionContactoJefe.
     *
     * @param informacionContactoJefeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<InformacionContactoJefeDTO> partialUpdate(InformacionContactoJefeDTO informacionContactoJefeDTO) {
        LOG.debug("Request to partially update InformacionContactoJefe : {}", informacionContactoJefeDTO);

        return informacionContactoJefeRepository
            .findById(informacionContactoJefeDTO.getId())
            .map(existingInformacionContactoJefe -> {
                informacionContactoJefeMapper.partialUpdate(existingInformacionContactoJefe, informacionContactoJefeDTO);

                return existingInformacionContactoJefe;
            })
            .map(informacionContactoJefeRepository::save)
            .map(informacionContactoJefeMapper::toDto);
    }

    /**
     * Get all the informacionContactoJefes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InformacionContactoJefeDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all InformacionContactoJefes");
        return informacionContactoJefeRepository.findAll(pageable).map(informacionContactoJefeMapper::toDto);
    }

    /**
     * Get one informacionContactoJefe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InformacionContactoJefeDTO> findOne(Long id) {
        LOG.debug("Request to get InformacionContactoJefe : {}", id);
        return informacionContactoJefeRepository.findById(id).map(informacionContactoJefeMapper::toDto);
    }

    /**
     * Delete the informacionContactoJefe by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete InformacionContactoJefe : {}", id);
        informacionContactoJefeRepository.deleteById(id);
    }
}
