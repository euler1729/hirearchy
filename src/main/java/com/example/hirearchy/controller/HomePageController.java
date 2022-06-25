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

import static com.example.hirearchy.model.Person.professionArr;

public class HomePageController implements Initializable {

    @FXML
    public ComboBox<String> LocationDropDown = new ComboBox<>();
    @FXML
    public ComboBox<String> ProfessionDropDown = new ComboBox<>();
    //Query condition field
    ObservableList<String> LocationsList = FXCollections.observableArrayList();
    ObservableList<String> ProfessionList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocationsList.addAll(Arrays.asList(professionArr));
        ProfessionList.addAll(Arrays.asList(professionArr));
        LocationDropDown.setItems(LocationsList);
        ProfessionDropDown.setItems(ProfessionList);
    }

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
