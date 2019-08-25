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

    private static final String DEFAULT_PERMALINK = "AAAAAAAAAA";
    private static final String UPDATED_PERMALINK = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ALSOKNOWNAS = "AAAAAAAAAA";
    private static final String UPDATED_ALSOKNOWNAS = "BBBBBBBBBB";

    private static final String DEFAULT_SHORTDESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SHORTDESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_PROFILEIMAGEID = 1;
    private static final Integer UPDATED_PROFILEIMAGEID = 2;
    private static final Integer SMALLER_PROFILEIMAGEID = 1 - 1;

    private static final String DEFAULT_PRIMARYROLE = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARYROLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_FOUNDEDON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FOUNDEDON = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_FOUNDEDON = Instant.ofEpochMilli(-1L);

    private static final Integer DEFAULT_FOUNDEDONTRUSTCODE = 1;
    private static final Integer UPDATED_FOUNDEDONTRUSTCODE = 2;
    private static final Integer SMALLER_FOUNDEDONTRUSTCODE = 1 - 1;

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

    private static final String DEFAULT_STOCKEXCHANGE = "AAAAAAAAAA";
    private static final String UPDATED_STOCKEXCHANGE = "BBBBBBBBBB";

    private static final String DEFAULT_STOCKSYMBOL = "AAAAAAAAAA";
    private static final String UPDATED_STOCKSYMBOL = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBEROFINVESTMENTS = 1;
    private static final Integer UPDATED_NUMBEROFINVESTMENTS = 2;
    private static final Integer SMALLER_NUMBEROFINVESTMENTS = 1 - 1;

    private static final Instant DEFAULT_CREATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_CREATEDAT = Instant.ofEpochMilli(-1L);

    private static final Instant DEFAULT_UPDATEDAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_UPDATEDAT = Instant.ofEpochMilli(-1L);

    private static final String DEFAULT_PERMALINKALIASES = "AAAAAAAAAA";
    private static final String UPDATED_PERMALINKALIASES = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTORTYPE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTORTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTEMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTEMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONENUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;
    private static final Integer SMALLER_RANK = 1 - 1;

    private static final Integer DEFAULT_PRIMARYIMAGEID = 1;
    private static final Integer UPDATED_PRIMARYIMAGEID = 2;
    private static final Integer SMALLER_PRIMARYIMAGEID = 1 - 1;

    private static final Integer DEFAULT_OWNEDBYID = 1;
    private static final Integer UPDATED_OWNEDBYID = 2;
    private static final Integer SMALLER_OWNEDBYID = 1 - 1;

    private static final Integer DEFAULT_HEADQUARTERSID = 1;
    private static final Integer UPDATED_HEADQUARTERSID = 2;
    private static final Integer SMALLER_HEADQUARTERSID = 1 - 1;

    private static final Integer DEFAULT_ACQUIREDBYID = 1;
    private static final Integer UPDATED_ACQUIREDBYID = 2;
    private static final Integer SMALLER_ACQUIREDBYID = 1 - 1;

    private static final Integer DEFAULT_IPOID = 1;
    private static final Integer UPDATED_IPOID = 2;
    private static final Integer SMALLER_IPOID = 1 - 1;

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
            .permalink(DEFAULT_PERMALINK)
            .name(DEFAULT_NAME)
            .alsoknownas(DEFAULT_ALSOKNOWNAS)
            .shortdescription(DEFAULT_SHORTDESCRIPTION)
            .description(DEFAULT_DESCRIPTION)
            .profileimageid(DEFAULT_PROFILEIMAGEID)
            .primaryrole(DEFAULT_PRIMARYROLE)
            .foundedon(DEFAULT_FOUNDEDON)
            .foundedontrustcode(DEFAULT_FOUNDEDONTRUSTCODE)
            .closedon(DEFAULT_CLOSEDON)
            .numemployeesmin(DEFAULT_NUMEMPLOYEESMIN)
            .numemployeesmax(DEFAULT_NUMEMPLOYEESMAX)
            .totalfundingusd(DEFAULT_TOTALFUNDINGUSD)
            .totalfundingvnd(DEFAULT_TOTALFUNDINGVND)
            .stockexchange(DEFAULT_STOCKEXCHANGE)
            .stocksymbol(DEFAULT_STOCKSYMBOL)
            .numberofinvestments(DEFAULT_NUMBEROFINVESTMENTS)
            .createdat(DEFAULT_CREATEDAT)
            .updatedat(DEFAULT_UPDATEDAT)
            .permalinkaliases(DEFAULT_PERMALINKALIASES)
            .investortype(DEFAULT_INVESTORTYPE)
            .contactemail(DEFAULT_CONTACTEMAIL)
            .phonenumber(DEFAULT_PHONENUMBER)
            .rank(DEFAULT_RANK)
            .primaryimageid(DEFAULT_PRIMARYIMAGEID)
            .ownedbyid(DEFAULT_OWNEDBYID)
            .headquartersid(DEFAULT_HEADQUARTERSID)
            .acquiredbyid(DEFAULT_ACQUIREDBYID)
            .ipoid(DEFAULT_IPOID)
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
            .permalink(UPDATED_PERMALINK)
            .name(UPDATED_NAME)
            .alsoknownas(UPDATED_ALSOKNOWNAS)
            .shortdescription(UPDATED_SHORTDESCRIPTION)
            .description(UPDATED_DESCRIPTION)
            .profileimageid(UPDATED_PROFILEIMAGEID)
            .primaryrole(UPDATED_PRIMARYROLE)
            .foundedon(UPDATED_FOUNDEDON)
            .foundedontrustcode(UPDATED_FOUNDEDONTRUSTCODE)
            .closedon(UPDATED_CLOSEDON)
            .numemployeesmin(UPDATED_NUMEMPLOYEESMIN)
            .numemployeesmax(UPDATED_NUMEMPLOYEESMAX)
            .totalfundingusd(UPDATED_TOTALFUNDINGUSD)
            .totalfundingvnd(UPDATED_TOTALFUNDINGVND)
            .stockexchange(UPDATED_STOCKEXCHANGE)
            .stocksymbol(UPDATED_STOCKSYMBOL)
            .numberofinvestments(UPDATED_NUMBEROFINVESTMENTS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT)
            .permalinkaliases(UPDATED_PERMALINKALIASES)
            .investortype(UPDATED_INVESTORTYPE)
            .contactemail(UPDATED_CONTACTEMAIL)
            .phonenumber(UPDATED_PHONENUMBER)
            .rank(UPDATED_RANK)
            .primaryimageid(UPDATED_PRIMARYIMAGEID)
            .ownedbyid(UPDATED_OWNEDBYID)
            .headquartersid(UPDATED_HEADQUARTERSID)
            .acquiredbyid(UPDATED_ACQUIREDBYID)
            .ipoid(UPDATED_IPOID)
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
        assertThat(testCompany.getPermalink()).isEqualTo(DEFAULT_PERMALINK);
        assertThat(testCompany.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCompany.getAlsoknownas()).isEqualTo(DEFAULT_ALSOKNOWNAS);
        assertThat(testCompany.getShortdescription()).isEqualTo(DEFAULT_SHORTDESCRIPTION);
        assertThat(testCompany.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCompany.getProfileimageid()).isEqualTo(DEFAULT_PROFILEIMAGEID);
        assertThat(testCompany.getPrimaryrole()).isEqualTo(DEFAULT_PRIMARYROLE);
        assertThat(testCompany.getFoundedon()).isEqualTo(DEFAULT_FOUNDEDON);
        assertThat(testCompany.getFoundedontrustcode()).isEqualTo(DEFAULT_FOUNDEDONTRUSTCODE);
        assertThat(testCompany.getClosedon()).isEqualTo(DEFAULT_CLOSEDON);
        assertThat(testCompany.getNumemployeesmin()).isEqualTo(DEFAULT_NUMEMPLOYEESMIN);
        assertThat(testCompany.getNumemployeesmax()).isEqualTo(DEFAULT_NUMEMPLOYEESMAX);
        assertThat(testCompany.getTotalfundingusd()).isEqualTo(DEFAULT_TOTALFUNDINGUSD);
        assertThat(testCompany.getTotalfundingvnd()).isEqualTo(DEFAULT_TOTALFUNDINGVND);
        assertThat(testCompany.getStockexchange()).isEqualTo(DEFAULT_STOCKEXCHANGE);
        assertThat(testCompany.getStocksymbol()).isEqualTo(DEFAULT_STOCKSYMBOL);
        assertThat(testCompany.getNumberofinvestments()).isEqualTo(DEFAULT_NUMBEROFINVESTMENTS);
        assertThat(testCompany.getCreatedat()).isEqualTo(DEFAULT_CREATEDAT);
        assertThat(testCompany.getUpdatedat()).isEqualTo(DEFAULT_UPDATEDAT);
        assertThat(testCompany.getPermalinkaliases()).isEqualTo(DEFAULT_PERMALINKALIASES);
        assertThat(testCompany.getInvestortype()).isEqualTo(DEFAULT_INVESTORTYPE);
        assertThat(testCompany.getContactemail()).isEqualTo(DEFAULT_CONTACTEMAIL);
        assertThat(testCompany.getPhonenumber()).isEqualTo(DEFAULT_PHONENUMBER);
        assertThat(testCompany.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testCompany.getPrimaryimageid()).isEqualTo(DEFAULT_PRIMARYIMAGEID);
        assertThat(testCompany.getOwnedbyid()).isEqualTo(DEFAULT_OWNEDBYID);
        assertThat(testCompany.getHeadquartersid()).isEqualTo(DEFAULT_HEADQUARTERSID);
        assertThat(testCompany.getAcquiredbyid()).isEqualTo(DEFAULT_ACQUIREDBYID);
        assertThat(testCompany.getIpoid()).isEqualTo(DEFAULT_IPOID);
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
    public void checkPermalinkIsRequired() throws Exception {
        int databaseSizeBeforeTest = companyRepository.findAll().size();
        // set the field null
        company.setPermalink(null);

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
            .andExpect(jsonPath("$.[*].permalink").value(hasItem(DEFAULT_PERMALINK.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].alsoknownas").value(hasItem(DEFAULT_ALSOKNOWNAS.toString())))
            .andExpect(jsonPath("$.[*].shortdescription").value(hasItem(DEFAULT_SHORTDESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].profileimageid").value(hasItem(DEFAULT_PROFILEIMAGEID)))
            .andExpect(jsonPath("$.[*].primaryrole").value(hasItem(DEFAULT_PRIMARYROLE.toString())))
            .andExpect(jsonPath("$.[*].foundedon").value(hasItem(DEFAULT_FOUNDEDON.toString())))
            .andExpect(jsonPath("$.[*].foundedontrustcode").value(hasItem(DEFAULT_FOUNDEDONTRUSTCODE)))
            .andExpect(jsonPath("$.[*].closedon").value(hasItem(DEFAULT_CLOSEDON.toString())))
            .andExpect(jsonPath("$.[*].numemployeesmin").value(hasItem(DEFAULT_NUMEMPLOYEESMIN)))
            .andExpect(jsonPath("$.[*].numemployeesmax").value(hasItem(DEFAULT_NUMEMPLOYEESMAX)))
            .andExpect(jsonPath("$.[*].totalfundingusd").value(hasItem(DEFAULT_TOTALFUNDINGUSD)))
            .andExpect(jsonPath("$.[*].totalfundingvnd").value(hasItem(DEFAULT_TOTALFUNDINGVND)))
            .andExpect(jsonPath("$.[*].stockexchange").value(hasItem(DEFAULT_STOCKEXCHANGE.toString())))
            .andExpect(jsonPath("$.[*].stocksymbol").value(hasItem(DEFAULT_STOCKSYMBOL.toString())))
            .andExpect(jsonPath("$.[*].numberofinvestments").value(hasItem(DEFAULT_NUMBEROFINVESTMENTS)))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())))
            .andExpect(jsonPath("$.[*].permalinkaliases").value(hasItem(DEFAULT_PERMALINKALIASES.toString())))
            .andExpect(jsonPath("$.[*].investortype").value(hasItem(DEFAULT_INVESTORTYPE.toString())))
            .andExpect(jsonPath("$.[*].contactemail").value(hasItem(DEFAULT_CONTACTEMAIL.toString())))
            .andExpect(jsonPath("$.[*].phonenumber").value(hasItem(DEFAULT_PHONENUMBER.toString())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].primaryimageid").value(hasItem(DEFAULT_PRIMARYIMAGEID)))
            .andExpect(jsonPath("$.[*].ownedbyid").value(hasItem(DEFAULT_OWNEDBYID)))
            .andExpect(jsonPath("$.[*].headquartersid").value(hasItem(DEFAULT_HEADQUARTERSID)))
            .andExpect(jsonPath("$.[*].acquiredbyid").value(hasItem(DEFAULT_ACQUIREDBYID)))
            .andExpect(jsonPath("$.[*].ipoid").value(hasItem(DEFAULT_IPOID)))
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
            .andExpect(jsonPath("$.permalink").value(DEFAULT_PERMALINK.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.alsoknownas").value(DEFAULT_ALSOKNOWNAS.toString()))
            .andExpect(jsonPath("$.shortdescription").value(DEFAULT_SHORTDESCRIPTION.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.profileimageid").value(DEFAULT_PROFILEIMAGEID))
            .andExpect(jsonPath("$.primaryrole").value(DEFAULT_PRIMARYROLE.toString()))
            .andExpect(jsonPath("$.foundedon").value(DEFAULT_FOUNDEDON.toString()))
            .andExpect(jsonPath("$.foundedontrustcode").value(DEFAULT_FOUNDEDONTRUSTCODE))
            .andExpect(jsonPath("$.closedon").value(DEFAULT_CLOSEDON.toString()))
            .andExpect(jsonPath("$.numemployeesmin").value(DEFAULT_NUMEMPLOYEESMIN))
            .andExpect(jsonPath("$.numemployeesmax").value(DEFAULT_NUMEMPLOYEESMAX))
            .andExpect(jsonPath("$.totalfundingusd").value(DEFAULT_TOTALFUNDINGUSD))
            .andExpect(jsonPath("$.totalfundingvnd").value(DEFAULT_TOTALFUNDINGVND))
            .andExpect(jsonPath("$.stockexchange").value(DEFAULT_STOCKEXCHANGE.toString()))
            .andExpect(jsonPath("$.stocksymbol").value(DEFAULT_STOCKSYMBOL.toString()))
            .andExpect(jsonPath("$.numberofinvestments").value(DEFAULT_NUMBEROFINVESTMENTS))
            .andExpect(jsonPath("$.createdat").value(DEFAULT_CREATEDAT.toString()))
            .andExpect(jsonPath("$.updatedat").value(DEFAULT_UPDATEDAT.toString()))
            .andExpect(jsonPath("$.permalinkaliases").value(DEFAULT_PERMALINKALIASES.toString()))
            .andExpect(jsonPath("$.investortype").value(DEFAULT_INVESTORTYPE.toString()))
            .andExpect(jsonPath("$.contactemail").value(DEFAULT_CONTACTEMAIL.toString()))
            .andExpect(jsonPath("$.phonenumber").value(DEFAULT_PHONENUMBER.toString()))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.primaryimageid").value(DEFAULT_PRIMARYIMAGEID))
            .andExpect(jsonPath("$.ownedbyid").value(DEFAULT_OWNEDBYID))
            .andExpect(jsonPath("$.headquartersid").value(DEFAULT_HEADQUARTERSID))
            .andExpect(jsonPath("$.acquiredbyid").value(DEFAULT_ACQUIREDBYID))
            .andExpect(jsonPath("$.ipoid").value(DEFAULT_IPOID))
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
    public void getAllCompaniesByPermalinkIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where permalink equals to DEFAULT_PERMALINK
        defaultCompanyShouldBeFound("permalink.equals=" + DEFAULT_PERMALINK);

        // Get all the companyList where permalink equals to UPDATED_PERMALINK
        defaultCompanyShouldNotBeFound("permalink.equals=" + UPDATED_PERMALINK);
    }

    @Test
    @Transactional
    public void getAllCompaniesByPermalinkIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where permalink in DEFAULT_PERMALINK or UPDATED_PERMALINK
        defaultCompanyShouldBeFound("permalink.in=" + DEFAULT_PERMALINK + "," + UPDATED_PERMALINK);

        // Get all the companyList where permalink equals to UPDATED_PERMALINK
        defaultCompanyShouldNotBeFound("permalink.in=" + UPDATED_PERMALINK);
    }

    @Test
    @Transactional
    public void getAllCompaniesByPermalinkIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where permalink is not null
        defaultCompanyShouldBeFound("permalink.specified=true");

        // Get all the companyList where permalink is null
        defaultCompanyShouldNotBeFound("permalink.specified=false");
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
    public void getAllCompaniesByAlsoknownasIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where alsoknownas equals to DEFAULT_ALSOKNOWNAS
        defaultCompanyShouldBeFound("alsoknownas.equals=" + DEFAULT_ALSOKNOWNAS);

        // Get all the companyList where alsoknownas equals to UPDATED_ALSOKNOWNAS
        defaultCompanyShouldNotBeFound("alsoknownas.equals=" + UPDATED_ALSOKNOWNAS);
    }

    @Test
    @Transactional
    public void getAllCompaniesByAlsoknownasIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where alsoknownas in DEFAULT_ALSOKNOWNAS or UPDATED_ALSOKNOWNAS
        defaultCompanyShouldBeFound("alsoknownas.in=" + DEFAULT_ALSOKNOWNAS + "," + UPDATED_ALSOKNOWNAS);

        // Get all the companyList where alsoknownas equals to UPDATED_ALSOKNOWNAS
        defaultCompanyShouldNotBeFound("alsoknownas.in=" + UPDATED_ALSOKNOWNAS);
    }

    @Test
    @Transactional
    public void getAllCompaniesByAlsoknownasIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where alsoknownas is not null
        defaultCompanyShouldBeFound("alsoknownas.specified=true");

        // Get all the companyList where alsoknownas is null
        defaultCompanyShouldNotBeFound("alsoknownas.specified=false");
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
    public void getAllCompaniesByProfileimageidIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where profileimageid equals to DEFAULT_PROFILEIMAGEID
        defaultCompanyShouldBeFound("profileimageid.equals=" + DEFAULT_PROFILEIMAGEID);

        // Get all the companyList where profileimageid equals to UPDATED_PROFILEIMAGEID
        defaultCompanyShouldNotBeFound("profileimageid.equals=" + UPDATED_PROFILEIMAGEID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByProfileimageidIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where profileimageid in DEFAULT_PROFILEIMAGEID or UPDATED_PROFILEIMAGEID
        defaultCompanyShouldBeFound("profileimageid.in=" + DEFAULT_PROFILEIMAGEID + "," + UPDATED_PROFILEIMAGEID);

        // Get all the companyList where profileimageid equals to UPDATED_PROFILEIMAGEID
        defaultCompanyShouldNotBeFound("profileimageid.in=" + UPDATED_PROFILEIMAGEID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByProfileimageidIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where profileimageid is not null
        defaultCompanyShouldBeFound("profileimageid.specified=true");

        // Get all the companyList where profileimageid is null
        defaultCompanyShouldNotBeFound("profileimageid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByProfileimageidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where profileimageid is greater than or equal to DEFAULT_PROFILEIMAGEID
        defaultCompanyShouldBeFound("profileimageid.greaterThanOrEqual=" + DEFAULT_PROFILEIMAGEID);

        // Get all the companyList where profileimageid is greater than or equal to UPDATED_PROFILEIMAGEID
        defaultCompanyShouldNotBeFound("profileimageid.greaterThanOrEqual=" + UPDATED_PROFILEIMAGEID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByProfileimageidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where profileimageid is less than or equal to DEFAULT_PROFILEIMAGEID
        defaultCompanyShouldBeFound("profileimageid.lessThanOrEqual=" + DEFAULT_PROFILEIMAGEID);

        // Get all the companyList where profileimageid is less than or equal to SMALLER_PROFILEIMAGEID
        defaultCompanyShouldNotBeFound("profileimageid.lessThanOrEqual=" + SMALLER_PROFILEIMAGEID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByProfileimageidIsLessThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where profileimageid is less than DEFAULT_PROFILEIMAGEID
        defaultCompanyShouldNotBeFound("profileimageid.lessThan=" + DEFAULT_PROFILEIMAGEID);

        // Get all the companyList where profileimageid is less than UPDATED_PROFILEIMAGEID
        defaultCompanyShouldBeFound("profileimageid.lessThan=" + UPDATED_PROFILEIMAGEID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByProfileimageidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where profileimageid is greater than DEFAULT_PROFILEIMAGEID
        defaultCompanyShouldNotBeFound("profileimageid.greaterThan=" + DEFAULT_PROFILEIMAGEID);

        // Get all the companyList where profileimageid is greater than SMALLER_PROFILEIMAGEID
        defaultCompanyShouldBeFound("profileimageid.greaterThan=" + SMALLER_PROFILEIMAGEID);
    }


    @Test
    @Transactional
    public void getAllCompaniesByPrimaryroleIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where primaryrole equals to DEFAULT_PRIMARYROLE
        defaultCompanyShouldBeFound("primaryrole.equals=" + DEFAULT_PRIMARYROLE);

        // Get all the companyList where primaryrole equals to UPDATED_PRIMARYROLE
        defaultCompanyShouldNotBeFound("primaryrole.equals=" + UPDATED_PRIMARYROLE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByPrimaryroleIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where primaryrole in DEFAULT_PRIMARYROLE or UPDATED_PRIMARYROLE
        defaultCompanyShouldBeFound("primaryrole.in=" + DEFAULT_PRIMARYROLE + "," + UPDATED_PRIMARYROLE);

        // Get all the companyList where primaryrole equals to UPDATED_PRIMARYROLE
        defaultCompanyShouldNotBeFound("primaryrole.in=" + UPDATED_PRIMARYROLE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByPrimaryroleIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where primaryrole is not null
        defaultCompanyShouldBeFound("primaryrole.specified=true");

        // Get all the companyList where primaryrole is null
        defaultCompanyShouldNotBeFound("primaryrole.specified=false");
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
    public void getAllCompaniesByFoundedontrustcodeIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where foundedontrustcode equals to DEFAULT_FOUNDEDONTRUSTCODE
        defaultCompanyShouldBeFound("foundedontrustcode.equals=" + DEFAULT_FOUNDEDONTRUSTCODE);

        // Get all the companyList where foundedontrustcode equals to UPDATED_FOUNDEDONTRUSTCODE
        defaultCompanyShouldNotBeFound("foundedontrustcode.equals=" + UPDATED_FOUNDEDONTRUSTCODE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByFoundedontrustcodeIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where foundedontrustcode in DEFAULT_FOUNDEDONTRUSTCODE or UPDATED_FOUNDEDONTRUSTCODE
        defaultCompanyShouldBeFound("foundedontrustcode.in=" + DEFAULT_FOUNDEDONTRUSTCODE + "," + UPDATED_FOUNDEDONTRUSTCODE);

        // Get all the companyList where foundedontrustcode equals to UPDATED_FOUNDEDONTRUSTCODE
        defaultCompanyShouldNotBeFound("foundedontrustcode.in=" + UPDATED_FOUNDEDONTRUSTCODE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByFoundedontrustcodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where foundedontrustcode is not null
        defaultCompanyShouldBeFound("foundedontrustcode.specified=true");

        // Get all the companyList where foundedontrustcode is null
        defaultCompanyShouldNotBeFound("foundedontrustcode.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByFoundedontrustcodeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where foundedontrustcode is greater than or equal to DEFAULT_FOUNDEDONTRUSTCODE
        defaultCompanyShouldBeFound("foundedontrustcode.greaterThanOrEqual=" + DEFAULT_FOUNDEDONTRUSTCODE);

        // Get all the companyList where foundedontrustcode is greater than or equal to UPDATED_FOUNDEDONTRUSTCODE
        defaultCompanyShouldNotBeFound("foundedontrustcode.greaterThanOrEqual=" + UPDATED_FOUNDEDONTRUSTCODE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByFoundedontrustcodeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where foundedontrustcode is less than or equal to DEFAULT_FOUNDEDONTRUSTCODE
        defaultCompanyShouldBeFound("foundedontrustcode.lessThanOrEqual=" + DEFAULT_FOUNDEDONTRUSTCODE);

        // Get all the companyList where foundedontrustcode is less than or equal to SMALLER_FOUNDEDONTRUSTCODE
        defaultCompanyShouldNotBeFound("foundedontrustcode.lessThanOrEqual=" + SMALLER_FOUNDEDONTRUSTCODE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByFoundedontrustcodeIsLessThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where foundedontrustcode is less than DEFAULT_FOUNDEDONTRUSTCODE
        defaultCompanyShouldNotBeFound("foundedontrustcode.lessThan=" + DEFAULT_FOUNDEDONTRUSTCODE);

        // Get all the companyList where foundedontrustcode is less than UPDATED_FOUNDEDONTRUSTCODE
        defaultCompanyShouldBeFound("foundedontrustcode.lessThan=" + UPDATED_FOUNDEDONTRUSTCODE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByFoundedontrustcodeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where foundedontrustcode is greater than DEFAULT_FOUNDEDONTRUSTCODE
        defaultCompanyShouldNotBeFound("foundedontrustcode.greaterThan=" + DEFAULT_FOUNDEDONTRUSTCODE);

        // Get all the companyList where foundedontrustcode is greater than SMALLER_FOUNDEDONTRUSTCODE
        defaultCompanyShouldBeFound("foundedontrustcode.greaterThan=" + SMALLER_FOUNDEDONTRUSTCODE);
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
    public void getAllCompaniesByStockexchangeIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where stockexchange equals to DEFAULT_STOCKEXCHANGE
        defaultCompanyShouldBeFound("stockexchange.equals=" + DEFAULT_STOCKEXCHANGE);

        // Get all the companyList where stockexchange equals to UPDATED_STOCKEXCHANGE
        defaultCompanyShouldNotBeFound("stockexchange.equals=" + UPDATED_STOCKEXCHANGE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByStockexchangeIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where stockexchange in DEFAULT_STOCKEXCHANGE or UPDATED_STOCKEXCHANGE
        defaultCompanyShouldBeFound("stockexchange.in=" + DEFAULT_STOCKEXCHANGE + "," + UPDATED_STOCKEXCHANGE);

        // Get all the companyList where stockexchange equals to UPDATED_STOCKEXCHANGE
        defaultCompanyShouldNotBeFound("stockexchange.in=" + UPDATED_STOCKEXCHANGE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByStockexchangeIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where stockexchange is not null
        defaultCompanyShouldBeFound("stockexchange.specified=true");

        // Get all the companyList where stockexchange is null
        defaultCompanyShouldNotBeFound("stockexchange.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByStocksymbolIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where stocksymbol equals to DEFAULT_STOCKSYMBOL
        defaultCompanyShouldBeFound("stocksymbol.equals=" + DEFAULT_STOCKSYMBOL);

        // Get all the companyList where stocksymbol equals to UPDATED_STOCKSYMBOL
        defaultCompanyShouldNotBeFound("stocksymbol.equals=" + UPDATED_STOCKSYMBOL);
    }

    @Test
    @Transactional
    public void getAllCompaniesByStocksymbolIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where stocksymbol in DEFAULT_STOCKSYMBOL or UPDATED_STOCKSYMBOL
        defaultCompanyShouldBeFound("stocksymbol.in=" + DEFAULT_STOCKSYMBOL + "," + UPDATED_STOCKSYMBOL);

        // Get all the companyList where stocksymbol equals to UPDATED_STOCKSYMBOL
        defaultCompanyShouldNotBeFound("stocksymbol.in=" + UPDATED_STOCKSYMBOL);
    }

    @Test
    @Transactional
    public void getAllCompaniesByStocksymbolIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where stocksymbol is not null
        defaultCompanyShouldBeFound("stocksymbol.specified=true");

        // Get all the companyList where stocksymbol is null
        defaultCompanyShouldNotBeFound("stocksymbol.specified=false");
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
    public void getAllCompaniesByPermalinkaliasesIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where permalinkaliases equals to DEFAULT_PERMALINKALIASES
        defaultCompanyShouldBeFound("permalinkaliases.equals=" + DEFAULT_PERMALINKALIASES);

        // Get all the companyList where permalinkaliases equals to UPDATED_PERMALINKALIASES
        defaultCompanyShouldNotBeFound("permalinkaliases.equals=" + UPDATED_PERMALINKALIASES);
    }

    @Test
    @Transactional
    public void getAllCompaniesByPermalinkaliasesIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where permalinkaliases in DEFAULT_PERMALINKALIASES or UPDATED_PERMALINKALIASES
        defaultCompanyShouldBeFound("permalinkaliases.in=" + DEFAULT_PERMALINKALIASES + "," + UPDATED_PERMALINKALIASES);

        // Get all the companyList where permalinkaliases equals to UPDATED_PERMALINKALIASES
        defaultCompanyShouldNotBeFound("permalinkaliases.in=" + UPDATED_PERMALINKALIASES);
    }

    @Test
    @Transactional
    public void getAllCompaniesByPermalinkaliasesIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where permalinkaliases is not null
        defaultCompanyShouldBeFound("permalinkaliases.specified=true");

        // Get all the companyList where permalinkaliases is null
        defaultCompanyShouldNotBeFound("permalinkaliases.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByInvestortypeIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where investortype equals to DEFAULT_INVESTORTYPE
        defaultCompanyShouldBeFound("investortype.equals=" + DEFAULT_INVESTORTYPE);

        // Get all the companyList where investortype equals to UPDATED_INVESTORTYPE
        defaultCompanyShouldNotBeFound("investortype.equals=" + UPDATED_INVESTORTYPE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByInvestortypeIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where investortype in DEFAULT_INVESTORTYPE or UPDATED_INVESTORTYPE
        defaultCompanyShouldBeFound("investortype.in=" + DEFAULT_INVESTORTYPE + "," + UPDATED_INVESTORTYPE);

        // Get all the companyList where investortype equals to UPDATED_INVESTORTYPE
        defaultCompanyShouldNotBeFound("investortype.in=" + UPDATED_INVESTORTYPE);
    }

    @Test
    @Transactional
    public void getAllCompaniesByInvestortypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where investortype is not null
        defaultCompanyShouldBeFound("investortype.specified=true");

        // Get all the companyList where investortype is null
        defaultCompanyShouldNotBeFound("investortype.specified=false");
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
    public void getAllCompaniesByRankIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where rank equals to DEFAULT_RANK
        defaultCompanyShouldBeFound("rank.equals=" + DEFAULT_RANK);

        // Get all the companyList where rank equals to UPDATED_RANK
        defaultCompanyShouldNotBeFound("rank.equals=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllCompaniesByRankIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where rank in DEFAULT_RANK or UPDATED_RANK
        defaultCompanyShouldBeFound("rank.in=" + DEFAULT_RANK + "," + UPDATED_RANK);

        // Get all the companyList where rank equals to UPDATED_RANK
        defaultCompanyShouldNotBeFound("rank.in=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllCompaniesByRankIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where rank is not null
        defaultCompanyShouldBeFound("rank.specified=true");

        // Get all the companyList where rank is null
        defaultCompanyShouldNotBeFound("rank.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByRankIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where rank is greater than or equal to DEFAULT_RANK
        defaultCompanyShouldBeFound("rank.greaterThanOrEqual=" + DEFAULT_RANK);

        // Get all the companyList where rank is greater than or equal to UPDATED_RANK
        defaultCompanyShouldNotBeFound("rank.greaterThanOrEqual=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllCompaniesByRankIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where rank is less than or equal to DEFAULT_RANK
        defaultCompanyShouldBeFound("rank.lessThanOrEqual=" + DEFAULT_RANK);

        // Get all the companyList where rank is less than or equal to SMALLER_RANK
        defaultCompanyShouldNotBeFound("rank.lessThanOrEqual=" + SMALLER_RANK);
    }

    @Test
    @Transactional
    public void getAllCompaniesByRankIsLessThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where rank is less than DEFAULT_RANK
        defaultCompanyShouldNotBeFound("rank.lessThan=" + DEFAULT_RANK);

        // Get all the companyList where rank is less than UPDATED_RANK
        defaultCompanyShouldBeFound("rank.lessThan=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllCompaniesByRankIsGreaterThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where rank is greater than DEFAULT_RANK
        defaultCompanyShouldNotBeFound("rank.greaterThan=" + DEFAULT_RANK);

        // Get all the companyList where rank is greater than SMALLER_RANK
        defaultCompanyShouldBeFound("rank.greaterThan=" + SMALLER_RANK);
    }


    @Test
    @Transactional
    public void getAllCompaniesByPrimaryimageidIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where primaryimageid equals to DEFAULT_PRIMARYIMAGEID
        defaultCompanyShouldBeFound("primaryimageid.equals=" + DEFAULT_PRIMARYIMAGEID);

        // Get all the companyList where primaryimageid equals to UPDATED_PRIMARYIMAGEID
        defaultCompanyShouldNotBeFound("primaryimageid.equals=" + UPDATED_PRIMARYIMAGEID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByPrimaryimageidIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where primaryimageid in DEFAULT_PRIMARYIMAGEID or UPDATED_PRIMARYIMAGEID
        defaultCompanyShouldBeFound("primaryimageid.in=" + DEFAULT_PRIMARYIMAGEID + "," + UPDATED_PRIMARYIMAGEID);

        // Get all the companyList where primaryimageid equals to UPDATED_PRIMARYIMAGEID
        defaultCompanyShouldNotBeFound("primaryimageid.in=" + UPDATED_PRIMARYIMAGEID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByPrimaryimageidIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where primaryimageid is not null
        defaultCompanyShouldBeFound("primaryimageid.specified=true");

        // Get all the companyList where primaryimageid is null
        defaultCompanyShouldNotBeFound("primaryimageid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByPrimaryimageidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where primaryimageid is greater than or equal to DEFAULT_PRIMARYIMAGEID
        defaultCompanyShouldBeFound("primaryimageid.greaterThanOrEqual=" + DEFAULT_PRIMARYIMAGEID);

        // Get all the companyList where primaryimageid is greater than or equal to UPDATED_PRIMARYIMAGEID
        defaultCompanyShouldNotBeFound("primaryimageid.greaterThanOrEqual=" + UPDATED_PRIMARYIMAGEID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByPrimaryimageidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where primaryimageid is less than or equal to DEFAULT_PRIMARYIMAGEID
        defaultCompanyShouldBeFound("primaryimageid.lessThanOrEqual=" + DEFAULT_PRIMARYIMAGEID);

        // Get all the companyList where primaryimageid is less than or equal to SMALLER_PRIMARYIMAGEID
        defaultCompanyShouldNotBeFound("primaryimageid.lessThanOrEqual=" + SMALLER_PRIMARYIMAGEID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByPrimaryimageidIsLessThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where primaryimageid is less than DEFAULT_PRIMARYIMAGEID
        defaultCompanyShouldNotBeFound("primaryimageid.lessThan=" + DEFAULT_PRIMARYIMAGEID);

        // Get all the companyList where primaryimageid is less than UPDATED_PRIMARYIMAGEID
        defaultCompanyShouldBeFound("primaryimageid.lessThan=" + UPDATED_PRIMARYIMAGEID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByPrimaryimageidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where primaryimageid is greater than DEFAULT_PRIMARYIMAGEID
        defaultCompanyShouldNotBeFound("primaryimageid.greaterThan=" + DEFAULT_PRIMARYIMAGEID);

        // Get all the companyList where primaryimageid is greater than SMALLER_PRIMARYIMAGEID
        defaultCompanyShouldBeFound("primaryimageid.greaterThan=" + SMALLER_PRIMARYIMAGEID);
    }


    @Test
    @Transactional
    public void getAllCompaniesByOwnedbyidIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where ownedbyid equals to DEFAULT_OWNEDBYID
        defaultCompanyShouldBeFound("ownedbyid.equals=" + DEFAULT_OWNEDBYID);

        // Get all the companyList where ownedbyid equals to UPDATED_OWNEDBYID
        defaultCompanyShouldNotBeFound("ownedbyid.equals=" + UPDATED_OWNEDBYID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByOwnedbyidIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where ownedbyid in DEFAULT_OWNEDBYID or UPDATED_OWNEDBYID
        defaultCompanyShouldBeFound("ownedbyid.in=" + DEFAULT_OWNEDBYID + "," + UPDATED_OWNEDBYID);

        // Get all the companyList where ownedbyid equals to UPDATED_OWNEDBYID
        defaultCompanyShouldNotBeFound("ownedbyid.in=" + UPDATED_OWNEDBYID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByOwnedbyidIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where ownedbyid is not null
        defaultCompanyShouldBeFound("ownedbyid.specified=true");

        // Get all the companyList where ownedbyid is null
        defaultCompanyShouldNotBeFound("ownedbyid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByOwnedbyidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where ownedbyid is greater than or equal to DEFAULT_OWNEDBYID
        defaultCompanyShouldBeFound("ownedbyid.greaterThanOrEqual=" + DEFAULT_OWNEDBYID);

        // Get all the companyList where ownedbyid is greater than or equal to UPDATED_OWNEDBYID
        defaultCompanyShouldNotBeFound("ownedbyid.greaterThanOrEqual=" + UPDATED_OWNEDBYID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByOwnedbyidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where ownedbyid is less than or equal to DEFAULT_OWNEDBYID
        defaultCompanyShouldBeFound("ownedbyid.lessThanOrEqual=" + DEFAULT_OWNEDBYID);

        // Get all the companyList where ownedbyid is less than or equal to SMALLER_OWNEDBYID
        defaultCompanyShouldNotBeFound("ownedbyid.lessThanOrEqual=" + SMALLER_OWNEDBYID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByOwnedbyidIsLessThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where ownedbyid is less than DEFAULT_OWNEDBYID
        defaultCompanyShouldNotBeFound("ownedbyid.lessThan=" + DEFAULT_OWNEDBYID);

        // Get all the companyList where ownedbyid is less than UPDATED_OWNEDBYID
        defaultCompanyShouldBeFound("ownedbyid.lessThan=" + UPDATED_OWNEDBYID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByOwnedbyidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where ownedbyid is greater than DEFAULT_OWNEDBYID
        defaultCompanyShouldNotBeFound("ownedbyid.greaterThan=" + DEFAULT_OWNEDBYID);

        // Get all the companyList where ownedbyid is greater than SMALLER_OWNEDBYID
        defaultCompanyShouldBeFound("ownedbyid.greaterThan=" + SMALLER_OWNEDBYID);
    }


    @Test
    @Transactional
    public void getAllCompaniesByHeadquartersidIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where headquartersid equals to DEFAULT_HEADQUARTERSID
        defaultCompanyShouldBeFound("headquartersid.equals=" + DEFAULT_HEADQUARTERSID);

        // Get all the companyList where headquartersid equals to UPDATED_HEADQUARTERSID
        defaultCompanyShouldNotBeFound("headquartersid.equals=" + UPDATED_HEADQUARTERSID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByHeadquartersidIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where headquartersid in DEFAULT_HEADQUARTERSID or UPDATED_HEADQUARTERSID
        defaultCompanyShouldBeFound("headquartersid.in=" + DEFAULT_HEADQUARTERSID + "," + UPDATED_HEADQUARTERSID);

        // Get all the companyList where headquartersid equals to UPDATED_HEADQUARTERSID
        defaultCompanyShouldNotBeFound("headquartersid.in=" + UPDATED_HEADQUARTERSID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByHeadquartersidIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where headquartersid is not null
        defaultCompanyShouldBeFound("headquartersid.specified=true");

        // Get all the companyList where headquartersid is null
        defaultCompanyShouldNotBeFound("headquartersid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByHeadquartersidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where headquartersid is greater than or equal to DEFAULT_HEADQUARTERSID
        defaultCompanyShouldBeFound("headquartersid.greaterThanOrEqual=" + DEFAULT_HEADQUARTERSID);

        // Get all the companyList where headquartersid is greater than or equal to UPDATED_HEADQUARTERSID
        defaultCompanyShouldNotBeFound("headquartersid.greaterThanOrEqual=" + UPDATED_HEADQUARTERSID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByHeadquartersidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where headquartersid is less than or equal to DEFAULT_HEADQUARTERSID
        defaultCompanyShouldBeFound("headquartersid.lessThanOrEqual=" + DEFAULT_HEADQUARTERSID);

        // Get all the companyList where headquartersid is less than or equal to SMALLER_HEADQUARTERSID
        defaultCompanyShouldNotBeFound("headquartersid.lessThanOrEqual=" + SMALLER_HEADQUARTERSID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByHeadquartersidIsLessThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where headquartersid is less than DEFAULT_HEADQUARTERSID
        defaultCompanyShouldNotBeFound("headquartersid.lessThan=" + DEFAULT_HEADQUARTERSID);

        // Get all the companyList where headquartersid is less than UPDATED_HEADQUARTERSID
        defaultCompanyShouldBeFound("headquartersid.lessThan=" + UPDATED_HEADQUARTERSID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByHeadquartersidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where headquartersid is greater than DEFAULT_HEADQUARTERSID
        defaultCompanyShouldNotBeFound("headquartersid.greaterThan=" + DEFAULT_HEADQUARTERSID);

        // Get all the companyList where headquartersid is greater than SMALLER_HEADQUARTERSID
        defaultCompanyShouldBeFound("headquartersid.greaterThan=" + SMALLER_HEADQUARTERSID);
    }


    @Test
    @Transactional
    public void getAllCompaniesByAcquiredbyidIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where acquiredbyid equals to DEFAULT_ACQUIREDBYID
        defaultCompanyShouldBeFound("acquiredbyid.equals=" + DEFAULT_ACQUIREDBYID);

        // Get all the companyList where acquiredbyid equals to UPDATED_ACQUIREDBYID
        defaultCompanyShouldNotBeFound("acquiredbyid.equals=" + UPDATED_ACQUIREDBYID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByAcquiredbyidIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where acquiredbyid in DEFAULT_ACQUIREDBYID or UPDATED_ACQUIREDBYID
        defaultCompanyShouldBeFound("acquiredbyid.in=" + DEFAULT_ACQUIREDBYID + "," + UPDATED_ACQUIREDBYID);

        // Get all the companyList where acquiredbyid equals to UPDATED_ACQUIREDBYID
        defaultCompanyShouldNotBeFound("acquiredbyid.in=" + UPDATED_ACQUIREDBYID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByAcquiredbyidIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where acquiredbyid is not null
        defaultCompanyShouldBeFound("acquiredbyid.specified=true");

        // Get all the companyList where acquiredbyid is null
        defaultCompanyShouldNotBeFound("acquiredbyid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByAcquiredbyidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where acquiredbyid is greater than or equal to DEFAULT_ACQUIREDBYID
        defaultCompanyShouldBeFound("acquiredbyid.greaterThanOrEqual=" + DEFAULT_ACQUIREDBYID);

        // Get all the companyList where acquiredbyid is greater than or equal to UPDATED_ACQUIREDBYID
        defaultCompanyShouldNotBeFound("acquiredbyid.greaterThanOrEqual=" + UPDATED_ACQUIREDBYID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByAcquiredbyidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where acquiredbyid is less than or equal to DEFAULT_ACQUIREDBYID
        defaultCompanyShouldBeFound("acquiredbyid.lessThanOrEqual=" + DEFAULT_ACQUIREDBYID);

        // Get all the companyList where acquiredbyid is less than or equal to SMALLER_ACQUIREDBYID
        defaultCompanyShouldNotBeFound("acquiredbyid.lessThanOrEqual=" + SMALLER_ACQUIREDBYID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByAcquiredbyidIsLessThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where acquiredbyid is less than DEFAULT_ACQUIREDBYID
        defaultCompanyShouldNotBeFound("acquiredbyid.lessThan=" + DEFAULT_ACQUIREDBYID);

        // Get all the companyList where acquiredbyid is less than UPDATED_ACQUIREDBYID
        defaultCompanyShouldBeFound("acquiredbyid.lessThan=" + UPDATED_ACQUIREDBYID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByAcquiredbyidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where acquiredbyid is greater than DEFAULT_ACQUIREDBYID
        defaultCompanyShouldNotBeFound("acquiredbyid.greaterThan=" + DEFAULT_ACQUIREDBYID);

        // Get all the companyList where acquiredbyid is greater than SMALLER_ACQUIREDBYID
        defaultCompanyShouldBeFound("acquiredbyid.greaterThan=" + SMALLER_ACQUIREDBYID);
    }


    @Test
    @Transactional
    public void getAllCompaniesByIpoidIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where ipoid equals to DEFAULT_IPOID
        defaultCompanyShouldBeFound("ipoid.equals=" + DEFAULT_IPOID);

        // Get all the companyList where ipoid equals to UPDATED_IPOID
        defaultCompanyShouldNotBeFound("ipoid.equals=" + UPDATED_IPOID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByIpoidIsInShouldWork() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where ipoid in DEFAULT_IPOID or UPDATED_IPOID
        defaultCompanyShouldBeFound("ipoid.in=" + DEFAULT_IPOID + "," + UPDATED_IPOID);

        // Get all the companyList where ipoid equals to UPDATED_IPOID
        defaultCompanyShouldNotBeFound("ipoid.in=" + UPDATED_IPOID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByIpoidIsNullOrNotNull() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where ipoid is not null
        defaultCompanyShouldBeFound("ipoid.specified=true");

        // Get all the companyList where ipoid is null
        defaultCompanyShouldNotBeFound("ipoid.specified=false");
    }

    @Test
    @Transactional
    public void getAllCompaniesByIpoidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where ipoid is greater than or equal to DEFAULT_IPOID
        defaultCompanyShouldBeFound("ipoid.greaterThanOrEqual=" + DEFAULT_IPOID);

        // Get all the companyList where ipoid is greater than or equal to UPDATED_IPOID
        defaultCompanyShouldNotBeFound("ipoid.greaterThanOrEqual=" + UPDATED_IPOID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByIpoidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where ipoid is less than or equal to DEFAULT_IPOID
        defaultCompanyShouldBeFound("ipoid.lessThanOrEqual=" + DEFAULT_IPOID);

        // Get all the companyList where ipoid is less than or equal to SMALLER_IPOID
        defaultCompanyShouldNotBeFound("ipoid.lessThanOrEqual=" + SMALLER_IPOID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByIpoidIsLessThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where ipoid is less than DEFAULT_IPOID
        defaultCompanyShouldNotBeFound("ipoid.lessThan=" + DEFAULT_IPOID);

        // Get all the companyList where ipoid is less than UPDATED_IPOID
        defaultCompanyShouldBeFound("ipoid.lessThan=" + UPDATED_IPOID);
    }

    @Test
    @Transactional
    public void getAllCompaniesByIpoidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList where ipoid is greater than DEFAULT_IPOID
        defaultCompanyShouldNotBeFound("ipoid.greaterThan=" + DEFAULT_IPOID);

        // Get all the companyList where ipoid is greater than SMALLER_IPOID
        defaultCompanyShouldBeFound("ipoid.greaterThan=" + SMALLER_IPOID);
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
    public void getAllCompaniesByOwnerIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);
        User owner = UserResourceIT.createEntity(em);
        em.persist(owner);
        em.flush();
        company.setOwner(owner);
        companyRepository.saveAndFlush(company);
        Long ownerId = owner.getId();

        // Get all the companyList where owner equals to ownerId
        defaultCompanyShouldBeFound("ownerId.equals=" + ownerId);

        // Get all the companyList where owner equals to ownerId + 1
        defaultCompanyShouldNotBeFound("ownerId.equals=" + (ownerId + 1));
    }


    @Test
    @Transactional
    public void getAllCompaniesByAssigneeIsEqualToSomething() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);
        User assignee = UserResourceIT.createEntity(em);
        em.persist(assignee);
        em.flush();
        company.setAssignee(assignee);
        companyRepository.saveAndFlush(company);
        Long assigneeId = assignee.getId();

        // Get all the companyList where assignee equals to assigneeId
        defaultCompanyShouldBeFound("assigneeId.equals=" + assigneeId);

        // Get all the companyList where assignee equals to assigneeId + 1
        defaultCompanyShouldNotBeFound("assigneeId.equals=" + (assigneeId + 1));
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
            .andExpect(jsonPath("$.[*].permalink").value(hasItem(DEFAULT_PERMALINK)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].alsoknownas").value(hasItem(DEFAULT_ALSOKNOWNAS)))
            .andExpect(jsonPath("$.[*].shortdescription").value(hasItem(DEFAULT_SHORTDESCRIPTION)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].profileimageid").value(hasItem(DEFAULT_PROFILEIMAGEID)))
            .andExpect(jsonPath("$.[*].primaryrole").value(hasItem(DEFAULT_PRIMARYROLE)))
            .andExpect(jsonPath("$.[*].foundedon").value(hasItem(DEFAULT_FOUNDEDON.toString())))
            .andExpect(jsonPath("$.[*].foundedontrustcode").value(hasItem(DEFAULT_FOUNDEDONTRUSTCODE)))
            .andExpect(jsonPath("$.[*].closedon").value(hasItem(DEFAULT_CLOSEDON.toString())))
            .andExpect(jsonPath("$.[*].numemployeesmin").value(hasItem(DEFAULT_NUMEMPLOYEESMIN)))
            .andExpect(jsonPath("$.[*].numemployeesmax").value(hasItem(DEFAULT_NUMEMPLOYEESMAX)))
            .andExpect(jsonPath("$.[*].totalfundingusd").value(hasItem(DEFAULT_TOTALFUNDINGUSD)))
            .andExpect(jsonPath("$.[*].totalfundingvnd").value(hasItem(DEFAULT_TOTALFUNDINGVND)))
            .andExpect(jsonPath("$.[*].stockexchange").value(hasItem(DEFAULT_STOCKEXCHANGE)))
            .andExpect(jsonPath("$.[*].stocksymbol").value(hasItem(DEFAULT_STOCKSYMBOL)))
            .andExpect(jsonPath("$.[*].numberofinvestments").value(hasItem(DEFAULT_NUMBEROFINVESTMENTS)))
            .andExpect(jsonPath("$.[*].createdat").value(hasItem(DEFAULT_CREATEDAT.toString())))
            .andExpect(jsonPath("$.[*].updatedat").value(hasItem(DEFAULT_UPDATEDAT.toString())))
            .andExpect(jsonPath("$.[*].permalinkaliases").value(hasItem(DEFAULT_PERMALINKALIASES)))
            .andExpect(jsonPath("$.[*].investortype").value(hasItem(DEFAULT_INVESTORTYPE)))
            .andExpect(jsonPath("$.[*].contactemail").value(hasItem(DEFAULT_CONTACTEMAIL)))
            .andExpect(jsonPath("$.[*].phonenumber").value(hasItem(DEFAULT_PHONENUMBER)))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].primaryimageid").value(hasItem(DEFAULT_PRIMARYIMAGEID)))
            .andExpect(jsonPath("$.[*].ownedbyid").value(hasItem(DEFAULT_OWNEDBYID)))
            .andExpect(jsonPath("$.[*].headquartersid").value(hasItem(DEFAULT_HEADQUARTERSID)))
            .andExpect(jsonPath("$.[*].acquiredbyid").value(hasItem(DEFAULT_ACQUIREDBYID)))
            .andExpect(jsonPath("$.[*].ipoid").value(hasItem(DEFAULT_IPOID)))
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
            .permalink(UPDATED_PERMALINK)
            .name(UPDATED_NAME)
            .alsoknownas(UPDATED_ALSOKNOWNAS)
            .shortdescription(UPDATED_SHORTDESCRIPTION)
            .description(UPDATED_DESCRIPTION)
            .profileimageid(UPDATED_PROFILEIMAGEID)
            .primaryrole(UPDATED_PRIMARYROLE)
            .foundedon(UPDATED_FOUNDEDON)
            .foundedontrustcode(UPDATED_FOUNDEDONTRUSTCODE)
            .closedon(UPDATED_CLOSEDON)
            .numemployeesmin(UPDATED_NUMEMPLOYEESMIN)
            .numemployeesmax(UPDATED_NUMEMPLOYEESMAX)
            .totalfundingusd(UPDATED_TOTALFUNDINGUSD)
            .totalfundingvnd(UPDATED_TOTALFUNDINGVND)
            .stockexchange(UPDATED_STOCKEXCHANGE)
            .stocksymbol(UPDATED_STOCKSYMBOL)
            .numberofinvestments(UPDATED_NUMBEROFINVESTMENTS)
            .createdat(UPDATED_CREATEDAT)
            .updatedat(UPDATED_UPDATEDAT)
            .permalinkaliases(UPDATED_PERMALINKALIASES)
            .investortype(UPDATED_INVESTORTYPE)
            .contactemail(UPDATED_CONTACTEMAIL)
            .phonenumber(UPDATED_PHONENUMBER)
            .rank(UPDATED_RANK)
            .primaryimageid(UPDATED_PRIMARYIMAGEID)
            .ownedbyid(UPDATED_OWNEDBYID)
            .headquartersid(UPDATED_HEADQUARTERSID)
            .acquiredbyid(UPDATED_ACQUIREDBYID)
            .ipoid(UPDATED_IPOID)
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
        assertThat(testCompany.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testCompany.getPermalink()).isEqualTo(UPDATED_PERMALINK);
        assertThat(testCompany.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCompany.getAlsoknownas()).isEqualTo(UPDATED_ALSOKNOWNAS);
        assertThat(testCompany.getShortdescription()).isEqualTo(UPDATED_SHORTDESCRIPTION);
        assertThat(testCompany.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCompany.getProfileimageid()).isEqualTo(UPDATED_PROFILEIMAGEID);
        assertThat(testCompany.getPrimaryrole()).isEqualTo(UPDATED_PRIMARYROLE);
        assertThat(testCompany.getFoundedon()).isEqualTo(UPDATED_FOUNDEDON);
        assertThat(testCompany.getFoundedontrustcode()).isEqualTo(UPDATED_FOUNDEDONTRUSTCODE);
        assertThat(testCompany.getClosedon()).isEqualTo(UPDATED_CLOSEDON);
        assertThat(testCompany.getNumemployeesmin()).isEqualTo(UPDATED_NUMEMPLOYEESMIN);
        assertThat(testCompany.getNumemployeesmax()).isEqualTo(UPDATED_NUMEMPLOYEESMAX);
        assertThat(testCompany.getTotalfundingusd()).isEqualTo(UPDATED_TOTALFUNDINGUSD);
        assertThat(testCompany.getTotalfundingvnd()).isEqualTo(UPDATED_TOTALFUNDINGVND);
        assertThat(testCompany.getStockexchange()).isEqualTo(UPDATED_STOCKEXCHANGE);
        assertThat(testCompany.getStocksymbol()).isEqualTo(UPDATED_STOCKSYMBOL);
        assertThat(testCompany.getNumberofinvestments()).isEqualTo(UPDATED_NUMBEROFINVESTMENTS);
        assertThat(testCompany.getCreatedat()).isEqualTo(UPDATED_CREATEDAT);
        assertThat(testCompany.getUpdatedat()).isEqualTo(UPDATED_UPDATEDAT);
        assertThat(testCompany.getPermalinkaliases()).isEqualTo(UPDATED_PERMALINKALIASES);
        assertThat(testCompany.getInvestortype()).isEqualTo(UPDATED_INVESTORTYPE);
        assertThat(testCompany.getContactemail()).isEqualTo(UPDATED_CONTACTEMAIL);
        assertThat(testCompany.getPhonenumber()).isEqualTo(UPDATED_PHONENUMBER);
        assertThat(testCompany.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testCompany.getPrimaryimageid()).isEqualTo(UPDATED_PRIMARYIMAGEID);
        assertThat(testCompany.getOwnedbyid()).isEqualTo(UPDATED_OWNEDBYID);
        assertThat(testCompany.getHeadquartersid()).isEqualTo(UPDATED_HEADQUARTERSID);
        assertThat(testCompany.getAcquiredbyid()).isEqualTo(UPDATED_ACQUIREDBYID);
        assertThat(testCompany.getIpoid()).isEqualTo(UPDATED_IPOID);
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
