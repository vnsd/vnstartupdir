package com.vnsd.business.service.mapper;

import com.vnsd.business.domain.Company;
import com.vnsd.business.domain.Person;
import com.vnsd.business.domain.User;
import com.vnsd.business.service.dto.CompanyDTO;
import com.vnsd.business.service.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-08-25T19:23:28+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_191-1-redhat (Oracle Corporation)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Autowired
    private UserMapper userMapper;
/*
    @Override
    public List<Person> toEntity(List<PersonDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Person> list = new ArrayList<Person>( dtoList.size() );
        for ( PersonDTO personDTO : dtoList ) {
            list.add( toEntity( personDTO ) );
        }

        return list;
    }

 */

    @Override
    public List<PersonDTO> toDto(List<Person> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PersonDTO> list = new ArrayList<PersonDTO>( entityList.size() );
        for ( Person person : entityList ) {
            list.add( toDto( person ) );
        }

        return list;
    }

    @Override
    public PersonDTO toDto(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setAssignedToId( personAssignedToId( person ) );
        personDTO.setUpdatedById( personUpdatedById( person ) );
        personDTO.setCreatedById( personCreatedById( person ) );
        personDTO.setId( person.getId() );
        personDTO.setUuid( person.getUuid()==null ? null: person.getUuid().toString() );
        personDTO.setFirstname( person.getFirstname() );
        personDTO.setLastname( person.getLastname() );
        personDTO.setBornon( person.getBornon() );
        personDTO.setDiedon( person.getDiedon() );
        personDTO.setCreatedat( person.getCreatedat() );
        personDTO.setUpdatedat( person.getUpdatedat() );
        personDTO.setGender( person.getGender() );
        personDTO.setTitle( person.getTitle() );
        personDTO.setHomepageurl( person.getHomepageurl() );
        personDTO.setFacebookurl( person.getFacebookurl() );
        personDTO.setTwitterurl( person.getTwitterurl() );
        personDTO.setLinkedinurl( person.getLinkedinurl() );
        personDTO.setCityname( person.getCityname() );
        personDTO.setRegionname( person.getRegionname() );
        personDTO.setCountrycode( person.getCountrycode() );

        return personDTO;
    }
/*
    @Override
    public Person toEntity(PersonDTO personDTO) {
        if ( personDTO == null ) {
            return null;
        }

        Person person = new Person();

        person.setUpdatedBy( userMapper.userFromId( personDTO.getUpdatedById() ) );
        person.setCreatedBy( userMapper.userFromId( personDTO.getCreatedById() ) );
        person.setAssignedTo( userMapper.userFromId( personDTO.getAssignedToId() ) );
        person.setId( personDTO.getId() );
        person.setUuid( personDTO.getUuid() );
        person.setFirstname( personDTO.getFirstname() );
        person.setLastname( personDTO.getLastname() );
        person.setBornon( personDTO.getBornon() );
        person.setDiedon( personDTO.getDiedon() );
        person.setCreatedat( personDTO.getCreatedat() );
        person.setUpdatedat( personDTO.getUpdatedat() );
        person.setGender( personDTO.getGender() );
        person.setTitle( personDTO.getTitle() );
        person.setHomepageurl( personDTO.getHomepageurl() );
        person.setFacebookurl( personDTO.getFacebookurl() );
        person.setTwitterurl( personDTO.getTwitterurl() );
        person.setLinkedinurl( personDTO.getLinkedinurl() );
        person.setCityname( personDTO.getCityname() );
        person.setRegionname( personDTO.getRegionname() );
        person.setCountrycode( personDTO.getCountrycode() );

        return person;
    }

 */
    @Override
    public void update(Person person, PersonDTO personDTO, User updatedBy, Instant updatedAt) {
            /*
            company.setUpdatedBy( userMapper.userFromId( companyDTO.getUpdatedById() ) );
            company.setCreatedBy( userMapper.userFromId( companyDTO.getCreatedById() ) );
            company.setAssignedTo( userMapper.userFromId( companyDTO.getAssignedToId() ) );
            company.setId( companyDTO.getId() );
            company.setUuid( companyDTO.getUuid() );
            */

        person.setFirstname( personDTO.getFirstname() );
        person.setLastname( personDTO.getLastname() );
        person.setBornon( personDTO.getBornon() );
        person.setDiedon( personDTO.getDiedon() );
        person.setGender( personDTO.getGender() );
        person.setTitle( personDTO.getTitle() );
        person.setHomepageurl( personDTO.getHomepageurl() );
        person.setFacebookurl( personDTO.getFacebookurl() );
        person.setTwitterurl( personDTO.getTwitterurl() );
        person.setLinkedinurl( personDTO.getLinkedinurl() );
        person.setCityname( personDTO.getCityname() );
        person.setRegionname( personDTO.getRegionname() );
        person.setCountrycode( personDTO.getCountrycode() );

        person.setUpdatedat(updatedAt);
        person.setUpdatedBy(updatedBy);
    }

    private Long personAssignedToId(Person person) {
        if ( person == null ) {
            return null;
        }
        User assignedTo = person.getAssignedTo();
        if ( assignedTo == null ) {
            return null;
        }
        Long id = assignedTo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long personUpdatedById(Person person) {
        if ( person == null ) {
            return null;
        }
        User updatedBy = person.getUpdatedBy();
        if ( updatedBy == null ) {
            return null;
        }
        Long id = updatedBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long personCreatedById(Person person) {
        if ( person == null ) {
            return null;
        }
        User createdBy = person.getCreatedBy();
        if ( createdBy == null ) {
            return null;
        }
        Long id = createdBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
