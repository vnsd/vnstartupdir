package com.vnsd.business.service.impl;

import com.vnsd.business.domain.Company;
import com.vnsd.business.domain.User;
import com.vnsd.business.repository.UserRepository;
import com.vnsd.business.security.SecurityUtils;
import com.vnsd.business.service.PersonService;
import com.vnsd.business.domain.Person;
import com.vnsd.business.repository.PersonRepository;
import com.vnsd.business.service.dto.PersonDTO;
import com.vnsd.business.service.mapper.PersonMapper;
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
 * Service Implementation for managing {@link Person}.
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    private final PersonMapper personMapper;

    public PersonServiceImpl(PersonRepository personRepository,UserRepository userRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
        this.personMapper = personMapper;
    }

    /**
     * Save a person.
     *
     * @param personDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PersonDTO save(PersonDTO personDTO) {
        log.debug("Request to save Person : {}", personDTO);
        User currentUser = getCurrentUser().orElse(null);
        Person person;
        if (personDTO.getId() == null) { // Create
            person = new Person(UUID.randomUUID(), Instant.now(), currentUser);
        }else{ // Update
            person = personRepository.findById(personDTO.getId()).get();
        }
        personMapper.update(person, personDTO,  currentUser, Instant.now() );
        person = personRepository.save(person);
        return personMapper.toDto(person);
    }

    private Optional<User> getCurrentUser(){
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin);
    }

    /**
     * Get all the people.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PersonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all People");
        return personRepository.findAll(pageable)
            .map(personMapper::toDto);
    }


    /**
     * Get one person by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PersonDTO> findOne(Long id) {
        log.debug("Request to get Person : {}", id);
        return personRepository.findById(id)
            .map(personMapper::toDto);
    }

    /**
     * Delete the person by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Person : {}", id);
        personRepository.deleteById(id);
    }
}
