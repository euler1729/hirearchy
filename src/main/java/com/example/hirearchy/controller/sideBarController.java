package com.example.hirearchy.controller;

import com.example.hirearchy.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class sideBarController {
    private ActionEvent event;

    @FXML
    private void onHireButtonClick (ActionEvent event){
        loadOption("Customer", event);
    }

    @FXML
    private void onProfileButtonClick (ActionEvent event){
        loadOption("profile", event);
    }

    @FXML
    public void onFaqButtonClick(ActionEvent event){
        loadOption("faq", event);
    }

    @FXML
    private void onAboutButtonClick (ActionEvent event){
        loadOption("about", event);
    }

    @FXML
    private void onLogoutButtonClick(ActionEvent event){
        loadOption("LoginPage", event);
    }

    private void loadOption(String opt, ActionEvent event) {
        Scene scene;
        Stage stage;
        FXMLLoader fxmlLoader;
        try{
            fxmlLoader = new FXMLLoader(App.class.getResource(opt + ".fxml"));
            scene = new Scene(fxmlLoader.load());
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            e.getStackTrace();
        }
    }
}
