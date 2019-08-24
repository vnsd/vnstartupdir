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
import io.github.jhipster.service.filter.UUIDFilter;

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

    private UUIDFilter uuid;

    private StringFilter permalink;

    private StringFilter name;

    private StringFilter alsoknownas;

    private StringFilter shortdescription;

    private StringFilter description;

    private IntegerFilter profileimageid;

    private StringFilter primaryrole;

    private InstantFilter foundedon;

    private IntegerFilter foundedontrustcode;

    private InstantFilter closedon;

    private IntegerFilter numemployeesmin;

    private IntegerFilter numemployeesmax;

    private IntegerFilter totalfundingusd;

    private IntegerFilter totalfundingvnd;

    private StringFilter stockexchange;

    private StringFilter stocksymbol;

    private IntegerFilter numberofinvestments;

    private InstantFilter createdat;

    private InstantFilter updatedat;

    private StringFilter permalinkaliases;

    private StringFilter investortype;

    private StringFilter contactemail;

    private StringFilter phonenumber;

    private IntegerFilter rank;

    private IntegerFilter primaryimageid;

    private IntegerFilter ownedbyid;

    private IntegerFilter headquartersid;

    private IntegerFilter acquiredbyid;

    private IntegerFilter ipoid;

    private StringFilter homepageurl;

    private StringFilter facebookurl;

    private StringFilter twitterurl;

    private StringFilter linkedinurl;

    private StringFilter cityname;

    private StringFilter regionname;

    private StringFilter countrycode;

    private LongFilter ownerId;

    private LongFilter assigneeId;

    public CompanyCriteria(){
    }

    public CompanyCriteria(CompanyCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.uuid = other.uuid == null ? null : other.uuid.copy();
        this.permalink = other.permalink == null ? null : other.permalink.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.alsoknownas = other.alsoknownas == null ? null : other.alsoknownas.copy();
        this.shortdescription = other.shortdescription == null ? null : other.shortdescription.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.profileimageid = other.profileimageid == null ? null : other.profileimageid.copy();
        this.primaryrole = other.primaryrole == null ? null : other.primaryrole.copy();
        this.foundedon = other.foundedon == null ? null : other.foundedon.copy();
        this.foundedontrustcode = other.foundedontrustcode == null ? null : other.foundedontrustcode.copy();
        this.closedon = other.closedon == null ? null : other.closedon.copy();
        this.numemployeesmin = other.numemployeesmin == null ? null : other.numemployeesmin.copy();
        this.numemployeesmax = other.numemployeesmax == null ? null : other.numemployeesmax.copy();
        this.totalfundingusd = other.totalfundingusd == null ? null : other.totalfundingusd.copy();
        this.totalfundingvnd = other.totalfundingvnd == null ? null : other.totalfundingvnd.copy();
        this.stockexchange = other.stockexchange == null ? null : other.stockexchange.copy();
        this.stocksymbol = other.stocksymbol == null ? null : other.stocksymbol.copy();
        this.numberofinvestments = other.numberofinvestments == null ? null : other.numberofinvestments.copy();
        this.createdat = other.createdat == null ? null : other.createdat.copy();
        this.updatedat = other.updatedat == null ? null : other.updatedat.copy();
        this.permalinkaliases = other.permalinkaliases == null ? null : other.permalinkaliases.copy();
        this.investortype = other.investortype == null ? null : other.investortype.copy();
        this.contactemail = other.contactemail == null ? null : other.contactemail.copy();
        this.phonenumber = other.phonenumber == null ? null : other.phonenumber.copy();
        this.rank = other.rank == null ? null : other.rank.copy();
        this.primaryimageid = other.primaryimageid == null ? null : other.primaryimageid.copy();
        this.ownedbyid = other.ownedbyid == null ? null : other.ownedbyid.copy();
        this.headquartersid = other.headquartersid == null ? null : other.headquartersid.copy();
        this.acquiredbyid = other.acquiredbyid == null ? null : other.acquiredbyid.copy();
        this.ipoid = other.ipoid == null ? null : other.ipoid.copy();
        this.homepageurl = other.homepageurl == null ? null : other.homepageurl.copy();
        this.facebookurl = other.facebookurl == null ? null : other.facebookurl.copy();
        this.twitterurl = other.twitterurl == null ? null : other.twitterurl.copy();
        this.linkedinurl = other.linkedinurl == null ? null : other.linkedinurl.copy();
        this.cityname = other.cityname == null ? null : other.cityname.copy();
        this.regionname = other.regionname == null ? null : other.regionname.copy();
        this.countrycode = other.countrycode == null ? null : other.countrycode.copy();
        this.ownerId = other.ownerId == null ? null : other.ownerId.copy();
        this.assigneeId = other.assigneeId == null ? null : other.assigneeId.copy();
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

    public UUIDFilter getUuid() {
        return uuid;
    }

    public void setUuid(UUIDFilter uuid) {
        this.uuid = uuid;
    }

    public StringFilter getPermalink() {
        return permalink;
    }

    public void setPermalink(StringFilter permalink) {
        this.permalink = permalink;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getAlsoknownas() {
        return alsoknownas;
    }

    public void setAlsoknownas(StringFilter alsoknownas) {
        this.alsoknownas = alsoknownas;
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

    public IntegerFilter getProfileimageid() {
        return profileimageid;
    }

    public void setProfileimageid(IntegerFilter profileimageid) {
        this.profileimageid = profileimageid;
    }

    public StringFilter getPrimaryrole() {
        return primaryrole;
    }

    public void setPrimaryrole(StringFilter primaryrole) {
        this.primaryrole = primaryrole;
    }

    public InstantFilter getFoundedon() {
        return foundedon;
    }

    public void setFoundedon(InstantFilter foundedon) {
        this.foundedon = foundedon;
    }

    public IntegerFilter getFoundedontrustcode() {
        return foundedontrustcode;
    }

    public void setFoundedontrustcode(IntegerFilter foundedontrustcode) {
        this.foundedontrustcode = foundedontrustcode;
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

    public StringFilter getStockexchange() {
        return stockexchange;
    }

    public void setStockexchange(StringFilter stockexchange) {
        this.stockexchange = stockexchange;
    }

    public StringFilter getStocksymbol() {
        return stocksymbol;
    }

    public void setStocksymbol(StringFilter stocksymbol) {
        this.stocksymbol = stocksymbol;
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

    public StringFilter getPermalinkaliases() {
        return permalinkaliases;
    }

    public void setPermalinkaliases(StringFilter permalinkaliases) {
        this.permalinkaliases = permalinkaliases;
    }

    public StringFilter getInvestortype() {
        return investortype;
    }

    public void setInvestortype(StringFilter investortype) {
        this.investortype = investortype;
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

    public IntegerFilter getRank() {
        return rank;
    }

    public void setRank(IntegerFilter rank) {
        this.rank = rank;
    }

    public IntegerFilter getPrimaryimageid() {
        return primaryimageid;
    }

    public void setPrimaryimageid(IntegerFilter primaryimageid) {
        this.primaryimageid = primaryimageid;
    }

    public IntegerFilter getOwnedbyid() {
        return ownedbyid;
    }

    public void setOwnedbyid(IntegerFilter ownedbyid) {
        this.ownedbyid = ownedbyid;
    }

    public IntegerFilter getHeadquartersid() {
        return headquartersid;
    }

    public void setHeadquartersid(IntegerFilter headquartersid) {
        this.headquartersid = headquartersid;
    }

    public IntegerFilter getAcquiredbyid() {
        return acquiredbyid;
    }

    public void setAcquiredbyid(IntegerFilter acquiredbyid) {
        this.acquiredbyid = acquiredbyid;
    }

    public IntegerFilter getIpoid() {
        return ipoid;
    }

    public void setIpoid(IntegerFilter ipoid) {
        this.ipoid = ipoid;
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

    public LongFilter getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(LongFilter ownerId) {
        this.ownerId = ownerId;
    }

    public LongFilter getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(LongFilter assigneeId) {
        this.assigneeId = assigneeId;
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
            Objects.equals(permalink, that.permalink) &&
            Objects.equals(name, that.name) &&
            Objects.equals(alsoknownas, that.alsoknownas) &&
            Objects.equals(shortdescription, that.shortdescription) &&
            Objects.equals(description, that.description) &&
            Objects.equals(profileimageid, that.profileimageid) &&
            Objects.equals(primaryrole, that.primaryrole) &&
            Objects.equals(foundedon, that.foundedon) &&
            Objects.equals(foundedontrustcode, that.foundedontrustcode) &&
            Objects.equals(closedon, that.closedon) &&
            Objects.equals(numemployeesmin, that.numemployeesmin) &&
            Objects.equals(numemployeesmax, that.numemployeesmax) &&
            Objects.equals(totalfundingusd, that.totalfundingusd) &&
            Objects.equals(totalfundingvnd, that.totalfundingvnd) &&
            Objects.equals(stockexchange, that.stockexchange) &&
            Objects.equals(stocksymbol, that.stocksymbol) &&
            Objects.equals(numberofinvestments, that.numberofinvestments) &&
            Objects.equals(createdat, that.createdat) &&
            Objects.equals(updatedat, that.updatedat) &&
            Objects.equals(permalinkaliases, that.permalinkaliases) &&
            Objects.equals(investortype, that.investortype) &&
            Objects.equals(contactemail, that.contactemail) &&
            Objects.equals(phonenumber, that.phonenumber) &&
            Objects.equals(rank, that.rank) &&
            Objects.equals(primaryimageid, that.primaryimageid) &&
            Objects.equals(ownedbyid, that.ownedbyid) &&
            Objects.equals(headquartersid, that.headquartersid) &&
            Objects.equals(acquiredbyid, that.acquiredbyid) &&
            Objects.equals(ipoid, that.ipoid) &&
            Objects.equals(homepageurl, that.homepageurl) &&
            Objects.equals(facebookurl, that.facebookurl) &&
            Objects.equals(twitterurl, that.twitterurl) &&
            Objects.equals(linkedinurl, that.linkedinurl) &&
            Objects.equals(cityname, that.cityname) &&
            Objects.equals(regionname, that.regionname) &&
            Objects.equals(countrycode, that.countrycode) &&
            Objects.equals(ownerId, that.ownerId) &&
            Objects.equals(assigneeId, that.assigneeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        uuid,
        permalink,
        name,
        alsoknownas,
        shortdescription,
        description,
        profileimageid,
        primaryrole,
        foundedon,
        foundedontrustcode,
        closedon,
        numemployeesmin,
        numemployeesmax,
        totalfundingusd,
        totalfundingvnd,
        stockexchange,
        stocksymbol,
        numberofinvestments,
        createdat,
        updatedat,
        permalinkaliases,
        investortype,
        contactemail,
        phonenumber,
        rank,
        primaryimageid,
        ownedbyid,
        headquartersid,
        acquiredbyid,
        ipoid,
        homepageurl,
        facebookurl,
        twitterurl,
        linkedinurl,
        cityname,
        regionname,
        countrycode,
        ownerId,
        assigneeId
        );
    }

    @Override
    public String toString() {
        return "CompanyCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (uuid != null ? "uuid=" + uuid + ", " : "") +
                (permalink != null ? "permalink=" + permalink + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (alsoknownas != null ? "alsoknownas=" + alsoknownas + ", " : "") +
                (shortdescription != null ? "shortdescription=" + shortdescription + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (profileimageid != null ? "profileimageid=" + profileimageid + ", " : "") +
                (primaryrole != null ? "primaryrole=" + primaryrole + ", " : "") +
                (foundedon != null ? "foundedon=" + foundedon + ", " : "") +
                (foundedontrustcode != null ? "foundedontrustcode=" + foundedontrustcode + ", " : "") +
                (closedon != null ? "closedon=" + closedon + ", " : "") +
                (numemployeesmin != null ? "numemployeesmin=" + numemployeesmin + ", " : "") +
                (numemployeesmax != null ? "numemployeesmax=" + numemployeesmax + ", " : "") +
                (totalfundingusd != null ? "totalfundingusd=" + totalfundingusd + ", " : "") +
                (totalfundingvnd != null ? "totalfundingvnd=" + totalfundingvnd + ", " : "") +
                (stockexchange != null ? "stockexchange=" + stockexchange + ", " : "") +
                (stocksymbol != null ? "stocksymbol=" + stocksymbol + ", " : "") +
                (numberofinvestments != null ? "numberofinvestments=" + numberofinvestments + ", " : "") +
                (createdat != null ? "createdat=" + createdat + ", " : "") +
                (updatedat != null ? "updatedat=" + updatedat + ", " : "") +
                (permalinkaliases != null ? "permalinkaliases=" + permalinkaliases + ", " : "") +
                (investortype != null ? "investortype=" + investortype + ", " : "") +
                (contactemail != null ? "contactemail=" + contactemail + ", " : "") +
                (phonenumber != null ? "phonenumber=" + phonenumber + ", " : "") +
                (rank != null ? "rank=" + rank + ", " : "") +
                (primaryimageid != null ? "primaryimageid=" + primaryimageid + ", " : "") +
                (ownedbyid != null ? "ownedbyid=" + ownedbyid + ", " : "") +
                (headquartersid != null ? "headquartersid=" + headquartersid + ", " : "") +
                (acquiredbyid != null ? "acquiredbyid=" + acquiredbyid + ", " : "") +
                (ipoid != null ? "ipoid=" + ipoid + ", " : "") +
                (homepageurl != null ? "homepageurl=" + homepageurl + ", " : "") +
                (facebookurl != null ? "facebookurl=" + facebookurl + ", " : "") +
                (twitterurl != null ? "twitterurl=" + twitterurl + ", " : "") +
                (linkedinurl != null ? "linkedinurl=" + linkedinurl + ", " : "") +
                (cityname != null ? "cityname=" + cityname + ", " : "") +
                (regionname != null ? "regionname=" + regionname + ", " : "") +
                (countrycode != null ? "countrycode=" + countrycode + ", " : "") +
                (ownerId != null ? "ownerId=" + ownerId + ", " : "") +
                (assigneeId != null ? "assigneeId=" + assigneeId + ", " : "") +
            "}";
    }

}
