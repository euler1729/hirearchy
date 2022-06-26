package com.example.hirearchy.model;

public class Worker extends Person {
    private String experience;
    private double monthly_rate;
    private double hourly_rate;
    private double rating;

    public Worker(String name, String contact_no, String email, int profession, String password, int location) {
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    public void offers(){};
}
