package com.aiep.dundermifflin.service;

import com.aiep.dundermifflin.domain.Jefes;
import com.aiep.dundermifflin.repository.JefesRepository;
import com.aiep.dundermifflin.service.dto.JefesDTO;
import com.aiep.dundermifflin.service.mapper.JefesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.aiep.dundermifflin.domain.Jefes}.
 */
@Service
@Transactional
public class JefesService {

    private static final Logger LOG = LoggerFactory.getLogger(JefesService.class);

    private final JefesRepository jefesRepository;

    private final JefesMapper jefesMapper;

    public JefesService(JefesRepository jefesRepository, JefesMapper jefesMapper) {
        this.jefesRepository = jefesRepository;
        this.jefesMapper = jefesMapper;
    }

    /**
     * Save a jefes.
     *
     * @param jefesDTO the entity to save.
     * @return the persisted entity.
     */
    public JefesDTO save(JefesDTO jefesDTO) {
        LOG.debug("Request to save Jefes : {}", jefesDTO);
        Jefes jefes = jefesMapper.toEntity(jefesDTO);
        jefes = jefesRepository.save(jefes);
        return jefesMapper.toDto(jefes);
    }

    /**
     * Update a jefes.
     *
     * @param jefesDTO the entity to save.
     * @return the persisted entity.
     */
    public JefesDTO update(JefesDTO jefesDTO) {
        LOG.debug("Request to update Jefes : {}", jefesDTO);
        Jefes jefes = jefesMapper.toEntity(jefesDTO);
        jefes = jefesRepository.save(jefes);
        return jefesMapper.toDto(jefes);
    }

    /**
     * Partially update a jefes.
     *
     * @param jefesDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<JefesDTO> partialUpdate(JefesDTO jefesDTO) {
        LOG.debug("Request to partially update Jefes : {}", jefesDTO);

        return jefesRepository
            .findById(jefesDTO.getId())
            .map(existingJefes -> {
                jefesMapper.partialUpdate(existingJefes, jefesDTO);

                return existingJefes;
            })
            .map(jefesRepository::save)
            .map(jefesMapper::toDto);
    }

    /**
     * Get all the jefes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<JefesDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Jefes");
        return jefesRepository.findAll(pageable).map(jefesMapper::toDto);
    }

    /**
     * Get one jefes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<JefesDTO> findOne(Long id) {
        LOG.debug("Request to get Jefes : {}", id);
        return jefesRepository.findById(id).map(jefesMapper::toDto);
    }

    /**
     * Delete the jefes by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Jefes : {}", id);
        jefesRepository.deleteById(id);
    }
}
