package com.example.hirearchy.controller;

public class Worker extends Person{
    public Worker(String name, String contact_no, String email,
                  String profession, String password, int location) {
        super(name, contact_no, email, profession, password, location);
    }
}
