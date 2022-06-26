package com.example.hirearchy.controller;

import com.example.hirearchy.App;
import com.example.hirearchy.model.Customer;
import com.example.hirearchy.model.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CorporateCustomerController extends Customer implements Initializable {

    public CorporateCustomerController(String name, String contact_no, String email, int profession, String password, int location) {
        super(name, contact_no, email, profession, password, location);
    }

    @Override
    public void searchWorker() {

    }

    @Override
    public boolean editProfile() {
        return false;
    }

    @Override
    public boolean showProfile() {
        return false;
    }

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
    private void onHireButtonClick (ActionEvent event){
        loadOption("Customer", event); // change when corp. customer fxml fixed
    }

    @FXML
    private void onProfileButtonClick (ActionEvent event){
        loadOption("corporatecustoer", event);
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

//    public void onRegularCustomerSearchButtonClick(ActionEvent event) {
//        DB_Operations db = new DB_Operations();
//        ArrayList<Worker> workers = db.search_custom(0,0);
//    }

}
