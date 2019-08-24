package com.vnsd.business.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vnsd.business.domain.Person} entity.
 */
public class PersonDTO implements Serializable {

    private Long id;

    @NotNull
    private String uuid;

    @NotNull
    private String permalink;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    private String alsoknownas;

    private String bio;

    private Integer profileimageid;

    private Boolean roleinvestor;

    private Instant bornon;

    private Integer bornontrustcode;

    private Instant diedon;

    private Instant createdat;

    private Instant updatedat;

    private String permalinkaliases;

    private String gender;

    private Integer rank;

    private Integer primaryaffiliationid;

    private Integer primarylocationid;

    private Integer primaryimageid;

    private String title;

    private String homepageurl;

    private String facebookurl;

    private String twitterurl;

    private String linkedinurl;

    private String cityname;

    private String regionname;

    private String countrycode;


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

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAlsoknownas() {
        return alsoknownas;
    }

    public void setAlsoknownas(String alsoknownas) {
        this.alsoknownas = alsoknownas;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getProfileimageid() {
        return profileimageid;
    }

    public void setProfileimageid(Integer profileimageid) {
        this.profileimageid = profileimageid;
    }

    public Boolean isRoleinvestor() {
        return roleinvestor;
    }

    public void setRoleinvestor(Boolean roleinvestor) {
        this.roleinvestor = roleinvestor;
    }

    public Instant getBornon() {
        return bornon;
    }

    public void setBornon(Instant bornon) {
        this.bornon = bornon;
    }

    public Integer getBornontrustcode() {
        return bornontrustcode;
    }

    public void setBornontrustcode(Integer bornontrustcode) {
        this.bornontrustcode = bornontrustcode;
    }

    public Instant getDiedon() {
        return diedon;
    }

    public void setDiedon(Instant diedon) {
        this.diedon = diedon;
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

    public String getPermalinkaliases() {
        return permalinkaliases;
    }

    public void setPermalinkaliases(String permalinkaliases) {
        this.permalinkaliases = permalinkaliases;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPrimaryaffiliationid() {
        return primaryaffiliationid;
    }

    public void setPrimaryaffiliationid(Integer primaryaffiliationid) {
        this.primaryaffiliationid = primaryaffiliationid;
    }

    public Integer getPrimarylocationid() {
        return primarylocationid;
    }

    public void setPrimarylocationid(Integer primarylocationid) {
        this.primarylocationid = primarylocationid;
    }

    public Integer getPrimaryimageid() {
        return primaryimageid;
    }

    public void setPrimaryimageid(Integer primaryimageid) {
        this.primaryimageid = primaryimageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonDTO personDTO = (PersonDTO) o;
        if (personDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", permalink='" + getPermalink() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", alsoknownas='" + getAlsoknownas() + "'" +
            ", bio='" + getBio() + "'" +
            ", profileimageid=" + getProfileimageid() +
            ", roleinvestor='" + isRoleinvestor() + "'" +
            ", bornon='" + getBornon() + "'" +
            ", bornontrustcode=" + getBornontrustcode() +
            ", diedon='" + getDiedon() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            ", permalinkaliases='" + getPermalinkaliases() + "'" +
            ", gender='" + getGender() + "'" +
            ", rank=" + getRank() +
            ", primaryaffiliationid=" + getPrimaryaffiliationid() +
            ", primarylocationid=" + getPrimarylocationid() +
            ", primaryimageid=" + getPrimaryimageid() +
            ", title='" + getTitle() + "'" +
            ", homepageurl='" + getHomepageurl() + "'" +
            ", facebookurl='" + getFacebookurl() + "'" +
            ", twitterurl='" + getTwitterurl() + "'" +
            ", linkedinurl='" + getLinkedinurl() + "'" +
            ", cityname='" + getCityname() + "'" +
            ", regionname='" + getRegionname() + "'" +
            ", countrycode='" + getCountrycode() + "'" +
            "}";
    }
}
