package com.example.hirearchy.model;

public class CorporateCustomer extends Customer{
    public CorporateCustomer(String name, String contact_no, String email, int profession, String password, int location) {
        super(name, contact_no, email, profession, password, location);
    }

    @Override
    public boolean editProfile() {
        return false;
    }

    @Override
    public boolean showProfile() {
        return false;
    }

    public String ShowHireGroup(){

        return "";
    }
    public String HireGroup(){

        return "";
    }
}
