package com.example.hirearchy.model;

public class PartTimeWorker extends Worker{
    private double HiringRate;
    public PartTimeWorker(String name, String contact_no, String email, int profession, String password, int location) {
        super(name, contact_no, email, profession, password, location);
    }
    public double getHiringRate() {
        return HiringRate;
    }
    public void setHiringRate(double hiringRate) {
        HiringRate = hiringRate;
    }


}
