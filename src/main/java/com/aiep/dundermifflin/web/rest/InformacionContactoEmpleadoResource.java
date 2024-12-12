package com.aiep.dundermifflin.web.rest;

import com.aiep.dundermifflin.repository.InformacionContactoEmpleadoRepository;
import com.aiep.dundermifflin.service.InformacionContactoEmpleadoService;
import com.aiep.dundermifflin.service.dto.InformacionContactoEmpleadoDTO;
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
 * REST controller for managing {@link com.aiep.dundermifflin.domain.InformacionContactoEmpleado}.
 */
@RestController
@RequestMapping("/api/informacion-contacto-empleados")
public class InformacionContactoEmpleadoResource {

    private static final Logger LOG = LoggerFactory.getLogger(InformacionContactoEmpleadoResource.class);

    private static final String ENTITY_NAME = "informacionContactoEmpleado";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InformacionContactoEmpleadoService informacionContactoEmpleadoService;

    private final InformacionContactoEmpleadoRepository informacionContactoEmpleadoRepository;

    public InformacionContactoEmpleadoResource(
        InformacionContactoEmpleadoService informacionContactoEmpleadoService,
        InformacionContactoEmpleadoRepository informacionContactoEmpleadoRepository
    ) {
        this.informacionContactoEmpleadoService = informacionContactoEmpleadoService;
        this.informacionContactoEmpleadoRepository = informacionContactoEmpleadoRepository;
    }

    /**
     * {@code POST  /informacion-contacto-empleados} : Create a new informacionContactoEmpleado.
     *
     * @param informacionContactoEmpleadoDTO the informacionContactoEmpleadoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new informacionContactoEmpleadoDTO, or with status {@code 400 (Bad Request)} if the informacionContactoEmpleado has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<InformacionContactoEmpleadoDTO> createInformacionContactoEmpleado(
        @RequestBody InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save InformacionContactoEmpleado : {}", informacionContactoEmpleadoDTO);
        if (informacionContactoEmpleadoDTO.getId() != null) {
            throw new BadRequestAlertException("A new informacionContactoEmpleado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        informacionContactoEmpleadoDTO = informacionContactoEmpleadoService.save(informacionContactoEmpleadoDTO);
        return ResponseEntity.created(new URI("/api/informacion-contacto-empleados/" + informacionContactoEmpleadoDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, informacionContactoEmpleadoDTO.getId().toString())
            )
            .body(informacionContactoEmpleadoDTO);
    }

    /**
     * {@code PUT  /informacion-contacto-empleados/:id} : Updates an existing informacionContactoEmpleado.
     *
     * @param id the id of the informacionContactoEmpleadoDTO to save.
     * @param informacionContactoEmpleadoDTO the informacionContactoEmpleadoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated informacionContactoEmpleadoDTO,
     * or with status {@code 400 (Bad Request)} if the informacionContactoEmpleadoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the informacionContactoEmpleadoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<InformacionContactoEmpleadoDTO> updateInformacionContactoEmpleado(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update InformacionContactoEmpleado : {}, {}", id, informacionContactoEmpleadoDTO);
        if (informacionContactoEmpleadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, informacionContactoEmpleadoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!informacionContactoEmpleadoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        informacionContactoEmpleadoDTO = informacionContactoEmpleadoService.update(informacionContactoEmpleadoDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, informacionContactoEmpleadoDTO.getId().toString())
            )
            .body(informacionContactoEmpleadoDTO);
    }

    /**
     * {@code PATCH  /informacion-contacto-empleados/:id} : Partial updates given fields of an existing informacionContactoEmpleado, field will ignore if it is null
     *
     * @param id the id of the informacionContactoEmpleadoDTO to save.
     * @param informacionContactoEmpleadoDTO the informacionContactoEmpleadoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated informacionContactoEmpleadoDTO,
     * or with status {@code 400 (Bad Request)} if the informacionContactoEmpleadoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the informacionContactoEmpleadoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the informacionContactoEmpleadoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InformacionContactoEmpleadoDTO> partialUpdateInformacionContactoEmpleado(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update InformacionContactoEmpleado partially : {}, {}", id, informacionContactoEmpleadoDTO);
        if (informacionContactoEmpleadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, informacionContactoEmpleadoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!informacionContactoEmpleadoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InformacionContactoEmpleadoDTO> result = informacionContactoEmpleadoService.partialUpdate(informacionContactoEmpleadoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, informacionContactoEmpleadoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /informacion-contacto-empleados} : get all the informacionContactoEmpleados.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of informacionContactoEmpleados in body.
     */
    @GetMapping("")
    public ResponseEntity<List<InformacionContactoEmpleadoDTO>> getAllInformacionContactoEmpleados(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of InformacionContactoEmpleados");
        Page<InformacionContactoEmpleadoDTO> page = informacionContactoEmpleadoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /informacion-contacto-empleados/:id} : get the "id" informacionContactoEmpleado.
     *
     * @param id the id of the informacionContactoEmpleadoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the informacionContactoEmpleadoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<InformacionContactoEmpleadoDTO> getInformacionContactoEmpleado(@PathVariable("id") Long id) {
        LOG.debug("REST request to get InformacionContactoEmpleado : {}", id);
        Optional<InformacionContactoEmpleadoDTO> informacionContactoEmpleadoDTO = informacionContactoEmpleadoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(informacionContactoEmpleadoDTO);
    }

    /**
     * {@code DELETE  /informacion-contacto-empleados/:id} : delete the "id" informacionContactoEmpleado.
     *
     * @param id the id of the informacionContactoEmpleadoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInformacionContactoEmpleado(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete InformacionContactoEmpleado : {}", id);
        informacionContactoEmpleadoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
