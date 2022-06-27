package com.example.hirearchy.model;

public class Worker extends Person {
    private String experience;
    private double monthly_rate;
    private double hourly_rate;
    private double rating;

    public Worker(String name, String contact_no, String email, int profession, String password, int location, double monthly_rate, double hourly_rate) {
        super(name, contact_no, email, profession, password, location);
        this.monthly_rate = monthly_rate;
        this.hourly_rate = hourly_rate;

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
    public double getMonthly_rate() {
        return monthly_rate;
    }
    public void setMonthly_rate(double monthly_rate) {
        this.monthly_rate = monthly_rate;
    }
    public double getHourly_rate() {
        return hourly_rate;
    }
    public void setHourly_rate(double hourly_rate) {
        this.hourly_rate = hourly_rate;
    }
}
