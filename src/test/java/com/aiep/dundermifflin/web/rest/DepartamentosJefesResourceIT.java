package com.aiep.dundermifflin.web.rest;

import static com.aiep.dundermifflin.domain.DepartamentosJefesAsserts.*;
import static com.aiep.dundermifflin.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.aiep.dundermifflin.IntegrationTest;
import com.aiep.dundermifflin.domain.DepartamentosJefes;
import com.aiep.dundermifflin.repository.DepartamentosJefesRepository;
import com.aiep.dundermifflin.service.dto.DepartamentosJefesDTO;
import com.aiep.dundermifflin.service.mapper.DepartamentosJefesMapper;
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
 * Integration tests for the {@link DepartamentosJefesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DepartamentosJefesResourceIT {

    private static final String ENTITY_API_URL = "/api/departamentos-jefes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DepartamentosJefesRepository departamentosJefesRepository;

    @Autowired
    private DepartamentosJefesMapper departamentosJefesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDepartamentosJefesMockMvc;

    private DepartamentosJefes departamentosJefes;

    private DepartamentosJefes insertedDepartamentosJefes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DepartamentosJefes createEntity() {
        return new DepartamentosJefes();
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DepartamentosJefes createUpdatedEntity() {
        return new DepartamentosJefes();
    }

    @BeforeEach
    public void initTest() {
        departamentosJefes = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDepartamentosJefes != null) {
            departamentosJefesRepository.delete(insertedDepartamentosJefes);
            insertedDepartamentosJefes = null;
        }
    }

    @Test
    @Transactional
    void createDepartamentosJefes() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DepartamentosJefes
        DepartamentosJefesDTO departamentosJefesDTO = departamentosJefesMapper.toDto(departamentosJefes);
        var returnedDepartamentosJefesDTO = om.readValue(
            restDepartamentosJefesMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(departamentosJefesDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DepartamentosJefesDTO.class
        );

        // Validate the DepartamentosJefes in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDepartamentosJefes = departamentosJefesMapper.toEntity(returnedDepartamentosJefesDTO);
        assertDepartamentosJefesUpdatableFieldsEquals(
            returnedDepartamentosJefes,
            getPersistedDepartamentosJefes(returnedDepartamentosJefes)
        );

        insertedDepartamentosJefes = returnedDepartamentosJefes;
    }

    @Test
    @Transactional
    void createDepartamentosJefesWithExistingId() throws Exception {
        // Create the DepartamentosJefes with an existing ID
        departamentosJefes.setId(1L);
        DepartamentosJefesDTO departamentosJefesDTO = departamentosJefesMapper.toDto(departamentosJefes);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepartamentosJefesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(departamentosJefesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DepartamentosJefes in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDepartamentosJefes() throws Exception {
        // Initialize the database
        insertedDepartamentosJefes = departamentosJefesRepository.saveAndFlush(departamentosJefes);

        // Get all the departamentosJefesList
        restDepartamentosJefesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(departamentosJefes.getId().intValue())));
    }

    @Test
    @Transactional
    void getDepartamentosJefes() throws Exception {
        // Initialize the database
        insertedDepartamentosJefes = departamentosJefesRepository.saveAndFlush(departamentosJefes);

        // Get the departamentosJefes
        restDepartamentosJefesMockMvc
            .perform(get(ENTITY_API_URL_ID, departamentosJefes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(departamentosJefes.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingDepartamentosJefes() throws Exception {
        // Get the departamentosJefes
        restDepartamentosJefesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDepartamentosJefes() throws Exception {
        // Initialize the database
        insertedDepartamentosJefes = departamentosJefesRepository.saveAndFlush(departamentosJefes);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the departamentosJefes
        DepartamentosJefes updatedDepartamentosJefes = departamentosJefesRepository.findById(departamentosJefes.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDepartamentosJefes are not directly saved in db
        em.detach(updatedDepartamentosJefes);
        DepartamentosJefesDTO departamentosJefesDTO = departamentosJefesMapper.toDto(updatedDepartamentosJefes);

        restDepartamentosJefesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, departamentosJefesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(departamentosJefesDTO))
            )
            .andExpect(status().isOk());

        // Validate the DepartamentosJefes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDepartamentosJefesToMatchAllProperties(updatedDepartamentosJefes);
    }

    @Test
    @Transactional
    void putNonExistingDepartamentosJefes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        departamentosJefes.setId(longCount.incrementAndGet());

        // Create the DepartamentosJefes
        DepartamentosJefesDTO departamentosJefesDTO = departamentosJefesMapper.toDto(departamentosJefes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepartamentosJefesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, departamentosJefesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(departamentosJefesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DepartamentosJefes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDepartamentosJefes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        departamentosJefes.setId(longCount.incrementAndGet());

        // Create the DepartamentosJefes
        DepartamentosJefesDTO departamentosJefesDTO = departamentosJefesMapper.toDto(departamentosJefes);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepartamentosJefesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(departamentosJefesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DepartamentosJefes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDepartamentosJefes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        departamentosJefes.setId(longCount.incrementAndGet());

        // Create the DepartamentosJefes
        DepartamentosJefesDTO departamentosJefesDTO = departamentosJefesMapper.toDto(departamentosJefes);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepartamentosJefesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(departamentosJefesDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DepartamentosJefes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDepartamentosJefesWithPatch() throws Exception {
        // Initialize the database
        insertedDepartamentosJefes = departamentosJefesRepository.saveAndFlush(departamentosJefes);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the departamentosJefes using partial update
        DepartamentosJefes partialUpdatedDepartamentosJefes = new DepartamentosJefes();
        partialUpdatedDepartamentosJefes.setId(departamentosJefes.getId());

        restDepartamentosJefesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDepartamentosJefes.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDepartamentosJefes))
            )
            .andExpect(status().isOk());

        // Validate the DepartamentosJefes in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDepartamentosJefesUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDepartamentosJefes, departamentosJefes),
            getPersistedDepartamentosJefes(departamentosJefes)
        );
    }

    @Test
    @Transactional
    void fullUpdateDepartamentosJefesWithPatch() throws Exception {
        // Initialize the database
        insertedDepartamentosJefes = departamentosJefesRepository.saveAndFlush(departamentosJefes);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the departamentosJefes using partial update
        DepartamentosJefes partialUpdatedDepartamentosJefes = new DepartamentosJefes();
        partialUpdatedDepartamentosJefes.setId(departamentosJefes.getId());

        restDepartamentosJefesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDepartamentosJefes.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDepartamentosJefes))
            )
            .andExpect(status().isOk());

        // Validate the DepartamentosJefes in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDepartamentosJefesUpdatableFieldsEquals(
            partialUpdatedDepartamentosJefes,
            getPersistedDepartamentosJefes(partialUpdatedDepartamentosJefes)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDepartamentosJefes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        departamentosJefes.setId(longCount.incrementAndGet());

        // Create the DepartamentosJefes
        DepartamentosJefesDTO departamentosJefesDTO = departamentosJefesMapper.toDto(departamentosJefes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepartamentosJefesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, departamentosJefesDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(departamentosJefesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DepartamentosJefes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDepartamentosJefes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        departamentosJefes.setId(longCount.incrementAndGet());

        // Create the DepartamentosJefes
        DepartamentosJefesDTO departamentosJefesDTO = departamentosJefesMapper.toDto(departamentosJefes);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepartamentosJefesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(departamentosJefesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DepartamentosJefes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDepartamentosJefes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        departamentosJefes.setId(longCount.incrementAndGet());

        // Create the DepartamentosJefes
        DepartamentosJefesDTO departamentosJefesDTO = departamentosJefesMapper.toDto(departamentosJefes);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepartamentosJefesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(departamentosJefesDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DepartamentosJefes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDepartamentosJefes() throws Exception {
        // Initialize the database
        insertedDepartamentosJefes = departamentosJefesRepository.saveAndFlush(departamentosJefes);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the departamentosJefes
        restDepartamentosJefesMockMvc
            .perform(delete(ENTITY_API_URL_ID, departamentosJefes.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return departamentosJefesRepository.count();
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

    protected DepartamentosJefes getPersistedDepartamentosJefes(DepartamentosJefes departamentosJefes) {
        return departamentosJefesRepository.findById(departamentosJefes.getId()).orElseThrow();
    }

    protected void assertPersistedDepartamentosJefesToMatchAllProperties(DepartamentosJefes expectedDepartamentosJefes) {
        assertDepartamentosJefesAllPropertiesEquals(expectedDepartamentosJefes, getPersistedDepartamentosJefes(expectedDepartamentosJefes));
    }

    protected void assertPersistedDepartamentosJefesToMatchUpdatableProperties(DepartamentosJefes expectedDepartamentosJefes) {
        assertDepartamentosJefesAllUpdatablePropertiesEquals(
            expectedDepartamentosJefes,
            getPersistedDepartamentosJefes(expectedDepartamentosJefes)
        );
    }
}
