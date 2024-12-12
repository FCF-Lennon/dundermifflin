package com.aiep.dundermifflin.web.rest;

import com.aiep.dundermifflin.repository.JefesRepository;
import com.aiep.dundermifflin.service.JefesService;
import com.aiep.dundermifflin.service.dto.JefesDTO;
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
 * REST controller for managing {@link com.aiep.dundermifflin.domain.Jefes}.
 */
@RestController
@RequestMapping("/api/jefes")
public class JefesResource {

    private static final Logger LOG = LoggerFactory.getLogger(JefesResource.class);

    private static final String ENTITY_NAME = "jefes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JefesService jefesService;

    private final JefesRepository jefesRepository;

    public JefesResource(JefesService jefesService, JefesRepository jefesRepository) {
        this.jefesService = jefesService;
        this.jefesRepository = jefesRepository;
    }

    /**
     * {@code POST  /jefes} : Create a new jefes.
     *
     * @param jefesDTO the jefesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jefesDTO, or with status {@code 400 (Bad Request)} if the jefes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<JefesDTO> createJefes(@RequestBody JefesDTO jefesDTO) throws URISyntaxException {
        LOG.debug("REST request to save Jefes : {}", jefesDTO);
        if (jefesDTO.getId() != null) {
            throw new BadRequestAlertException("A new jefes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        jefesDTO = jefesService.save(jefesDTO);
        return ResponseEntity.created(new URI("/api/jefes/" + jefesDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, jefesDTO.getId().toString()))
            .body(jefesDTO);
    }

    /**
     * {@code PUT  /jefes/:id} : Updates an existing jefes.
     *
     * @param id the id of the jefesDTO to save.
     * @param jefesDTO the jefesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jefesDTO,
     * or with status {@code 400 (Bad Request)} if the jefesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jefesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<JefesDTO> updateJefes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JefesDTO jefesDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Jefes : {}, {}", id, jefesDTO);
        if (jefesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jefesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jefesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        jefesDTO = jefesService.update(jefesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jefesDTO.getId().toString()))
            .body(jefesDTO);
    }

    /**
     * {@code PATCH  /jefes/:id} : Partial updates given fields of an existing jefes, field will ignore if it is null
     *
     * @param id the id of the jefesDTO to save.
     * @param jefesDTO the jefesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jefesDTO,
     * or with status {@code 400 (Bad Request)} if the jefesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the jefesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the jefesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<JefesDTO> partialUpdateJefes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JefesDTO jefesDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Jefes partially : {}, {}", id, jefesDTO);
        if (jefesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jefesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jefesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JefesDTO> result = jefesService.partialUpdate(jefesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jefesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /jefes} : get all the jefes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jefes in body.
     */
    @GetMapping("")
    public ResponseEntity<List<JefesDTO>> getAllJefes(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of Jefes");
        Page<JefesDTO> page = jefesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /jefes/:id} : get the "id" jefes.
     *
     * @param id the id of the jefesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jefesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<JefesDTO> getJefes(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Jefes : {}", id);
        Optional<JefesDTO> jefesDTO = jefesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jefesDTO);
    }

    /**
     * {@code DELETE  /jefes/:id} : delete the "id" jefes.
     *
     * @param id the id of the jefesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJefes(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Jefes : {}", id);
        jefesService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
