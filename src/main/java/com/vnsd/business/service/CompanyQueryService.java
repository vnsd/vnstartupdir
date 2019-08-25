package com.vnsd.business.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.vnsd.business.domain.Company;
import com.vnsd.business.domain.*; // for static metamodels
import com.vnsd.business.repository.CompanyRepository;
import com.vnsd.business.service.dto.CompanyCriteria;
import com.vnsd.business.service.dto.CompanyDTO;
import com.vnsd.business.service.mapper.CompanyMapper;

/**
 * Service for executing complex queries for {@link Company} entities in the database.
 * The main input is a {@link CompanyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CompanyDTO} or a {@link Page} of {@link CompanyDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CompanyQueryService extends QueryService<Company> {

    private final Logger log = LoggerFactory.getLogger(CompanyQueryService.class);

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    public CompanyQueryService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    /**
     * Return a {@link List} of {@link CompanyDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CompanyDTO> findByCriteria(CompanyCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Company> specification = createSpecification(criteria);
        return companyMapper.toDto(companyRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CompanyDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CompanyDTO> findByCriteria(CompanyCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Company> specification = createSpecification(criteria);
        return companyRepository.findAll(specification, page)
            .map(companyMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CompanyCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Company> specification = createSpecification(criteria);
        return companyRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<Company> createSpecification(CompanyCriteria criteria) {
        Specification<Company> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Company_.id));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), Company_.uuid));
            }
            if (criteria.getPermalink() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPermalink(), Company_.permalink));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Company_.name));
            }
            if (criteria.getAlsoknownas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAlsoknownas(), Company_.alsoknownas));
            }
            if (criteria.getShortdescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getShortdescription(), Company_.shortdescription));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Company_.description));
            }
            if (criteria.getProfileimageid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProfileimageid(), Company_.profileimageid));
            }
            if (criteria.getPrimaryrole() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrimaryrole(), Company_.primaryrole));
            }
            if (criteria.getFoundedon() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFoundedon(), Company_.foundedon));
            }
            if (criteria.getFoundedontrustcode() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFoundedontrustcode(), Company_.foundedontrustcode));
            }
            if (criteria.getClosedon() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClosedon(), Company_.closedon));
            }
            if (criteria.getNumemployeesmin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumemployeesmin(), Company_.numemployeesmin));
            }
            if (criteria.getNumemployeesmax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumemployeesmax(), Company_.numemployeesmax));
            }
            if (criteria.getTotalfundingusd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalfundingusd(), Company_.totalfundingusd));
            }
            if (criteria.getTotalfundingvnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalfundingvnd(), Company_.totalfundingvnd));
            }
            if (criteria.getStockexchange() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStockexchange(), Company_.stockexchange));
            }
            if (criteria.getStocksymbol() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStocksymbol(), Company_.stocksymbol));
            }
            if (criteria.getNumberofinvestments() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberofinvestments(), Company_.numberofinvestments));
            }
            if (criteria.getCreatedat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedat(), Company_.createdat));
            }
            if (criteria.getUpdatedat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdatedat(), Company_.updatedat));
            }
            if (criteria.getPermalinkaliases() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPermalinkaliases(), Company_.permalinkaliases));
            }
            if (criteria.getInvestortype() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInvestortype(), Company_.investortype));
            }
            if (criteria.getContactemail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactemail(), Company_.contactemail));
            }
            if (criteria.getPhonenumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhonenumber(), Company_.phonenumber));
            }
            if (criteria.getRank() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRank(), Company_.rank));
            }
            if (criteria.getPrimaryimageid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrimaryimageid(), Company_.primaryimageid));
            }
            if (criteria.getOwnedbyid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOwnedbyid(), Company_.ownedbyid));
            }
            if (criteria.getHeadquartersid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHeadquartersid(), Company_.headquartersid));
            }
            if (criteria.getAcquiredbyid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAcquiredbyid(), Company_.acquiredbyid));
            }
            if (criteria.getIpoid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIpoid(), Company_.ipoid));
            }
            if (criteria.getHomepageurl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHomepageurl(), Company_.homepageurl));
            }
            if (criteria.getFacebookurl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFacebookurl(), Company_.facebookurl));
            }
            if (criteria.getTwitterurl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTwitterurl(), Company_.twitterurl));
            }
            if (criteria.getLinkedinurl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLinkedinurl(), Company_.linkedinurl));
            }
            if (criteria.getCityname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCityname(), Company_.cityname));
            }
            if (criteria.getRegionname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRegionname(), Company_.regionname));
            }
            if (criteria.getCountrycode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCountrycode(), Company_.countrycode));
            }
            if (criteria.getPeopleId() != null) {
                specification = specification.and(buildSpecification(criteria.getPeopleId(),
                    root -> root.join(Company_.people, JoinType.LEFT).get(PersonCompanyRelation_.id)));
            }
            if (criteria.getOwnerId() != null) {
                specification = specification.and(buildSpecification(criteria.getOwnerId(),
                    root -> root.join(Company_.owner, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getAssigneeId() != null) {
                specification = specification.and(buildSpecification(criteria.getAssigneeId(),
                    root -> root.join(Company_.assignee, JoinType.LEFT).get(User_.id)));
            }
        }
        return specification;
    }
}
