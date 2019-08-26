package com.vnsd.business.service.mapper;

import com.vnsd.business.domain.*;
import com.vnsd.business.service.dto.PersonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Person} and its DTO {@link PersonDTO}.
 */
//@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "updatedBy.id", target = "updatedById")
    @Mapping(source = "assignedTo.id", target = "assignedToId")
    PersonDTO toDto(Person person);

    @Mapping(target = "companies", ignore = true)
    @Mapping(target = "removeCompanies", ignore = true)
    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "updatedById", target = "updatedBy")
    @Mapping(source = "assignedToId", target = "assignedTo")
    //Person toEntity(PersonDTO personDTO);

    default Person fromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }
}
