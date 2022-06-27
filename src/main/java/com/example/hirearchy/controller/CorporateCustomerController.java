package com.example.hirearchy.controller;

import com.example.hirearchy.App;
import com.example.hirearchy.model.Customer;
import com.example.hirearchy.model.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CorporateCustomerController implements Initializable {

    mainController controller = new mainController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onHireButtonClick (ActionEvent event){
        controller.loadOption("corporateCustomerHire", event);
    }

    @FXML
    private void onProfileButtonClick (ActionEvent event){
        controller.loadOption("corporateProfile", event);
    }

    @FXML
    public void onFaqButtonClick(ActionEvent event){
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

//    public void onRegularCustomerSearchButtonClick(ActionEvent event) {
//        DB_Operations db = new DB_Operations();
//        ArrayList<Worker> workers = db.search_custom(0,0);
//    }

}
