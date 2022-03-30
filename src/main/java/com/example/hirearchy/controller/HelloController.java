package com.example.hirearchy.controller;

import com.example.hirearchy.model.PGSQL;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(){
        Person user = new Person("abc", "516551","fardin@gmail.com",0,"dsafa",1);
        try{
            boolean authenticated = PGSQL.authenticate("fardin@gmail.com","dsafa");
            System.out.println(authenticated);
        }catch (Exception exp){
            System.out.println(exp);
        }
        welcomeText.setText("Welcome to Hire-Archy");
    }
}