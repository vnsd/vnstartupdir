package com.vnsd.business.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PersonCompanyRelation.
 */
@Entity
@Table(name = "person_company_relation")
public class PersonCompanyRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "relation_code")
    private String relationCode;

    @ManyToOne
    @JsonIgnoreProperties("personCompanyRelations")
    private Company company;

    @ManyToOne
    @JsonIgnoreProperties("personCompanyRelations")
    private Person person;

    @ManyToOne
    @JsonIgnoreProperties("personCompanyRelations")
    private Company company;

    @ManyToOne
    @JsonIgnoreProperties("personCompanyRelations")
    private Person person;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelationCode() {
        return relationCode;
    }

    public PersonCompanyRelation relationCode(String relationCode) {
        this.relationCode = relationCode;
        return this;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }

    public Company getCompany() {
        return company;
    }

    public PersonCompanyRelation company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Person getPerson() {
        return person;
    }

    public PersonCompanyRelation person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Company getCompany() {
        return company;
    }

    public PersonCompanyRelation company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Person getPerson() {
        return person;
    }

    public PersonCompanyRelation person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonCompanyRelation)) {
            return false;
        }
        return id != null && id.equals(((PersonCompanyRelation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PersonCompanyRelation{" +
            "id=" + getId() +
            ", relationCode='" + getRelationCode() + "'" +
            "}";
    }
}
