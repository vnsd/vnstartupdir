package com.vnsd.business.service.impl;

import com.vnsd.business.domain.PersonCompanyRelation;
import com.vnsd.business.domain.User;
import com.vnsd.business.repository.PersonCompanyRelationRepository;
import com.vnsd.business.repository.PersonRepository;
import com.vnsd.business.security.SecurityUtils;
import com.vnsd.business.service.CompanyService;
import com.vnsd.business.domain.Company;
import com.vnsd.business.repository.CompanyRepository;
import com.vnsd.business.repository.UserRepository;
import com.vnsd.business.service.dto.CompanyDTO;
import com.vnsd.business.service.mapper.CompanyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation for managing {@link Company}.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;

    private final PersonRepository personRepository;

    private final PersonCompanyRelationRepository relationRepository;

    private final UserRepository userRepository;

    private final CompanyMapper companyMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, PersonRepository personRepository,
                              PersonCompanyRelationRepository relationRepository, UserRepository userRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.personRepository = personRepository;
        this.relationRepository = relationRepository;
        this.userRepository = userRepository;
        this.companyMapper = companyMapper;
    }

    /**
     * Save a company.
     *
     * @param companyDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        log.debug("Request to save Company : {}", companyDTO);
        User currentUser = getCurrentUser().orElse(null);
        final Company company;
        if (companyDTO.getId() == null) { // Create
            company = new Company(UUID.randomUUID(), Instant.now(), currentUser);
        } else { // Update
            company = companyRepository.findById(companyDTO.getId()).get();
            relationRepository.deleteAll(company.getPeople());
        }
        companyMapper.update(company, companyDTO, currentUser, Instant.now());

        // Relation
        companyDTO.getPeople().forEach((r, ps) -> ps.forEach(p -> relationRepository.save(new PersonCompanyRelation(r, company, personRepository.findById(p).get()))));

        Company newCompany = companyRepository.save(company);
        return companyMapper.toDto(newCompany);
    }

    private Optional<User> getCurrentUser() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin);
    }

    /**
     * Get all the companies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CompanyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Companies");
        return companyRepository.findAll(pageable)
            .map(companyMapper::toDto);
    }


    /**
     * Get one company by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CompanyDTO> findOne(Long id) {
        log.debug("Request to get Company : {}", id);
        return companyRepository.findById(id)
            .map(companyMapper::toDto);
    }

    /**
     * Delete the company by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Company : {}", id);
        companyRepository.deleteById(id);
    }
}
