package com.example.demo.model;

public class Company
{
    private int company_id;
    private String company;


    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Company{" +
                "company_id=" + company_id +
                ", company='" + company + '\'' +
                '}';
    }
}
