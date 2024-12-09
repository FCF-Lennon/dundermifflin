package com.aiep.dundermifflin.service.impl;

import com.aiep.dundermifflin.domain.InformacionContactoEmpleados;
import com.aiep.dundermifflin.repository.InformacionContactoEmpleadosRepository;
import com.aiep.dundermifflin.service.InformacionContactoEmpleadosService;
import com.aiep.dundermifflin.service.dto.InformacionContactoEmpleadosDTO;
import com.aiep.dundermifflin.service.mapper.InformacionContactoEmpleadosMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.aiep.dundermifflin.domain.InformacionContactoEmpleados}.
 */
@Service
@Transactional
public class InformacionContactoEmpleadosServiceImpl implements InformacionContactoEmpleadosService {

    private static final Logger LOG = LoggerFactory.getLogger(InformacionContactoEmpleadosServiceImpl.class);

    private final InformacionContactoEmpleadosRepository informacionContactoEmpleadosRepository;

    private final InformacionContactoEmpleadosMapper informacionContactoEmpleadosMapper;

    public InformacionContactoEmpleadosServiceImpl(
        InformacionContactoEmpleadosRepository informacionContactoEmpleadosRepository,
        InformacionContactoEmpleadosMapper informacionContactoEmpleadosMapper
    ) {
        this.informacionContactoEmpleadosRepository = informacionContactoEmpleadosRepository;
        this.informacionContactoEmpleadosMapper = informacionContactoEmpleadosMapper;
    }

    @Override
    public InformacionContactoEmpleadosDTO save(InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO) {
        LOG.debug("Request to save InformacionContactoEmpleados : {}", informacionContactoEmpleadosDTO);
        InformacionContactoEmpleados informacionContactoEmpleados = informacionContactoEmpleadosMapper.toEntity(
            informacionContactoEmpleadosDTO
        );
        informacionContactoEmpleados = informacionContactoEmpleadosRepository.save(informacionContactoEmpleados);
        return informacionContactoEmpleadosMapper.toDto(informacionContactoEmpleados);
    }

    @Override
    public InformacionContactoEmpleadosDTO update(InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO) {
        LOG.debug("Request to update InformacionContactoEmpleados : {}", informacionContactoEmpleadosDTO);
        InformacionContactoEmpleados informacionContactoEmpleados = informacionContactoEmpleadosMapper.toEntity(
            informacionContactoEmpleadosDTO
        );
        informacionContactoEmpleados = informacionContactoEmpleadosRepository.save(informacionContactoEmpleados);
        return informacionContactoEmpleadosMapper.toDto(informacionContactoEmpleados);
    }

    @Override
    public Optional<InformacionContactoEmpleadosDTO> partialUpdate(InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO) {
        LOG.debug("Request to partially update InformacionContactoEmpleados : {}", informacionContactoEmpleadosDTO);

        return informacionContactoEmpleadosRepository
            .findById(informacionContactoEmpleadosDTO.getId())
            .map(existingInformacionContactoEmpleados -> {
                informacionContactoEmpleadosMapper.partialUpdate(existingInformacionContactoEmpleados, informacionContactoEmpleadosDTO);

                return existingInformacionContactoEmpleados;
            })
            .map(informacionContactoEmpleadosRepository::save)
            .map(informacionContactoEmpleadosMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InformacionContactoEmpleadosDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all InformacionContactoEmpleados");
        return informacionContactoEmpleadosRepository.findAll(pageable).map(informacionContactoEmpleadosMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InformacionContactoEmpleadosDTO> findOne(Long id) {
        LOG.debug("Request to get InformacionContactoEmpleados : {}", id);
        return informacionContactoEmpleadosRepository.findById(id).map(informacionContactoEmpleadosMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete InformacionContactoEmpleados : {}", id);
        informacionContactoEmpleadosRepository.deleteById(id);
    }
}
