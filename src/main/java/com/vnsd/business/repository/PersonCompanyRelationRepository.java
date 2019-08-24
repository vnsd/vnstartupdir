package com.vnsd.business.repository;

import com.vnsd.business.domain.PersonCompanyRelation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PersonCompanyRelation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonCompanyRelationRepository extends JpaRepository<PersonCompanyRelation, Long> {

}
