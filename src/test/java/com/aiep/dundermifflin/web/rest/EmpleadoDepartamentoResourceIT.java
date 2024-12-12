package com.aiep.dundermifflin.web.rest;

import static com.aiep.dundermifflin.domain.EmpleadoDepartamentoAsserts.*;
import static com.aiep.dundermifflin.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.aiep.dundermifflin.IntegrationTest;
import com.aiep.dundermifflin.domain.EmpleadoDepartamento;
import com.aiep.dundermifflin.repository.EmpleadoDepartamentoRepository;
import com.aiep.dundermifflin.service.dto.EmpleadoDepartamentoDTO;
import com.aiep.dundermifflin.service.mapper.EmpleadoDepartamentoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EmpleadoDepartamentoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EmpleadoDepartamentoResourceIT {

    private static final LocalDate DEFAULT_FECHA_ASIGNACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_ASIGNACION = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/empleado-departamentos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EmpleadoDepartamentoRepository empleadoDepartamentoRepository;

    @Autowired
    private EmpleadoDepartamentoMapper empleadoDepartamentoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmpleadoDepartamentoMockMvc;

    private EmpleadoDepartamento empleadoDepartamento;

    private EmpleadoDepartamento insertedEmpleadoDepartamento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmpleadoDepartamento createEntity() {
        return new EmpleadoDepartamento().fechaAsignacion(DEFAULT_FECHA_ASIGNACION);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmpleadoDepartamento createUpdatedEntity() {
        return new EmpleadoDepartamento().fechaAsignacion(UPDATED_FECHA_ASIGNACION);
    }

    @BeforeEach
    public void initTest() {
        empleadoDepartamento = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedEmpleadoDepartamento != null) {
            empleadoDepartamentoRepository.delete(insertedEmpleadoDepartamento);
            insertedEmpleadoDepartamento = null;
        }
    }

    @Test
    @Transactional
    void createEmpleadoDepartamento() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EmpleadoDepartamento
        EmpleadoDepartamentoDTO empleadoDepartamentoDTO = empleadoDepartamentoMapper.toDto(empleadoDepartamento);
        var returnedEmpleadoDepartamentoDTO = om.readValue(
            restEmpleadoDepartamentoMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(empleadoDepartamentoDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EmpleadoDepartamentoDTO.class
        );

        // Validate the EmpleadoDepartamento in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedEmpleadoDepartamento = empleadoDepartamentoMapper.toEntity(returnedEmpleadoDepartamentoDTO);
        assertEmpleadoDepartamentoUpdatableFieldsEquals(
            returnedEmpleadoDepartamento,
            getPersistedEmpleadoDepartamento(returnedEmpleadoDepartamento)
        );

        insertedEmpleadoDepartamento = returnedEmpleadoDepartamento;
    }

    @Test
    @Transactional
    void createEmpleadoDepartamentoWithExistingId() throws Exception {
        // Create the EmpleadoDepartamento with an existing ID
        empleadoDepartamento.setId(1L);
        EmpleadoDepartamentoDTO empleadoDepartamentoDTO = empleadoDepartamentoMapper.toDto(empleadoDepartamento);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmpleadoDepartamentoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(empleadoDepartamentoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmpleadoDepartamento in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEmpleadoDepartamentos() throws Exception {
        // Initialize the database
        insertedEmpleadoDepartamento = empleadoDepartamentoRepository.saveAndFlush(empleadoDepartamento);

        // Get all the empleadoDepartamentoList
        restEmpleadoDepartamentoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(empleadoDepartamento.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaAsignacion").value(hasItem(DEFAULT_FECHA_ASIGNACION.toString())));
    }

    @Test
    @Transactional
    void getEmpleadoDepartamento() throws Exception {
        // Initialize the database
        insertedEmpleadoDepartamento = empleadoDepartamentoRepository.saveAndFlush(empleadoDepartamento);

        // Get the empleadoDepartamento
        restEmpleadoDepartamentoMockMvc
            .perform(get(ENTITY_API_URL_ID, empleadoDepartamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(empleadoDepartamento.getId().intValue()))
            .andExpect(jsonPath("$.fechaAsignacion").value(DEFAULT_FECHA_ASIGNACION.toString()));
    }

    @Test
    @Transactional
    void getNonExistingEmpleadoDepartamento() throws Exception {
        // Get the empleadoDepartamento
        restEmpleadoDepartamentoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEmpleadoDepartamento() throws Exception {
        // Initialize the database
        insertedEmpleadoDepartamento = empleadoDepartamentoRepository.saveAndFlush(empleadoDepartamento);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the empleadoDepartamento
        EmpleadoDepartamento updatedEmpleadoDepartamento = empleadoDepartamentoRepository
            .findById(empleadoDepartamento.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedEmpleadoDepartamento are not directly saved in db
        em.detach(updatedEmpleadoDepartamento);
        updatedEmpleadoDepartamento.fechaAsignacion(UPDATED_FECHA_ASIGNACION);
        EmpleadoDepartamentoDTO empleadoDepartamentoDTO = empleadoDepartamentoMapper.toDto(updatedEmpleadoDepartamento);

        restEmpleadoDepartamentoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, empleadoDepartamentoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(empleadoDepartamentoDTO))
            )
            .andExpect(status().isOk());

        // Validate the EmpleadoDepartamento in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEmpleadoDepartamentoToMatchAllProperties(updatedEmpleadoDepartamento);
    }

    @Test
    @Transactional
    void putNonExistingEmpleadoDepartamento() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empleadoDepartamento.setId(longCount.incrementAndGet());

        // Create the EmpleadoDepartamento
        EmpleadoDepartamentoDTO empleadoDepartamentoDTO = empleadoDepartamentoMapper.toDto(empleadoDepartamento);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmpleadoDepartamentoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, empleadoDepartamentoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(empleadoDepartamentoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmpleadoDepartamento in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEmpleadoDepartamento() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empleadoDepartamento.setId(longCount.incrementAndGet());

        // Create the EmpleadoDepartamento
        EmpleadoDepartamentoDTO empleadoDepartamentoDTO = empleadoDepartamentoMapper.toDto(empleadoDepartamento);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleadoDepartamentoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(empleadoDepartamentoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmpleadoDepartamento in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEmpleadoDepartamento() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empleadoDepartamento.setId(longCount.incrementAndGet());

        // Create the EmpleadoDepartamento
        EmpleadoDepartamentoDTO empleadoDepartamentoDTO = empleadoDepartamentoMapper.toDto(empleadoDepartamento);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleadoDepartamentoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(empleadoDepartamentoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EmpleadoDepartamento in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEmpleadoDepartamentoWithPatch() throws Exception {
        // Initialize the database
        insertedEmpleadoDepartamento = empleadoDepartamentoRepository.saveAndFlush(empleadoDepartamento);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the empleadoDepartamento using partial update
        EmpleadoDepartamento partialUpdatedEmpleadoDepartamento = new EmpleadoDepartamento();
        partialUpdatedEmpleadoDepartamento.setId(empleadoDepartamento.getId());

        restEmpleadoDepartamentoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmpleadoDepartamento.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEmpleadoDepartamento))
            )
            .andExpect(status().isOk());

        // Validate the EmpleadoDepartamento in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEmpleadoDepartamentoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEmpleadoDepartamento, empleadoDepartamento),
            getPersistedEmpleadoDepartamento(empleadoDepartamento)
        );
    }

    @Test
    @Transactional
    void fullUpdateEmpleadoDepartamentoWithPatch() throws Exception {
        // Initialize the database
        insertedEmpleadoDepartamento = empleadoDepartamentoRepository.saveAndFlush(empleadoDepartamento);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the empleadoDepartamento using partial update
        EmpleadoDepartamento partialUpdatedEmpleadoDepartamento = new EmpleadoDepartamento();
        partialUpdatedEmpleadoDepartamento.setId(empleadoDepartamento.getId());

        partialUpdatedEmpleadoDepartamento.fechaAsignacion(UPDATED_FECHA_ASIGNACION);

        restEmpleadoDepartamentoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmpleadoDepartamento.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEmpleadoDepartamento))
            )
            .andExpect(status().isOk());

        // Validate the EmpleadoDepartamento in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEmpleadoDepartamentoUpdatableFieldsEquals(
            partialUpdatedEmpleadoDepartamento,
            getPersistedEmpleadoDepartamento(partialUpdatedEmpleadoDepartamento)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEmpleadoDepartamento() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empleadoDepartamento.setId(longCount.incrementAndGet());

        // Create the EmpleadoDepartamento
        EmpleadoDepartamentoDTO empleadoDepartamentoDTO = empleadoDepartamentoMapper.toDto(empleadoDepartamento);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmpleadoDepartamentoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, empleadoDepartamentoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(empleadoDepartamentoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmpleadoDepartamento in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEmpleadoDepartamento() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empleadoDepartamento.setId(longCount.incrementAndGet());

        // Create the EmpleadoDepartamento
        EmpleadoDepartamentoDTO empleadoDepartamentoDTO = empleadoDepartamentoMapper.toDto(empleadoDepartamento);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleadoDepartamentoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(empleadoDepartamentoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmpleadoDepartamento in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEmpleadoDepartamento() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empleadoDepartamento.setId(longCount.incrementAndGet());

        // Create the EmpleadoDepartamento
        EmpleadoDepartamentoDTO empleadoDepartamentoDTO = empleadoDepartamentoMapper.toDto(empleadoDepartamento);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpleadoDepartamentoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(empleadoDepartamentoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EmpleadoDepartamento in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEmpleadoDepartamento() throws Exception {
        // Initialize the database
        insertedEmpleadoDepartamento = empleadoDepartamentoRepository.saveAndFlush(empleadoDepartamento);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the empleadoDepartamento
        restEmpleadoDepartamentoMockMvc
            .perform(delete(ENTITY_API_URL_ID, empleadoDepartamento.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return empleadoDepartamentoRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected EmpleadoDepartamento getPersistedEmpleadoDepartamento(EmpleadoDepartamento empleadoDepartamento) {
        return empleadoDepartamentoRepository.findById(empleadoDepartamento.getId()).orElseThrow();
    }

    protected void assertPersistedEmpleadoDepartamentoToMatchAllProperties(EmpleadoDepartamento expectedEmpleadoDepartamento) {
        assertEmpleadoDepartamentoAllPropertiesEquals(
            expectedEmpleadoDepartamento,
            getPersistedEmpleadoDepartamento(expectedEmpleadoDepartamento)
        );
    }

    protected void assertPersistedEmpleadoDepartamentoToMatchUpdatableProperties(EmpleadoDepartamento expectedEmpleadoDepartamento) {
        assertEmpleadoDepartamentoAllUpdatablePropertiesEquals(
            expectedEmpleadoDepartamento,
            getPersistedEmpleadoDepartamento(expectedEmpleadoDepartamento)
        );
    }
}
