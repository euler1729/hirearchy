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
        Person user = new Person("abc", "516551","fasad@gmail.com",0,"dsafa",1);
        try{
            PGSQL.insertRecord(user);
        }catch (Exception exp){
            System.out.println(exp);
        }
        welcomeText.setText("Welcome to Hire-Archy");
    }
}