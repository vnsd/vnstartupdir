package com.vnsd.business.repository;

import com.vnsd.business.domain.Company;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Company entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {

    @Query("select company from Company company where company.createdBy.login = ?#{principal.username}")
    List<Company> findByCreatedByIsCurrentUser();

    @Query("select company from Company company where company.updatedBy.login = ?#{principal.username}")
    List<Company> findByUpdatedByIsCurrentUser();

    @Query("select company from Company company where company.assignedTo.login = ?#{principal.username}")
    List<Company> findByAssignedToIsCurrentUser();

}
