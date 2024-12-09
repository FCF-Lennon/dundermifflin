package com.aiep.dundermifflin.web.rest;

import com.aiep.dundermifflin.repository.InformacionContactoEmpleadosRepository;
import com.aiep.dundermifflin.service.InformacionContactoEmpleadosService;
import com.aiep.dundermifflin.service.dto.InformacionContactoEmpleadosDTO;
import com.aiep.dundermifflin.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.aiep.dundermifflin.domain.InformacionContactoEmpleados}.
 */
@RestController
@RequestMapping("/api/informacion-contacto-empleados")
public class InformacionContactoEmpleadosResource {

    private static final Logger LOG = LoggerFactory.getLogger(InformacionContactoEmpleadosResource.class);

    private static final String ENTITY_NAME = "informacionContactoEmpleados";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InformacionContactoEmpleadosService informacionContactoEmpleadosService;

    private final InformacionContactoEmpleadosRepository informacionContactoEmpleadosRepository;

    public InformacionContactoEmpleadosResource(
        InformacionContactoEmpleadosService informacionContactoEmpleadosService,
        InformacionContactoEmpleadosRepository informacionContactoEmpleadosRepository
    ) {
        this.informacionContactoEmpleadosService = informacionContactoEmpleadosService;
        this.informacionContactoEmpleadosRepository = informacionContactoEmpleadosRepository;
    }

    /**
     * {@code POST  /informacion-contacto-empleados} : Create a new informacionContactoEmpleados.
     *
     * @param informacionContactoEmpleadosDTO the informacionContactoEmpleadosDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new informacionContactoEmpleadosDTO, or with status {@code 400 (Bad Request)} if the informacionContactoEmpleados has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<InformacionContactoEmpleadosDTO> createInformacionContactoEmpleados(
        @Valid @RequestBody InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save InformacionContactoEmpleados : {}", informacionContactoEmpleadosDTO);
        if (informacionContactoEmpleadosDTO.getId() != null) {
            throw new BadRequestAlertException("A new informacionContactoEmpleados cannot already have an ID", ENTITY_NAME, "idexists");
        }
        informacionContactoEmpleadosDTO = informacionContactoEmpleadosService.save(informacionContactoEmpleadosDTO);
        return ResponseEntity.created(new URI("/api/informacion-contacto-empleados/" + informacionContactoEmpleadosDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, informacionContactoEmpleadosDTO.getId().toString())
            )
            .body(informacionContactoEmpleadosDTO);
    }

    /**
     * {@code PUT  /informacion-contacto-empleados/:id} : Updates an existing informacionContactoEmpleados.
     *
     * @param id the id of the informacionContactoEmpleadosDTO to save.
     * @param informacionContactoEmpleadosDTO the informacionContactoEmpleadosDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated informacionContactoEmpleadosDTO,
     * or with status {@code 400 (Bad Request)} if the informacionContactoEmpleadosDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the informacionContactoEmpleadosDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<InformacionContactoEmpleadosDTO> updateInformacionContactoEmpleados(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update InformacionContactoEmpleados : {}, {}", id, informacionContactoEmpleadosDTO);
        if (informacionContactoEmpleadosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, informacionContactoEmpleadosDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!informacionContactoEmpleadosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        informacionContactoEmpleadosDTO = informacionContactoEmpleadosService.update(informacionContactoEmpleadosDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, informacionContactoEmpleadosDTO.getId().toString())
            )
            .body(informacionContactoEmpleadosDTO);
    }

    /**
     * {@code PATCH  /informacion-contacto-empleados/:id} : Partial updates given fields of an existing informacionContactoEmpleados, field will ignore if it is null
     *
     * @param id the id of the informacionContactoEmpleadosDTO to save.
     * @param informacionContactoEmpleadosDTO the informacionContactoEmpleadosDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated informacionContactoEmpleadosDTO,
     * or with status {@code 400 (Bad Request)} if the informacionContactoEmpleadosDTO is not valid,
     * or with status {@code 404 (Not Found)} if the informacionContactoEmpleadosDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the informacionContactoEmpleadosDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InformacionContactoEmpleadosDTO> partialUpdateInformacionContactoEmpleados(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update InformacionContactoEmpleados partially : {}, {}", id, informacionContactoEmpleadosDTO);
        if (informacionContactoEmpleadosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, informacionContactoEmpleadosDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!informacionContactoEmpleadosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InformacionContactoEmpleadosDTO> result = informacionContactoEmpleadosService.partialUpdate(
            informacionContactoEmpleadosDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, informacionContactoEmpleadosDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /informacion-contacto-empleados} : get all the informacionContactoEmpleados.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of informacionContactoEmpleados in body.
     */
    @GetMapping("")
    public ResponseEntity<List<InformacionContactoEmpleadosDTO>> getAllInformacionContactoEmpleados(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of InformacionContactoEmpleados");
        Page<InformacionContactoEmpleadosDTO> page = informacionContactoEmpleadosService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /informacion-contacto-empleados/:id} : get the "id" informacionContactoEmpleados.
     *
     * @param id the id of the informacionContactoEmpleadosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the informacionContactoEmpleadosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<InformacionContactoEmpleadosDTO> getInformacionContactoEmpleados(@PathVariable("id") Long id) {
        LOG.debug("REST request to get InformacionContactoEmpleados : {}", id);
        Optional<InformacionContactoEmpleadosDTO> informacionContactoEmpleadosDTO = informacionContactoEmpleadosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(informacionContactoEmpleadosDTO);
    }

    /**
     * {@code DELETE  /informacion-contacto-empleados/:id} : delete the "id" informacionContactoEmpleados.
     *
     * @param id the id of the informacionContactoEmpleadosDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInformacionContactoEmpleados(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete InformacionContactoEmpleados : {}", id);
        informacionContactoEmpleadosService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
