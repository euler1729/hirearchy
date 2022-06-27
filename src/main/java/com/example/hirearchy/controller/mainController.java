package com.example.hirearchy.controller;

import com.example.hirearchy.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.hirearchy.controller.RegisterAndLoginPageController.*;

public class mainController {

    public void loadOption(String fileName, ActionEvent event) {
        Scene scene;
        Stage stage;
        FXMLLoader fxmlLoader;
        try{
            fxmlLoader = new FXMLLoader(App.class.getResource(fileName + ".fxml"));
            scene = new Scene(fxmlLoader.load());
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(fileName);
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            e.getStackTrace();
        }
    }

    @FXML
    public void onBackButtonClick(ActionEvent event){
        String filename;
        if(regularCustomer != null){
            filename = "regularCustomerHire";
        }
        else if(corporateCustomer != null){
            filename = "corporateCustomerHire";
        }
        else {
            filename = "Worker";
        }
        loadOption(filename, event);
    }

}
