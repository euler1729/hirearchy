package com.example.hirearchy.controller;

public class Person {
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
////⭐️⭐⭐️⭐️️⭐️Getters and Setters⭐️⭐️⭐️⭐️/////////////
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }



}
