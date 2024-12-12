package com.aiep.dundermifflin.web.rest;

import com.aiep.dundermifflin.repository.InformacionContactoJefeRepository;
import com.aiep.dundermifflin.service.InformacionContactoJefeService;
import com.aiep.dundermifflin.service.dto.InformacionContactoJefeDTO;
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
 * REST controller for managing {@link com.aiep.dundermifflin.domain.InformacionContactoJefe}.
 */
@RestController
@RequestMapping("/api/informacion-contacto-jefes")
public class InformacionContactoJefeResource {

    private static final Logger LOG = LoggerFactory.getLogger(InformacionContactoJefeResource.class);

    private static final String ENTITY_NAME = "informacionContactoJefe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InformacionContactoJefeService informacionContactoJefeService;

    private final InformacionContactoJefeRepository informacionContactoJefeRepository;

    public InformacionContactoJefeResource(
        InformacionContactoJefeService informacionContactoJefeService,
        InformacionContactoJefeRepository informacionContactoJefeRepository
    ) {
        this.informacionContactoJefeService = informacionContactoJefeService;
        this.informacionContactoJefeRepository = informacionContactoJefeRepository;
    }

    /**
     * {@code POST  /informacion-contacto-jefes} : Create a new informacionContactoJefe.
     *
     * @param informacionContactoJefeDTO the informacionContactoJefeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new informacionContactoJefeDTO, or with status {@code 400 (Bad Request)} if the informacionContactoJefe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<InformacionContactoJefeDTO> createInformacionContactoJefe(
        @RequestBody InformacionContactoJefeDTO informacionContactoJefeDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save InformacionContactoJefe : {}", informacionContactoJefeDTO);
        if (informacionContactoJefeDTO.getId() != null) {
            throw new BadRequestAlertException("A new informacionContactoJefe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        informacionContactoJefeDTO = informacionContactoJefeService.save(informacionContactoJefeDTO);
        return ResponseEntity.created(new URI("/api/informacion-contacto-jefes/" + informacionContactoJefeDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, informacionContactoJefeDTO.getId().toString())
            )
            .body(informacionContactoJefeDTO);
    }

    /**
     * {@code PUT  /informacion-contacto-jefes/:id} : Updates an existing informacionContactoJefe.
     *
     * @param id the id of the informacionContactoJefeDTO to save.
     * @param informacionContactoJefeDTO the informacionContactoJefeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated informacionContactoJefeDTO,
     * or with status {@code 400 (Bad Request)} if the informacionContactoJefeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the informacionContactoJefeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<InformacionContactoJefeDTO> updateInformacionContactoJefe(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InformacionContactoJefeDTO informacionContactoJefeDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update InformacionContactoJefe : {}, {}", id, informacionContactoJefeDTO);
        if (informacionContactoJefeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, informacionContactoJefeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!informacionContactoJefeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        informacionContactoJefeDTO = informacionContactoJefeService.update(informacionContactoJefeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, informacionContactoJefeDTO.getId().toString()))
            .body(informacionContactoJefeDTO);
    }

    /**
     * {@code PATCH  /informacion-contacto-jefes/:id} : Partial updates given fields of an existing informacionContactoJefe, field will ignore if it is null
     *
     * @param id the id of the informacionContactoJefeDTO to save.
     * @param informacionContactoJefeDTO the informacionContactoJefeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated informacionContactoJefeDTO,
     * or with status {@code 400 (Bad Request)} if the informacionContactoJefeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the informacionContactoJefeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the informacionContactoJefeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InformacionContactoJefeDTO> partialUpdateInformacionContactoJefe(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InformacionContactoJefeDTO informacionContactoJefeDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update InformacionContactoJefe partially : {}, {}", id, informacionContactoJefeDTO);
        if (informacionContactoJefeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, informacionContactoJefeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!informacionContactoJefeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InformacionContactoJefeDTO> result = informacionContactoJefeService.partialUpdate(informacionContactoJefeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, informacionContactoJefeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /informacion-contacto-jefes} : get all the informacionContactoJefes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of informacionContactoJefes in body.
     */
    @GetMapping("")
    public ResponseEntity<List<InformacionContactoJefeDTO>> getAllInformacionContactoJefes(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of InformacionContactoJefes");
        Page<InformacionContactoJefeDTO> page = informacionContactoJefeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /informacion-contacto-jefes/:id} : get the "id" informacionContactoJefe.
     *
     * @param id the id of the informacionContactoJefeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the informacionContactoJefeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<InformacionContactoJefeDTO> getInformacionContactoJefe(@PathVariable("id") Long id) {
        LOG.debug("REST request to get InformacionContactoJefe : {}", id);
        Optional<InformacionContactoJefeDTO> informacionContactoJefeDTO = informacionContactoJefeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(informacionContactoJefeDTO);
    }

    /**
     * {@code DELETE  /informacion-contacto-jefes/:id} : delete the "id" informacionContactoJefe.
     *
     * @param id the id of the informacionContactoJefeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInformacionContactoJefe(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete InformacionContactoJefe : {}", id);
        informacionContactoJefeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
