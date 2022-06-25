package com.example.hirearchy.controller;
import com.example.hirearchy.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import static com.example.hirearchy.controller.RegisterAndLoginPageController.locationArr;
import static com.example.hirearchy.controller.RegisterAndLoginPageController.professionArr;


public class HomePageController {

    public void showCustomerHomePage(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Customer.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Welcome Home");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void showWorkerHomePage(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Worker.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Welcome Home");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
