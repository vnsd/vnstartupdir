package com.vnsd.business.web.rest;

import com.vnsd.business.domain.PersonCompanyRelation;
import com.vnsd.business.repository.PersonCompanyRelationRepository;
import com.vnsd.business.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.vnsd.business.domain.PersonCompanyRelation}.
 */
@RestController
@RequestMapping("/api")
public class PersonCompanyRelationResource {

    private final Logger log = LoggerFactory.getLogger(PersonCompanyRelationResource.class);

    private static final String ENTITY_NAME = "personCompanyRelation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonCompanyRelationRepository personCompanyRelationRepository;

    public PersonCompanyRelationResource(PersonCompanyRelationRepository personCompanyRelationRepository) {
        this.personCompanyRelationRepository = personCompanyRelationRepository;
    }

    /**
     * {@code POST  /person-company-relations} : Create a new personCompanyRelation.
     *
     * @param personCompanyRelation the personCompanyRelation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personCompanyRelation, or with status {@code 400 (Bad Request)} if the personCompanyRelation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/person-company-relations")
    public ResponseEntity<PersonCompanyRelation> createPersonCompanyRelation(@RequestBody PersonCompanyRelation personCompanyRelation) throws URISyntaxException {
        log.debug("REST request to save PersonCompanyRelation : {}", personCompanyRelation);
        if (personCompanyRelation.getId() != null) {
            throw new BadRequestAlertException("A new personCompanyRelation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonCompanyRelation result = personCompanyRelationRepository.save(personCompanyRelation);
        return ResponseEntity.created(new URI("/api/person-company-relations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /person-company-relations} : Updates an existing personCompanyRelation.
     *
     * @param personCompanyRelation the personCompanyRelation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personCompanyRelation,
     * or with status {@code 400 (Bad Request)} if the personCompanyRelation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personCompanyRelation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/person-company-relations")
    public ResponseEntity<PersonCompanyRelation> updatePersonCompanyRelation(@RequestBody PersonCompanyRelation personCompanyRelation) throws URISyntaxException {
        log.debug("REST request to update PersonCompanyRelation : {}", personCompanyRelation);
        if (personCompanyRelation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersonCompanyRelation result = personCompanyRelationRepository.save(personCompanyRelation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personCompanyRelation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /person-company-relations} : get all the personCompanyRelations.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personCompanyRelations in body.
     */
    @GetMapping("/person-company-relations")
    public List<PersonCompanyRelation> getAllPersonCompanyRelations() {
        log.debug("REST request to get all PersonCompanyRelations");
        return personCompanyRelationRepository.findAll();
    }

    /**
     * {@code GET  /person-company-relations/:id} : get the "id" personCompanyRelation.
     *
     * @param id the id of the personCompanyRelation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personCompanyRelation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/person-company-relations/{id}")
    public ResponseEntity<PersonCompanyRelation> getPersonCompanyRelation(@PathVariable Long id) {
        log.debug("REST request to get PersonCompanyRelation : {}", id);
        Optional<PersonCompanyRelation> personCompanyRelation = personCompanyRelationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(personCompanyRelation);
    }

    /**
     * {@code DELETE  /person-company-relations/:id} : delete the "id" personCompanyRelation.
     *
     * @param id the id of the personCompanyRelation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/person-company-relations/{id}")
    public ResponseEntity<Void> deletePersonCompanyRelation(@PathVariable Long id) {
        log.debug("REST request to delete PersonCompanyRelation : {}", id);
        personCompanyRelationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
