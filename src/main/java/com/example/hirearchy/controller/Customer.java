package com.example.hirearchy.controller;

public class Customer extends Person{
    public Customer(String name, String contact_no, String email,
                    int profession, String password, int location) {
        super(name, contact_no, email, profession, password, location);
    }

}
