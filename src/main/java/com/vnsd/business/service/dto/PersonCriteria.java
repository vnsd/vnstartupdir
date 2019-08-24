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

    private StringFilter permalink;

    private StringFilter firstname;

    private StringFilter lastname;

    private StringFilter alsoknownas;

    private StringFilter bio;

    private IntegerFilter profileimageid;

    private BooleanFilter roleinvestor;

    private InstantFilter bornon;

    private IntegerFilter bornontrustcode;

    private InstantFilter diedon;

    private InstantFilter createdat;

    private InstantFilter updatedat;

    private StringFilter permalinkaliases;

    private StringFilter gender;

    private IntegerFilter rank;

    private IntegerFilter primaryaffiliationid;

    private IntegerFilter primarylocationid;

    private IntegerFilter primaryimageid;

    private StringFilter title;

    private StringFilter homepageurl;

    private StringFilter facebookurl;

    private StringFilter twitterurl;

    private StringFilter linkedinurl;

    private StringFilter cityname;

    private StringFilter regionname;

    private StringFilter countrycode;

    public PersonCriteria(){
    }

    public PersonCriteria(PersonCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.uuid = other.uuid == null ? null : other.uuid.copy();
        this.permalink = other.permalink == null ? null : other.permalink.copy();
        this.firstname = other.firstname == null ? null : other.firstname.copy();
        this.lastname = other.lastname == null ? null : other.lastname.copy();
        this.alsoknownas = other.alsoknownas == null ? null : other.alsoknownas.copy();
        this.bio = other.bio == null ? null : other.bio.copy();
        this.profileimageid = other.profileimageid == null ? null : other.profileimageid.copy();
        this.roleinvestor = other.roleinvestor == null ? null : other.roleinvestor.copy();
        this.bornon = other.bornon == null ? null : other.bornon.copy();
        this.bornontrustcode = other.bornontrustcode == null ? null : other.bornontrustcode.copy();
        this.diedon = other.diedon == null ? null : other.diedon.copy();
        this.createdat = other.createdat == null ? null : other.createdat.copy();
        this.updatedat = other.updatedat == null ? null : other.updatedat.copy();
        this.permalinkaliases = other.permalinkaliases == null ? null : other.permalinkaliases.copy();
        this.gender = other.gender == null ? null : other.gender.copy();
        this.rank = other.rank == null ? null : other.rank.copy();
        this.primaryaffiliationid = other.primaryaffiliationid == null ? null : other.primaryaffiliationid.copy();
        this.primarylocationid = other.primarylocationid == null ? null : other.primarylocationid.copy();
        this.primaryimageid = other.primaryimageid == null ? null : other.primaryimageid.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.homepageurl = other.homepageurl == null ? null : other.homepageurl.copy();
        this.facebookurl = other.facebookurl == null ? null : other.facebookurl.copy();
        this.twitterurl = other.twitterurl == null ? null : other.twitterurl.copy();
        this.linkedinurl = other.linkedinurl == null ? null : other.linkedinurl.copy();
        this.cityname = other.cityname == null ? null : other.cityname.copy();
        this.regionname = other.regionname == null ? null : other.regionname.copy();
        this.countrycode = other.countrycode == null ? null : other.countrycode.copy();
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

    public StringFilter getPermalink() {
        return permalink;
    }

    public void setPermalink(StringFilter permalink) {
        this.permalink = permalink;
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

    public StringFilter getAlsoknownas() {
        return alsoknownas;
    }

    public void setAlsoknownas(StringFilter alsoknownas) {
        this.alsoknownas = alsoknownas;
    }

    public StringFilter getBio() {
        return bio;
    }

    public void setBio(StringFilter bio) {
        this.bio = bio;
    }

    public IntegerFilter getProfileimageid() {
        return profileimageid;
    }

    public void setProfileimageid(IntegerFilter profileimageid) {
        this.profileimageid = profileimageid;
    }

    public BooleanFilter getRoleinvestor() {
        return roleinvestor;
    }

    public void setRoleinvestor(BooleanFilter roleinvestor) {
        this.roleinvestor = roleinvestor;
    }

    public InstantFilter getBornon() {
        return bornon;
    }

    public void setBornon(InstantFilter bornon) {
        this.bornon = bornon;
    }

    public IntegerFilter getBornontrustcode() {
        return bornontrustcode;
    }

    public void setBornontrustcode(IntegerFilter bornontrustcode) {
        this.bornontrustcode = bornontrustcode;
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

    public StringFilter getPermalinkaliases() {
        return permalinkaliases;
    }

    public void setPermalinkaliases(StringFilter permalinkaliases) {
        this.permalinkaliases = permalinkaliases;
    }

    public StringFilter getGender() {
        return gender;
    }

    public void setGender(StringFilter gender) {
        this.gender = gender;
    }

    public IntegerFilter getRank() {
        return rank;
    }

    public void setRank(IntegerFilter rank) {
        this.rank = rank;
    }

    public IntegerFilter getPrimaryaffiliationid() {
        return primaryaffiliationid;
    }

    public void setPrimaryaffiliationid(IntegerFilter primaryaffiliationid) {
        this.primaryaffiliationid = primaryaffiliationid;
    }

    public IntegerFilter getPrimarylocationid() {
        return primarylocationid;
    }

    public void setPrimarylocationid(IntegerFilter primarylocationid) {
        this.primarylocationid = primarylocationid;
    }

    public IntegerFilter getPrimaryimageid() {
        return primaryimageid;
    }

    public void setPrimaryimageid(IntegerFilter primaryimageid) {
        this.primaryimageid = primaryimageid;
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
            Objects.equals(permalink, that.permalink) &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(alsoknownas, that.alsoknownas) &&
            Objects.equals(bio, that.bio) &&
            Objects.equals(profileimageid, that.profileimageid) &&
            Objects.equals(roleinvestor, that.roleinvestor) &&
            Objects.equals(bornon, that.bornon) &&
            Objects.equals(bornontrustcode, that.bornontrustcode) &&
            Objects.equals(diedon, that.diedon) &&
            Objects.equals(createdat, that.createdat) &&
            Objects.equals(updatedat, that.updatedat) &&
            Objects.equals(permalinkaliases, that.permalinkaliases) &&
            Objects.equals(gender, that.gender) &&
            Objects.equals(rank, that.rank) &&
            Objects.equals(primaryaffiliationid, that.primaryaffiliationid) &&
            Objects.equals(primarylocationid, that.primarylocationid) &&
            Objects.equals(primaryimageid, that.primaryimageid) &&
            Objects.equals(title, that.title) &&
            Objects.equals(homepageurl, that.homepageurl) &&
            Objects.equals(facebookurl, that.facebookurl) &&
            Objects.equals(twitterurl, that.twitterurl) &&
            Objects.equals(linkedinurl, that.linkedinurl) &&
            Objects.equals(cityname, that.cityname) &&
            Objects.equals(regionname, that.regionname) &&
            Objects.equals(countrycode, that.countrycode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        uuid,
        permalink,
        firstname,
        lastname,
        alsoknownas,
        bio,
        profileimageid,
        roleinvestor,
        bornon,
        bornontrustcode,
        diedon,
        createdat,
        updatedat,
        permalinkaliases,
        gender,
        rank,
        primaryaffiliationid,
        primarylocationid,
        primaryimageid,
        title,
        homepageurl,
        facebookurl,
        twitterurl,
        linkedinurl,
        cityname,
        regionname,
        countrycode
        );
    }

    @Override
    public String toString() {
        return "PersonCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (uuid != null ? "uuid=" + uuid + ", " : "") +
                (permalink != null ? "permalink=" + permalink + ", " : "") +
                (firstname != null ? "firstname=" + firstname + ", " : "") +
                (lastname != null ? "lastname=" + lastname + ", " : "") +
                (alsoknownas != null ? "alsoknownas=" + alsoknownas + ", " : "") +
                (bio != null ? "bio=" + bio + ", " : "") +
                (profileimageid != null ? "profileimageid=" + profileimageid + ", " : "") +
                (roleinvestor != null ? "roleinvestor=" + roleinvestor + ", " : "") +
                (bornon != null ? "bornon=" + bornon + ", " : "") +
                (bornontrustcode != null ? "bornontrustcode=" + bornontrustcode + ", " : "") +
                (diedon != null ? "diedon=" + diedon + ", " : "") +
                (createdat != null ? "createdat=" + createdat + ", " : "") +
                (updatedat != null ? "updatedat=" + updatedat + ", " : "") +
                (permalinkaliases != null ? "permalinkaliases=" + permalinkaliases + ", " : "") +
                (gender != null ? "gender=" + gender + ", " : "") +
                (rank != null ? "rank=" + rank + ", " : "") +
                (primaryaffiliationid != null ? "primaryaffiliationid=" + primaryaffiliationid + ", " : "") +
                (primarylocationid != null ? "primarylocationid=" + primarylocationid + ", " : "") +
                (primaryimageid != null ? "primaryimageid=" + primaryimageid + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (homepageurl != null ? "homepageurl=" + homepageurl + ", " : "") +
                (facebookurl != null ? "facebookurl=" + facebookurl + ", " : "") +
                (twitterurl != null ? "twitterurl=" + twitterurl + ", " : "") +
                (linkedinurl != null ? "linkedinurl=" + linkedinurl + ", " : "") +
                (cityname != null ? "cityname=" + cityname + ", " : "") +
                (regionname != null ? "regionname=" + regionname + ", " : "") +
                (countrycode != null ? "countrycode=" + countrycode + ", " : "") +
            "}";
    }

}
