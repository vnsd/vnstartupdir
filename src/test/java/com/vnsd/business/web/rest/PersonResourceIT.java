package com.vnsd.business.web.rest;

import com.vnsd.business.VnstartupdirApp;
import com.vnsd.business.domain.Person;
import com.vnsd.business.domain.PersonCompanyRelation;
import com.vnsd.business.domain.User;
import com.vnsd.business.repository.PersonRepository;
import com.vnsd.business.service.PersonService;
import com.vnsd.business.service.dto.PersonDTO;
import com.vnsd.business.service.mapper.PersonMapper;
import com.vnsd.business.web.rest.errors.ExceptionTranslator;
import com.vnsd.business.service.dto.PersonCriteria;
import com.vnsd.business.service.PersonQueryService;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.vnsd.business.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PersonResource} REST controller.
 */
@SpringBootTest(classes = VnstartupdirApp.class)
public class PersonResourceIT {

    private static final String DEFAULT_UUID = "AAAAAAAAAA";
    private static final String UPDATED_UUID = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_BORNON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BORNON = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_BORNON = Instant.ofEpochMilli(-1L);

    private static final Instant DEFAULT_DIEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DIEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_DIEDON = Instant.ofEpochMilli(-1L);

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_CREATEDAT = Instant.ofEpochMilli(-1L);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_UPDATEDAT = Instant.ofEpochMilli(-1L);

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_HOMEPAGEURL = "AAAAAAAAAA";
    private static final String UPDATED_HOMEPAGEURL = "BBBBBBBBBB";

    private static final String DEFAULT_FACEBOOKURL = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOKURL = "BBBBBBBBBB";

    private static final String DEFAULT_TWITTERURL = "AAAAAAAAAA";
    private static final String UPDATED_TWITTERURL = "BBBBBBBBBB";

    private static final String DEFAULT_LINKEDINURL = "AAAAAAAAAA";
    private static final String UPDATED_LINKEDINURL = "BBBBBBBBBB";

    private static final String DEFAULT_CITYNAME = "AAAAAAAAAA";
    private static final String UPDATED_CITYNAME = "BBBBBBBBBB";

    private static final String DEFAULT_REGIONNAME = "AAAAAAAAAA";
    private static final String UPDATED_REGIONNAME = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRYCODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRYCODE = "BBBBBBBBBB";

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonQueryService personQueryService;

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

    private MockMvc restPersonMockMvc;

    private Person person;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PersonResource personResource = new PersonResource(personService, personQueryService);
        this.restPersonMockMvc = MockMvcBuilders.standaloneSetup(personResource)
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
    public static Person createEntity(EntityManager em) {
        Person person = new Person()
            .uuid(DEFAULT_UUID)
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .bornon(DEFAULT_BORNON)
            .diedon(DEFAULT_DIEDON)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT)
            .gender(DEFAULT_GENDER)
            .title(DEFAULT_TITLE)
            .homepageurl(DEFAULT_HOMEPAGEURL)
            .facebookurl(DEFAULT_FACEBOOKURL)
            .twitterurl(DEFAULT_TWITTERURL)
            .linkedinurl(DEFAULT_LINKEDINURL)
            .cityname(DEFAULT_CITYNAME)
            .regionname(DEFAULT_REGIONNAME)
            .countrycode(DEFAULT_COUNTRYCODE);
        return person;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Person createUpdatedEntity(EntityManager em) {
        Person person = new Person()
            .uuid(UPDATED_UUID)
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .bornon(UPDATED_BORNON)
            .diedon(UPDATED_DIEDON)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT)
            .gender(UPDATED_GENDER)
            .title(UPDATED_TITLE)
            .homepageurl(UPDATED_HOMEPAGEURL)
            .facebookurl(UPDATED_FACEBOOKURL)
            .twitterurl(UPDATED_TWITTERURL)
            .linkedinurl(UPDATED_LINKEDINURL)
            .cityname(UPDATED_CITYNAME)
            .regionname(UPDATED_REGIONNAME)
            .countrycode(UPDATED_COUNTRYCODE);
        return person;
    }

    @BeforeEach
    public void initTest() {
        person = createEntity(em);
    }

    @Test
    @Transactional
    public void createPerson() throws Exception {
        int databaseSizeBeforeCreate = personRepository.findAll().size();

        // Create the Person
        PersonDTO personDTO = personMapper.toDto(person);
        restPersonMockMvc.perform(post("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personDTO)))
            .andExpect(status().isCreated());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeCreate + 1);
        Person testPerson = personList.get(personList.size() - 1);
        assertThat(testPerson.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testPerson.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testPerson.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testPerson.getBornon()).isEqualTo(DEFAULT_BORNON);
        assertThat(testPerson.getDiedon()).isEqualTo(DEFAULT_DIEDON);
        assertThat(testPerson.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testPerson.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
        assertThat(testPerson.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testPerson.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testPerson.getHomepageurl()).isEqualTo(DEFAULT_HOMEPAGEURL);
        assertThat(testPerson.getFacebookurl()).isEqualTo(DEFAULT_FACEBOOKURL);
        assertThat(testPerson.getTwitterurl()).isEqualTo(DEFAULT_TWITTERURL);
        assertThat(testPerson.getLinkedinurl()).isEqualTo(DEFAULT_LINKEDINURL);
        assertThat(testPerson.getCityname()).isEqualTo(DEFAULT_CITYNAME);
        assertThat(testPerson.getRegionname()).isEqualTo(DEFAULT_REGIONNAME);
        assertThat(testPerson.getCountrycode()).isEqualTo(DEFAULT_COUNTRYCODE);
    }

    @Test
    @Transactional
    public void createPersonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personRepository.findAll().size();

        // Create the Person with an existing ID
        person.setId(1L);
        PersonDTO personDTO = personMapper.toDto(person);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonMockMvc.perform(post("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = personRepository.findAll().size();
        // set the field null
        person.setUuid(null);

        // Create the Person, which fails.
        PersonDTO personDTO = personMapper.toDto(person);

        restPersonMockMvc.perform(post("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personDTO)))
            .andExpect(status().isBadRequest());

        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personRepository.findAll().size();
        // set the field null
        person.setFirstname(null);

        // Create the Person, which fails.
        PersonDTO personDTO = personMapper.toDto(person);

        restPersonMockMvc.perform(post("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personDTO)))
            .andExpect(status().isBadRequest());

        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personRepository.findAll().size();
        // set the field null
        person.setLastname(null);

        // Create the Person, which fails.
        PersonDTO personDTO = personMapper.toDto(person);

        restPersonMockMvc.perform(post("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personDTO)))
            .andExpect(status().isBadRequest());

        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPeople() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList
        restPersonMockMvc.perform(get("/api/people?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(person.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME.toString())))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME.toString())))
            .andExpect(jsonPath("$.[*].bornon").value(hasItem(DEFAULT_BORNON.toString())))
            .andExpect(jsonPath("$.[*].diedon").value(hasItem(DEFAULT_DIEDON.toString())))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].homepageurl").value(hasItem(DEFAULT_HOMEPAGEURL.toString())))
            .andExpect(jsonPath("$.[*].facebookurl").value(hasItem(DEFAULT_FACEBOOKURL.toString())))
            .andExpect(jsonPath("$.[*].twitterurl").value(hasItem(DEFAULT_TWITTERURL.toString())))
            .andExpect(jsonPath("$.[*].linkedinurl").value(hasItem(DEFAULT_LINKEDINURL.toString())))
            .andExpect(jsonPath("$.[*].cityname").value(hasItem(DEFAULT_CITYNAME.toString())))
            .andExpect(jsonPath("$.[*].regionname").value(hasItem(DEFAULT_REGIONNAME.toString())))
            .andExpect(jsonPath("$.[*].countrycode").value(hasItem(DEFAULT_COUNTRYCODE.toString())));
    }

    @Test
    @Transactional
    public void getPerson() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get the person
        restPersonMockMvc.perform(get("/api/people/{id}", person.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(person.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME.toString()))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME.toString()))
            .andExpect(jsonPath("$.bornon").value(DEFAULT_BORNON.toString()))
            .andExpect(jsonPath("$.diedon").value(DEFAULT_DIEDON.toString()))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.homepageurl").value(DEFAULT_HOMEPAGEURL.toString()))
            .andExpect(jsonPath("$.facebookurl").value(DEFAULT_FACEBOOKURL.toString()))
            .andExpect(jsonPath("$.twitterurl").value(DEFAULT_TWITTERURL.toString()))
            .andExpect(jsonPath("$.linkedinurl").value(DEFAULT_LINKEDINURL.toString()))
            .andExpect(jsonPath("$.cityname").value(DEFAULT_CITYNAME.toString()))
            .andExpect(jsonPath("$.regionname").value(DEFAULT_REGIONNAME.toString()))
            .andExpect(jsonPath("$.countrycode").value(DEFAULT_COUNTRYCODE.toString()));
    }

    @Test
    @Transactional
    public void getAllPeopleByUuidIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where uuid equals to DEFAULT_UUID
        defaultPersonShouldBeFound("uuid.equals=" + DEFAULT_UUID);

        // Get all the personList where uuid equals to UPDATED_UUID
        defaultPersonShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    public void getAllPeopleByUuidIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where uuid in DEFAULT_UUID or UPDATED_UUID
        defaultPersonShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

        // Get all the personList where uuid equals to UPDATED_UUID
        defaultPersonShouldNotBeFound("uuid.in=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    public void getAllPeopleByUuidIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where uuid is not null
        defaultPersonShouldBeFound("uuid.specified=true");

        // Get all the personList where uuid is null
        defaultPersonShouldNotBeFound("uuid.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByFirstnameIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where firstname equals to DEFAULT_FIRSTNAME
        defaultPersonShouldBeFound("firstname.equals=" + DEFAULT_FIRSTNAME);

        // Get all the personList where firstname equals to UPDATED_FIRSTNAME
        defaultPersonShouldNotBeFound("firstname.equals=" + UPDATED_FIRSTNAME);
    }

    @Test
    @Transactional
    public void getAllPeopleByFirstnameIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where firstname in DEFAULT_FIRSTNAME or UPDATED_FIRSTNAME
        defaultPersonShouldBeFound("firstname.in=" + DEFAULT_FIRSTNAME + "," + UPDATED_FIRSTNAME);

        // Get all the personList where firstname equals to UPDATED_FIRSTNAME
        defaultPersonShouldNotBeFound("firstname.in=" + UPDATED_FIRSTNAME);
    }

    @Test
    @Transactional
    public void getAllPeopleByFirstnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where firstname is not null
        defaultPersonShouldBeFound("firstname.specified=true");

        // Get all the personList where firstname is null
        defaultPersonShouldNotBeFound("firstname.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByLastnameIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where lastname equals to DEFAULT_LASTNAME
        defaultPersonShouldBeFound("lastname.equals=" + DEFAULT_LASTNAME);

        // Get all the personList where lastname equals to UPDATED_LASTNAME
        defaultPersonShouldNotBeFound("lastname.equals=" + UPDATED_LASTNAME);
    }

    @Test
    @Transactional
    public void getAllPeopleByLastnameIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where lastname in DEFAULT_LASTNAME or UPDATED_LASTNAME
        defaultPersonShouldBeFound("lastname.in=" + DEFAULT_LASTNAME + "," + UPDATED_LASTNAME);

        // Get all the personList where lastname equals to UPDATED_LASTNAME
        defaultPersonShouldNotBeFound("lastname.in=" + UPDATED_LASTNAME);
    }

    @Test
    @Transactional
    public void getAllPeopleByLastnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where lastname is not null
        defaultPersonShouldBeFound("lastname.specified=true");

        // Get all the personList where lastname is null
        defaultPersonShouldNotBeFound("lastname.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByBornonIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where bornon equals to DEFAULT_BORNON
        defaultPersonShouldBeFound("bornon.equals=" + DEFAULT_BORNON);

        // Get all the personList where bornon equals to UPDATED_BORNON
        defaultPersonShouldNotBeFound("bornon.equals=" + UPDATED_BORNON);
    }

    @Test
    @Transactional
    public void getAllPeopleByBornonIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where bornon in DEFAULT_BORNON or UPDATED_BORNON
        defaultPersonShouldBeFound("bornon.in=" + DEFAULT_BORNON + "," + UPDATED_BORNON);

        // Get all the personList where bornon equals to UPDATED_BORNON
        defaultPersonShouldNotBeFound("bornon.in=" + UPDATED_BORNON);
    }

    @Test
    @Transactional
    public void getAllPeopleByBornonIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where bornon is not null
        defaultPersonShouldBeFound("bornon.specified=true");

        // Get all the personList where bornon is null
        defaultPersonShouldNotBeFound("bornon.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByDiedonIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where diedon equals to DEFAULT_DIEDON
        defaultPersonShouldBeFound("diedon.equals=" + DEFAULT_DIEDON);

        // Get all the personList where diedon equals to UPDATED_DIEDON
        defaultPersonShouldNotBeFound("diedon.equals=" + UPDATED_DIEDON);
    }

    @Test
    @Transactional
    public void getAllPeopleByDiedonIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where diedon in DEFAULT_DIEDON or UPDATED_DIEDON
        defaultPersonShouldBeFound("diedon.in=" + DEFAULT_DIEDON + "," + UPDATED_DIEDON);

        // Get all the personList where diedon equals to UPDATED_DIEDON
        defaultPersonShouldNotBeFound("diedon.in=" + UPDATED_DIEDON);
    }

    @Test
    @Transactional
    public void getAllPeopleByDiedonIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where diedon is not null
        defaultPersonShouldBeFound("diedon.specified=true");

        // Get all the personList where diedon is null
        defaultPersonShouldNotBeFound("diedon.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByCreatedatIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where createdat equals to DEFAULT_CREATEDAT
        defaultPersonShouldBeFound("createdat.equals=" + DEFAULT_CREATEDAT);

        // Get all the personList where createdat equals to UPDATED_CREATEDAT
        defaultPersonShouldNotBeFound("createdat.equals=" + UPDATED_CREATEDAT);
    }

    @Test
    @Transactional
    public void getAllPeopleByCreatedatIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where createdat in DEFAULT_CREATEDAT or UPDATED_CREATEDAT
        defaultPersonShouldBeFound("createdat.in=" + DEFAULT_CREATEDAT + "," + UPDATED_CREATEDAT);

        // Get all the personList where createdat equals to UPDATED_CREATEDAT
        defaultPersonShouldNotBeFound("createdat.in=" + UPDATED_CREATEDAT);
    }

    @Test
    @Transactional
    public void getAllPeopleByCreatedatIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where createdat is not null
        defaultPersonShouldBeFound("createdat.specified=true");

        // Get all the personList where createdat is null
        defaultPersonShouldNotBeFound("createdat.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByUpdatedatIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where updatedat equals to DEFAULT_UPDATEDAT
        defaultPersonShouldBeFound("updatedat.equals=" + DEFAULT_UPDATEDAT);

        // Get all the personList where updatedat equals to UPDATED_UPDATEDAT
        defaultPersonShouldNotBeFound("updatedat.equals=" + UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void getAllPeopleByUpdatedatIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where updatedat in DEFAULT_UPDATEDAT or UPDATED_UPDATEDAT
        defaultPersonShouldBeFound("updatedat.in=" + DEFAULT_UPDATEDAT + "," + UPDATED_UPDATEDAT);

        // Get all the personList where updatedat equals to UPDATED_UPDATEDAT
        defaultPersonShouldNotBeFound("updatedat.in=" + UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void getAllPeopleByUpdatedatIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where updatedat is not null
        defaultPersonShouldBeFound("updatedat.specified=true");

        // Get all the personList where updatedat is null
        defaultPersonShouldNotBeFound("updatedat.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByGenderIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where gender equals to DEFAULT_GENDER
        defaultPersonShouldBeFound("gender.equals=" + DEFAULT_GENDER);

        // Get all the personList where gender equals to UPDATED_GENDER
        defaultPersonShouldNotBeFound("gender.equals=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    public void getAllPeopleByGenderIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where gender in DEFAULT_GENDER or UPDATED_GENDER
        defaultPersonShouldBeFound("gender.in=" + DEFAULT_GENDER + "," + UPDATED_GENDER);

        // Get all the personList where gender equals to UPDATED_GENDER
        defaultPersonShouldNotBeFound("gender.in=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    public void getAllPeopleByGenderIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where gender is not null
        defaultPersonShouldBeFound("gender.specified=true");

        // Get all the personList where gender is null
        defaultPersonShouldNotBeFound("gender.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByTitleIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where title equals to DEFAULT_TITLE
        defaultPersonShouldBeFound("title.equals=" + DEFAULT_TITLE);

        // Get all the personList where title equals to UPDATED_TITLE
        defaultPersonShouldNotBeFound("title.equals=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllPeopleByTitleIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where title in DEFAULT_TITLE or UPDATED_TITLE
        defaultPersonShouldBeFound("title.in=" + DEFAULT_TITLE + "," + UPDATED_TITLE);

        // Get all the personList where title equals to UPDATED_TITLE
        defaultPersonShouldNotBeFound("title.in=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllPeopleByTitleIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where title is not null
        defaultPersonShouldBeFound("title.specified=true");

        // Get all the personList where title is null
        defaultPersonShouldNotBeFound("title.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByHomepageurlIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where homepageurl equals to DEFAULT_HOMEPAGEURL
        defaultPersonShouldBeFound("homepageurl.equals=" + DEFAULT_HOMEPAGEURL);

        // Get all the personList where homepageurl equals to UPDATED_HOMEPAGEURL
        defaultPersonShouldNotBeFound("homepageurl.equals=" + UPDATED_HOMEPAGEURL);
    }

    @Test
    @Transactional
    public void getAllPeopleByHomepageurlIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where homepageurl in DEFAULT_HOMEPAGEURL or UPDATED_HOMEPAGEURL
        defaultPersonShouldBeFound("homepageurl.in=" + DEFAULT_HOMEPAGEURL + "," + UPDATED_HOMEPAGEURL);

        // Get all the personList where homepageurl equals to UPDATED_HOMEPAGEURL
        defaultPersonShouldNotBeFound("homepageurl.in=" + UPDATED_HOMEPAGEURL);
    }

    @Test
    @Transactional
    public void getAllPeopleByHomepageurlIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where homepageurl is not null
        defaultPersonShouldBeFound("homepageurl.specified=true");

        // Get all the personList where homepageurl is null
        defaultPersonShouldNotBeFound("homepageurl.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByFacebookurlIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where facebookurl equals to DEFAULT_FACEBOOKURL
        defaultPersonShouldBeFound("facebookurl.equals=" + DEFAULT_FACEBOOKURL);

        // Get all the personList where facebookurl equals to UPDATED_FACEBOOKURL
        defaultPersonShouldNotBeFound("facebookurl.equals=" + UPDATED_FACEBOOKURL);
    }

    @Test
    @Transactional
    public void getAllPeopleByFacebookurlIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where facebookurl in DEFAULT_FACEBOOKURL or UPDATED_FACEBOOKURL
        defaultPersonShouldBeFound("facebookurl.in=" + DEFAULT_FACEBOOKURL + "," + UPDATED_FACEBOOKURL);

        // Get all the personList where facebookurl equals to UPDATED_FACEBOOKURL
        defaultPersonShouldNotBeFound("facebookurl.in=" + UPDATED_FACEBOOKURL);
    }

    @Test
    @Transactional
    public void getAllPeopleByFacebookurlIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where facebookurl is not null
        defaultPersonShouldBeFound("facebookurl.specified=true");

        // Get all the personList where facebookurl is null
        defaultPersonShouldNotBeFound("facebookurl.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByTwitterurlIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where twitterurl equals to DEFAULT_TWITTERURL
        defaultPersonShouldBeFound("twitterurl.equals=" + DEFAULT_TWITTERURL);

        // Get all the personList where twitterurl equals to UPDATED_TWITTERURL
        defaultPersonShouldNotBeFound("twitterurl.equals=" + UPDATED_TWITTERURL);
    }

    @Test
    @Transactional
    public void getAllPeopleByTwitterurlIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where twitterurl in DEFAULT_TWITTERURL or UPDATED_TWITTERURL
        defaultPersonShouldBeFound("twitterurl.in=" + DEFAULT_TWITTERURL + "," + UPDATED_TWITTERURL);

        // Get all the personList where twitterurl equals to UPDATED_TWITTERURL
        defaultPersonShouldNotBeFound("twitterurl.in=" + UPDATED_TWITTERURL);
    }

    @Test
    @Transactional
    public void getAllPeopleByTwitterurlIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where twitterurl is not null
        defaultPersonShouldBeFound("twitterurl.specified=true");

        // Get all the personList where twitterurl is null
        defaultPersonShouldNotBeFound("twitterurl.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByLinkedinurlIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where linkedinurl equals to DEFAULT_LINKEDINURL
        defaultPersonShouldBeFound("linkedinurl.equals=" + DEFAULT_LINKEDINURL);

        // Get all the personList where linkedinurl equals to UPDATED_LINKEDINURL
        defaultPersonShouldNotBeFound("linkedinurl.equals=" + UPDATED_LINKEDINURL);
    }

    @Test
    @Transactional
    public void getAllPeopleByLinkedinurlIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where linkedinurl in DEFAULT_LINKEDINURL or UPDATED_LINKEDINURL
        defaultPersonShouldBeFound("linkedinurl.in=" + DEFAULT_LINKEDINURL + "," + UPDATED_LINKEDINURL);

        // Get all the personList where linkedinurl equals to UPDATED_LINKEDINURL
        defaultPersonShouldNotBeFound("linkedinurl.in=" + UPDATED_LINKEDINURL);
    }

    @Test
    @Transactional
    public void getAllPeopleByLinkedinurlIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where linkedinurl is not null
        defaultPersonShouldBeFound("linkedinurl.specified=true");

        // Get all the personList where linkedinurl is null
        defaultPersonShouldNotBeFound("linkedinurl.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByCitynameIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where cityname equals to DEFAULT_CITYNAME
        defaultPersonShouldBeFound("cityname.equals=" + DEFAULT_CITYNAME);

        // Get all the personList where cityname equals to UPDATED_CITYNAME
        defaultPersonShouldNotBeFound("cityname.equals=" + UPDATED_CITYNAME);
    }

    @Test
    @Transactional
    public void getAllPeopleByCitynameIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where cityname in DEFAULT_CITYNAME or UPDATED_CITYNAME
        defaultPersonShouldBeFound("cityname.in=" + DEFAULT_CITYNAME + "," + UPDATED_CITYNAME);

        // Get all the personList where cityname equals to UPDATED_CITYNAME
        defaultPersonShouldNotBeFound("cityname.in=" + UPDATED_CITYNAME);
    }

    @Test
    @Transactional
    public void getAllPeopleByCitynameIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where cityname is not null
        defaultPersonShouldBeFound("cityname.specified=true");

        // Get all the personList where cityname is null
        defaultPersonShouldNotBeFound("cityname.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByRegionnameIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where regionname equals to DEFAULT_REGIONNAME
        defaultPersonShouldBeFound("regionname.equals=" + DEFAULT_REGIONNAME);

        // Get all the personList where regionname equals to UPDATED_REGIONNAME
        defaultPersonShouldNotBeFound("regionname.equals=" + UPDATED_REGIONNAME);
    }

    @Test
    @Transactional
    public void getAllPeopleByRegionnameIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where regionname in DEFAULT_REGIONNAME or UPDATED_REGIONNAME
        defaultPersonShouldBeFound("regionname.in=" + DEFAULT_REGIONNAME + "," + UPDATED_REGIONNAME);

        // Get all the personList where regionname equals to UPDATED_REGIONNAME
        defaultPersonShouldNotBeFound("regionname.in=" + UPDATED_REGIONNAME);
    }

    @Test
    @Transactional
    public void getAllPeopleByRegionnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where regionname is not null
        defaultPersonShouldBeFound("regionname.specified=true");

        // Get all the personList where regionname is null
        defaultPersonShouldNotBeFound("regionname.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByCountrycodeIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where countrycode equals to DEFAULT_COUNTRYCODE
        defaultPersonShouldBeFound("countrycode.equals=" + DEFAULT_COUNTRYCODE);

        // Get all the personList where countrycode equals to UPDATED_COUNTRYCODE
        defaultPersonShouldNotBeFound("countrycode.equals=" + UPDATED_COUNTRYCODE);
    }

    @Test
    @Transactional
    public void getAllPeopleByCountrycodeIsInShouldWork() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where countrycode in DEFAULT_COUNTRYCODE or UPDATED_COUNTRYCODE
        defaultPersonShouldBeFound("countrycode.in=" + DEFAULT_COUNTRYCODE + "," + UPDATED_COUNTRYCODE);

        // Get all the personList where countrycode equals to UPDATED_COUNTRYCODE
        defaultPersonShouldNotBeFound("countrycode.in=" + UPDATED_COUNTRYCODE);
    }

    @Test
    @Transactional
    public void getAllPeopleByCountrycodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList where countrycode is not null
        defaultPersonShouldBeFound("countrycode.specified=true");

        // Get all the personList where countrycode is null
        defaultPersonShouldNotBeFound("countrycode.specified=false");
    }

    @Test
    @Transactional
    public void getAllPeopleByCompaniesIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);
        PersonCompanyRelation companies = PersonCompanyRelationResourceIT.createEntity(em);
        em.persist(companies);
        em.flush();
        person.addCompanies(companies);
        personRepository.saveAndFlush(person);
        Long companiesId = companies.getId();

        // Get all the personList where companies equals to companiesId
        defaultPersonShouldBeFound("companiesId.equals=" + companiesId);

        // Get all the personList where companies equals to companiesId + 1
        defaultPersonShouldNotBeFound("companiesId.equals=" + (companiesId + 1));
    }


    @Test
    @Transactional
    public void getAllPeopleByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);
        User createdBy = UserResourceIT.createEntity(em);
        em.persist(createdBy);
        em.flush();
        person.setCreatedBy(createdBy);
        personRepository.saveAndFlush(person);
        Long createdById = createdBy.getId();

        // Get all the personList where createdBy equals to createdById
        defaultPersonShouldBeFound("createdById.equals=" + createdById);

        // Get all the personList where createdBy equals to createdById + 1
        defaultPersonShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllPeopleByUpdatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);
        User updatedBy = UserResourceIT.createEntity(em);
        em.persist(updatedBy);
        em.flush();
        person.setUpdatedBy(updatedBy);
        personRepository.saveAndFlush(person);
        Long updatedById = updatedBy.getId();

        // Get all the personList where updatedBy equals to updatedById
        defaultPersonShouldBeFound("updatedById.equals=" + updatedById);

        // Get all the personList where updatedBy equals to updatedById + 1
        defaultPersonShouldNotBeFound("updatedById.equals=" + (updatedById + 1));
    }


    @Test
    @Transactional
    public void getAllPeopleByAssignedToIsEqualToSomething() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);
        User assignedTo = UserResourceIT.createEntity(em);
        em.persist(assignedTo);
        em.flush();
        person.setAssignedTo(assignedTo);
        personRepository.saveAndFlush(person);
        Long assignedToId = assignedTo.getId();

        // Get all the personList where assignedTo equals to assignedToId
        defaultPersonShouldBeFound("assignedToId.equals=" + assignedToId);

        // Get all the personList where assignedTo equals to assignedToId + 1
        defaultPersonShouldNotBeFound("assignedToId.equals=" + (assignedToId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPersonShouldBeFound(String filter) throws Exception {
        restPersonMockMvc.perform(get("/api/people?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(person.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID)))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].bornon").value(hasItem(DEFAULT_BORNON.toString())))
            .andExpect(jsonPath("$.[*].diedon").value(hasItem(DEFAULT_DIEDON.toString())))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].homepageurl").value(hasItem(DEFAULT_HOMEPAGEURL)))
            .andExpect(jsonPath("$.[*].facebookurl").value(hasItem(DEFAULT_FACEBOOKURL)))
            .andExpect(jsonPath("$.[*].twitterurl").value(hasItem(DEFAULT_TWITTERURL)))
            .andExpect(jsonPath("$.[*].linkedinurl").value(hasItem(DEFAULT_LINKEDINURL)))
            .andExpect(jsonPath("$.[*].cityname").value(hasItem(DEFAULT_CITYNAME)))
            .andExpect(jsonPath("$.[*].regionname").value(hasItem(DEFAULT_REGIONNAME)))
            .andExpect(jsonPath("$.[*].countrycode").value(hasItem(DEFAULT_COUNTRYCODE)));

        // Check, that the count call also returns 1
        restPersonMockMvc.perform(get("/api/people/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPersonShouldNotBeFound(String filter) throws Exception {
        restPersonMockMvc.perform(get("/api/people?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPersonMockMvc.perform(get("/api/people/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPerson() throws Exception {
        // Get the person
        restPersonMockMvc.perform(get("/api/people/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePerson() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        int databaseSizeBeforeUpdate = personRepository.findAll().size();

        // Update the person
        Person updatedPerson = personRepository.findById(person.getId()).get();
        // Disconnect from session so that the updates on updatedPerson are not directly saved in db
        em.detach(updatedPerson);
        updatedPerson
            .uuid(UPDATED_UUID)
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .bornon(UPDATED_BORNON)
            .diedon(UPDATED_DIEDON)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT)
            .gender(UPDATED_GENDER)
            .title(UPDATED_TITLE)
            .homepageurl(UPDATED_HOMEPAGEURL)
            .facebookurl(UPDATED_FACEBOOKURL)
            .twitterurl(UPDATED_TWITTERURL)
            .linkedinurl(UPDATED_LINKEDINURL)
            .cityname(UPDATED_CITYNAME)
            .regionname(UPDATED_REGIONNAME)
            .countrycode(UPDATED_COUNTRYCODE);
        PersonDTO personDTO = personMapper.toDto(updatedPerson);

        restPersonMockMvc.perform(put("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personDTO)))
            .andExpect(status().isOk());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeUpdate);
        Person testPerson = personList.get(personList.size() - 1);
        assertThat(testPerson.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testPerson.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testPerson.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testPerson.getBornon()).isEqualTo(UPDATED_BORNON);
        assertThat(testPerson.getDiedon()).isEqualTo(UPDATED_DIEDON);
        assertThat(testPerson.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testPerson.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
        assertThat(testPerson.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testPerson.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testPerson.getHomepageurl()).isEqualTo(UPDATED_HOMEPAGEURL);
        assertThat(testPerson.getFacebookurl()).isEqualTo(UPDATED_FACEBOOKURL);
        assertThat(testPerson.getTwitterurl()).isEqualTo(UPDATED_TWITTERURL);
        assertThat(testPerson.getLinkedinurl()).isEqualTo(UPDATED_LINKEDINURL);
        assertThat(testPerson.getCityname()).isEqualTo(UPDATED_CITYNAME);
        assertThat(testPerson.getRegionname()).isEqualTo(UPDATED_REGIONNAME);
        assertThat(testPerson.getCountrycode()).isEqualTo(UPDATED_COUNTRYCODE);
    }

    @Test
    @Transactional
    public void updateNonExistingPerson() throws Exception {
        int databaseSizeBeforeUpdate = personRepository.findAll().size();

        // Create the Person
        PersonDTO personDTO = personMapper.toDto(person);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonMockMvc.perform(put("/api/people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePerson() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        int databaseSizeBeforeDelete = personRepository.findAll().size();

        // Delete the person
        restPersonMockMvc.perform(delete("/api/people/{id}", person.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Person.class);
        Person person1 = new Person();
        person1.setId(1L);
        Person person2 = new Person();
        person2.setId(person1.getId());
        assertThat(person1).isEqualTo(person2);
        person2.setId(2L);
        assertThat(person1).isNotEqualTo(person2);
        person1.setId(null);
        assertThat(person1).isNotEqualTo(person2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonDTO.class);
        PersonDTO personDTO1 = new PersonDTO();
        personDTO1.setId(1L);
        PersonDTO personDTO2 = new PersonDTO();
        assertThat(personDTO1).isNotEqualTo(personDTO2);
        personDTO2.setId(personDTO1.getId());
        assertThat(personDTO1).isEqualTo(personDTO2);
        personDTO2.setId(2L);
        assertThat(personDTO1).isNotEqualTo(personDTO2);
        personDTO1.setId(null);
        assertThat(personDTO1).isNotEqualTo(personDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(personMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(personMapper.fromId(null)).isNull();
    }
}
