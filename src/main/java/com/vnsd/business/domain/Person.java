package com.vnsd.business.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * A Person.
 */
@Entity
@Table(name = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstname;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastname;

    @Column(name = "born_on")
    private Instant bornon;

    @Column(name = "died_on")
    private Instant diedon;

    @Column(name = "created_at")
    private Instant createdat;

    @Column(name = "updated_at")
    private Instant updatedat;

    @Column(name = "gender")
    private String gender;

    @Column(name = "title")
    private String title;

    @Column(name = "homepage_url")
    private String homepageurl;

    @Column(name = "facebook_url")
    private String facebookurl;

    @Column(name = "twitter_url")
    private String twitterurl;

    @Column(name = "linkedin_url")
    private String linkedinurl;

    @Column(name = "city_name")
    private String cityname;

    @Column(name = "region_name")
    private String regionname;

    @Column(name = "country_code")
    private String countrycode;

    @OneToMany(mappedBy = "person")
    private Set<PersonCompanyRelation> companies = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("people")
    private User createdBy;

    @ManyToOne
    @JsonIgnoreProperties("people")
    private User updatedBy;

    @ManyToOne
    @JsonIgnoreProperties("people")
    private User assignedTo;

    public Person(){}

    public Person(@NotNull UUID uuid, Instant createdat, User createdBy) {
        this.uuid = uuid.toString();
        this.createdat = createdat;
        this.createdBy = createdBy;
    }
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

    public Person uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstname() {
        return firstname;
    }

    public Person firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Person lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Instant getBornon() {
        return bornon;
    }

    public Person bornon(Instant bornon) {
        this.bornon = bornon;
        return this;
    }

    public void setBornon(Instant bornon) {
        this.bornon = bornon;
    }

    public Instant getDiedon() {
        return diedon;
    }

    public Person diedon(Instant diedon) {
        this.diedon = diedon;
        return this;
    }

    public void setDiedon(Instant diedon) {
        this.diedon = diedon;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Person createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Person updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public String getGender() {
        return gender;
    }

    public Person gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public Person title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHomepageurl() {
        return homepageurl;
    }

    public Person homepageurl(String homepageurl) {
        this.homepageurl = homepageurl;
        return this;
    }

    public void setHomepageurl(String homepageurl) {
        this.homepageurl = homepageurl;
    }

    public String getFacebookurl() {
        return facebookurl;
    }

    public Person facebookurl(String facebookurl) {
        this.facebookurl = facebookurl;
        return this;
    }

    public void setFacebookurl(String facebookurl) {
        this.facebookurl = facebookurl;
    }

    public String getTwitterurl() {
        return twitterurl;
    }

    public Person twitterurl(String twitterurl) {
        this.twitterurl = twitterurl;
        return this;
    }

    public void setTwitterurl(String twitterurl) {
        this.twitterurl = twitterurl;
    }

    public String getLinkedinurl() {
        return linkedinurl;
    }

    public Person linkedinurl(String linkedinurl) {
        this.linkedinurl = linkedinurl;
        return this;
    }

    public void setLinkedinurl(String linkedinurl) {
        this.linkedinurl = linkedinurl;
    }

    public String getCityname() {
        return cityname;
    }

    public Person cityname(String cityname) {
        this.cityname = cityname;
        return this;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getRegionname() {
        return regionname;
    }

    public Person regionname(String regionname) {
        this.regionname = regionname;
        return this;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public Person countrycode(String countrycode) {
        this.countrycode = countrycode;
        return this;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public Set<PersonCompanyRelation> getCompanies() {
        return companies;
    }

    public Person companies(Set<PersonCompanyRelation> personCompanyRelations) {
        this.companies = personCompanyRelations;
        return this;
    }

    public Person addCompanies(PersonCompanyRelation personCompanyRelation) {
        this.companies.add(personCompanyRelation);
        personCompanyRelation.setPerson(this);
        return this;
    }

    public Person removeCompanies(PersonCompanyRelation personCompanyRelation) {
        this.companies.remove(personCompanyRelation);
        personCompanyRelation.setPerson(null);
        return this;
    }

    public void setCompanies(Set<PersonCompanyRelation> personCompanyRelations) {
        this.companies = personCompanyRelations;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Person createdBy(User user) {
        this.createdBy = user;
        return this;
    }

    public void setCreatedBy(User user) {
        this.createdBy = user;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public Person updatedBy(User user) {
        this.updatedBy = user;
        return this;
    }

    public void setUpdatedBy(User user) {
        this.updatedBy = user;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public Person assignedTo(User user) {
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
        if (!(o instanceof Person)) {
            return false;
        }
        return id != null && id.equals(((Person) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", bornon='" + getBornon() + "'" +
            ", diedon='" + getDiedon() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            ", gender='" + getGender() + "'" +
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
