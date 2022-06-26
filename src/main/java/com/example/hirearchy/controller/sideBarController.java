package com.example.hirearchy.controller;

import com.example.hirearchy.App;
import com.example.hirearchy.model.Worker;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static com.example.hirearchy.controller.RegisterAndLoginPageController.locationArr;
import static com.example.hirearchy.controller.RegisterAndLoginPageController.professionArr;

public class sideBarController implements Initializable {

    @FXML
    public ComboBox<String> LocationDropDown1 = new ComboBox<>();
    @FXML
    public ComboBox<String> ProfessionDropDown1 = new ComboBox<>();
    //Query condition field
    ObservableList<String> LocationsList1 = FXCollections.observableArrayList();
    ObservableList<String> ProfessionList1 = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i=2; i<professionArr.length; ++i) {
            ProfessionList1.add(professionArr[i]);
        }
        LocationsList1.addAll(Arrays.asList(locationArr));
        LocationDropDown1.setItems(LocationsList1);
        ProfessionDropDown1.setItems(ProfessionList1);
    }



    private ActionEvent event;

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

    public void onRegularCustomerSearchButtonClick(ActionEvent event) {
        DB_Operations db = new DB_Operations();
        ArrayList<Worker> workers = db.search_custom(0,0);
    }
}
