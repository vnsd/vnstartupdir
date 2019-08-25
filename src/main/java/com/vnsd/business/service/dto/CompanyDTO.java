package com.vnsd.business.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnsd.business.domain.Company} entity.
 */
public class CompanyDTO implements Serializable {

    private Long id;

    @NotNull
    private String uuid;

    @NotNull
    private String type;

    @NotNull
    private String name;

    private String shortdescription;

    private String description;

    private Instant foundedon;

    private Instant closedon;

    private Integer numemployeesmin;

    private Integer numemployeesmax;

    private Integer totalfundingusd;

    private Integer totalfundingvnd;

    private Integer numberofinvestments;

    private Instant createdat;

    private Instant updatedat;

    private String contactemail;

    private String phonenumber;

    private String homepageurl;

    private String facebookurl;

    private String twitterurl;

    private String linkedinurl;

    private String cityname;

    private String regionname;

    private String countrycode;


    private Long createdById;

    private Long updatedById;

    private Long assignedToId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getFoundedon() {
        return foundedon;
    }

    public void setFoundedon(Instant foundedon) {
        this.foundedon = foundedon;
    }

    public Instant getClosedon() {
        return closedon;
    }

    public void setClosedon(Instant closedon) {
        this.closedon = closedon;
    }

    public Integer getNumemployeesmin() {
        return numemployeesmin;
    }

    public void setNumemployeesmin(Integer numemployeesmin) {
        this.numemployeesmin = numemployeesmin;
    }

    public Integer getNumemployeesmax() {
        return numemployeesmax;
    }

    public void setNumemployeesmax(Integer numemployeesmax) {
        this.numemployeesmax = numemployeesmax;
    }

    public Integer getTotalfundingusd() {
        return totalfundingusd;
    }

    public void setTotalfundingusd(Integer totalfundingusd) {
        this.totalfundingusd = totalfundingusd;
    }

    public Integer getTotalfundingvnd() {
        return totalfundingvnd;
    }

    public void setTotalfundingvnd(Integer totalfundingvnd) {
        this.totalfundingvnd = totalfundingvnd;
    }

    public Integer getNumberofinvestments() {
        return numberofinvestments;
    }

    public void setNumberofinvestments(Integer numberofinvestments) {
        this.numberofinvestments = numberofinvestments;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public String getContactemail() {
        return contactemail;
    }

    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getHomepageurl() {
        return homepageurl;
    }

    public void setHomepageurl(String homepageurl) {
        this.homepageurl = homepageurl;
    }

    public String getFacebookurl() {
        return facebookurl;
    }

    public void setFacebookurl(String facebookurl) {
        this.facebookurl = facebookurl;
    }

    public String getTwitterurl() {
        return twitterurl;
    }

    public void setTwitterurl(String twitterurl) {
        this.twitterurl = twitterurl;
    }

    public String getLinkedinurl() {
        return linkedinurl;
    }

    public void setLinkedinurl(String linkedinurl) {
        this.linkedinurl = linkedinurl;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long userId) {
        this.createdById = userId;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long userId) {
        this.updatedById = userId;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long userId) {
        this.assignedToId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompanyDTO companyDTO = (CompanyDTO) o;
        if (companyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), companyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", type='" + getType() + "'" +
            ", name='" + getName() + "'" +
            ", shortdescription='" + getShortdescription() + "'" +
            ", description='" + getDescription() + "'" +
            ", foundedon='" + getFoundedon() + "'" +
            ", closedon='" + getClosedon() + "'" +
            ", numemployeesmin=" + getNumemployeesmin() +
            ", numemployeesmax=" + getNumemployeesmax() +
            ", totalfundingusd=" + getTotalfundingusd() +
            ", totalfundingvnd=" + getTotalfundingvnd() +
            ", numberofinvestments=" + getNumberofinvestments() +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            ", contactemail='" + getContactemail() + "'" +
            ", phonenumber='" + getPhonenumber() + "'" +
            ", homepageurl='" + getHomepageurl() + "'" +
            ", facebookurl='" + getFacebookurl() + "'" +
            ", twitterurl='" + getTwitterurl() + "'" +
            ", linkedinurl='" + getLinkedinurl() + "'" +
            ", cityname='" + getCityname() + "'" +
            ", regionname='" + getRegionname() + "'" +
            ", countrycode='" + getCountrycode() + "'" +
            ", createdBy=" + getCreatedById() +
            ", updatedBy=" + getUpdatedById() +
            ", assignedTo=" + getAssignedToId() +
            "}";
    }
}
