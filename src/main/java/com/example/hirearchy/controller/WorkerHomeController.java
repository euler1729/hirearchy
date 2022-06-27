package com.example.hirearchy.controller;

import com.example.hirearchy.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class WorkerHomeController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    mainController controller = new mainController();

    @FXML
    private void onOffersButtonClick (ActionEvent event){
        controller.loadOption("Worker", event);
    }

    @FXML
    private void onProfileButtonClick (ActionEvent event){
        controller.loadOption("workerprofile", event);
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
