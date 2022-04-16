package com.example.hirearchy.model;

import com.example.hirearchy.model.Person;

public abstract class Worker extends Person {
    private String experience;
    private double rating;
    public Worker(String name, String contact_no, String email,
                  int profession, String password, int location) {
        super(name, contact_no, email, profession, password, location);
    }
}
