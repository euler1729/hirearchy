package com.example.hirearchy.model;

import com.example.hirearchy.model.Person;

public abstract class Customer extends Person {
    private int HiredCount;
    private int rating;
    public Customer(String name, String contact_no, String email,
                    int profession, String password, int location) {
        super(name, contact_no, email, profession, password, location);
    }

}
