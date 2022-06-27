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

    TableEntry(String name, String email, String contact_no, Integer location, Integer profession){
        this.name = name;
        this. email = email;
        this. contact_no = contact_no;
        int l = 0, p = 0;
        if(locationMap.get(location) != null)l = locationMap.get(location);
        if(professionMap.get(profession) != null)l = professionMap.get(profession);
        this.location = locationArr[l];
        this.profession = professionArr[p];
        this.button = new Button("Action");
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
