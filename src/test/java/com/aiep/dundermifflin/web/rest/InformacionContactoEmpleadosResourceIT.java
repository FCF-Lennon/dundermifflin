package com.aiep.dundermifflin.web.rest;

import static com.aiep.dundermifflin.domain.InformacionContactoEmpleadosAsserts.*;
import static com.aiep.dundermifflin.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.aiep.dundermifflin.IntegrationTest;
import com.aiep.dundermifflin.domain.InformacionContactoEmpleados;
import com.aiep.dundermifflin.repository.InformacionContactoEmpleadosRepository;
import com.aiep.dundermifflin.service.dto.InformacionContactoEmpleadosDTO;
import com.aiep.dundermifflin.service.mapper.InformacionContactoEmpleadosMapper;
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
 * Integration tests for the {@link InformacionContactoEmpleadosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InformacionContactoEmpleadosResourceIT {

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_FONO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_FONO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/informacion-contacto-empleados";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private InformacionContactoEmpleadosRepository informacionContactoEmpleadosRepository;

    @Autowired
    private InformacionContactoEmpleadosMapper informacionContactoEmpleadosMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInformacionContactoEmpleadosMockMvc;

    private InformacionContactoEmpleados informacionContactoEmpleados;

    private InformacionContactoEmpleados insertedInformacionContactoEmpleados;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InformacionContactoEmpleados createEntity() {
        return new InformacionContactoEmpleados().telefono(DEFAULT_TELEFONO).tipoFono(DEFAULT_TIPO_FONO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InformacionContactoEmpleados createUpdatedEntity() {
        return new InformacionContactoEmpleados().telefono(UPDATED_TELEFONO).tipoFono(UPDATED_TIPO_FONO);
    }

    @BeforeEach
    public void initTest() {
        informacionContactoEmpleados = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedInformacionContactoEmpleados != null) {
            informacionContactoEmpleadosRepository.delete(insertedInformacionContactoEmpleados);
            insertedInformacionContactoEmpleados = null;
        }
    }

    @Test
    @Transactional
    void createInformacionContactoEmpleados() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the InformacionContactoEmpleados
        InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO = informacionContactoEmpleadosMapper.toDto(
            informacionContactoEmpleados
        );
        var returnedInformacionContactoEmpleadosDTO = om.readValue(
            restInformacionContactoEmpleadosMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(informacionContactoEmpleadosDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            InformacionContactoEmpleadosDTO.class
        );

        // Validate the InformacionContactoEmpleados in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedInformacionContactoEmpleados = informacionContactoEmpleadosMapper.toEntity(returnedInformacionContactoEmpleadosDTO);
        assertInformacionContactoEmpleadosUpdatableFieldsEquals(
            returnedInformacionContactoEmpleados,
            getPersistedInformacionContactoEmpleados(returnedInformacionContactoEmpleados)
        );

        insertedInformacionContactoEmpleados = returnedInformacionContactoEmpleados;
    }

    @Test
    @Transactional
    void createInformacionContactoEmpleadosWithExistingId() throws Exception {
        // Create the InformacionContactoEmpleados with an existing ID
        informacionContactoEmpleados.setId(1L);
        InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO = informacionContactoEmpleadosMapper.toDto(
            informacionContactoEmpleados
        );

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInformacionContactoEmpleadosMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(informacionContactoEmpleadosDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoEmpleados in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInformacionContactoEmpleados() throws Exception {
        // Initialize the database
        insertedInformacionContactoEmpleados = informacionContactoEmpleadosRepository.saveAndFlush(informacionContactoEmpleados);

        // Get all the informacionContactoEmpleadosList
        restInformacionContactoEmpleadosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(informacionContactoEmpleados.getId().intValue())))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].tipoFono").value(hasItem(DEFAULT_TIPO_FONO)));
    }

    @Test
    @Transactional
    void getInformacionContactoEmpleados() throws Exception {
        // Initialize the database
        insertedInformacionContactoEmpleados = informacionContactoEmpleadosRepository.saveAndFlush(informacionContactoEmpleados);

        // Get the informacionContactoEmpleados
        restInformacionContactoEmpleadosMockMvc
            .perform(get(ENTITY_API_URL_ID, informacionContactoEmpleados.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(informacionContactoEmpleados.getId().intValue()))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.tipoFono").value(DEFAULT_TIPO_FONO));
    }

    @Test
    @Transactional
    void getNonExistingInformacionContactoEmpleados() throws Exception {
        // Get the informacionContactoEmpleados
        restInformacionContactoEmpleadosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInformacionContactoEmpleados() throws Exception {
        // Initialize the database
        insertedInformacionContactoEmpleados = informacionContactoEmpleadosRepository.saveAndFlush(informacionContactoEmpleados);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the informacionContactoEmpleados
        InformacionContactoEmpleados updatedInformacionContactoEmpleados = informacionContactoEmpleadosRepository
            .findById(informacionContactoEmpleados.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedInformacionContactoEmpleados are not directly saved in db
        em.detach(updatedInformacionContactoEmpleados);
        updatedInformacionContactoEmpleados.telefono(UPDATED_TELEFONO).tipoFono(UPDATED_TIPO_FONO);
        InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO = informacionContactoEmpleadosMapper.toDto(
            updatedInformacionContactoEmpleados
        );

        restInformacionContactoEmpleadosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, informacionContactoEmpleadosDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(informacionContactoEmpleadosDTO))
            )
            .andExpect(status().isOk());

        // Validate the InformacionContactoEmpleados in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedInformacionContactoEmpleadosToMatchAllProperties(updatedInformacionContactoEmpleados);
    }

    @Test
    @Transactional
    void putNonExistingInformacionContactoEmpleados() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoEmpleados.setId(longCount.incrementAndGet());

        // Create the InformacionContactoEmpleados
        InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO = informacionContactoEmpleadosMapper.toDto(
            informacionContactoEmpleados
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInformacionContactoEmpleadosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, informacionContactoEmpleadosDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(informacionContactoEmpleadosDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoEmpleados in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInformacionContactoEmpleados() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoEmpleados.setId(longCount.incrementAndGet());

        // Create the InformacionContactoEmpleados
        InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO = informacionContactoEmpleadosMapper.toDto(
            informacionContactoEmpleados
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacionContactoEmpleadosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(informacionContactoEmpleadosDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoEmpleados in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInformacionContactoEmpleados() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoEmpleados.setId(longCount.incrementAndGet());

        // Create the InformacionContactoEmpleados
        InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO = informacionContactoEmpleadosMapper.toDto(
            informacionContactoEmpleados
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacionContactoEmpleadosMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(informacionContactoEmpleadosDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InformacionContactoEmpleados in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInformacionContactoEmpleadosWithPatch() throws Exception {
        // Initialize the database
        insertedInformacionContactoEmpleados = informacionContactoEmpleadosRepository.saveAndFlush(informacionContactoEmpleados);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the informacionContactoEmpleados using partial update
        InformacionContactoEmpleados partialUpdatedInformacionContactoEmpleados = new InformacionContactoEmpleados();
        partialUpdatedInformacionContactoEmpleados.setId(informacionContactoEmpleados.getId());

        restInformacionContactoEmpleadosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInformacionContactoEmpleados.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInformacionContactoEmpleados))
            )
            .andExpect(status().isOk());

        // Validate the InformacionContactoEmpleados in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInformacionContactoEmpleadosUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedInformacionContactoEmpleados, informacionContactoEmpleados),
            getPersistedInformacionContactoEmpleados(informacionContactoEmpleados)
        );
    }

    @Test
    @Transactional
    void fullUpdateInformacionContactoEmpleadosWithPatch() throws Exception {
        // Initialize the database
        insertedInformacionContactoEmpleados = informacionContactoEmpleadosRepository.saveAndFlush(informacionContactoEmpleados);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the informacionContactoEmpleados using partial update
        InformacionContactoEmpleados partialUpdatedInformacionContactoEmpleados = new InformacionContactoEmpleados();
        partialUpdatedInformacionContactoEmpleados.setId(informacionContactoEmpleados.getId());

        partialUpdatedInformacionContactoEmpleados.telefono(UPDATED_TELEFONO).tipoFono(UPDATED_TIPO_FONO);

        restInformacionContactoEmpleadosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInformacionContactoEmpleados.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInformacionContactoEmpleados))
            )
            .andExpect(status().isOk());

        // Validate the InformacionContactoEmpleados in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInformacionContactoEmpleadosUpdatableFieldsEquals(
            partialUpdatedInformacionContactoEmpleados,
            getPersistedInformacionContactoEmpleados(partialUpdatedInformacionContactoEmpleados)
        );
    }

    @Test
    @Transactional
    void patchNonExistingInformacionContactoEmpleados() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoEmpleados.setId(longCount.incrementAndGet());

        // Create the InformacionContactoEmpleados
        InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO = informacionContactoEmpleadosMapper.toDto(
            informacionContactoEmpleados
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInformacionContactoEmpleadosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, informacionContactoEmpleadosDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(informacionContactoEmpleadosDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoEmpleados in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInformacionContactoEmpleados() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoEmpleados.setId(longCount.incrementAndGet());

        // Create the InformacionContactoEmpleados
        InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO = informacionContactoEmpleadosMapper.toDto(
            informacionContactoEmpleados
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacionContactoEmpleadosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(informacionContactoEmpleadosDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoEmpleados in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInformacionContactoEmpleados() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoEmpleados.setId(longCount.incrementAndGet());

        // Create the InformacionContactoEmpleados
        InformacionContactoEmpleadosDTO informacionContactoEmpleadosDTO = informacionContactoEmpleadosMapper.toDto(
            informacionContactoEmpleados
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacionContactoEmpleadosMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(informacionContactoEmpleadosDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InformacionContactoEmpleados in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInformacionContactoEmpleados() throws Exception {
        // Initialize the database
        insertedInformacionContactoEmpleados = informacionContactoEmpleadosRepository.saveAndFlush(informacionContactoEmpleados);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the informacionContactoEmpleados
        restInformacionContactoEmpleadosMockMvc
            .perform(delete(ENTITY_API_URL_ID, informacionContactoEmpleados.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return informacionContactoEmpleadosRepository.count();
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

    protected InformacionContactoEmpleados getPersistedInformacionContactoEmpleados(
        InformacionContactoEmpleados informacionContactoEmpleados
    ) {
        return informacionContactoEmpleadosRepository.findById(informacionContactoEmpleados.getId()).orElseThrow();
    }

    protected void assertPersistedInformacionContactoEmpleadosToMatchAllProperties(
        InformacionContactoEmpleados expectedInformacionContactoEmpleados
    ) {
        assertInformacionContactoEmpleadosAllPropertiesEquals(
            expectedInformacionContactoEmpleados,
            getPersistedInformacionContactoEmpleados(expectedInformacionContactoEmpleados)
        );
    }

    protected void assertPersistedInformacionContactoEmpleadosToMatchUpdatableProperties(
        InformacionContactoEmpleados expectedInformacionContactoEmpleados
    ) {
        assertInformacionContactoEmpleadosAllUpdatablePropertiesEquals(
            expectedInformacionContactoEmpleados,
            getPersistedInformacionContactoEmpleados(expectedInformacionContactoEmpleados)
        );
    }
}
