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
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "shortdescription")
    private String shortdescription;

    @Column(name = "description")
    private String description;

    @Column(name = "foundedon")
    private Instant foundedon;

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

    @Column(name = "numberofinvestments")
    private Integer numberofinvestments;

    @Column(name = "createdat")
    private Instant createdat;

    @Column(name = "updatedat")
    private Instant updatedat;

    @Column(name = "contactemail")
    private String contactemail;

    @Column(name = "phonenumber")
    private String phonenumber;

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
    private User createdBy;

    @ManyToOne
    @JsonIgnoreProperties("companies")
    private User updatedBy;

    @ManyToOne
    @JsonIgnoreProperties("companies")
    private User assignedTo;

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

    public String getType() {
        return type;
    }

    public Company type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public Company createdBy(User user) {
        this.createdBy = user;
        return this;
    }

    public void setCreatedBy(User user) {
        this.createdBy = user;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public Company updatedBy(User user) {
        this.updatedBy = user;
        return this;
    }

    public void setUpdatedBy(User user) {
        this.updatedBy = user;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public Company assignedTo(User user) {
        this.assignedTo = user;
        return this;
    }

    public void setAssignedTo(User user) {
        this.assignedTo = user;
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
            "}";
    }
}
