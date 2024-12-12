package com.aiep.dundermifflin.web.rest;

import com.aiep.dundermifflin.repository.DepartamentosJefesRepository;
import com.aiep.dundermifflin.service.DepartamentosJefesService;
import com.aiep.dundermifflin.service.dto.DepartamentosJefesDTO;
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
 * REST controller for managing {@link com.aiep.dundermifflin.domain.DepartamentosJefes}.
 */
@RestController
@RequestMapping("/api/departamentos-jefes")
public class DepartamentosJefesResource {

    private static final Logger LOG = LoggerFactory.getLogger(DepartamentosJefesResource.class);

    private static final String ENTITY_NAME = "departamentosJefes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DepartamentosJefesService departamentosJefesService;

    private final DepartamentosJefesRepository departamentosJefesRepository;

    public DepartamentosJefesResource(
        DepartamentosJefesService departamentosJefesService,
        DepartamentosJefesRepository departamentosJefesRepository
    ) {
        this.departamentosJefesService = departamentosJefesService;
        this.departamentosJefesRepository = departamentosJefesRepository;
    }

    /**
     * {@code POST  /departamentos-jefes} : Create a new departamentosJefes.
     *
     * @param departamentosJefesDTO the departamentosJefesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new departamentosJefesDTO, or with status {@code 400 (Bad Request)} if the departamentosJefes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DepartamentosJefesDTO> createDepartamentosJefes(@RequestBody DepartamentosJefesDTO departamentosJefesDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save DepartamentosJefes : {}", departamentosJefesDTO);
        if (departamentosJefesDTO.getId() != null) {
            throw new BadRequestAlertException("A new departamentosJefes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        departamentosJefesDTO = departamentosJefesService.save(departamentosJefesDTO);
        return ResponseEntity.created(new URI("/api/departamentos-jefes/" + departamentosJefesDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, departamentosJefesDTO.getId().toString()))
            .body(departamentosJefesDTO);
    }

    /**
     * {@code PUT  /departamentos-jefes/:id} : Updates an existing departamentosJefes.
     *
     * @param id the id of the departamentosJefesDTO to save.
     * @param departamentosJefesDTO the departamentosJefesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated departamentosJefesDTO,
     * or with status {@code 400 (Bad Request)} if the departamentosJefesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the departamentosJefesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DepartamentosJefesDTO> updateDepartamentosJefes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DepartamentosJefesDTO departamentosJefesDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DepartamentosJefes : {}, {}", id, departamentosJefesDTO);
        if (departamentosJefesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, departamentosJefesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!departamentosJefesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        departamentosJefesDTO = departamentosJefesService.update(departamentosJefesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, departamentosJefesDTO.getId().toString()))
            .body(departamentosJefesDTO);
    }

    /**
     * {@code PATCH  /departamentos-jefes/:id} : Partial updates given fields of an existing departamentosJefes, field will ignore if it is null
     *
     * @param id the id of the departamentosJefesDTO to save.
     * @param departamentosJefesDTO the departamentosJefesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated departamentosJefesDTO,
     * or with status {@code 400 (Bad Request)} if the departamentosJefesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the departamentosJefesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the departamentosJefesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DepartamentosJefesDTO> partialUpdateDepartamentosJefes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DepartamentosJefesDTO departamentosJefesDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DepartamentosJefes partially : {}, {}", id, departamentosJefesDTO);
        if (departamentosJefesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, departamentosJefesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!departamentosJefesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DepartamentosJefesDTO> result = departamentosJefesService.partialUpdate(departamentosJefesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, departamentosJefesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /departamentos-jefes} : get all the departamentosJefes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of departamentosJefes in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DepartamentosJefesDTO>> getAllDepartamentosJefes(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of DepartamentosJefes");
        Page<DepartamentosJefesDTO> page = departamentosJefesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /departamentos-jefes/:id} : get the "id" departamentosJefes.
     *
     * @param id the id of the departamentosJefesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the departamentosJefesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartamentosJefesDTO> getDepartamentosJefes(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DepartamentosJefes : {}", id);
        Optional<DepartamentosJefesDTO> departamentosJefesDTO = departamentosJefesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(departamentosJefesDTO);
    }

    /**
     * {@code DELETE  /departamentos-jefes/:id} : delete the "id" departamentosJefes.
     *
     * @param id the id of the departamentosJefesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartamentosJefes(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DepartamentosJefes : {}", id);
        departamentosJefesService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
