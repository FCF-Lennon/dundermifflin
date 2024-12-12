package com.aiep.dundermifflin.service;

import com.aiep.dundermifflin.domain.DepartamentosJefes;
import com.aiep.dundermifflin.repository.DepartamentosJefesRepository;
import com.aiep.dundermifflin.service.dto.DepartamentosJefesDTO;
import com.aiep.dundermifflin.service.mapper.DepartamentosJefesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.aiep.dundermifflin.domain.DepartamentosJefes}.
 */
@Service
@Transactional
public class DepartamentosJefesService {

    private static final Logger LOG = LoggerFactory.getLogger(DepartamentosJefesService.class);

    private final DepartamentosJefesRepository departamentosJefesRepository;

    private final DepartamentosJefesMapper departamentosJefesMapper;

    public DepartamentosJefesService(
        DepartamentosJefesRepository departamentosJefesRepository,
        DepartamentosJefesMapper departamentosJefesMapper
    ) {
        this.departamentosJefesRepository = departamentosJefesRepository;
        this.departamentosJefesMapper = departamentosJefesMapper;
    }

    /**
     * Save a departamentosJefes.
     *
     * @param departamentosJefesDTO the entity to save.
     * @return the persisted entity.
     */
    public DepartamentosJefesDTO save(DepartamentosJefesDTO departamentosJefesDTO) {
        LOG.debug("Request to save DepartamentosJefes : {}", departamentosJefesDTO);
        DepartamentosJefes departamentosJefes = departamentosJefesMapper.toEntity(departamentosJefesDTO);
        departamentosJefes = departamentosJefesRepository.save(departamentosJefes);
        return departamentosJefesMapper.toDto(departamentosJefes);
    }

    /**
     * Update a departamentosJefes.
     *
     * @param departamentosJefesDTO the entity to save.
     * @return the persisted entity.
     */
    public DepartamentosJefesDTO update(DepartamentosJefesDTO departamentosJefesDTO) {
        LOG.debug("Request to update DepartamentosJefes : {}", departamentosJefesDTO);
        DepartamentosJefes departamentosJefes = departamentosJefesMapper.toEntity(departamentosJefesDTO);
        departamentosJefes = departamentosJefesRepository.save(departamentosJefes);
        return departamentosJefesMapper.toDto(departamentosJefes);
    }

    /**
     * Partially update a departamentosJefes.
     *
     * @param departamentosJefesDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DepartamentosJefesDTO> partialUpdate(DepartamentosJefesDTO departamentosJefesDTO) {
        LOG.debug("Request to partially update DepartamentosJefes : {}", departamentosJefesDTO);

        return departamentosJefesRepository
            .findById(departamentosJefesDTO.getId())
            .map(existingDepartamentosJefes -> {
                departamentosJefesMapper.partialUpdate(existingDepartamentosJefes, departamentosJefesDTO);

                return existingDepartamentosJefes;
            })
            .map(departamentosJefesRepository::save)
            .map(departamentosJefesMapper::toDto);
    }

    /**
     * Get all the departamentosJefes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DepartamentosJefesDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all DepartamentosJefes");
        return departamentosJefesRepository.findAll(pageable).map(departamentosJefesMapper::toDto);
    }

    /**
     * Get one departamentosJefes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DepartamentosJefesDTO> findOne(Long id) {
        LOG.debug("Request to get DepartamentosJefes : {}", id);
        return departamentosJefesRepository.findById(id).map(departamentosJefesMapper::toDto);
    }

    /**
     * Delete the departamentosJefes by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DepartamentosJefes : {}", id);
        departamentosJefesRepository.deleteById(id);
    }
}
