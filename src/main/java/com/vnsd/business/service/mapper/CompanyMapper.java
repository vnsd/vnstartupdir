package com.vnsd.business.service.mapper;

import com.vnsd.business.domain.*;
import com.vnsd.business.service.dto.CompanyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Company} and its DTO {@link CompanyDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CompanyMapper extends EntityMapper<CompanyDTO, Company> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "updatedBy.id", target = "updatedById")
    @Mapping(source = "assignedTo.id", target = "assignedToId")
    CompanyDTO toDto(Company company);

    @Mapping(target = "people", ignore = true)
    @Mapping(target = "removePeople", ignore = true)
    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "updatedById", target = "updatedBy")
    @Mapping(source = "assignedToId", target = "assignedTo")
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
