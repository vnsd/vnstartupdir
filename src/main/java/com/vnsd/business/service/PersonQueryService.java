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

import com.vnsd.business.domain.Person;
import com.vnsd.business.domain.*; // for static metamodels
import com.vnsd.business.repository.PersonRepository;
import com.vnsd.business.service.dto.PersonCriteria;
import com.vnsd.business.service.dto.PersonDTO;
import com.vnsd.business.service.mapper.PersonMapper;

/**
 * Service for executing complex queries for {@link Person} entities in the database.
 * The main input is a {@link PersonCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PersonDTO} or a {@link Page} of {@link PersonDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PersonQueryService extends QueryService<Person> {

    private final Logger log = LoggerFactory.getLogger(PersonQueryService.class);

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public PersonQueryService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    /**
     * Return a {@link List} of {@link PersonDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PersonDTO> findByCriteria(PersonCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Person> specification = createSpecification(criteria);
        return personMapper.toDto(personRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PersonDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findByCriteria(PersonCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Person> specification = createSpecification(criteria);
        return personRepository.findAll(specification, page)
            .map(personMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PersonCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Person> specification = createSpecification(criteria);
        return personRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<Person> createSpecification(PersonCriteria criteria) {
        Specification<Person> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Person_.id));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), Person_.uuid));
            }
            if (criteria.getPermalink() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPermalink(), Person_.permalink));
            }
            if (criteria.getFirstname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstname(), Person_.firstname));
            }
            if (criteria.getLastname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastname(), Person_.lastname));
            }
            if (criteria.getAlsoknownas() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAlsoknownas(), Person_.alsoknownas));
            }
            if (criteria.getBio() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBio(), Person_.bio));
            }
            if (criteria.getProfileimageid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProfileimageid(), Person_.profileimageid));
            }
            if (criteria.getRoleinvestor() != null) {
                specification = specification.and(buildSpecification(criteria.getRoleinvestor(), Person_.roleinvestor));
            }
            if (criteria.getBornon() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBornon(), Person_.bornon));
            }
            if (criteria.getBornontrustcode() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBornontrustcode(), Person_.bornontrustcode));
            }
            if (criteria.getDiedon() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiedon(), Person_.diedon));
            }
            if (criteria.getCreatedat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedat(), Person_.createdat));
            }
            if (criteria.getUpdatedat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdatedat(), Person_.updatedat));
            }
            if (criteria.getPermalinkaliases() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPermalinkaliases(), Person_.permalinkaliases));
            }
            if (criteria.getGender() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGender(), Person_.gender));
            }
            if (criteria.getRank() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRank(), Person_.rank));
            }
            if (criteria.getPrimaryaffiliationid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrimaryaffiliationid(), Person_.primaryaffiliationid));
            }
            if (criteria.getPrimarylocationid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrimarylocationid(), Person_.primarylocationid));
            }
            if (criteria.getPrimaryimageid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrimaryimageid(), Person_.primaryimageid));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Person_.title));
            }
            if (criteria.getHomepageurl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHomepageurl(), Person_.homepageurl));
            }
            if (criteria.getFacebookurl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFacebookurl(), Person_.facebookurl));
            }
            if (criteria.getTwitterurl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTwitterurl(), Person_.twitterurl));
            }
            if (criteria.getLinkedinurl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLinkedinurl(), Person_.linkedinurl));
            }
            if (criteria.getCityname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCityname(), Person_.cityname));
            }
            if (criteria.getRegionname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRegionname(), Person_.regionname));
            }
            if (criteria.getCountrycode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCountrycode(), Person_.countrycode));
            }
        }
        return specification;
    }
}
