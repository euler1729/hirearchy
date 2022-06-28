package com.example.hirearchy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class editProfile {
    // edit profile
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField ContactNoTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField LocationTextField;
    @FXML
    private TextField ProfessionTextField;
    @FXML
    private TextField PasswordTextField;

    mainController controller = new mainController();

    @FXML
    private void onHireButtonClick (ActionEvent event){
        controller.loadOption("regularCustomerHire", event);
    }

    @FXML
    private void onProfileButtonClick (ActionEvent event){
        controller.loadOption("regularcustomerprofile", event);
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
