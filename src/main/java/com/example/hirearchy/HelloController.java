package com.example.hirearchy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        PGSQL.Connect();
        welcomeText.setText("Welcome to Hire-Archy");
    }
}