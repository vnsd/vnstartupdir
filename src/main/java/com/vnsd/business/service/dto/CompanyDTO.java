package com.vnsd.business.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.vnsd.business.domain.Company} entity.
 */
public class CompanyDTO implements Serializable {

    private Long id;

    @NotNull
    private UUID uuid;

    @NotNull
    private String permalink;

    @NotNull
    private String name;

    private String alsoknownas;

    private String shortdescription;

    private String description;

    private Integer profileimageid;

    private String primaryrole;

    private Instant foundedon;

    private Integer foundedontrustcode;

    private Instant closedon;

    private Integer numemployeesmin;

    private Integer numemployeesmax;

    private Integer totalfundingusd;

    private Integer totalfundingvnd;

    private String stockexchange;

    private String stocksymbol;

    private Integer numberofinvestments;

    private Instant createdat;

    private Instant updatedat;

    private String permalinkaliases;

    private String investortype;

    private String contactemail;

    private String phonenumber;

    private Integer rank;

    private Integer primaryimageid;

    private Integer ownedbyid;

    private Integer headquartersid;

    private Integer acquiredbyid;

    private Integer ipoid;

    private String homepageurl;

    private String facebookurl;

    private String twitterurl;

    private String linkedinurl;

    private String cityname;

    private String regionname;

    private String countrycode;


    private Long ownerId;

    private Long assigneeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlsoknownas() {
        return alsoknownas;
    }

    public void setAlsoknownas(String alsoknownas) {
        this.alsoknownas = alsoknownas;
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

    public Integer getProfileimageid() {
        return profileimageid;
    }

    public void setProfileimageid(Integer profileimageid) {
        this.profileimageid = profileimageid;
    }

    public String getPrimaryrole() {
        return primaryrole;
    }

    public void setPrimaryrole(String primaryrole) {
        this.primaryrole = primaryrole;
    }

    public Instant getFoundedon() {
        return foundedon;
    }

    public void setFoundedon(Instant foundedon) {
        this.foundedon = foundedon;
    }

    public Integer getFoundedontrustcode() {
        return foundedontrustcode;
    }

    public void setFoundedontrustcode(Integer foundedontrustcode) {
        this.foundedontrustcode = foundedontrustcode;
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

    public String getStockexchange() {
        return stockexchange;
    }

    public void setStockexchange(String stockexchange) {
        this.stockexchange = stockexchange;
    }

    public String getStocksymbol() {
        return stocksymbol;
    }

    public void setStocksymbol(String stocksymbol) {
        this.stocksymbol = stocksymbol;
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

    public String getPermalinkaliases() {
        return permalinkaliases;
    }

    public void setPermalinkaliases(String permalinkaliases) {
        this.permalinkaliases = permalinkaliases;
    }

    public String getInvestortype() {
        return investortype;
    }

    public void setInvestortype(String investortype) {
        this.investortype = investortype;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPrimaryimageid() {
        return primaryimageid;
    }

    public void setPrimaryimageid(Integer primaryimageid) {
        this.primaryimageid = primaryimageid;
    }

    public Integer getOwnedbyid() {
        return ownedbyid;
    }

    public void setOwnedbyid(Integer ownedbyid) {
        this.ownedbyid = ownedbyid;
    }

    public Integer getHeadquartersid() {
        return headquartersid;
    }

    public void setHeadquartersid(Integer headquartersid) {
        this.headquartersid = headquartersid;
    }

    public Integer getAcquiredbyid() {
        return acquiredbyid;
    }

    public void setAcquiredbyid(Integer acquiredbyid) {
        this.acquiredbyid = acquiredbyid;
    }

    public Integer getIpoid() {
        return ipoid;
    }

    public void setIpoid(Integer ipoid) {
        this.ipoid = ipoid;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long userId) {
        this.ownerId = userId;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long userId) {
        this.assigneeId = userId;
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
            ", permalink='" + getPermalink() + "'" +
            ", name='" + getName() + "'" +
            ", alsoknownas='" + getAlsoknownas() + "'" +
            ", shortdescription='" + getShortdescription() + "'" +
            ", description='" + getDescription() + "'" +
            ", profileimageid=" + getProfileimageid() +
            ", primaryrole='" + getPrimaryrole() + "'" +
            ", foundedon='" + getFoundedon() + "'" +
            ", foundedontrustcode=" + getFoundedontrustcode() +
            ", closedon='" + getClosedon() + "'" +
            ", numemployeesmin=" + getNumemployeesmin() +
            ", numemployeesmax=" + getNumemployeesmax() +
            ", totalfundingusd=" + getTotalfundingusd() +
            ", totalfundingvnd=" + getTotalfundingvnd() +
            ", stockexchange='" + getStockexchange() + "'" +
            ", stocksymbol='" + getStocksymbol() + "'" +
            ", numberofinvestments=" + getNumberofinvestments() +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            ", permalinkaliases='" + getPermalinkaliases() + "'" +
            ", investortype='" + getInvestortype() + "'" +
            ", contactemail='" + getContactemail() + "'" +
            ", phonenumber='" + getPhonenumber() + "'" +
            ", rank=" + getRank() +
            ", primaryimageid=" + getPrimaryimageid() +
            ", ownedbyid=" + getOwnedbyid() +
            ", headquartersid=" + getHeadquartersid() +
            ", acquiredbyid=" + getAcquiredbyid() +
            ", ipoid=" + getIpoid() +
            ", homepageurl='" + getHomepageurl() + "'" +
            ", facebookurl='" + getFacebookurl() + "'" +
            ", twitterurl='" + getTwitterurl() + "'" +
            ", linkedinurl='" + getLinkedinurl() + "'" +
            ", cityname='" + getCityname() + "'" +
            ", regionname='" + getRegionname() + "'" +
            ", countrycode='" + getCountrycode() + "'" +
            ", owner=" + getOwnerId() +
            ", assignee=" + getAssigneeId() +
            "}";
    }
}
