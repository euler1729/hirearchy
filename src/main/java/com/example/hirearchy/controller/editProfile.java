package com.example.hirearchy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static com.example.hirearchy.controller.RegisterAndLoginPageController.corporateCustomer;
import static com.example.hirearchy.controller.RegisterAndLoginPageController.regularCustomer;

public class editProfile {
    // edit profile
    @FXML
    public TextField NameTextField;
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

    mainController controller = new mainController();

    @FXML
    private void onHireButtonClick (ActionEvent event){
        controller.loadOption("regularCustomerHire", event);
    }

    @FXML
    private void onProfileButtonClick (ActionEvent event){
        System.out.println("profile");
        System.out.println(regularCustomer.getName());
        NameTextField.setText(regularCustomer.getName());
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
