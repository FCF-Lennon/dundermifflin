package com.aiep.dundermifflin.web.rest;

import com.aiep.dundermifflin.repository.EmpleadoDepartamentoRepository;
import com.aiep.dundermifflin.service.EmpleadoDepartamentoService;
import com.aiep.dundermifflin.service.dto.EmpleadoDepartamentoDTO;
import com.aiep.dundermifflin.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.aiep.dundermifflin.domain.EmpleadoDepartamento}.
 */
@RestController
@RequestMapping("/api/empleado-departamentos")
public class EmpleadoDepartamentoResource {

    private static final Logger LOG = LoggerFactory.getLogger(EmpleadoDepartamentoResource.class);

    private static final String ENTITY_NAME = "empleadoDepartamento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmpleadoDepartamentoService empleadoDepartamentoService;

    private final EmpleadoDepartamentoRepository empleadoDepartamentoRepository;

    public EmpleadoDepartamentoResource(
        EmpleadoDepartamentoService empleadoDepartamentoService,
        EmpleadoDepartamentoRepository empleadoDepartamentoRepository
    ) {
        this.empleadoDepartamentoService = empleadoDepartamentoService;
        this.empleadoDepartamentoRepository = empleadoDepartamentoRepository;
    }

    /**
     * {@code POST  /empleado-departamentos} : Create a new empleadoDepartamento.
     *
     * @param empleadoDepartamentoDTO the empleadoDepartamentoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new empleadoDepartamentoDTO, or with status {@code 400 (Bad Request)} if the empleadoDepartamento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EmpleadoDepartamentoDTO> createEmpleadoDepartamento(@RequestBody EmpleadoDepartamentoDTO empleadoDepartamentoDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save EmpleadoDepartamento : {}", empleadoDepartamentoDTO);
        if (empleadoDepartamentoDTO.getId() != null) {
            throw new BadRequestAlertException("A new empleadoDepartamento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        empleadoDepartamentoDTO = empleadoDepartamentoService.save(empleadoDepartamentoDTO);
        return ResponseEntity.created(new URI("/api/empleado-departamentos/" + empleadoDepartamentoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, empleadoDepartamentoDTO.getId().toString()))
            .body(empleadoDepartamentoDTO);
    }

    /**
     * {@code PUT  /empleado-departamentos/:id} : Updates an existing empleadoDepartamento.
     *
     * @param id the id of the empleadoDepartamentoDTO to save.
     * @param empleadoDepartamentoDTO the empleadoDepartamentoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated empleadoDepartamentoDTO,
     * or with status {@code 400 (Bad Request)} if the empleadoDepartamentoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the empleadoDepartamentoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDepartamentoDTO> updateEmpleadoDepartamento(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EmpleadoDepartamentoDTO empleadoDepartamentoDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update EmpleadoDepartamento : {}, {}", id, empleadoDepartamentoDTO);
        if (empleadoDepartamentoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, empleadoDepartamentoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!empleadoDepartamentoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        empleadoDepartamentoDTO = empleadoDepartamentoService.update(empleadoDepartamentoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, empleadoDepartamentoDTO.getId().toString()))
            .body(empleadoDepartamentoDTO);
    }

    /**
     * {@code PATCH  /empleado-departamentos/:id} : Partial updates given fields of an existing empleadoDepartamento, field will ignore if it is null
     *
     * @param id the id of the empleadoDepartamentoDTO to save.
     * @param empleadoDepartamentoDTO the empleadoDepartamentoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated empleadoDepartamentoDTO,
     * or with status {@code 400 (Bad Request)} if the empleadoDepartamentoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the empleadoDepartamentoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the empleadoDepartamentoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EmpleadoDepartamentoDTO> partialUpdateEmpleadoDepartamento(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EmpleadoDepartamentoDTO empleadoDepartamentoDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EmpleadoDepartamento partially : {}, {}", id, empleadoDepartamentoDTO);
        if (empleadoDepartamentoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, empleadoDepartamentoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!empleadoDepartamentoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EmpleadoDepartamentoDTO> result = empleadoDepartamentoService.partialUpdate(empleadoDepartamentoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, empleadoDepartamentoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /empleado-departamentos} : get all the empleadoDepartamentos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of empleadoDepartamentos in body.
     */
    @GetMapping("")
    public ResponseEntity<List<EmpleadoDepartamentoDTO>> getAllEmpleadoDepartamentos(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of EmpleadoDepartamentos");
        Page<EmpleadoDepartamentoDTO> page = empleadoDepartamentoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /empleado-departamentos/:id} : get the "id" empleadoDepartamento.
     *
     * @param id the id of the empleadoDepartamentoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the empleadoDepartamentoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDepartamentoDTO> getEmpleadoDepartamento(@PathVariable("id") Long id) {
        LOG.debug("REST request to get EmpleadoDepartamento : {}", id);
        Optional<EmpleadoDepartamentoDTO> empleadoDepartamentoDTO = empleadoDepartamentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(empleadoDepartamentoDTO);
    }

    /**
     * {@code DELETE  /empleado-departamentos/:id} : delete the "id" empleadoDepartamento.
     *
     * @param id the id of the empleadoDepartamentoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleadoDepartamento(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete EmpleadoDepartamento : {}", id);
        empleadoDepartamentoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
