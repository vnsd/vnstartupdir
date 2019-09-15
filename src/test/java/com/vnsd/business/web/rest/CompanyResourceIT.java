package com.vnsd.business.web.rest;

import com.vnsd.business.VnstartupdirApp;
import com.vnsd.business.domain.Company;
import com.vnsd.business.domain.PersonCompanyRelation;
import com.vnsd.business.domain.User;
import com.vnsd.business.repository.CompanyRepository;
import com.vnsd.business.service.CompanyService;
import com.vnsd.business.service.dto.CompanyDTO;
import com.vnsd.business.service.mapper.CompanyMapper;
import com.vnsd.business.web.rest.errors.ExceptionTranslator;
import com.vnsd.business.service.dto.CompanyCriteria;
import com.vnsd.business.service.CompanyQueryService;

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
 * Integration tests for the {@link CompanyResource} REST controller.
 */
@SpringBootTest(classes = VnstartupdirApp.class)
public class CompanyResourceIT {

    private static final String DEFAULT_UUID = "AAAAAAAAAA";
    private static final String UPDATED_UUID = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORTDESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SHORTDESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_FOUNDEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FOUNDEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_FOUNDEDON = Instant.ofEpochMilli(-1L);

    private static final Instant DEFAULT_CLOSEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CLOSEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_CLOSEDON = Instant.ofEpochMilli(-1L);

    private static final Integer DEFAULT_NUMEMPLOYEESMIN = 1;
    private static final Integer UPDATED_NUMEMPLOYEESMIN = 2;
    private static final Integer SMALLER_NUMEMPLOYEESMIN = 1 - 1;

    private static final Integer DEFAULT_NUMEMPLOYEESMAX = 1;
    private static final Integer UPDATED_NUMEMPLOYEESMAX = 2;
    private static final Integer SMALLER_NUMEMPLOYEESMAX = 1 - 1;

    private static final Integer DEFAULT_TOTALFUNDINGUSD = 1;
    private static final Integer UPDATED_TOTALFUNDINGUSD = 2;
    private static final Integer SMALLER_TOTALFUNDINGUSD = 1 - 1;

    private static final Integer DEFAULT_TOTALFUNDINGVND = 1;
    private static final Integer UPDATED_TOTALFUNDINGVND = 2;
    private static final Integer SMALLER_TOTALFUNDINGVND = 1 - 1;

    private static final Integer DEFAULT_NUMBEROFINVESTMENTS = 1;
    private static final Integer UPDATED_NUMBEROFINVESTMENTS = 2;
    private static final Integer SMALLER_NUMBEROFINVESTMENTS = 1 - 1;

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_CREATEDAT = Instant.ofEpochMilli(-1L);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_UPDATEDAT = Instant.ofEpochMilli(-1L);

    private static final String DEFAULT_CONTACTEMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTEMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONENUMBER = "BBBBBBBBBB";

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
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyQueryService companyQueryService;

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

    private MockMvc restCompanyMockMvc;

    private Company company;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CompanyResource companyResource = new CompanyResource(companyService, companyQueryService);
        this.restCompanyMockMvc = MockMvcBuilders.standaloneSetup(companyResource)
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
    public static Company createEntity(EntityManager em) {
        Company company = new Company()
            .uuid(DEFAULT_UUID)
            .type(DEFAULT_TYPE)
            .name(DEFAULT_NAME)
            .shortdescription(DEFAULT_SHORTDESCRIPTION)
            .description(DEFAULT_DESCRIPTION)
            .foundedon(DEFAULT_FOUNDEDON)
            .closedon(DEFAULT_CLOSEDON)
            .numemployeesmin(DEFAULT_NUMEMPLOYEESMIN)
            .numemployeesmax(DEFAULT_NUMEMPLOYEESMAX)
            .totalfundingusd(DEFAULT_TOTALFUNDINGUSD)
            .totalfundingvnd(DEFAULT_TOTALFUNDINGVND)
            .numberofinvestments(DEFAULT_NUMBEROFINVESTMENTS)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT)
            .contactemail(DEFAULT_CONTACTEMAIL)
            .phonenumber(DEFAULT_PHONENUMBER)
            .homepageurl(DEFAULT_HOMEPAGEURL)
            .facebookurl(DEFAULT_FACEBOOKURL)
            .twitterurl(DEFAULT_TWITTERURL)
            .linkedinurl(DEFAULT_LINKEDINURL)
            .cityname(DEFAULT_CITYNAME)
            .regionname(DEFAULT_REGIONNAME)
            .countrycode(DEFAULT_COUNTRYCODE);
        return company;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Company createUpdatedEntity(EntityManager em) {
        Company company = new Company()
            .uuid(UPDATED_UUID)
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .shortdescription(UPDATED_SHORTDESCRIPTION)
            .description(UPDATED_DESCRIPTION)
            .foundedon(UPDATED_FOUNDEDON)
            .closedon(UPDATED_CLOSEDON)
            .numemployeesmin(UPDATED_NUMEMPLOYEESMIN)
            .numemployeesmax(UPDATED_NUMEMPLOYEESMAX)
            .totalfundingusd(UPDATED_TOTALFUNDINGUSD)
            .totalfundingvnd(UPDATED_TOTALFUNDINGVND)
            .numberofinvestments(UPDATED_NUMBEROFINVESTMENTS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT)
            .contactemail(UPDATED_CONTACTEMAIL)
            .phonenumber(UPDATED_PHONENUMBER)
            .homepageurl(UPDATED_HOMEPAGEURL)
            .facebookurl(UPDATED_FACEBOOKURL)
            .twitterurl(UPDATED_TWITTERURL)
            .linkedinurl(UPDATED_LINKEDINURL)
            .cityname(UPDATED_CITYNAME)
            .regionname(UPDATED_REGIONNAME)
            .countrycode(UPDATED_COUNTRYCODE);
        return company;
    }

    @BeforeEach
    public void initTest() {
        company = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompany() throws Exception {
        int databaseSizeBeforeCreate = companyRepository.findAll().size();

        // Create the Company
        CompanyDTO companyDTO = companyMapper.toDto(company);
        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isCreated());

        // Validate the Company in the database
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeCreate + 1);
        Company testCompany = companyList.get(companyList.size() - 1);
        assertThat(testCompany.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testCompany.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testCompany.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCompany.getShortdescription()).isEqualTo(DEFAULT_SHORTDESCRIPTION);
        assertThat(testCompany.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCompany.getFoundedon()).isEqualTo(DEFAULT_FOUNDEDON);
        assertThat(testCompany.getClosedon()).isEqualTo(DEFAULT_CLOSEDON);
        assertThat(testCompany.getNumemployeesmin()).isEqualTo(DEFAULT_NUMEMPLOYEESMIN);
        assertThat(testCompany.getNumemployeesmax()).isEqualTo(DEFAULT_NUMEMPLOYEESMAX);
        assertThat(testCompany.getTotalfundingusd()).isEqualTo(DEFAULT_TOTALFUNDINGUSD);
        assertThat(testCompany.getTotalfundingvnd()).isEqualTo(DEFAULT_TOTALFUNDINGVND);
        assertThat(testCompany.getNumberofinvestments()).isEqualTo(DEFAULT_NUMBEROFINVESTMENTS);
        assertThat(testCompany.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testCompany.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
        assertThat(testCompany.getContactemail()).isEqualTo(DEFAULT_CONTACTEMAIL);
        assertThat(testCompany.getPhonenumber()).isEqualTo(DEFAULT_PHONENUMBER);
        assertThat(testCompany.getHomepageurl()).isEqualTo(DEFAULT_HOMEPAGEURL);
        assertThat(testCompany.getFacebookurl()).isEqualTo(DEFAULT_FACEBOOKURL);
        assertThat(testCompany.getTwitterurl()).isEqualTo(DEFAULT_TWITTERURL);
        assertThat(testCompany.getLinkedinurl()).isEqualTo(DEFAULT_LINKEDINURL);
        assertThat(testCompany.getCityname()).isEqualTo(DEFAULT_CITYNAME);
        assertThat(testCompany.getRegionname()).isEqualTo(DEFAULT_REGIONNAME);
        assertThat(testCompany.getCountrycode()).isEqualTo(DEFAULT_COUNTRYCODE);
    }

    @Test
    @Transactional
    public void createCompanyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = companyRepository.findAll().size();

        // Create the Company with an existing ID
        company.setId(1L);
        CompanyDTO companyDTO = companyMapper.toDto(company);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Company in the database
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = companyRepository.findAll().size();
        // set the field null
        company.setUuid(null);

        // Create the Company, which fails.
        CompanyDTO companyDTO = companyMapper.toDto(company);

        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = companyRepository.findAll().size();
        // set the field null
        company.setType(null);

        // Create the Company, which fails.
        CompanyDTO companyDTO = companyMapper.toDto(company);

        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = companyRepository.findAll().size();
        // set the field null
        company.setName(null);

        // Create the Company, which fails.
        CompanyDTO companyDTO = companyMapper.toDto(company);

        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCompanies() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList
        restCompanyMockMvc.perform(get("/api/companies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(company.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortdescription").value(hasItem(DEFAULT_SHORTDESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].foundedon").value(hasItem(DEFAULT_FOUNDEDON.toString())))
            .andExpect(jsonPath("$.[*].closedon").value(hasItem(DEFAULT_CLOSEDON.toString())))
            .andExpect(jsonPath("$.[*].numemployeesmin").value(hasItem(DEFAULT_NUMEMPLOYEESMIN)))
            .andExpect(jsonPath("$.[*].numemployeesmax").value(hasItem(DEFAULT_NUMEMPLOYEESMAX)))
            .andExpect(jsonPath("$.[*].totalfundingusd").value(hasItem(DEFAULT_TOTALFUNDINGUSD)))
            .andExpect(jsonPath("$.[*].totalfundingvnd").value(hasItem(DEFAULT_TOTALFUNDINGVND)))
            .andExpect(jsonPath("$.[*].numberofinvestments").value(hasItem(DEFAULT_NUMBEROFINVESTMENTS)))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())))
            .andExpect(jsonPath("$.[*].contactemail").value(hasItem(DEFAULT_CONTACTEMAIL.toString())))
            .andExpect(jsonPath("$.[*].phonenumber").value(hasItem(DEFAULT_PHONENUMBER.toString())))
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
    public void getCompany() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get the company
        restCompanyMockMvc.perform(get("/api/companies/{id}", company.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(company.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.shortdescription").value(DEFAULT_SHORTDESCRIPTION.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.foundedon").value(DEFAULT_FOUNDEDON.toString()))
            .andExpect(jsonPath("$.closedon").value(DEFAULT_CLOSEDON.toString()))
            .andExpect(jsonPath("$.numemployeesmin").value(DEFAULT_NUMEMPLOYEESMIN))
            .andExpect(jsonPath("$.numemployeesmax").value(DEFAULT_NUMEMPLOYEESMAX))
            .andExpect(jsonPath("$.totalfundingusd").value(DEFAULT_TOTALFUNDINGUSD))
            .andExpect(jsonPath("$.totalfundingvnd").value(DEFAULT_TOTALFUNDINGVND))
            .andExpect(jsonPath("$.numberofinvestments").value(DEFAULT_NUMBEROFINVESTMENTS))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()))
            .andExpect(jsonPath("$.contactemail").value(DEFAULT_CONTACTEMAIL.toString()))
            .andExpect(jsonPath("$.phonenumber").value(DEFAULT_PHONENUMBER.toString()))
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
    public void getAllCompaniesByUuidIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where uuid equals to DEFAULT_UUID
        defaultCompanyShouldBeFound("uuid.equals=" + DEFAULT_UUID);

        // Get all the companyList where uuid equals to UPDATED_UUID
        defaultCompanyShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByUuidIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where uuid in DEFAULT_UUID or UPDATED_UUID
        defaultCompanyShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

        // Get all the companyList where uuid equals to UPDATED_UUID
        defaultCompanyShouldNotBeFound("uuid.in=" + UPDATED_UUID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByUuidIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where uuid is not null
        defaultCompanyShouldBeFound("uuid.specified=true");

        // Get all the companyList where uuid is null
        defaultCompanyShouldNotBeFound("uuid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where type equals to DEFAULT_TYPE
        defaultCompanyShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the companyList where type equals to UPDATED_TYPE
        defaultCompanyShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultCompanyShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the companyList where type equals to UPDATED_TYPE
        defaultCompanyShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where type is not null
        defaultCompanyShouldBeFound("type.specified=true");

        // Get all the companyList where type is null
        defaultCompanyShouldNotBeFound("type.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where name equals to DEFAULT_NAME
        defaultCompanyShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the companyList where name equals to UPDATED_NAME
        defaultCompanyShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where name in DEFAULT_NAME or UPDATED_NAME
        defaultCompanyShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the companyList where name equals to UPDATED_NAME
        defaultCompanyShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where name is not null
        defaultCompanyShouldBeFound("name.specified=true");

        // Get all the companyList where name is null
        defaultCompanyShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByShortdescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where shortdescription equals to DEFAULT_SHORTDESCRIPTION
        defaultCompanyShouldBeFound("shortdescription.equals=" + DEFAULT_SHORTDESCRIPTION);

        // Get all the companyList where shortdescription equals to UPDATED_SHORTDESCRIPTION
        defaultCompanyShouldNotBeFound("shortdescription.equals=" + UPDATED_SHORTDESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCompaniesByShortdescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where shortdescription in DEFAULT_SHORTDESCRIPTION or UPDATED_SHORTDESCRIPTION
        defaultCompanyShouldBeFound("shortdescription.in=" + DEFAULT_SHORTDESCRIPTION + "," + UPDATED_SHORTDESCRIPTION);

        // Get all the companyList where shortdescription equals to UPDATED_SHORTDESCRIPTION
        defaultCompanyShouldNotBeFound("shortdescription.in=" + UPDATED_SHORTDESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCompaniesByShortdescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where shortdescription is not null
        defaultCompanyShouldBeFound("shortdescription.specified=true");

        // Get all the companyList where shortdescription is null
        defaultCompanyShouldNotBeFound("shortdescription.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where description equals to DEFAULT_DESCRIPTION
        defaultCompanyShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the companyList where description equals to UPDATED_DESCRIPTION
        defaultCompanyShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCompaniesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultCompanyShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the companyList where description equals to UPDATED_DESCRIPTION
        defaultCompanyShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCompaniesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where description is not null
        defaultCompanyShouldBeFound("description.specified=true");

        // Get all the companyList where description is null
        defaultCompanyShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByFoundedonIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where foundedon equals to DEFAULT_FOUNDEDON
        defaultCompanyShouldBeFound("foundedon.equals=" + DEFAULT_FOUNDEDON);

        // Get all the companyList where foundedon equals to UPDATED_FOUNDEDON
        defaultCompanyShouldNotBeFound("foundedon.equals=" + UPDATED_FOUNDEDON);
    }

    @Test
    @Transactional
    public void getAllCompaniesByFoundedonIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where foundedon in DEFAULT_FOUNDEDON or UPDATED_FOUNDEDON
        defaultCompanyShouldBeFound("foundedon.in=" + DEFAULT_FOUNDEDON + "," + UPDATED_FOUNDEDON);

        // Get all the companyList where foundedon equals to UPDATED_FOUNDEDON
        defaultCompanyShouldNotBeFound("foundedon.in=" + UPDATED_FOUNDEDON);
    }

    @Test
    @Transactional
    public void getAllCompaniesByFoundedonIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where foundedon is not null
        defaultCompanyShouldBeFound("foundedon.specified=true");

        // Get all the companyList where foundedon is null
        defaultCompanyShouldNotBeFound("foundedon.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByClosedonIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where closedon equals to DEFAULT_CLOSEDON
        defaultCompanyShouldBeFound("closedon.equals=" + DEFAULT_CLOSEDON);

        // Get all the companyList where closedon equals to UPDATED_CLOSEDON
        defaultCompanyShouldNotBeFound("closedon.equals=" + UPDATED_CLOSEDON);
    }

    @Test
    @Transactional
    public void getAllCompaniesByClosedonIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where closedon in DEFAULT_CLOSEDON or UPDATED_CLOSEDON
        defaultCompanyShouldBeFound("closedon.in=" + DEFAULT_CLOSEDON + "," + UPDATED_CLOSEDON);

        // Get all the companyList where closedon equals to UPDATED_CLOSEDON
        defaultCompanyShouldNotBeFound("closedon.in=" + UPDATED_CLOSEDON);
    }

    @Test
    @Transactional
    public void getAllCompaniesByClosedonIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where closedon is not null
        defaultCompanyShouldBeFound("closedon.specified=true");

        // Get all the companyList where closedon is null
        defaultCompanyShouldNotBeFound("closedon.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumemployeesminIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numemployeesmin equals to DEFAULT_NUMEMPLOYEESMIN
        defaultCompanyShouldBeFound("numemployeesmin.equals=" + DEFAULT_NUMEMPLOYEESMIN);

        // Get all the companyList where numemployeesmin equals to UPDATED_NUMEMPLOYEESMIN
        defaultCompanyShouldNotBeFound("numemployeesmin.equals=" + UPDATED_NUMEMPLOYEESMIN);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumemployeesminIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numemployeesmin in DEFAULT_NUMEMPLOYEESMIN or UPDATED_NUMEMPLOYEESMIN
        defaultCompanyShouldBeFound("numemployeesmin.in=" + DEFAULT_NUMEMPLOYEESMIN + "," + UPDATED_NUMEMPLOYEESMIN);

        // Get all the companyList where numemployeesmin equals to UPDATED_NUMEMPLOYEESMIN
        defaultCompanyShouldNotBeFound("numemployeesmin.in=" + UPDATED_NUMEMPLOYEESMIN);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumemployeesminIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numemployeesmin is not null
        defaultCompanyShouldBeFound("numemployeesmin.specified=true");

        // Get all the companyList where numemployeesmin is null
        defaultCompanyShouldNotBeFound("numemployeesmin.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumemployeesminIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numemployeesmin is greater than or equal to DEFAULT_NUMEMPLOYEESMIN
        defaultCompanyShouldBeFound("numemployeesmin.greaterThanOrEqual=" + DEFAULT_NUMEMPLOYEESMIN);

        // Get all the companyList where numemployeesmin is greater than or equal to UPDATED_NUMEMPLOYEESMIN
        defaultCompanyShouldNotBeFound("numemployeesmin.greaterThanOrEqual=" + UPDATED_NUMEMPLOYEESMIN);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumemployeesminIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numemployeesmin is less than or equal to DEFAULT_NUMEMPLOYEESMIN
        defaultCompanyShouldBeFound("numemployeesmin.lessThanOrEqual=" + DEFAULT_NUMEMPLOYEESMIN);

        // Get all the companyList where numemployeesmin is less than or equal to SMALLER_NUMEMPLOYEESMIN
        defaultCompanyShouldNotBeFound("numemployeesmin.lessThanOrEqual=" + SMALLER_NUMEMPLOYEESMIN);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumemployeesminIsLessThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numemployeesmin is less than DEFAULT_NUMEMPLOYEESMIN
        defaultCompanyShouldNotBeFound("numemployeesmin.lessThan=" + DEFAULT_NUMEMPLOYEESMIN);

        // Get all the companyList where numemployeesmin is less than UPDATED_NUMEMPLOYEESMIN
        defaultCompanyShouldBeFound("numemployeesmin.lessThan=" + UPDATED_NUMEMPLOYEESMIN);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumemployeesminIsGreaterThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numemployeesmin is greater than DEFAULT_NUMEMPLOYEESMIN
        defaultCompanyShouldNotBeFound("numemployeesmin.greaterThan=" + DEFAULT_NUMEMPLOYEESMIN);

        // Get all the companyList where numemployeesmin is greater than SMALLER_NUMEMPLOYEESMIN
        defaultCompanyShouldBeFound("numemployeesmin.greaterThan=" + SMALLER_NUMEMPLOYEESMIN);
    }


    @Test
    @Transactional
    public void getAllCompaniesByNumemployeesmaxIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numemployeesmax equals to DEFAULT_NUMEMPLOYEESMAX
        defaultCompanyShouldBeFound("numemployeesmax.equals=" + DEFAULT_NUMEMPLOYEESMAX);

        // Get all the companyList where numemployeesmax equals to UPDATED_NUMEMPLOYEESMAX
        defaultCompanyShouldNotBeFound("numemployeesmax.equals=" + UPDATED_NUMEMPLOYEESMAX);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumemployeesmaxIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numemployeesmax in DEFAULT_NUMEMPLOYEESMAX or UPDATED_NUMEMPLOYEESMAX
        defaultCompanyShouldBeFound("numemployeesmax.in=" + DEFAULT_NUMEMPLOYEESMAX + "," + UPDATED_NUMEMPLOYEESMAX);

        // Get all the companyList where numemployeesmax equals to UPDATED_NUMEMPLOYEESMAX
        defaultCompanyShouldNotBeFound("numemployeesmax.in=" + UPDATED_NUMEMPLOYEESMAX);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumemployeesmaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numemployeesmax is not null
        defaultCompanyShouldBeFound("numemployeesmax.specified=true");

        // Get all the companyList where numemployeesmax is null
        defaultCompanyShouldNotBeFound("numemployeesmax.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumemployeesmaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numemployeesmax is greater than or equal to DEFAULT_NUMEMPLOYEESMAX
        defaultCompanyShouldBeFound("numemployeesmax.greaterThanOrEqual=" + DEFAULT_NUMEMPLOYEESMAX);

        // Get all the companyList where numemployeesmax is greater than or equal to UPDATED_NUMEMPLOYEESMAX
        defaultCompanyShouldNotBeFound("numemployeesmax.greaterThanOrEqual=" + UPDATED_NUMEMPLOYEESMAX);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumemployeesmaxIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numemployeesmax is less than or equal to DEFAULT_NUMEMPLOYEESMAX
        defaultCompanyShouldBeFound("numemployeesmax.lessThanOrEqual=" + DEFAULT_NUMEMPLOYEESMAX);

        // Get all the companyList where numemployeesmax is less than or equal to SMALLER_NUMEMPLOYEESMAX
        defaultCompanyShouldNotBeFound("numemployeesmax.lessThanOrEqual=" + SMALLER_NUMEMPLOYEESMAX);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumemployeesmaxIsLessThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numemployeesmax is less than DEFAULT_NUMEMPLOYEESMAX
        defaultCompanyShouldNotBeFound("numemployeesmax.lessThan=" + DEFAULT_NUMEMPLOYEESMAX);

        // Get all the companyList where numemployeesmax is less than UPDATED_NUMEMPLOYEESMAX
        defaultCompanyShouldBeFound("numemployeesmax.lessThan=" + UPDATED_NUMEMPLOYEESMAX);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumemployeesmaxIsGreaterThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numemployeesmax is greater than DEFAULT_NUMEMPLOYEESMAX
        defaultCompanyShouldNotBeFound("numemployeesmax.greaterThan=" + DEFAULT_NUMEMPLOYEESMAX);

        // Get all the companyList where numemployeesmax is greater than SMALLER_NUMEMPLOYEESMAX
        defaultCompanyShouldBeFound("numemployeesmax.greaterThan=" + SMALLER_NUMEMPLOYEESMAX);
    }


    @Test
    @Transactional
    public void getAllCompaniesByTotalfundingusdIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where totalfundingusd equals to DEFAULT_TOTALFUNDINGUSD
        defaultCompanyShouldBeFound("totalfundingusd.equals=" + DEFAULT_TOTALFUNDINGUSD);

        // Get all the companyList where totalfundingusd equals to UPDATED_TOTALFUNDINGUSD
        defaultCompanyShouldNotBeFound("totalfundingusd.equals=" + UPDATED_TOTALFUNDINGUSD);
    }

    @Test
    @Transactional
    public void getAllCompaniesByTotalfundingusdIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where totalfundingusd in DEFAULT_TOTALFUNDINGUSD or UPDATED_TOTALFUNDINGUSD
        defaultCompanyShouldBeFound("totalfundingusd.in=" + DEFAULT_TOTALFUNDINGUSD + "," + UPDATED_TOTALFUNDINGUSD);

        // Get all the companyList where totalfundingusd equals to UPDATED_TOTALFUNDINGUSD
        defaultCompanyShouldNotBeFound("totalfundingusd.in=" + UPDATED_TOTALFUNDINGUSD);
    }

    @Test
    @Transactional
    public void getAllCompaniesByTotalfundingusdIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where totalfundingusd is not null
        defaultCompanyShouldBeFound("totalfundingusd.specified=true");

        // Get all the companyList where totalfundingusd is null
        defaultCompanyShouldNotBeFound("totalfundingusd.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByTotalfundingusdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where totalfundingusd is greater than or equal to DEFAULT_TOTALFUNDINGUSD
        defaultCompanyShouldBeFound("totalfundingusd.greaterThanOrEqual=" + DEFAULT_TOTALFUNDINGUSD);

        // Get all the companyList where totalfundingusd is greater than or equal to UPDATED_TOTALFUNDINGUSD
        defaultCompanyShouldNotBeFound("totalfundingusd.greaterThanOrEqual=" + UPDATED_TOTALFUNDINGUSD);
    }

    @Test
    @Transactional
    public void getAllCompaniesByTotalfundingusdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where totalfundingusd is less than or equal to DEFAULT_TOTALFUNDINGUSD
        defaultCompanyShouldBeFound("totalfundingusd.lessThanOrEqual=" + DEFAULT_TOTALFUNDINGUSD);

        // Get all the companyList where totalfundingusd is less than or equal to SMALLER_TOTALFUNDINGUSD
        defaultCompanyShouldNotBeFound("totalfundingusd.lessThanOrEqual=" + SMALLER_TOTALFUNDINGUSD);
    }

    @Test
    @Transactional
    public void getAllCompaniesByTotalfundingusdIsLessThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where totalfundingusd is less than DEFAULT_TOTALFUNDINGUSD
        defaultCompanyShouldNotBeFound("totalfundingusd.lessThan=" + DEFAULT_TOTALFUNDINGUSD);

        // Get all the companyList where totalfundingusd is less than UPDATED_TOTALFUNDINGUSD
        defaultCompanyShouldBeFound("totalfundingusd.lessThan=" + UPDATED_TOTALFUNDINGUSD);
    }

    @Test
    @Transactional
    public void getAllCompaniesByTotalfundingusdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where totalfundingusd is greater than DEFAULT_TOTALFUNDINGUSD
        defaultCompanyShouldNotBeFound("totalfundingusd.greaterThan=" + DEFAULT_TOTALFUNDINGUSD);

        // Get all the companyList where totalfundingusd is greater than SMALLER_TOTALFUNDINGUSD
        defaultCompanyShouldBeFound("totalfundingusd.greaterThan=" + SMALLER_TOTALFUNDINGUSD);
    }


    @Test
    @Transactional
    public void getAllCompaniesByTotalfundingvndIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where totalfundingvnd equals to DEFAULT_TOTALFUNDINGVND
        defaultCompanyShouldBeFound("totalfundingvnd.equals=" + DEFAULT_TOTALFUNDINGVND);

        // Get all the companyList where totalfundingvnd equals to UPDATED_TOTALFUNDINGVND
        defaultCompanyShouldNotBeFound("totalfundingvnd.equals=" + UPDATED_TOTALFUNDINGVND);
    }

    @Test
    @Transactional
    public void getAllCompaniesByTotalfundingvndIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where totalfundingvnd in DEFAULT_TOTALFUNDINGVND or UPDATED_TOTALFUNDINGVND
        defaultCompanyShouldBeFound("totalfundingvnd.in=" + DEFAULT_TOTALFUNDINGVND + "," + UPDATED_TOTALFUNDINGVND);

        // Get all the companyList where totalfundingvnd equals to UPDATED_TOTALFUNDINGVND
        defaultCompanyShouldNotBeFound("totalfundingvnd.in=" + UPDATED_TOTALFUNDINGVND);
    }

    @Test
    @Transactional
    public void getAllCompaniesByTotalfundingvndIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where totalfundingvnd is not null
        defaultCompanyShouldBeFound("totalfundingvnd.specified=true");

        // Get all the companyList where totalfundingvnd is null
        defaultCompanyShouldNotBeFound("totalfundingvnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByTotalfundingvndIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where totalfundingvnd is greater than or equal to DEFAULT_TOTALFUNDINGVND
        defaultCompanyShouldBeFound("totalfundingvnd.greaterThanOrEqual=" + DEFAULT_TOTALFUNDINGVND);

        // Get all the companyList where totalfundingvnd is greater than or equal to UPDATED_TOTALFUNDINGVND
        defaultCompanyShouldNotBeFound("totalfundingvnd.greaterThanOrEqual=" + UPDATED_TOTALFUNDINGVND);
    }

    @Test
    @Transactional
    public void getAllCompaniesByTotalfundingvndIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where totalfundingvnd is less than or equal to DEFAULT_TOTALFUNDINGVND
        defaultCompanyShouldBeFound("totalfundingvnd.lessThanOrEqual=" + DEFAULT_TOTALFUNDINGVND);

        // Get all the companyList where totalfundingvnd is less than or equal to SMALLER_TOTALFUNDINGVND
        defaultCompanyShouldNotBeFound("totalfundingvnd.lessThanOrEqual=" + SMALLER_TOTALFUNDINGVND);
    }

    @Test
    @Transactional
    public void getAllCompaniesByTotalfundingvndIsLessThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where totalfundingvnd is less than DEFAULT_TOTALFUNDINGVND
        defaultCompanyShouldNotBeFound("totalfundingvnd.lessThan=" + DEFAULT_TOTALFUNDINGVND);

        // Get all the companyList where totalfundingvnd is less than UPDATED_TOTALFUNDINGVND
        defaultCompanyShouldBeFound("totalfundingvnd.lessThan=" + UPDATED_TOTALFUNDINGVND);
    }

    @Test
    @Transactional
    public void getAllCompaniesByTotalfundingvndIsGreaterThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where totalfundingvnd is greater than DEFAULT_TOTALFUNDINGVND
        defaultCompanyShouldNotBeFound("totalfundingvnd.greaterThan=" + DEFAULT_TOTALFUNDINGVND);

        // Get all the companyList where totalfundingvnd is greater than SMALLER_TOTALFUNDINGVND
        defaultCompanyShouldBeFound("totalfundingvnd.greaterThan=" + SMALLER_TOTALFUNDINGVND);
    }


    @Test
    @Transactional
    public void getAllCompaniesByNumberofinvestmentsIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numberofinvestments equals to DEFAULT_NUMBEROFINVESTMENTS
        defaultCompanyShouldBeFound("numberofinvestments.equals=" + DEFAULT_NUMBEROFINVESTMENTS);

        // Get all the companyList where numberofinvestments equals to UPDATED_NUMBEROFINVESTMENTS
        defaultCompanyShouldNotBeFound("numberofinvestments.equals=" + UPDATED_NUMBEROFINVESTMENTS);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumberofinvestmentsIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numberofinvestments in DEFAULT_NUMBEROFINVESTMENTS or UPDATED_NUMBEROFINVESTMENTS
        defaultCompanyShouldBeFound("numberofinvestments.in=" + DEFAULT_NUMBEROFINVESTMENTS + "," + UPDATED_NUMBEROFINVESTMENTS);

        // Get all the companyList where numberofinvestments equals to UPDATED_NUMBEROFINVESTMENTS
        defaultCompanyShouldNotBeFound("numberofinvestments.in=" + UPDATED_NUMBEROFINVESTMENTS);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumberofinvestmentsIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numberofinvestments is not null
        defaultCompanyShouldBeFound("numberofinvestments.specified=true");

        // Get all the companyList where numberofinvestments is null
        defaultCompanyShouldNotBeFound("numberofinvestments.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumberofinvestmentsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numberofinvestments is greater than or equal to DEFAULT_NUMBEROFINVESTMENTS
        defaultCompanyShouldBeFound("numberofinvestments.greaterThanOrEqual=" + DEFAULT_NUMBEROFINVESTMENTS);

        // Get all the companyList where numberofinvestments is greater than or equal to UPDATED_NUMBEROFINVESTMENTS
        defaultCompanyShouldNotBeFound("numberofinvestments.greaterThanOrEqual=" + UPDATED_NUMBEROFINVESTMENTS);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumberofinvestmentsIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numberofinvestments is less than or equal to DEFAULT_NUMBEROFINVESTMENTS
        defaultCompanyShouldBeFound("numberofinvestments.lessThanOrEqual=" + DEFAULT_NUMBEROFINVESTMENTS);

        // Get all the companyList where numberofinvestments is less than or equal to SMALLER_NUMBEROFINVESTMENTS
        defaultCompanyShouldNotBeFound("numberofinvestments.lessThanOrEqual=" + SMALLER_NUMBEROFINVESTMENTS);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumberofinvestmentsIsLessThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numberofinvestments is less than DEFAULT_NUMBEROFINVESTMENTS
        defaultCompanyShouldNotBeFound("numberofinvestments.lessThan=" + DEFAULT_NUMBEROFINVESTMENTS);

        // Get all the companyList where numberofinvestments is less than UPDATED_NUMBEROFINVESTMENTS
        defaultCompanyShouldBeFound("numberofinvestments.lessThan=" + UPDATED_NUMBEROFINVESTMENTS);
    }

    @Test
    @Transactional
    public void getAllCompaniesByNumberofinvestmentsIsGreaterThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where numberofinvestments is greater than DEFAULT_NUMBEROFINVESTMENTS
        defaultCompanyShouldNotBeFound("numberofinvestments.greaterThan=" + DEFAULT_NUMBEROFINVESTMENTS);

        // Get all the companyList where numberofinvestments is greater than SMALLER_NUMBEROFINVESTMENTS
        defaultCompanyShouldBeFound("numberofinvestments.greaterThan=" + SMALLER_NUMBEROFINVESTMENTS);
    }


    @Test
    @Transactional
    public void getAllCompaniesByCreatedatIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where createdat equals to DEFAULT_CREATEDAT
        defaultCompanyShouldBeFound("createdat.equals=" + DEFAULT_CREATEDAT);

        // Get all the companyList where createdat equals to UPDATED_CREATEDAT
        defaultCompanyShouldNotBeFound("createdat.equals=" + UPDATED_CREATEDAT);
    }

    @Test
    @Transactional
    public void getAllCompaniesByCreatedatIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where createdat in DEFAULT_CREATEDAT or UPDATED_CREATEDAT
        defaultCompanyShouldBeFound("createdat.in=" + DEFAULT_CREATEDAT + "," + UPDATED_CREATEDAT);

        // Get all the companyList where createdat equals to UPDATED_CREATEDAT
        defaultCompanyShouldNotBeFound("createdat.in=" + UPDATED_CREATEDAT);
    }

    @Test
    @Transactional
    public void getAllCompaniesByCreatedatIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where createdat is not null
        defaultCompanyShouldBeFound("createdat.specified=true");

        // Get all the companyList where createdat is null
        defaultCompanyShouldNotBeFound("createdat.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByUpdatedatIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where updatedat equals to DEFAULT_UPDATEDAT
        defaultCompanyShouldBeFound("updatedat.equals=" + DEFAULT_UPDATEDAT);

        // Get all the companyList where updatedat equals to UPDATED_UPDATEDAT
        defaultCompanyShouldNotBeFound("updatedat.equals=" + UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void getAllCompaniesByUpdatedatIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where updatedat in DEFAULT_UPDATEDAT or UPDATED_UPDATEDAT
        defaultCompanyShouldBeFound("updatedat.in=" + DEFAULT_UPDATEDAT + "," + UPDATED_UPDATEDAT);

        // Get all the companyList where updatedat equals to UPDATED_UPDATEDAT
        defaultCompanyShouldNotBeFound("updatedat.in=" + UPDATED_UPDATEDAT);
    }

    @Test
    @Transactional
    public void getAllCompaniesByUpdatedatIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where updatedat is not null
        defaultCompanyShouldBeFound("updatedat.specified=true");

        // Get all the companyList where updatedat is null
        defaultCompanyShouldNotBeFound("updatedat.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByContactemailIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where contactemail equals to DEFAULT_CONTACTEMAIL
        defaultCompanyShouldBeFound("contactemail.equals=" + DEFAULT_CONTACTEMAIL);

        // Get all the companyList where contactemail equals to UPDATED_CONTACTEMAIL
        defaultCompanyShouldNotBeFound("contactemail.equals=" + UPDATED_CONTACTEMAIL);
    }

    @Test
    @Transactional
    public void getAllCompaniesByContactemailIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where contactemail in DEFAULT_CONTACTEMAIL or UPDATED_CONTACTEMAIL
        defaultCompanyShouldBeFound("contactemail.in=" + DEFAULT_CONTACTEMAIL + "," + UPDATED_CONTACTEMAIL);

        // Get all the companyList where contactemail equals to UPDATED_CONTACTEMAIL
        defaultCompanyShouldNotBeFound("contactemail.in=" + UPDATED_CONTACTEMAIL);
    }

    @Test
    @Transactional
    public void getAllCompaniesByContactemailIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where contactemail is not null
        defaultCompanyShouldBeFound("contactemail.specified=true");

        // Get all the companyList where contactemail is null
        defaultCompanyShouldNotBeFound("contactemail.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByPhonenumberIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where phonenumber equals to DEFAULT_PHONENUMBER
        defaultCompanyShouldBeFound("phonenumber.equals=" + DEFAULT_PHONENUMBER);

        // Get all the companyList where phonenumber equals to UPDATED_PHONENUMBER
        defaultCompanyShouldNotBeFound("phonenumber.equals=" + UPDATED_PHONENUMBER);
    }

    @Test
    @Transactional
    public void getAllCompaniesByPhonenumberIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where phonenumber in DEFAULT_PHONENUMBER or UPDATED_PHONENUMBER
        defaultCompanyShouldBeFound("phonenumber.in=" + DEFAULT_PHONENUMBER + "," + UPDATED_PHONENUMBER);

        // Get all the companyList where phonenumber equals to UPDATED_PHONENUMBER
        defaultCompanyShouldNotBeFound("phonenumber.in=" + UPDATED_PHONENUMBER);
    }

    @Test
    @Transactional
    public void getAllCompaniesByPhonenumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where phonenumber is not null
        defaultCompanyShouldBeFound("phonenumber.specified=true");

        // Get all the companyList where phonenumber is null
        defaultCompanyShouldNotBeFound("phonenumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByHomepageurlIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where homepageurl equals to DEFAULT_HOMEPAGEURL
        defaultCompanyShouldBeFound("homepageurl.equals=" + DEFAULT_HOMEPAGEURL);

        // Get all the companyList where homepageurl equals to UPDATED_HOMEPAGEURL
        defaultCompanyShouldNotBeFound("homepageurl.equals=" + UPDATED_HOMEPAGEURL);
    }

    @Test
    @Transactional
    public void getAllCompaniesByHomepageurlIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where homepageurl in DEFAULT_HOMEPAGEURL or UPDATED_HOMEPAGEURL
        defaultCompanyShouldBeFound("homepageurl.in=" + DEFAULT_HOMEPAGEURL + "," + UPDATED_HOMEPAGEURL);

        // Get all the companyList where homepageurl equals to UPDATED_HOMEPAGEURL
        defaultCompanyShouldNotBeFound("homepageurl.in=" + UPDATED_HOMEPAGEURL);
    }

    @Test
    @Transactional
    public void getAllCompaniesByHomepageurlIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where homepageurl is not null
        defaultCompanyShouldBeFound("homepageurl.specified=true");

        // Get all the companyList where homepageurl is null
        defaultCompanyShouldNotBeFound("homepageurl.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByFacebookurlIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where facebookurl equals to DEFAULT_FACEBOOKURL
        defaultCompanyShouldBeFound("facebookurl.equals=" + DEFAULT_FACEBOOKURL);

        // Get all the companyList where facebookurl equals to UPDATED_FACEBOOKURL
        defaultCompanyShouldNotBeFound("facebookurl.equals=" + UPDATED_FACEBOOKURL);
    }

    @Test
    @Transactional
    public void getAllCompaniesByFacebookurlIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where facebookurl in DEFAULT_FACEBOOKURL or UPDATED_FACEBOOKURL
        defaultCompanyShouldBeFound("facebookurl.in=" + DEFAULT_FACEBOOKURL + "," + UPDATED_FACEBOOKURL);

        // Get all the companyList where facebookurl equals to UPDATED_FACEBOOKURL
        defaultCompanyShouldNotBeFound("facebookurl.in=" + UPDATED_FACEBOOKURL);
    }

    @Test
    @Transactional
    public void getAllCompaniesByFacebookurlIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where facebookurl is not null
        defaultCompanyShouldBeFound("facebookurl.specified=true");

        // Get all the companyList where facebookurl is null
        defaultCompanyShouldNotBeFound("facebookurl.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByTwitterurlIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where twitterurl equals to DEFAULT_TWITTERURL
        defaultCompanyShouldBeFound("twitterurl.equals=" + DEFAULT_TWITTERURL);

        // Get all the companyList where twitterurl equals to UPDATED_TWITTERURL
        defaultCompanyShouldNotBeFound("twitterurl.equals=" + UPDATED_TWITTERURL);
    }

    @Test
    @Transactional
    public void getAllCompaniesByTwitterurlIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where twitterurl in DEFAULT_TWITTERURL or UPDATED_TWITTERURL
        defaultCompanyShouldBeFound("twitterurl.in=" + DEFAULT_TWITTERURL + "," + UPDATED_TWITTERURL);

        // Get all the companyList where twitterurl equals to UPDATED_TWITTERURL
        defaultCompanyShouldNotBeFound("twitterurl.in=" + UPDATED_TWITTERURL);
    }

    @Test
    @Transactional
    public void getAllCompaniesByTwitterurlIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where twitterurl is not null
        defaultCompanyShouldBeFound("twitterurl.specified=true");

        // Get all the companyList where twitterurl is null
        defaultCompanyShouldNotBeFound("twitterurl.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByLinkedinurlIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where linkedinurl equals to DEFAULT_LINKEDINURL
        defaultCompanyShouldBeFound("linkedinurl.equals=" + DEFAULT_LINKEDINURL);

        // Get all the companyList where linkedinurl equals to UPDATED_LINKEDINURL
        defaultCompanyShouldNotBeFound("linkedinurl.equals=" + UPDATED_LINKEDINURL);
    }

    @Test
    @Transactional
    public void getAllCompaniesByLinkedinurlIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where linkedinurl in DEFAULT_LINKEDINURL or UPDATED_LINKEDINURL
        defaultCompanyShouldBeFound("linkedinurl.in=" + DEFAULT_LINKEDINURL + "," + UPDATED_LINKEDINURL);

        // Get all the companyList where linkedinurl equals to UPDATED_LINKEDINURL
        defaultCompanyShouldNotBeFound("linkedinurl.in=" + UPDATED_LINKEDINURL);
    }

    @Test
    @Transactional
    public void getAllCompaniesByLinkedinurlIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where linkedinurl is not null
        defaultCompanyShouldBeFound("linkedinurl.specified=true");

        // Get all the companyList where linkedinurl is null
        defaultCompanyShouldNotBeFound("linkedinurl.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByCitynameIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where cityname equals to DEFAULT_CITYNAME
        defaultCompanyShouldBeFound("cityname.equals=" + DEFAULT_CITYNAME);

        // Get all the companyList where cityname equals to UPDATED_CITYNAME
        defaultCompanyShouldNotBeFound("cityname.equals=" + UPDATED_CITYNAME);
    }

    @Test
    @Transactional
    public void getAllCompaniesByCitynameIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where cityname in DEFAULT_CITYNAME or UPDATED_CITYNAME
        defaultCompanyShouldBeFound("cityname.in=" + DEFAULT_CITYNAME + "," + UPDATED_CITYNAME);

        // Get all the companyList where cityname equals to UPDATED_CITYNAME
        defaultCompanyShouldNotBeFound("cityname.in=" + UPDATED_CITYNAME);
    }

    @Test
    @Transactional
    public void getAllCompaniesByCitynameIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where cityname is not null
        defaultCompanyShouldBeFound("cityname.specified=true");

        // Get all the companyList where cityname is null
        defaultCompanyShouldNotBeFound("cityname.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByRegionnameIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where regionname equals to DEFAULT_REGIONNAME
        defaultCompanyShouldBeFound("regionname.equals=" + DEFAULT_REGIONNAME);

        // Get all the companyList where regionname equals to UPDATED_REGIONNAME
        defaultCompanyShouldNotBeFound("regionname.equals=" + UPDATED_REGIONNAME);
    }

    @Test
    @Transactional
    public void getAllCompaniesByRegionnameIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where regionname in DEFAULT_REGIONNAME or UPDATED_REGIONNAME
        defaultCompanyShouldBeFound("regionname.in=" + DEFAULT_REGIONNAME + "," + UPDATED_REGIONNAME);

        // Get all the companyList where regionname equals to UPDATED_REGIONNAME
        defaultCompanyShouldNotBeFound("regionname.in=" + UPDATED_REGIONNAME);
    }

    @Test
    @Transactional
    public void getAllCompaniesByRegionnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where regionname is not null
        defaultCompanyShouldBeFound("regionname.specified=true");

        // Get all the companyList where regionname is null
        defaultCompanyShouldNotBeFound("regionname.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByCountrycodeIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where countrycode equals to DEFAULT_COUNTRYCODE
        defaultCompanyShouldBeFound("countrycode.equals=" + DEFAULT_COUNTRYCODE);

        // Get all the companyList where countrycode equals to UPDATED_COUNTRYCODE
        defaultCompanyShouldNotBeFound("countrycode.equals=" + UPDATED_COUNTRYCODE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByCountrycodeIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where countrycode in DEFAULT_COUNTRYCODE or UPDATED_COUNTRYCODE
        defaultCompanyShouldBeFound("countrycode.in=" + DEFAULT_COUNTRYCODE + "," + UPDATED_COUNTRYCODE);

        // Get all the companyList where countrycode equals to UPDATED_COUNTRYCODE
        defaultCompanyShouldNotBeFound("countrycode.in=" + UPDATED_COUNTRYCODE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByCountrycodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where countrycode is not null
        defaultCompanyShouldBeFound("countrycode.specified=true");

        // Get all the companyList where countrycode is null
        defaultCompanyShouldNotBeFound("countrycode.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByPeopleIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);
        PersonCompanyRelation people = PersonCompanyRelationResourceIT.createEntity(em);
        em.persist(people);
        em.flush();
        company.addPeople(people);
        companyRepository.saveAndFlush(company);
        Long peopleId = people.getId();

        // Get all the companyList where people equals to peopleId
        defaultCompanyShouldBeFound("peopleId.equals=" + peopleId);

        // Get all the companyList where people equals to peopleId + 1
        defaultCompanyShouldNotBeFound("peopleId.equals=" + (peopleId + 1));
    }


    @Test
    @Transactional
    public void getAllCompaniesByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);
        User createdBy = UserResourceIT.createEntity(em);
        em.persist(createdBy);
        em.flush();
        company.setCreatedBy(createdBy);
        companyRepository.saveAndFlush(company);
        Long createdById = createdBy.getId();

        // Get all the companyList where createdBy equals to createdById
        defaultCompanyShouldBeFound("createdById.equals=" + createdById);

        // Get all the companyList where createdBy equals to createdById + 1
        defaultCompanyShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllCompaniesByUpdatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);
        User updatedBy = UserResourceIT.createEntity(em);
        em.persist(updatedBy);
        em.flush();
        company.setUpdatedBy(updatedBy);
        companyRepository.saveAndFlush(company);
        Long updatedById = updatedBy.getId();

        // Get all the companyList where updatedBy equals to updatedById
        defaultCompanyShouldBeFound("updatedById.equals=" + updatedById);

        // Get all the companyList where updatedBy equals to updatedById + 1
        defaultCompanyShouldNotBeFound("updatedById.equals=" + (updatedById + 1));
    }


    @Test
    @Transactional
    public void getAllCompaniesByAssignedToIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);
        User assignedTo = UserResourceIT.createEntity(em);
        em.persist(assignedTo);
        em.flush();
        company.setAssignedTo(assignedTo);
        companyRepository.saveAndFlush(company);
        Long assignedToId = assignedTo.getId();

        // Get all the companyList where assignedTo equals to assignedToId
        defaultCompanyShouldBeFound("assignedToId.equals=" + assignedToId);

        // Get all the companyList where assignedTo equals to assignedToId + 1
        defaultCompanyShouldNotBeFound("assignedToId.equals=" + (assignedToId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCompanyShouldBeFound(String filter) throws Exception {
        restCompanyMockMvc.perform(get("/api/companies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(company.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].shortdescription").value(hasItem(DEFAULT_SHORTDESCRIPTION)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].foundedon").value(hasItem(DEFAULT_FOUNDEDON.toString())))
            .andExpect(jsonPath("$.[*].closedon").value(hasItem(DEFAULT_CLOSEDON.toString())))
            .andExpect(jsonPath("$.[*].numemployeesmin").value(hasItem(DEFAULT_NUMEMPLOYEESMIN)))
            .andExpect(jsonPath("$.[*].numemployeesmax").value(hasItem(DEFAULT_NUMEMPLOYEESMAX)))
            .andExpect(jsonPath("$.[*].totalfundingusd").value(hasItem(DEFAULT_TOTALFUNDINGUSD)))
            .andExpect(jsonPath("$.[*].totalfundingvnd").value(hasItem(DEFAULT_TOTALFUNDINGVND)))
            .andExpect(jsonPath("$.[*].numberofinvestments").value(hasItem(DEFAULT_NUMBEROFINVESTMENTS)))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())))
            .andExpect(jsonPath("$.[*].contactemail").value(hasItem(DEFAULT_CONTACTEMAIL)))
            .andExpect(jsonPath("$.[*].phonenumber").value(hasItem(DEFAULT_PHONENUMBER)))
            .andExpect(jsonPath("$.[*].homepageurl").value(hasItem(DEFAULT_HOMEPAGEURL)))
            .andExpect(jsonPath("$.[*].facebookurl").value(hasItem(DEFAULT_FACEBOOKURL)))
            .andExpect(jsonPath("$.[*].twitterurl").value(hasItem(DEFAULT_TWITTERURL)))
            .andExpect(jsonPath("$.[*].linkedinurl").value(hasItem(DEFAULT_LINKEDINURL)))
            .andExpect(jsonPath("$.[*].cityname").value(hasItem(DEFAULT_CITYNAME)))
            .andExpect(jsonPath("$.[*].regionname").value(hasItem(DEFAULT_REGIONNAME)))
            .andExpect(jsonPath("$.[*].countrycode").value(hasItem(DEFAULT_COUNTRYCODE)));

        // Check, that the count call also returns 1
        restCompanyMockMvc.perform(get("/api/companies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCompanyShouldNotBeFound(String filter) throws Exception {
        restCompanyMockMvc.perform(get("/api/companies?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCompanyMockMvc.perform(get("/api/companies/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCompany() throws Exception {
        // Get the company
        restCompanyMockMvc.perform(get("/api/companies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompany() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        int databaseSizeBeforeUpdate = companyRepository.findAll().size();

        // Update the company
        Company updatedCompany = companyRepository.findById(company.getId()).get();
        // Disconnect from session so that the updates on updatedCompany are not directly saved in db
        em.detach(updatedCompany);
        updatedCompany
            .uuid(UPDATED_UUID)
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .shortdescription(UPDATED_SHORTDESCRIPTION)
            .description(UPDATED_DESCRIPTION)
            .foundedon(UPDATED_FOUNDEDON)
            .closedon(UPDATED_CLOSEDON)
            .numemployeesmin(UPDATED_NUMEMPLOYEESMIN)
            .numemployeesmax(UPDATED_NUMEMPLOYEESMAX)
            .totalfundingusd(UPDATED_TOTALFUNDINGUSD)
            .totalfundingvnd(UPDATED_TOTALFUNDINGVND)
            .numberofinvestments(UPDATED_NUMBEROFINVESTMENTS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT)
            .contactemail(UPDATED_CONTACTEMAIL)
            .phonenumber(UPDATED_PHONENUMBER)
            .homepageurl(UPDATED_HOMEPAGEURL)
            .facebookurl(UPDATED_FACEBOOKURL)
            .twitterurl(UPDATED_TWITTERURL)
            .linkedinurl(UPDATED_LINKEDINURL)
            .cityname(UPDATED_CITYNAME)
            .regionname(UPDATED_REGIONNAME)
            .countrycode(UPDATED_COUNTRYCODE);
        CompanyDTO companyDTO = companyMapper.toDto(updatedCompany);

        restCompanyMockMvc.perform(put("/api/companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isOk());

        // Validate the Company in the database
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeUpdate);
        Company testCompany = companyList.get(companyList.size() - 1);
        assertThat(testCompany.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testCompany.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testCompany.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCompany.getShortdescription()).isEqualTo(UPDATED_SHORTDESCRIPTION);
        assertThat(testCompany.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCompany.getFoundedon()).isEqualTo(UPDATED_FOUNDEDON);
        assertThat(testCompany.getClosedon()).isEqualTo(UPDATED_CLOSEDON);
        assertThat(testCompany.getNumemployeesmin()).isEqualTo(UPDATED_NUMEMPLOYEESMIN);
        assertThat(testCompany.getNumemployeesmax()).isEqualTo(UPDATED_NUMEMPLOYEESMAX);
        assertThat(testCompany.getTotalfundingusd()).isEqualTo(UPDATED_TOTALFUNDINGUSD);
        assertThat(testCompany.getTotalfundingvnd()).isEqualTo(UPDATED_TOTALFUNDINGVND);
        assertThat(testCompany.getNumberofinvestments()).isEqualTo(UPDATED_NUMBEROFINVESTMENTS);
        assertThat(testCompany.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testCompany.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
        assertThat(testCompany.getContactemail()).isEqualTo(UPDATED_CONTACTEMAIL);
        assertThat(testCompany.getPhonenumber()).isEqualTo(UPDATED_PHONENUMBER);
        assertThat(testCompany.getHomepageurl()).isEqualTo(UPDATED_HOMEPAGEURL);
        assertThat(testCompany.getFacebookurl()).isEqualTo(UPDATED_FACEBOOKURL);
        assertThat(testCompany.getTwitterurl()).isEqualTo(UPDATED_TWITTERURL);
        assertThat(testCompany.getLinkedinurl()).isEqualTo(UPDATED_LINKEDINURL);
        assertThat(testCompany.getCityname()).isEqualTo(UPDATED_CITYNAME);
        assertThat(testCompany.getRegionname()).isEqualTo(UPDATED_REGIONNAME);
        assertThat(testCompany.getCountrycode()).isEqualTo(UPDATED_COUNTRYCODE);
    }

    @Test
    @Transactional
    public void updateNonExistingCompany() throws Exception {
        int databaseSizeBeforeUpdate = companyRepository.findAll().size();

        // Create the Company
        CompanyDTO companyDTO = companyMapper.toDto(company);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompanyMockMvc.perform(put("/api/companies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Company in the database
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompany() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        int databaseSizeBeforeDelete = companyRepository.findAll().size();

        // Delete the company
        restCompanyMockMvc.perform(delete("/api/companies/{id}", company.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Company.class);
        Company company1 = new Company();
        company1.setId(1L);
        Company company2 = new Company();
        company2.setId(company1.getId());
        assertThat(company1).isEqualTo(company2);
        company2.setId(2L);
        assertThat(company1).isNotEqualTo(company2);
        company1.setId(null);
        assertThat(company1).isNotEqualTo(company2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompanyDTO.class);
        CompanyDTO companyDTO1 = new CompanyDTO();
        companyDTO1.setId(1L);
        CompanyDTO companyDTO2 = new CompanyDTO();
        assertThat(companyDTO1).isNotEqualTo(companyDTO2);
        companyDTO2.setId(companyDTO1.getId());
        assertThat(companyDTO1).isEqualTo(companyDTO2);
        companyDTO2.setId(2L);
        assertThat(companyDTO1).isNotEqualTo(companyDTO2);
        companyDTO1.setId(null);
        assertThat(companyDTO1).isNotEqualTo(companyDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(companyMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(companyMapper.fromId(null)).isNull();
    }
}
