package com.example.hirearchy.controller;

import com.example.hirearchy.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainController {

    public void loadOption(String opt, ActionEvent event) {
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
