package com.example.demo.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Employee
{
    private int id;
    private String firstname;
    private String lastname;
    private String contactemail;
    private String companyemail;
    private Date birthdate;
    private Date hireddate;

    private Address address = new Address();
    private List<Skill> skills;
    //need to tie these in
    private List<Company> companies;



    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getHireddate() {
        return hireddate;
    }

    public void setHireddate(Date hireddate) {
        this.hireddate = hireddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getContactemail() {
        return contactemail;
    }

    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", contactemail='" + contactemail + '\'' +
                ", companyemail='" + companyemail + '\'' +
                ", birthdate=" + birthdate +
                ", hireddate=" + hireddate +
                ", address=" + address +
                ", skills=" + skills +
                ", companies=" + companies +
                '}';
    }
}
