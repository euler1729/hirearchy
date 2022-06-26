package com.example.hirearchy.controller;

import com.example.hirearchy.model.Customer;

public class CorporateCustomerController extends Customer {

    public CorporateCustomerController(String name, String contact_no, String email, int profession, String password, int location) {
        super(name, contact_no, email, profession, password, location);
    }

    @Override
    public void searchWorker() {

    }

    @Override
    public boolean editProfile() {
        return false;
    }

    @Override
    public boolean showProfile() {
        return false;
    }
}
