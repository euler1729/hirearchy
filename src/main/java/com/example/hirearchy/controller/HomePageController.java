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



public class HomePageController {

    public void showCustomerHomePage(ActionEvent event, int type){
        try{
            FXMLLoader fxmlLoader;
            String fxmlName;
            if(type == 2)fxmlName = "Customer";
            else fxmlName = "corporatecustomer";
            fxmlLoader= new FXMLLoader(App.class.getResource(fxmlName + ".fxml"));
//            else fxmlLoader = new FXMLLoader(App.class.getResource("corporatecustomer.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Welcome Home");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.getStackTrace();
            System.out.println("here");
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
