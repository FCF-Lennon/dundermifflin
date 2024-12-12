package com.aiep.dundermifflin.web.rest;

import static com.aiep.dundermifflin.domain.InformacionContactoJefeAsserts.*;
import static com.aiep.dundermifflin.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.aiep.dundermifflin.IntegrationTest;
import com.aiep.dundermifflin.domain.InformacionContactoJefe;
import com.aiep.dundermifflin.repository.InformacionContactoJefeRepository;
import com.aiep.dundermifflin.service.dto.InformacionContactoJefeDTO;
import com.aiep.dundermifflin.service.mapper.InformacionContactoJefeMapper;
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
 * Integration tests for the {@link InformacionContactoJefeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InformacionContactoJefeResourceIT {

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_FONO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_FONO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/informacion-contacto-jefes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private InformacionContactoJefeRepository informacionContactoJefeRepository;

    @Autowired
    private InformacionContactoJefeMapper informacionContactoJefeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInformacionContactoJefeMockMvc;

    private InformacionContactoJefe informacionContactoJefe;

    private InformacionContactoJefe insertedInformacionContactoJefe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InformacionContactoJefe createEntity() {
        return new InformacionContactoJefe().telefono(DEFAULT_TELEFONO).tipoFono(DEFAULT_TIPO_FONO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InformacionContactoJefe createUpdatedEntity() {
        return new InformacionContactoJefe().telefono(UPDATED_TELEFONO).tipoFono(UPDATED_TIPO_FONO);
    }

    @BeforeEach
    public void initTest() {
        informacionContactoJefe = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedInformacionContactoJefe != null) {
            informacionContactoJefeRepository.delete(insertedInformacionContactoJefe);
            insertedInformacionContactoJefe = null;
        }
    }

    @Test
    @Transactional
    void createInformacionContactoJefe() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the InformacionContactoJefe
        InformacionContactoJefeDTO informacionContactoJefeDTO = informacionContactoJefeMapper.toDto(informacionContactoJefe);
        var returnedInformacionContactoJefeDTO = om.readValue(
            restInformacionContactoJefeMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(informacionContactoJefeDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            InformacionContactoJefeDTO.class
        );

        // Validate the InformacionContactoJefe in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedInformacionContactoJefe = informacionContactoJefeMapper.toEntity(returnedInformacionContactoJefeDTO);
        assertInformacionContactoJefeUpdatableFieldsEquals(
            returnedInformacionContactoJefe,
            getPersistedInformacionContactoJefe(returnedInformacionContactoJefe)
        );

        insertedInformacionContactoJefe = returnedInformacionContactoJefe;
    }

    @Test
    @Transactional
    void createInformacionContactoJefeWithExistingId() throws Exception {
        // Create the InformacionContactoJefe with an existing ID
        informacionContactoJefe.setId(1L);
        InformacionContactoJefeDTO informacionContactoJefeDTO = informacionContactoJefeMapper.toDto(informacionContactoJefe);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInformacionContactoJefeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(informacionContactoJefeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoJefe in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInformacionContactoJefes() throws Exception {
        // Initialize the database
        insertedInformacionContactoJefe = informacionContactoJefeRepository.saveAndFlush(informacionContactoJefe);

        // Get all the informacionContactoJefeList
        restInformacionContactoJefeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(informacionContactoJefe.getId().intValue())))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].tipoFono").value(hasItem(DEFAULT_TIPO_FONO)));
    }

    @Test
    @Transactional
    void getInformacionContactoJefe() throws Exception {
        // Initialize the database
        insertedInformacionContactoJefe = informacionContactoJefeRepository.saveAndFlush(informacionContactoJefe);

        // Get the informacionContactoJefe
        restInformacionContactoJefeMockMvc
            .perform(get(ENTITY_API_URL_ID, informacionContactoJefe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(informacionContactoJefe.getId().intValue()))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.tipoFono").value(DEFAULT_TIPO_FONO));
    }

    @Test
    @Transactional
    void getNonExistingInformacionContactoJefe() throws Exception {
        // Get the informacionContactoJefe
        restInformacionContactoJefeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInformacionContactoJefe() throws Exception {
        // Initialize the database
        insertedInformacionContactoJefe = informacionContactoJefeRepository.saveAndFlush(informacionContactoJefe);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the informacionContactoJefe
        InformacionContactoJefe updatedInformacionContactoJefe = informacionContactoJefeRepository
            .findById(informacionContactoJefe.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedInformacionContactoJefe are not directly saved in db
        em.detach(updatedInformacionContactoJefe);
        updatedInformacionContactoJefe.telefono(UPDATED_TELEFONO).tipoFono(UPDATED_TIPO_FONO);
        InformacionContactoJefeDTO informacionContactoJefeDTO = informacionContactoJefeMapper.toDto(updatedInformacionContactoJefe);

        restInformacionContactoJefeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, informacionContactoJefeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(informacionContactoJefeDTO))
            )
            .andExpect(status().isOk());

        // Validate the InformacionContactoJefe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedInformacionContactoJefeToMatchAllProperties(updatedInformacionContactoJefe);
    }

    @Test
    @Transactional
    void putNonExistingInformacionContactoJefe() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoJefe.setId(longCount.incrementAndGet());

        // Create the InformacionContactoJefe
        InformacionContactoJefeDTO informacionContactoJefeDTO = informacionContactoJefeMapper.toDto(informacionContactoJefe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInformacionContactoJefeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, informacionContactoJefeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(informacionContactoJefeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoJefe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInformacionContactoJefe() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoJefe.setId(longCount.incrementAndGet());

        // Create the InformacionContactoJefe
        InformacionContactoJefeDTO informacionContactoJefeDTO = informacionContactoJefeMapper.toDto(informacionContactoJefe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacionContactoJefeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(informacionContactoJefeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoJefe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInformacionContactoJefe() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoJefe.setId(longCount.incrementAndGet());

        // Create the InformacionContactoJefe
        InformacionContactoJefeDTO informacionContactoJefeDTO = informacionContactoJefeMapper.toDto(informacionContactoJefe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacionContactoJefeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(informacionContactoJefeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the InformacionContactoJefe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInformacionContactoJefeWithPatch() throws Exception {
        // Initialize the database
        insertedInformacionContactoJefe = informacionContactoJefeRepository.saveAndFlush(informacionContactoJefe);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the informacionContactoJefe using partial update
        InformacionContactoJefe partialUpdatedInformacionContactoJefe = new InformacionContactoJefe();
        partialUpdatedInformacionContactoJefe.setId(informacionContactoJefe.getId());

        partialUpdatedInformacionContactoJefe.telefono(UPDATED_TELEFONO);

        restInformacionContactoJefeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInformacionContactoJefe.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInformacionContactoJefe))
            )
            .andExpect(status().isOk());

        // Validate the InformacionContactoJefe in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInformacionContactoJefeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedInformacionContactoJefe, informacionContactoJefe),
            getPersistedInformacionContactoJefe(informacionContactoJefe)
        );
    }

    @Test
    @Transactional
    void fullUpdateInformacionContactoJefeWithPatch() throws Exception {
        // Initialize the database
        insertedInformacionContactoJefe = informacionContactoJefeRepository.saveAndFlush(informacionContactoJefe);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the informacionContactoJefe using partial update
        InformacionContactoJefe partialUpdatedInformacionContactoJefe = new InformacionContactoJefe();
        partialUpdatedInformacionContactoJefe.setId(informacionContactoJefe.getId());

        partialUpdatedInformacionContactoJefe.telefono(UPDATED_TELEFONO).tipoFono(UPDATED_TIPO_FONO);

        restInformacionContactoJefeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInformacionContactoJefe.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInformacionContactoJefe))
            )
            .andExpect(status().isOk());

        // Validate the InformacionContactoJefe in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInformacionContactoJefeUpdatableFieldsEquals(
            partialUpdatedInformacionContactoJefe,
            getPersistedInformacionContactoJefe(partialUpdatedInformacionContactoJefe)
        );
    }

    @Test
    @Transactional
    void patchNonExistingInformacionContactoJefe() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoJefe.setId(longCount.incrementAndGet());

        // Create the InformacionContactoJefe
        InformacionContactoJefeDTO informacionContactoJefeDTO = informacionContactoJefeMapper.toDto(informacionContactoJefe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInformacionContactoJefeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, informacionContactoJefeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(informacionContactoJefeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoJefe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInformacionContactoJefe() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoJefe.setId(longCount.incrementAndGet());

        // Create the InformacionContactoJefe
        InformacionContactoJefeDTO informacionContactoJefeDTO = informacionContactoJefeMapper.toDto(informacionContactoJefe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacionContactoJefeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(informacionContactoJefeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InformacionContactoJefe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInformacionContactoJefe() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        informacionContactoJefe.setId(longCount.incrementAndGet());

        // Create the InformacionContactoJefe
        InformacionContactoJefeDTO informacionContactoJefeDTO = informacionContactoJefeMapper.toDto(informacionContactoJefe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInformacionContactoJefeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(informacionContactoJefeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InformacionContactoJefe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInformacionContactoJefe() throws Exception {
        // Initialize the database
        insertedInformacionContactoJefe = informacionContactoJefeRepository.saveAndFlush(informacionContactoJefe);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the informacionContactoJefe
        restInformacionContactoJefeMockMvc
            .perform(delete(ENTITY_API_URL_ID, informacionContactoJefe.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return informacionContactoJefeRepository.count();
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

    protected InformacionContactoJefe getPersistedInformacionContactoJefe(InformacionContactoJefe informacionContactoJefe) {
        return informacionContactoJefeRepository.findById(informacionContactoJefe.getId()).orElseThrow();
    }

    protected void assertPersistedInformacionContactoJefeToMatchAllProperties(InformacionContactoJefe expectedInformacionContactoJefe) {
        assertInformacionContactoJefeAllPropertiesEquals(
            expectedInformacionContactoJefe,
            getPersistedInformacionContactoJefe(expectedInformacionContactoJefe)
        );
    }

    protected void assertPersistedInformacionContactoJefeToMatchUpdatableProperties(
        InformacionContactoJefe expectedInformacionContactoJefe
    ) {
        assertInformacionContactoJefeAllUpdatablePropertiesEquals(
            expectedInformacionContactoJefe,
            getPersistedInformacionContactoJefe(expectedInformacionContactoJefe)
        );
    }
}
