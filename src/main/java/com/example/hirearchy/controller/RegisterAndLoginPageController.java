package com.example.hirearchy.controller;

import com.example.hirearchy.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterAndLoginPageController {
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
}
