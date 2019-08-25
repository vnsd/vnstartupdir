package com.vnsd.business.repository;

import com.vnsd.business.domain.Person;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Person entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    @Query("select person from Person person where person.createdBy.login = ?#{principal.username}")
    List<Person> findByCreatedByIsCurrentUser();

    @Query("select person from Person person where person.updatedBy.login = ?#{principal.username}")
    List<Person> findByUpdatedByIsCurrentUser();

    @Query("select person from Person person where person.assignedTo.login = ?#{principal.username}")
    List<Person> findByAssignedToIsCurrentUser();

}
