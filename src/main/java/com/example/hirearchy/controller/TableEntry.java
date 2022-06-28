package com.example.hirearchy.controller;

import javafx.scene.control.Button;

import static com.example.hirearchy.controller.RegisterAndLoginPageController.*;

public class TableEntry {
    private String name;
    private String contact_no;
    private String location;
    private String email;
    private String profession;
    private Button button;
    private double monthly_rate;
    private double hourly_rate;

    public double getMonthly_rate() {
        return monthly_rate;
    }

    TableEntry(String name, String email, String contact_no, Integer location, Integer profession, double monthly_rate, double hourly_rate){
        this.name = name;
        this. email = email;
        this. contact_no = contact_no;
        int l = 0, p = 0;
//        if(locationMap.get(location) != null)l = locationMap.get(location);
//        if(professionMap.get(profession) != null)p = professionMap.get(profession);
        this.location = locationArr[location];
        this.profession = professionArr[profession];
        this.monthly_rate = monthly_rate;
        this.hourly_rate = hourly_rate;
        this.button = new Button("send");
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
