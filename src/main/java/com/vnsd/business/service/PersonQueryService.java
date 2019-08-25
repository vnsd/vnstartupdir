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
            if (criteria.getFirstname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstname(), Person_.firstname));
            }
            if (criteria.getLastname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastname(), Person_.lastname));
            }
            if (criteria.getBornon() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBornon(), Person_.bornon));
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
            if (criteria.getGender() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGender(), Person_.gender));
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
            if (criteria.getCompaniesId() != null) {
                specification = specification.and(buildSpecification(criteria.getCompaniesId(),
                    root -> root.join(Person_.companies, JoinType.LEFT).get(PersonCompanyRelation_.id)));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(Person_.createdBy, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getUpdatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getUpdatedById(),
                    root -> root.join(Person_.updatedBy, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getAssignedToId() != null) {
                specification = specification.and(buildSpecification(criteria.getAssignedToId(),
                    root -> root.join(Person_.assignedTo, JoinType.LEFT).get(User_.id)));
            }
        }
        return specification;
    }
}
