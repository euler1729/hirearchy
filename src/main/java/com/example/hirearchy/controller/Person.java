package com.example.hirearchy.controller;

public class Person {
    // info about the users
    private String name;
    private String contact_no;
    private String email;
    private String profession;
    private String password;
    private int location;

    public Person(String name, String contact_no, String email, String profession, String password, int location) {
        this.name = name;
        this.contact_no = contact_no;
        this.email = email;
        this.profession = profession;
        this.password = password;
        this.location = location;
    }


    // edit profile.
    // return true if done successfully, false if there's error.
    boolean edit_profile(){

        return true;
    }

}
