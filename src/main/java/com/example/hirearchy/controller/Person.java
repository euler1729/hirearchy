package com.example.hirearchy.controller;

public class Person {
    // info about the users
    private String name;
    private String contact_no;
    private String email;
    private String profession;
    private String password;
    private int location;
    //


    // edit profile.
    // return true if done successfully, false if there's error.
    boolean edit_profile(){

        return true;
    }
}

class Customer extends Person{

    // search option for customers
    boolean query(){

        return true;
    }
}


class Worker extends Person{

}
