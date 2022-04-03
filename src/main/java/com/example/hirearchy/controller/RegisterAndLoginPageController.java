package com.example.hirearchy.controller;

import com.example.hirearchy.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterAndLoginPageController implements Initializable {

    // Scene transition
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void RegisterToLoginPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Login to Hirearchy");
        stage.setScene(scene);
        stage.show();
    }

    public void LoginToRegisterPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("RegisterPage.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Register to Hirearchy");
        stage.setScene(scene);
        stage.show();
    }

    // dropdown options for register as

    @FXML
    public ComboBox<String> RegisterAsDropdown = new ComboBox<>();
    public ComboBox<String> LocationDropdown = new ComboBox<>();

    ObservableList<String> RegisterAsOptionsList = FXCollections.observableArrayList("Customer",
                                                                                    "Electrician", "Plumber",
                                                                                    "Painter", "Driver",
                                                                                    "Mechanic");
    ObservableList<String> LocationsList = FXCollections.observableArrayList("Mirpur",
            "Gabtoli", "Shyamoli",
            "Dhanmondi", "Gulshan",
            "Banani", "Mohammadpur", "Nilkhet", "Banasri", "Kamalapur", "Khilgaon", "Farmgate", "Shahbag");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RegisterAsDropdown.setItems(RegisterAsOptionsList);
        LocationDropdown.setItems(LocationsList);
    }
}
