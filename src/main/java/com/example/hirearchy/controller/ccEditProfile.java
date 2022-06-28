package com.example.hirearchy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ccEditProfile {

    mainController controller = new mainController();

    @FXML
    private void onHireButtonClick (ActionEvent event){
        controller.loadOption("regularCustomerHire", event);
    }

    @FXML
    private void onProfileButtonClick (ActionEvent event){
        controller.loadOption("corporateProfile", event);
    }

    @FXML
    private void onFaqButtonClick(ActionEvent event){
        controller.loadOption("faq", event);
    }

    @FXML
    private void onAboutButtonClick (ActionEvent event){
        controller.loadOption("about", event);
    }

    @FXML
    private void onLogoutButtonClick(ActionEvent event){
        controller.loadOption("LoginPage", event);
    }
}
