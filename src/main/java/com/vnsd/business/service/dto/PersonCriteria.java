package com.vnsd.business.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link com.vnsd.business.domain.Person} entity. This class is used
 * in {@link com.vnsd.business.web.rest.PersonResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /people?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PersonCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter uuid;

    private StringFilter firstname;

    private StringFilter lastname;

    private InstantFilter bornon;

    private InstantFilter diedon;

    private InstantFilter createdat;

    private InstantFilter updatedat;

    private StringFilter gender;

    private StringFilter title;

    private StringFilter homepageurl;

    private StringFilter facebookurl;

    private StringFilter twitterurl;

    private StringFilter linkedinurl;

    private StringFilter cityname;

    private StringFilter regionname;

    private StringFilter countrycode;

    private LongFilter companiesId;

    private LongFilter createdById;

    private LongFilter updatedById;

    private LongFilter assignedToId;

    public PersonCriteria(){
    }

    public PersonCriteria(PersonCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.uuid = other.uuid == null ? null : other.uuid.copy();
        this.firstname = other.firstname == null ? null : other.firstname.copy();
        this.lastname = other.lastname == null ? null : other.lastname.copy();
        this.bornon = other.bornon == null ? null : other.bornon.copy();
        this.diedon = other.diedon == null ? null : other.diedon.copy();
        this.createdat = other.createdat == null ? null : other.createdat.copy();
        this.updatedat = other.updatedat == null ? null : other.updatedat.copy();
        this.gender = other.gender == null ? null : other.gender.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.homepageurl = other.homepageurl == null ? null : other.homepageurl.copy();
        this.facebookurl = other.facebookurl == null ? null : other.facebookurl.copy();
        this.twitterurl = other.twitterurl == null ? null : other.twitterurl.copy();
        this.linkedinurl = other.linkedinurl == null ? null : other.linkedinurl.copy();
        this.cityname = other.cityname == null ? null : other.cityname.copy();
        this.regionname = other.regionname == null ? null : other.regionname.copy();
        this.countrycode = other.countrycode == null ? null : other.countrycode.copy();
        this.companiesId = other.companiesId == null ? null : other.companiesId.copy();
        this.createdById = other.createdById == null ? null : other.createdById.copy();
        this.updatedById = other.updatedById == null ? null : other.updatedById.copy();
        this.assignedToId = other.assignedToId == null ? null : other.assignedToId.copy();
    }

    @Override
    public PersonCriteria copy() {
        return new PersonCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getUuid() {
        return uuid;
    }

    public void setUuid(StringFilter uuid) {
        this.uuid = uuid;
    }

    public StringFilter getFirstname() {
        return firstname;
    }

    public void setFirstname(StringFilter firstname) {
        this.firstname = firstname;
    }

    public StringFilter getLastname() {
        return lastname;
    }

    public void setLastname(StringFilter lastname) {
        this.lastname = lastname;
    }

    public InstantFilter getBornon() {
        return bornon;
    }

    public void setBornon(InstantFilter bornon) {
        this.bornon = bornon;
    }

    public InstantFilter getDiedon() {
        return diedon;
    }

    public void setDiedon(InstantFilter diedon) {
        this.diedon = diedon;
    }

    public InstantFilter getCreatedat() {
        return createdat;
    }

    public void setCreatedat(InstantFilter createdat) {
        this.createdat = createdat;
    }

    public InstantFilter getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(InstantFilter updatedat) {
        this.updatedat = updatedat;
    }

    public StringFilter getGender() {
        return gender;
    }

    public void setGender(StringFilter gender) {
        this.gender = gender;
    }

    public StringFilter getTitle() {
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public StringFilter getHomepageurl() {
        return homepageurl;
    }

    public void setHomepageurl(StringFilter homepageurl) {
        this.homepageurl = homepageurl;
    }

    public StringFilter getFacebookurl() {
        return facebookurl;
    }

    public void setFacebookurl(StringFilter facebookurl) {
        this.facebookurl = facebookurl;
    }

    public StringFilter getTwitterurl() {
        return twitterurl;
    }

    public void setTwitterurl(StringFilter twitterurl) {
        this.twitterurl = twitterurl;
    }

    public StringFilter getLinkedinurl() {
        return linkedinurl;
    }

    public void setLinkedinurl(StringFilter linkedinurl) {
        this.linkedinurl = linkedinurl;
    }

    public StringFilter getCityname() {
        return cityname;
    }

    public void setCityname(StringFilter cityname) {
        this.cityname = cityname;
    }

    public StringFilter getRegionname() {
        return regionname;
    }

    public void setRegionname(StringFilter regionname) {
        this.regionname = regionname;
    }

    public StringFilter getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(StringFilter countrycode) {
        this.countrycode = countrycode;
    }

    public LongFilter getCompaniesId() {
        return companiesId;
    }

    public void setCompaniesId(LongFilter companiesId) {
        this.companiesId = companiesId;
    }

    public LongFilter getCreatedById() {
        return createdById;
    }

    public void setCreatedById(LongFilter createdById) {
        this.createdById = createdById;
    }

    public LongFilter getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(LongFilter updatedById) {
        this.updatedById = updatedById;
    }

    public LongFilter getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(LongFilter assignedToId) {
        this.assignedToId = assignedToId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PersonCriteria that = (PersonCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(uuid, that.uuid) &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(bornon, that.bornon) &&
            Objects.equals(diedon, that.diedon) &&
            Objects.equals(createdat, that.createdat) &&
            Objects.equals(updatedat, that.updatedat) &&
            Objects.equals(gender, that.gender) &&
            Objects.equals(title, that.title) &&
            Objects.equals(homepageurl, that.homepageurl) &&
            Objects.equals(facebookurl, that.facebookurl) &&
            Objects.equals(twitterurl, that.twitterurl) &&
            Objects.equals(linkedinurl, that.linkedinurl) &&
            Objects.equals(cityname, that.cityname) &&
            Objects.equals(regionname, that.regionname) &&
            Objects.equals(countrycode, that.countrycode) &&
            Objects.equals(companiesId, that.companiesId) &&
            Objects.equals(createdById, that.createdById) &&
            Objects.equals(updatedById, that.updatedById) &&
            Objects.equals(assignedToId, that.assignedToId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        uuid,
        firstname,
        lastname,
        bornon,
        diedon,
        createdat,
        updatedat,
        gender,
        title,
        homepageurl,
        facebookurl,
        twitterurl,
        linkedinurl,
        cityname,
        regionname,
        countrycode,
        companiesId,
        createdById,
        updatedById,
        assignedToId
        );
    }

    @Override
    public String toString() {
        return "PersonCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (uuid != null ? "uuid=" + uuid + ", " : "") +
                (firstname != null ? "firstname=" + firstname + ", " : "") +
                (lastname != null ? "lastname=" + lastname + ", " : "") +
                (bornon != null ? "bornon=" + bornon + ", " : "") +
                (diedon != null ? "diedon=" + diedon + ", " : "") +
                (createdat != null ? "createdat=" + createdat + ", " : "") +
                (updatedat != null ? "updatedat=" + updatedat + ", " : "") +
                (gender != null ? "gender=" + gender + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (homepageurl != null ? "homepageurl=" + homepageurl + ", " : "") +
                (facebookurl != null ? "facebookurl=" + facebookurl + ", " : "") +
                (twitterurl != null ? "twitterurl=" + twitterurl + ", " : "") +
                (linkedinurl != null ? "linkedinurl=" + linkedinurl + ", " : "") +
                (cityname != null ? "cityname=" + cityname + ", " : "") +
                (regionname != null ? "regionname=" + regionname + ", " : "") +
                (countrycode != null ? "countrycode=" + countrycode + ", " : "") +
                (companiesId != null ? "companiesId=" + companiesId + ", " : "") +
                (createdById != null ? "createdById=" + createdById + ", " : "") +
                (updatedById != null ? "updatedById=" + updatedById + ", " : "") +
                (assignedToId != null ? "assignedToId=" + assignedToId + ", " : "") +
            "}";
    }

}
