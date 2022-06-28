package com.example.hirearchy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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


    // edit profile
    @FXML
    public TextField OrgNameTextField;
    @FXML
    public TextField ContactNoTextField;
    @FXML
    public TextField EmailTextField;
    @FXML
    public TextField LocationTextField;
    @FXML
    public TextField ProfessionTextField;
    @FXML
    public TextField PasswordTextField;

    @FXML
    public void onUpdateButtonClick(ActionEvent event){

    }
}
