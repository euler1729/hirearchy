package com.example.hirearchy.controller;

import com.example.hirearchy.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WorkerHomeController implements Initializable {

//    @FXML
//    public ComboBox<String> LocationDropDown1 = new ComboBox<>();
//    @FXML
//    public ComboBox<String> ProfessionDropDown1 = new ComboBox<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    @FXML
    private void onOffersButtonClick (ActionEvent event){
        loadOption("Customer", event);
    }

    @FXML
    private void onProfileButtonClick (ActionEvent event){
        loadOption("workerprofile", event);
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
}
