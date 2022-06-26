package com.example.hirearchy.controller;

import com.example.hirearchy.model.RegularCustomer;
import com.example.hirearchy.model.Worker;
import javafx.event.ActionEvent;

import java.util.ArrayList;

public class RegularCustomerController extends RegularCustomer {
    public RegularCustomerController(String name, String contact_no, String email, int profession, String password, int location) {
        super(name, contact_no, email, profession, password, location);
    }
    public void onRegularCustomerSearchButtonClick(ActionEvent event){
        DB_Operations db = new DB_Operations();
        ArrayList<Worker> workers = db.search_custom(0,0);
    }
}
