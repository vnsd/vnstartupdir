package com.vnsd.business.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @NotNull
    @Column(name = "permalink", nullable = false)
    private String permalink;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "alsoknownas")
    private String alsoknownas;

    @Column(name = "shortdescription")
    private String shortdescription;

    @Column(name = "description")
    private String description;

    @Column(name = "profileimageid")
    private Integer profileimageid;

    @Column(name = "primaryrole")
    private String primaryrole;

    @Column(name = "foundedon")
    private Instant foundedon;

    @Column(name = "foundedontrustcode")
    private Integer foundedontrustcode;

    @Column(name = "closedon")
    private Instant closedon;

    @Column(name = "numemployeesmin")
    private Integer numemployeesmin;

    @Column(name = "numemployeesmax")
    private Integer numemployeesmax;

    @Column(name = "totalfundingusd")
    private Integer totalfundingusd;

    @Column(name = "totalfundingvnd")
    private Integer totalfundingvnd;

    @Column(name = "stockexchange")
    private String stockexchange;

    @Column(name = "stocksymbol")
    private String stocksymbol;

    @Column(name = "numberofinvestments")
    private Integer numberofinvestments;

    @Column(name = "createdat")
    private Instant createdat;

    @Column(name = "updatedat")
    private Instant updatedat;

    @Column(name = "permalinkaliases")
    private String permalinkaliases;

    @Column(name = "investortype")
    private String investortype;

    @Column(name = "contactemail")
    private String contactemail;

    @Column(name = "phonenumber")
    private String phonenumber;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "primaryimageid")
    private Integer primaryimageid;

    @Column(name = "ownedbyid")
    private Integer ownedbyid;

    @Column(name = "headquartersid")
    private Integer headquartersid;

    @Column(name = "acquiredbyid")
    private Integer acquiredbyid;

    @Column(name = "ipoid")
    private Integer ipoid;

    @Column(name = "homepageurl")
    private String homepageurl;

    @Column(name = "facebookurl")
    private String facebookurl;

    @Column(name = "twitterurl")
    private String twitterurl;

    @Column(name = "linkedinurl")
    private String linkedinurl;

    @Column(name = "cityname")
    private String cityname;

    @Column(name = "regionname")
    private String regionname;

    @Column(name = "countrycode")
    private String countrycode;

    @OneToMany(mappedBy = "company")
    private Set<PersonCompanyRelation> people = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("companies")
    private User owner;

    @ManyToOne
    @JsonIgnoreProperties("companies")
    private User assignee;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public Company uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPermalink() {
        return permalink;
    }

    public Company permalink(String permalink) {
        this.permalink = permalink;
        return this;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getName() {
        return name;
    }

    public Company name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlsoknownas() {
        return alsoknownas;
    }

    public Company alsoknownas(String alsoknownas) {
        this.alsoknownas = alsoknownas;
        return this;
    }

    public void setAlsoknownas(String alsoknownas) {
        this.alsoknownas = alsoknownas;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public Company shortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
        return this;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getDescription() {
        return description;
    }

    public Company description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProfileimageid() {
        return profileimageid;
    }

    public Company profileimageid(Integer profileimageid) {
        this.profileimageid = profileimageid;
        return this;
    }

    public void setProfileimageid(Integer profileimageid) {
        this.profileimageid = profileimageid;
    }

    public String getPrimaryrole() {
        return primaryrole;
    }

    public Company primaryrole(String primaryrole) {
        this.primaryrole = primaryrole;
        return this;
    }

    public void setPrimaryrole(String primaryrole) {
        this.primaryrole = primaryrole;
    }

    public Instant getFoundedon() {
        return foundedon;
    }

    public Company foundedon(Instant foundedon) {
        this.foundedon = foundedon;
        return this;
    }

    public void setFoundedon(Instant foundedon) {
        this.foundedon = foundedon;
    }

    public Integer getFoundedontrustcode() {
        return foundedontrustcode;
    }

    public Company foundedontrustcode(Integer foundedontrustcode) {
        this.foundedontrustcode = foundedontrustcode;
        return this;
    }

    public void setFoundedontrustcode(Integer foundedontrustcode) {
        this.foundedontrustcode = foundedontrustcode;
    }

    public Instant getClosedon() {
        return closedon;
    }

    public Company closedon(Instant closedon) {
        this.closedon = closedon;
        return this;
    }

    public void setClosedon(Instant closedon) {
        this.closedon = closedon;
    }

    public Integer getNumemployeesmin() {
        return numemployeesmin;
    }

    public Company numemployeesmin(Integer numemployeesmin) {
        this.numemployeesmin = numemployeesmin;
        return this;
    }

    public void setNumemployeesmin(Integer numemployeesmin) {
        this.numemployeesmin = numemployeesmin;
    }

    public Integer getNumemployeesmax() {
        return numemployeesmax;
    }

    public Company numemployeesmax(Integer numemployeesmax) {
        this.numemployeesmax = numemployeesmax;
        return this;
    }

    public void setNumemployeesmax(Integer numemployeesmax) {
        this.numemployeesmax = numemployeesmax;
    }

    public Integer getTotalfundingusd() {
        return totalfundingusd;
    }

    public Company totalfundingusd(Integer totalfundingusd) {
        this.totalfundingusd = totalfundingusd;
        return this;
    }

    public void setTotalfundingusd(Integer totalfundingusd) {
        this.totalfundingusd = totalfundingusd;
    }

    public Integer getTotalfundingvnd() {
        return totalfundingvnd;
    }

    public Company totalfundingvnd(Integer totalfundingvnd) {
        this.totalfundingvnd = totalfundingvnd;
        return this;
    }

    public void setTotalfundingvnd(Integer totalfundingvnd) {
        this.totalfundingvnd = totalfundingvnd;
    }

    public String getStockexchange() {
        return stockexchange;
    }

    public Company stockexchange(String stockexchange) {
        this.stockexchange = stockexchange;
        return this;
    }

    public void setStockexchange(String stockexchange) {
        this.stockexchange = stockexchange;
    }

    public String getStocksymbol() {
        return stocksymbol;
    }

    public Company stocksymbol(String stocksymbol) {
        this.stocksymbol = stocksymbol;
        return this;
    }

    public void setStocksymbol(String stocksymbol) {
        this.stocksymbol = stocksymbol;
    }

    public Integer getNumberofinvestments() {
        return numberofinvestments;
    }

    public Company numberofinvestments(Integer numberofinvestments) {
        this.numberofinvestments = numberofinvestments;
        return this;
    }

    public void setNumberofinvestments(Integer numberofinvestments) {
        this.numberofinvestments = numberofinvestments;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Company createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Company updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public String getPermalinkaliases() {
        return permalinkaliases;
    }

    public Company permalinkaliases(String permalinkaliases) {
        this.permalinkaliases = permalinkaliases;
        return this;
    }

    public void setPermalinkaliases(String permalinkaliases) {
        this.permalinkaliases = permalinkaliases;
    }

    public String getInvestortype() {
        return investortype;
    }

    public Company investortype(String investortype) {
        this.investortype = investortype;
        return this;
    }

    public void setInvestortype(String investortype) {
        this.investortype = investortype;
    }

    public String getContactemail() {
        return contactemail;
    }

    public Company contactemail(String contactemail) {
        this.contactemail = contactemail;
        return this;
    }

    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public Company phonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
        return this;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getRank() {
        return rank;
    }

    public Company rank(Integer rank) {
        this.rank = rank;
        return this;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPrimaryimageid() {
        return primaryimageid;
    }

    public Company primaryimageid(Integer primaryimageid) {
        this.primaryimageid = primaryimageid;
        return this;
    }

    public void setPrimaryimageid(Integer primaryimageid) {
        this.primaryimageid = primaryimageid;
    }

    public Integer getOwnedbyid() {
        return ownedbyid;
    }

    public Company ownedbyid(Integer ownedbyid) {
        this.ownedbyid = ownedbyid;
        return this;
    }

    public void setOwnedbyid(Integer ownedbyid) {
        this.ownedbyid = ownedbyid;
    }

    public Integer getHeadquartersid() {
        return headquartersid;
    }

    public Company headquartersid(Integer headquartersid) {
        this.headquartersid = headquartersid;
        return this;
    }

    public void setHeadquartersid(Integer headquartersid) {
        this.headquartersid = headquartersid;
    }

    public Integer getAcquiredbyid() {
        return acquiredbyid;
    }

    public Company acquiredbyid(Integer acquiredbyid) {
        this.acquiredbyid = acquiredbyid;
        return this;
    }

    public void setAcquiredbyid(Integer acquiredbyid) {
        this.acquiredbyid = acquiredbyid;
    }

    public Integer getIpoid() {
        return ipoid;
    }

    public Company ipoid(Integer ipoid) {
        this.ipoid = ipoid;
        return this;
    }

    public void setIpoid(Integer ipoid) {
        this.ipoid = ipoid;
    }

    public String getHomepageurl() {
        return homepageurl;
    }

    public Company homepageurl(String homepageurl) {
        this.homepageurl = homepageurl;
        return this;
    }

    public void setHomepageurl(String homepageurl) {
        this.homepageurl = homepageurl;
    }

    public String getFacebookurl() {
        return facebookurl;
    }

    public Company facebookurl(String facebookurl) {
        this.facebookurl = facebookurl;
        return this;
    }

    public void setFacebookurl(String facebookurl) {
        this.facebookurl = facebookurl;
    }

    public String getTwitterurl() {
        return twitterurl;
    }

    public Company twitterurl(String twitterurl) {
        this.twitterurl = twitterurl;
        return this;
    }

    public void setTwitterurl(String twitterurl) {
        this.twitterurl = twitterurl;
    }

    public String getLinkedinurl() {
        return linkedinurl;
    }

    public Company linkedinurl(String linkedinurl) {
        this.linkedinurl = linkedinurl;
        return this;
    }

    public void setLinkedinurl(String linkedinurl) {
        this.linkedinurl = linkedinurl;
    }

    public String getCityname() {
        return cityname;
    }

    public Company cityname(String cityname) {
        this.cityname = cityname;
        return this;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getRegionname() {
        return regionname;
    }

    public Company regionname(String regionname) {
        this.regionname = regionname;
        return this;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public Company countrycode(String countrycode) {
        this.countrycode = countrycode;
        return this;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public Set<PersonCompanyRelation> getPeople() {
        return people;
    }

    public Company people(Set<PersonCompanyRelation> personCompanyRelations) {
        this.people = personCompanyRelations;
        return this;
    }

    public Company addPeople(PersonCompanyRelation personCompanyRelation) {
        this.people.add(personCompanyRelation);
        personCompanyRelation.setCompany(this);
        return this;
    }

    public Company removePeople(PersonCompanyRelation personCompanyRelation) {
        this.people.remove(personCompanyRelation);
        personCompanyRelation.setCompany(null);
        return this;
    }

    public void setPeople(Set<PersonCompanyRelation> personCompanyRelations) {
        this.people = personCompanyRelations;
    }

    public User getOwner() {
        return owner;
    }

    public Company owner(User user) {
        this.owner = user;
        return this;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    public User getAssignee() {
        return assignee;
    }

    public Company assignee(User user) {
        this.assignee = user;
        return this;
    }

    public void setAssignee(User user) {
        this.assignee = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Company)) {
            return false;
        }
        return id != null && id.equals(((Company) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Company{" +
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
            "}";
    }
}
