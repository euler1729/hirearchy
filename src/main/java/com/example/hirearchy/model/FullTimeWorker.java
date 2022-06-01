package com.example.hirearchy.model;

public class FullTimeWorker extends Worker{
    private double monthlyRate;
    public FullTimeWorker(String name, String contact_no, String email, int profession, String password, int location) {
        super(name, contact_no, email, profession, password, location);
    }
    public double getMonthlyRate() {
        return monthlyRate;
    }
    public void setMonthlyRate(double monthlyRate) {
        this.monthlyRate = monthlyRate;
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
