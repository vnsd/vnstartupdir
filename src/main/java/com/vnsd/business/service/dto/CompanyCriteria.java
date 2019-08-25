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
 * Criteria class for the {@link com.vnsd.business.domain.Company} entity. This class is used
 * in {@link com.vnsd.business.web.rest.CompanyResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /companies?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CompanyCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter uuid;

    private StringFilter type;

    private StringFilter name;

    private StringFilter shortdescription;

    private StringFilter description;

    private InstantFilter foundedon;

    private InstantFilter closedon;

    private IntegerFilter numemployeesmin;

    private IntegerFilter numemployeesmax;

    private IntegerFilter totalfundingusd;

    private IntegerFilter totalfundingvnd;

    private IntegerFilter numberofinvestments;

    private InstantFilter createdat;

    private InstantFilter updatedat;

    private StringFilter contactemail;

    private StringFilter phonenumber;

    private StringFilter homepageurl;

    private StringFilter facebookurl;

    private StringFilter twitterurl;

    private StringFilter linkedinurl;

    private StringFilter cityname;

    private StringFilter regionname;

    private StringFilter countrycode;

    private LongFilter peopleId;

    private LongFilter createdById;

    private LongFilter updatedById;

    private LongFilter assignedToId;

    public CompanyCriteria(){
    }

    public CompanyCriteria(CompanyCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.uuid = other.uuid == null ? null : other.uuid.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.shortdescription = other.shortdescription == null ? null : other.shortdescription.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.foundedon = other.foundedon == null ? null : other.foundedon.copy();
        this.closedon = other.closedon == null ? null : other.closedon.copy();
        this.numemployeesmin = other.numemployeesmin == null ? null : other.numemployeesmin.copy();
        this.numemployeesmax = other.numemployeesmax == null ? null : other.numemployeesmax.copy();
        this.totalfundingusd = other.totalfundingusd == null ? null : other.totalfundingusd.copy();
        this.totalfundingvnd = other.totalfundingvnd == null ? null : other.totalfundingvnd.copy();
        this.numberofinvestments = other.numberofinvestments == null ? null : other.numberofinvestments.copy();
        this.createdat = other.createdat == null ? null : other.createdat.copy();
        this.updatedat = other.updatedat == null ? null : other.updatedat.copy();
        this.contactemail = other.contactemail == null ? null : other.contactemail.copy();
        this.phonenumber = other.phonenumber == null ? null : other.phonenumber.copy();
        this.homepageurl = other.homepageurl == null ? null : other.homepageurl.copy();
        this.facebookurl = other.facebookurl == null ? null : other.facebookurl.copy();
        this.twitterurl = other.twitterurl == null ? null : other.twitterurl.copy();
        this.linkedinurl = other.linkedinurl == null ? null : other.linkedinurl.copy();
        this.cityname = other.cityname == null ? null : other.cityname.copy();
        this.regionname = other.regionname == null ? null : other.regionname.copy();
        this.countrycode = other.countrycode == null ? null : other.countrycode.copy();
        this.peopleId = other.peopleId == null ? null : other.peopleId.copy();
        this.createdById = other.createdById == null ? null : other.createdById.copy();
        this.updatedById = other.updatedById == null ? null : other.updatedById.copy();
        this.assignedToId = other.assignedToId == null ? null : other.assignedToId.copy();
    }

    @Override
    public CompanyCriteria copy() {
        return new CompanyCriteria(this);
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

    public StringFilter getType() {
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(StringFilter shortdescription) {
        this.shortdescription = shortdescription;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public InstantFilter getFoundedon() {
        return foundedon;
    }

    public void setFoundedon(InstantFilter foundedon) {
        this.foundedon = foundedon;
    }

    public InstantFilter getClosedon() {
        return closedon;
    }

    public void setClosedon(InstantFilter closedon) {
        this.closedon = closedon;
    }

    public IntegerFilter getNumemployeesmin() {
        return numemployeesmin;
    }

    public void setNumemployeesmin(IntegerFilter numemployeesmin) {
        this.numemployeesmin = numemployeesmin;
    }

    public IntegerFilter getNumemployeesmax() {
        return numemployeesmax;
    }

    public void setNumemployeesmax(IntegerFilter numemployeesmax) {
        this.numemployeesmax = numemployeesmax;
    }

    public IntegerFilter getTotalfundingusd() {
        return totalfundingusd;
    }

    public void setTotalfundingusd(IntegerFilter totalfundingusd) {
        this.totalfundingusd = totalfundingusd;
    }

    public IntegerFilter getTotalfundingvnd() {
        return totalfundingvnd;
    }

    public void setTotalfundingvnd(IntegerFilter totalfundingvnd) {
        this.totalfundingvnd = totalfundingvnd;
    }

    public IntegerFilter getNumberofinvestments() {
        return numberofinvestments;
    }

    public void setNumberofinvestments(IntegerFilter numberofinvestments) {
        this.numberofinvestments = numberofinvestments;
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

    public StringFilter getContactemail() {
        return contactemail;
    }

    public void setContactemail(StringFilter contactemail) {
        this.contactemail = contactemail;
    }

    public StringFilter getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(StringFilter phonenumber) {
        this.phonenumber = phonenumber;
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

    public LongFilter getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(LongFilter peopleId) {
        this.peopleId = peopleId;
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
        final CompanyCriteria that = (CompanyCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(uuid, that.uuid) &&
            Objects.equals(type, that.type) &&
            Objects.equals(name, that.name) &&
            Objects.equals(shortdescription, that.shortdescription) &&
            Objects.equals(description, that.description) &&
            Objects.equals(foundedon, that.foundedon) &&
            Objects.equals(closedon, that.closedon) &&
            Objects.equals(numemployeesmin, that.numemployeesmin) &&
            Objects.equals(numemployeesmax, that.numemployeesmax) &&
            Objects.equals(totalfundingusd, that.totalfundingusd) &&
            Objects.equals(totalfundingvnd, that.totalfundingvnd) &&
            Objects.equals(numberofinvestments, that.numberofinvestments) &&
            Objects.equals(createdat, that.createdat) &&
            Objects.equals(updatedat, that.updatedat) &&
            Objects.equals(contactemail, that.contactemail) &&
            Objects.equals(phonenumber, that.phonenumber) &&
            Objects.equals(homepageurl, that.homepageurl) &&
            Objects.equals(facebookurl, that.facebookurl) &&
            Objects.equals(twitterurl, that.twitterurl) &&
            Objects.equals(linkedinurl, that.linkedinurl) &&
            Objects.equals(cityname, that.cityname) &&
            Objects.equals(regionname, that.regionname) &&
            Objects.equals(countrycode, that.countrycode) &&
            Objects.equals(peopleId, that.peopleId) &&
            Objects.equals(createdById, that.createdById) &&
            Objects.equals(updatedById, that.updatedById) &&
            Objects.equals(assignedToId, that.assignedToId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        uuid,
        type,
        name,
        shortdescription,
        description,
        foundedon,
        closedon,
        numemployeesmin,
        numemployeesmax,
        totalfundingusd,
        totalfundingvnd,
        numberofinvestments,
        createdat,
        updatedat,
        contactemail,
        phonenumber,
        homepageurl,
        facebookurl,
        twitterurl,
        linkedinurl,
        cityname,
        regionname,
        countrycode,
        peopleId,
        createdById,
        updatedById,
        assignedToId
        );
    }

    @Override
    public String toString() {
        return "CompanyCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (uuid != null ? "uuid=" + uuid + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (shortdescription != null ? "shortdescription=" + shortdescription + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (foundedon != null ? "foundedon=" + foundedon + ", " : "") +
                (closedon != null ? "closedon=" + closedon + ", " : "") +
                (numemployeesmin != null ? "numemployeesmin=" + numemployeesmin + ", " : "") +
                (numemployeesmax != null ? "numemployeesmax=" + numemployeesmax + ", " : "") +
                (totalfundingusd != null ? "totalfundingusd=" + totalfundingusd + ", " : "") +
                (totalfundingvnd != null ? "totalfundingvnd=" + totalfundingvnd + ", " : "") +
                (numberofinvestments != null ? "numberofinvestments=" + numberofinvestments + ", " : "") +
                (createdat != null ? "createdat=" + createdat + ", " : "") +
                (updatedat != null ? "updatedat=" + updatedat + ", " : "") +
                (contactemail != null ? "contactemail=" + contactemail + ", " : "") +
                (phonenumber != null ? "phonenumber=" + phonenumber + ", " : "") +
                (homepageurl != null ? "homepageurl=" + homepageurl + ", " : "") +
                (facebookurl != null ? "facebookurl=" + facebookurl + ", " : "") +
                (twitterurl != null ? "twitterurl=" + twitterurl + ", " : "") +
                (linkedinurl != null ? "linkedinurl=" + linkedinurl + ", " : "") +
                (cityname != null ? "cityname=" + cityname + ", " : "") +
                (regionname != null ? "regionname=" + regionname + ", " : "") +
                (countrycode != null ? "countrycode=" + countrycode + ", " : "") +
                (peopleId != null ? "peopleId=" + peopleId + ", " : "") +
                (createdById != null ? "createdById=" + createdById + ", " : "") +
                (updatedById != null ? "updatedById=" + updatedById + ", " : "") +
                (assignedToId != null ? "assignedToId=" + assignedToId + ", " : "") +
            "}";
    }

}
