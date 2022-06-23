package com.example.hirearchy.controller;
import com.example.hirearchy.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    public static void showCustomerHomePage(ActionEvent event){
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

    public static void showWorkerHomePage(ActionEvent event){
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
