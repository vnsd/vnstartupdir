package com.vnsd.business.service.mapper;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.vnsd.business.domain.Company;
import com.vnsd.business.domain.User;
import com.vnsd.business.service.dto.CompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-08-25T16:55:17+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_191-1-redhat (Oracle Corporation)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Autowired
    private UserMapper userMapper;
/*
    @Override
    public List<Company> toEntity(List<CompanyDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Company> list = new ArrayList<Company>( dtoList.size() );
        for ( CompanyDTO companyDTO : dtoList ) {
            list.add( toEntity( companyDTO ) );
        }

        return list;
    }
 */

    @Override
    public List<CompanyDTO> toDto(List<Company> entityList) {
        if (entityList == null) {
            return null;
        }

        List<CompanyDTO> list = new ArrayList<CompanyDTO>(entityList.size());
        for (Company company : entityList) {
            list.add(toDto(company));
        }

        return list;
    }

    @Override
    public CompanyDTO toDto(Company company) {
        if (company == null) {
            return null;
        }

        CompanyDTO companyDTO = new CompanyDTO();

        companyDTO.setAssignedToId(companyAssignedToId(company));
        companyDTO.setUpdatedById(companyUpdatedById(company));
        companyDTO.setCreatedById(companyCreatedById(company));
        companyDTO.setId(company.getId());
        companyDTO.setUuid(company.getUuid()==null ? null:company.getUuid().toString());
        companyDTO.setType(company.getType());
        companyDTO.setName(company.getName());
        companyDTO.setShortdescription(company.getShortdescription());
        companyDTO.setDescription(company.getDescription());
        companyDTO.setFoundedon(company.getFoundedon());
        companyDTO.setClosedon(company.getClosedon());
        companyDTO.setNumemployeesmin(company.getNumemployeesmin());
        companyDTO.setNumemployeesmax(company.getNumemployeesmax());
        companyDTO.setTotalfundingusd(company.getTotalfundingusd());
        companyDTO.setTotalfundingvnd(company.getTotalfundingvnd());
        companyDTO.setNumberofinvestments(company.getNumberofinvestments());
        companyDTO.setCreatedat(company.getCreatedat());
        companyDTO.setUpdatedat(company.getUpdatedat());
        companyDTO.setContactemail(company.getContactemail());
        companyDTO.setPhonenumber(company.getPhonenumber());
        companyDTO.setHomepageurl(company.getHomepageurl());
        companyDTO.setFacebookurl(company.getFacebookurl());
        companyDTO.setTwitterurl(company.getTwitterurl());
        companyDTO.setLinkedinurl(company.getLinkedinurl());
        companyDTO.setCityname(company.getCityname());
        companyDTO.setRegionname(company.getRegionname());
        companyDTO.setCountrycode(company.getCountrycode());

        Multimap<String, Long> people = HashMultimap.create();
        company.getPeople().stream().forEach(p -> people.put(p.getRelationCode(), p.getPerson().getId()));
        companyDTO.setPeople(people.asMap());

        return companyDTO;
    }
/*
    @Override
    public Company toEntity(CompanyDTO companyDTO) {
        if ( companyDTO == null ) {
            return null;
        }

        Company company = new Company();

        company.setUpdatedBy( userMapper.userFromId( companyDTO.getUpdatedById() ) );
        company.setCreatedBy( userMapper.userFromId( companyDTO.getCreatedById() ) );
        company.setAssignedTo( userMapper.userFromId( companyDTO.getAssignedToId() ) );
        company.setId( companyDTO.getId() );
        company.setUuid( companyDTO.getUuid() );
        company.setType( companyDTO.getType() );
        company.setName( companyDTO.getName() );
        company.setShortdescription( companyDTO.getShortdescription() );
        company.setDescription( companyDTO.getDescription() );
        company.setFoundedon( companyDTO.getFoundedon() );
        company.setClosedon( companyDTO.getClosedon() );
        company.setNumemployeesmin( companyDTO.getNumemployeesmin() );
        company.setNumemployeesmax( companyDTO.getNumemployeesmax() );
        company.setTotalfundingusd( companyDTO.getTotalfundingusd() );
        company.setTotalfundingvnd( companyDTO.getTotalfundingvnd() );
        company.setNumberofinvestments( companyDTO.getNumberofinvestments() );
        company.setCreatedat( companyDTO.getCreatedat() );
        company.setUpdatedat( companyDTO.getUpdatedat() );
        company.setContactemail( companyDTO.getContactemail() );
        company.setPhonenumber( companyDTO.getPhonenumber() );
        company.setHomepageurl( companyDTO.getHomepageurl() );
        company.setFacebookurl( companyDTO.getFacebookurl() );
        company.setTwitterurl( companyDTO.getTwitterurl() );
        company.setLinkedinurl( companyDTO.getLinkedinurl() );
        company.setCityname( companyDTO.getCityname() );
        company.setRegionname( companyDTO.getRegionname() );
        company.setCountrycode( companyDTO.getCountrycode() );

        return company;
    }
 */

    @Override
    public void update(Company company, CompanyDTO companyDTO, User updatedBy, Instant updatedAt) {
        /*
        company.setUpdatedBy( userMapper.userFromId( companyDTO.getUpdatedById() ) );
        company.setCreatedBy( userMapper.userFromId( companyDTO.getCreatedById() ) );
        company.setAssignedTo( userMapper.userFromId( companyDTO.getAssignedToId() ) );
        company.setId( companyDTO.getId() );
        company.setUuid( companyDTO.getUuid() );
        */
        company.setType(companyDTO.getType());
        company.setName(companyDTO.getName());
        company.setShortdescription(companyDTO.getShortdescription());
        company.setDescription(companyDTO.getDescription());
        company.setFoundedon(companyDTO.getFoundedon());
        company.setClosedon(companyDTO.getClosedon());
        company.setNumemployeesmin(companyDTO.getNumemployeesmin());
        company.setNumemployeesmax(companyDTO.getNumemployeesmax());
        company.setTotalfundingusd(companyDTO.getTotalfundingusd());
        company.setTotalfundingvnd(companyDTO.getTotalfundingvnd());
        company.setNumberofinvestments(companyDTO.getNumberofinvestments());
        company.setCreatedat(companyDTO.getCreatedat());
        company.setUpdatedat(companyDTO.getUpdatedat());
        company.setContactemail(companyDTO.getContactemail());
        company.setPhonenumber(companyDTO.getPhonenumber());
        company.setHomepageurl(companyDTO.getHomepageurl());
        company.setFacebookurl(companyDTO.getFacebookurl());
        company.setTwitterurl(companyDTO.getTwitterurl());
        company.setLinkedinurl(companyDTO.getLinkedinurl());
        company.setCityname(companyDTO.getCityname());
        company.setRegionname(companyDTO.getRegionname());
        company.setCountrycode(companyDTO.getCountrycode());

        company.setUpdatedat(updatedAt);
        company.setUpdatedBy(updatedBy);
    }

    private Long companyAssignedToId(Company company) {
        if (company == null) {
            return null;
        }
        User assignedTo = company.getAssignedTo();
        if (assignedTo == null) {
            return null;
        }
        Long id = assignedTo.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long companyUpdatedById(Company company) {
        if (company == null) {
            return null;
        }
        User updatedBy = company.getUpdatedBy();
        if (updatedBy == null) {
            return null;
        }
        Long id = updatedBy.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long companyCreatedById(Company company) {
        if (company == null) {
            return null;
        }
        User createdBy = company.getCreatedBy();
        if (createdBy == null) {
            return null;
        }
        Long id = createdBy.getId();
        if (id == null) {
            return null;
        }
        return id;
    }
}
