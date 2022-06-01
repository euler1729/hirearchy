package com.example.hirearchy.controller;

import com.example.hirearchy.model.Customer;
import com.example.hirearchy.model.DB_Operations;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(){
        Customer user = new Customer("abc", "516551", "fasad@gmail.com", 0, "dsafa", 1) {
            @Override
            public boolean editProfile() {
                return false;
            }

            @Override
            public boolean showProfile() {
                return false;
            }
        };
        try{
//            boolean authenticated = DB_Operations.auth("fardin@gmail.com","dsaffa");
//            System.out.println(authenticated);
//            PGSQL.insertRecord(user);
            DB_Operations.db_connect();
        }catch (Exception exp){
            System.out.println(exp);
        }
        welcomeText.setText("Welcome to Hire-Archy");
    }
}