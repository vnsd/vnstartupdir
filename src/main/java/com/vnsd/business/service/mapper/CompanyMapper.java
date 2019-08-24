package com.vnsd.business.service.mapper;

import com.vnsd.business.domain.*;
import com.vnsd.business.service.dto.CompanyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Company} and its DTO {@link CompanyDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CompanyMapper extends EntityMapper<CompanyDTO, Company> {

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "assignee.id", target = "assigneeId")
    CompanyDTO toDto(Company company);

    @Mapping(source = "ownerId", target = "owner")
    @Mapping(source = "assigneeId", target = "assignee")
    Company toEntity(CompanyDTO companyDTO);

    default Company fromId(Long id) {
        if (id == null) {
            return null;
        }
        Company company = new Company();
        company.setId(id);
        return company;
    }
}
