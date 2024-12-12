package com.aiep.dundermifflin.web.rest;

import static com.aiep.dundermifflin.domain.InformacionContactoEmpleadoAsserts.*;
import static com.aiep.dundermifflin.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.aiep.dundermifflin.IntegrationTest;
import com.aiep.dundermifflin.domain.InformacionContactoEmpleado;
import com.aiep.dundermifflin.repository.InformacionContactoEmpleadoRepository;
import com.aiep.dundermifflin.service.dto.InformacionContactoEmpleadoDTO;
import com.aiep.dundermifflin.service.mapper.InformacionContactoEmpleadoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link InformacionContactoEmpleadoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InformacionContactoEmpleadoResourceIT {

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/informacion-contacto-empleados";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private InformacionContactoEmpleadoRepository informacionContactoEmpleadoRepository;

    @Autowired
    private InformacionContactoEmpleadoMapper informacionContactoEmpleadoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInformacionContactoEmpleadoMockMvc;

    private InformacionContactoEmpleado informacionContactoEmpleado;

    private InformacionContactoEmpleado insertedInformacionContactoEmpleado;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InformacionContactoEmpleado createEntity() {
        return new InformacionContactoEmpleado().telefono(DEFAULT_TELEFONO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InformacionContactoEmpleado createUpdatedEntity() {
        return new InformacionContactoEmpleado().telefono(UPDATED_TELEFONO);
    }

    @BeforeEach
    public void initTest() {
        informacionContactoEmpleado = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedInformacionContactoEmpleado != null) {
            informacionContactoEmpleadoRepository.delete(insertedInformacionContactoEmpleado);
            insertedInformacionContactoEmpleado = null;
        }
    }

    @Test
    @Transactional
    void createInformacionContactoEmpleado() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the InformacionContactoEmpleado
        InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO = informacionContactoEmpleadoMapper.toDto(
            informacionContactoEmpleado
        );
        var returnedInformacionContactoEmpleadoDTO = om.readValue(
            restInformacionContactoEmpleadoMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(informacionContactoEmpleadoDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            InformacionContactoEmpleadoDTO.class
        );

        // Validate the InformacionContactoEmpleado in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedInformacionContactoEmpleado = informacionContactoEmpleadoMapper.toEntity(returnedInformacionContactoEmpleadoDTO);
        assertInformacionContactoEmpleadoUpdatableFieldsEquals(
            returnedInformacionContactoEmpleado,
            getPersistedInformacionContactoEmpleado(returnedInformacionContactoEmpleado)
        );

        insertedInformacionContactoEmpleado = returnedInformacionContactoEmpleado;
    }

    @Test
    @Transactional
    void createInformacionContactoEmpleadoWithExistingId() throws Exception {
        // Create the InformacionContactoEmpleado with an existing ID
        informacionContactoEmpleado.setId(1L);
        InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO = informacionContactoEmpleadoMapper.toDto(
            informacionContactoEmpleado
        );

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInformacionContactoEmpleadoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(informacionContactoEmpleadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoEmpleado in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInformacionContactoEmpleados() throws Exception {
        // Initialize the database
        insertedInformacionContactoEmpleado = informacionContactoEmpleadoRepository.saveAndFlush(informacionContactoEmpleado);

        // Get all the informacionContactoEmpleadoList
        restInformacionContactoEmpleadoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(informacionContactoEmpleado.getId().intValue())))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)));
    }

    @Test
    @Transactional
    void getInformacionContactoEmpleado() throws Exception {
        // Initialize the database
        insertedInformacionContactoEmpleado = informacionContactoEmpleadoRepository.saveAndFlush(informacionContactoEmpleado);

        // Get the informacionContactoEmpleado
        restInformacionContactoEmpleadoMockMvc
            .perform(get(ENTITY_API_URL_ID, informacionContactoEmpleado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(informacionContactoEmpleado.getId().intValue()))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO));
    }

    @Test
    @Transactional
    void getNonExistingInformacionContactoEmpleado() throws Exception {
        // Get the informacionContactoEmpleado
        restInformacionContactoEmpleadoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInformacionContactoEmpleado() throws Exception {
        // Initialize the database
        insertedInformacionContactoEmpleado = informacionContactoEmpleadoRepository.saveAndFlush(informacionContactoEmpleado);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the informacionContactoEmpleado
        InformacionContactoEmpleado updatedInformacionContactoEmpleado = informacionContactoEmpleadoRepository
            .findById(informacionContactoEmpleado.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedInformacionContactoEmpleado are not directly saved in db
        em.detach(updatedInformacionContactoEmpleado);
        updatedInformacionContactoEmpleado.telefono(UPDATED_TELEFONO);
        InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO = informacionContactoEmpleadoMapper.toDto(
            updatedInformacionContactoEmpleado
        );

        restInformacionContactoEmpleadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, informacionContactoEmpleadoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(informacionContactoEmpleadoDTO))
            )
            .andExpect(status().isOk());

        // Validate the InformacionContactoEmpleado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedInformacionContactoEmpleadoToMatchAllProperties(updatedInformacionContactoEmpleado);
    }

    @Test
    @Transactional
    void putNonExistingInformacionContactoEmpleado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoEmpleado.setId(longCount.incrementAndGet());

        // Create the InformacionContactoEmpleado
        InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO = informacionContactoEmpleadoMapper.toDto(
            informacionContactoEmpleado
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInformacionContactoEmpleadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, informacionContactoEmpleadoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(informacionContactoEmpleadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoEmpleado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInformacionContactoEmpleado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoEmpleado.setId(longCount.incrementAndGet());

        // Create the InformacionContactoEmpleado
        InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO = informacionContactoEmpleadoMapper.toDto(
            informacionContactoEmpleado
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacionContactoEmpleadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(informacionContactoEmpleadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoEmpleado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInformacionContactoEmpleado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoEmpleado.setId(longCount.incrementAndGet());

        // Create the InformacionContactoEmpleado
        InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO = informacionContactoEmpleadoMapper.toDto(
            informacionContactoEmpleado
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacionContactoEmpleadoMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(informacionContactoEmpleadoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InformacionContactoEmpleado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInformacionContactoEmpleadoWithPatch() throws Exception {
        // Initialize the database
        insertedInformacionContactoEmpleado = informacionContactoEmpleadoRepository.saveAndFlush(informacionContactoEmpleado);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the informacionContactoEmpleado using partial update
        InformacionContactoEmpleado partialUpdatedInformacionContactoEmpleado = new InformacionContactoEmpleado();
        partialUpdatedInformacionContactoEmpleado.setId(informacionContactoEmpleado.getId());

        partialUpdatedInformacionContactoEmpleado.telefono(UPDATED_TELEFONO);

        restInformacionContactoEmpleadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInformacionContactoEmpleado.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInformacionContactoEmpleado))
            )
            .andExpect(status().isOk());

        // Validate the InformacionContactoEmpleado in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInformacionContactoEmpleadoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedInformacionContactoEmpleado, informacionContactoEmpleado),
            getPersistedInformacionContactoEmpleado(informacionContactoEmpleado)
        );
    }

    @Test
    @Transactional
    void fullUpdateInformacionContactoEmpleadoWithPatch() throws Exception {
        // Initialize the database
        insertedInformacionContactoEmpleado = informacionContactoEmpleadoRepository.saveAndFlush(informacionContactoEmpleado);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the informacionContactoEmpleado using partial update
        InformacionContactoEmpleado partialUpdatedInformacionContactoEmpleado = new InformacionContactoEmpleado();
        partialUpdatedInformacionContactoEmpleado.setId(informacionContactoEmpleado.getId());

        partialUpdatedInformacionContactoEmpleado.telefono(UPDATED_TELEFONO);

        restInformacionContactoEmpleadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInformacionContactoEmpleado.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInformacionContactoEmpleado))
            )
            .andExpect(status().isOk());

        // Validate the InformacionContactoEmpleado in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInformacionContactoEmpleadoUpdatableFieldsEquals(
            partialUpdatedInformacionContactoEmpleado,
            getPersistedInformacionContactoEmpleado(partialUpdatedInformacionContactoEmpleado)
        );
    }

    @Test
    @Transactional
    void patchNonExistingInformacionContactoEmpleado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoEmpleado.setId(longCount.incrementAndGet());

        // Create the InformacionContactoEmpleado
        InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO = informacionContactoEmpleadoMapper.toDto(
            informacionContactoEmpleado
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInformacionContactoEmpleadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, informacionContactoEmpleadoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(informacionContactoEmpleadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoEmpleado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInformacionContactoEmpleado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoEmpleado.setId(longCount.incrementAndGet());

        // Create the InformacionContactoEmpleado
        InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO = informacionContactoEmpleadoMapper.toDto(
            informacionContactoEmpleado
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacionContactoEmpleadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(informacionContactoEmpleadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoEmpleado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInformacionContactoEmpleado() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoEmpleado.setId(longCount.incrementAndGet());

        // Create the InformacionContactoEmpleado
        InformacionContactoEmpleadoDTO informacionContactoEmpleadoDTO = informacionContactoEmpleadoMapper.toDto(
            informacionContactoEmpleado
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacionContactoEmpleadoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(informacionContactoEmpleadoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InformacionContactoEmpleado in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInformacionContactoEmpleado() throws Exception {
        // Initialize the database
        insertedInformacionContactoEmpleado = informacionContactoEmpleadoRepository.saveAndFlush(informacionContactoEmpleado);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the informacionContactoEmpleado
        restInformacionContactoEmpleadoMockMvc
            .perform(delete(ENTITY_API_URL_ID, informacionContactoEmpleado.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return informacionContactoEmpleadoRepository.count();
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

    protected InformacionContactoEmpleado getPersistedInformacionContactoEmpleado(InformacionContactoEmpleado informacionContactoEmpleado) {
        return informacionContactoEmpleadoRepository.findById(informacionContactoEmpleado.getId()).orElseThrow();
    }

    protected void assertPersistedInformacionContactoEmpleadoToMatchAllProperties(
        InformacionContactoEmpleado expectedInformacionContactoEmpleado
    ) {
        assertInformacionContactoEmpleadoAllPropertiesEquals(
            expectedInformacionContactoEmpleado,
            getPersistedInformacionContactoEmpleado(expectedInformacionContactoEmpleado)
        );
    }

    protected void assertPersistedInformacionContactoEmpleadoToMatchUpdatableProperties(
        InformacionContactoEmpleado expectedInformacionContactoEmpleado
    ) {
        assertInformacionContactoEmpleadoAllUpdatablePropertiesEquals(
            expectedInformacionContactoEmpleado,
            getPersistedInformacionContactoEmpleado(expectedInformacionContactoEmpleado)
        );
    }
}
