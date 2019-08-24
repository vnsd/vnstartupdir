package com.vnsd.business.web.rest;

import com.vnsd.business.VnstartupdirApp;
import com.vnsd.business.domain.PersonCompanyRelation;
import com.vnsd.business.repository.PersonCompanyRelationRepository;
import com.vnsd.business.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.vnsd.business.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PersonCompanyRelationResource} REST controller.
 */
@SpringBootTest(classes = VnstartupdirApp.class)
public class PersonCompanyRelationResourceIT {

    private static final String DEFAULT_RELATION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RELATION_CODE = "BBBBBBBBBB";

    @Autowired
    private PersonCompanyRelationRepository personCompanyRelationRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restPersonCompanyRelationMockMvc;

    private PersonCompanyRelation personCompanyRelation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PersonCompanyRelationResource personCompanyRelationResource = new PersonCompanyRelationResource(personCompanyRelationRepository);
        this.restPersonCompanyRelationMockMvc = MockMvcBuilders.standaloneSetup(personCompanyRelationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonCompanyRelation createEntity(EntityManager em) {
        PersonCompanyRelation personCompanyRelation = new PersonCompanyRelation()
            .relationCode(DEFAULT_RELATION_CODE);
        return personCompanyRelation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonCompanyRelation createUpdatedEntity(EntityManager em) {
        PersonCompanyRelation personCompanyRelation = new PersonCompanyRelation()
            .relationCode(UPDATED_RELATION_CODE);
        return personCompanyRelation;
    }

    @BeforeEach
    public void initTest() {
        personCompanyRelation = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonCompanyRelation() throws Exception {
        int databaseSizeBeforeCreate = personCompanyRelationRepository.findAll().size();

        // Create the PersonCompanyRelation
        restPersonCompanyRelationMockMvc.perform(post("/api/person-company-relations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personCompanyRelation)))
            .andExpect(status().isCreated());

        // Validate the PersonCompanyRelation in the database
        List<PersonCompanyRelation> personCompanyRelationList = personCompanyRelationRepository.findAll();
        assertThat(personCompanyRelationList).hasSize(databaseSizeBeforeCreate + 1);
        PersonCompanyRelation testPersonCompanyRelation = personCompanyRelationList.get(personCompanyRelationList.size() - 1);
        assertThat(testPersonCompanyRelation.getRelationCode()).isEqualTo(DEFAULT_RELATION_CODE);
    }

    @Test
    @Transactional
    public void createPersonCompanyRelationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personCompanyRelationRepository.findAll().size();

        // Create the PersonCompanyRelation with an existing ID
        personCompanyRelation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonCompanyRelationMockMvc.perform(post("/api/person-company-relations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personCompanyRelation)))
            .andExpect(status().isBadRequest());

        // Validate the PersonCompanyRelation in the database
        List<PersonCompanyRelation> personCompanyRelationList = personCompanyRelationRepository.findAll();
        assertThat(personCompanyRelationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPersonCompanyRelations() throws Exception {
        // Initialize the database
        personCompanyRelationRepository.saveAndFlush(personCompanyRelation);

        // Get all the personCompanyRelationList
        restPersonCompanyRelationMockMvc.perform(get("/api/person-company-relations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personCompanyRelation.getId().intValue())))
            .andExpect(jsonPath("$.[*].relationCode").value(hasItem(DEFAULT_RELATION_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getPersonCompanyRelation() throws Exception {
        // Initialize the database
        personCompanyRelationRepository.saveAndFlush(personCompanyRelation);

        // Get the personCompanyRelation
        restPersonCompanyRelationMockMvc.perform(get("/api/person-company-relations/{id}", personCompanyRelation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(personCompanyRelation.getId().intValue()))
            .andExpect(jsonPath("$.relationCode").value(DEFAULT_RELATION_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPersonCompanyRelation() throws Exception {
        // Get the personCompanyRelation
        restPersonCompanyRelationMockMvc.perform(get("/api/person-company-relations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonCompanyRelation() throws Exception {
        // Initialize the database
        personCompanyRelationRepository.saveAndFlush(personCompanyRelation);

        int databaseSizeBeforeUpdate = personCompanyRelationRepository.findAll().size();

        // Update the personCompanyRelation
        PersonCompanyRelation updatedPersonCompanyRelation = personCompanyRelationRepository.findById(personCompanyRelation.getId()).get();
        // Disconnect from session so that the updates on updatedPersonCompanyRelation are not directly saved in db
        em.detach(updatedPersonCompanyRelation);
        updatedPersonCompanyRelation
            .relationCode(UPDATED_RELATION_CODE);

        restPersonCompanyRelationMockMvc.perform(put("/api/person-company-relations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPersonCompanyRelation)))
            .andExpect(status().isOk());

        // Validate the PersonCompanyRelation in the database
        List<PersonCompanyRelation> personCompanyRelationList = personCompanyRelationRepository.findAll();
        assertThat(personCompanyRelationList).hasSize(databaseSizeBeforeUpdate);
        PersonCompanyRelation testPersonCompanyRelation = personCompanyRelationList.get(personCompanyRelationList.size() - 1);
        assertThat(testPersonCompanyRelation.getRelationCode()).isEqualTo(UPDATED_RELATION_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonCompanyRelation() throws Exception {
        int databaseSizeBeforeUpdate = personCompanyRelationRepository.findAll().size();

        // Create the PersonCompanyRelation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonCompanyRelationMockMvc.perform(put("/api/person-company-relations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personCompanyRelation)))
            .andExpect(status().isBadRequest());

        // Validate the PersonCompanyRelation in the database
        List<PersonCompanyRelation> personCompanyRelationList = personCompanyRelationRepository.findAll();
        assertThat(personCompanyRelationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersonCompanyRelation() throws Exception {
        // Initialize the database
        personCompanyRelationRepository.saveAndFlush(personCompanyRelation);

        int databaseSizeBeforeDelete = personCompanyRelationRepository.findAll().size();

        // Delete the personCompanyRelation
        restPersonCompanyRelationMockMvc.perform(delete("/api/person-company-relations/{id}", personCompanyRelation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PersonCompanyRelation> personCompanyRelationList = personCompanyRelationRepository.findAll();
        assertThat(personCompanyRelationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonCompanyRelation.class);
        PersonCompanyRelation personCompanyRelation1 = new PersonCompanyRelation();
        personCompanyRelation1.setId(1L);
        PersonCompanyRelation personCompanyRelation2 = new PersonCompanyRelation();
        personCompanyRelation2.setId(personCompanyRelation1.getId());
        assertThat(personCompanyRelation1).isEqualTo(personCompanyRelation2);
        personCompanyRelation2.setId(2L);
        assertThat(personCompanyRelation1).isNotEqualTo(personCompanyRelation2);
        personCompanyRelation1.setId(null);
        assertThat(personCompanyRelation1).isNotEqualTo(personCompanyRelation2);
    }
}
