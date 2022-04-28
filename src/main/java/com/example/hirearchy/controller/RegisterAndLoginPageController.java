package com.example.hirearchy.controller;

import com.example.hirearchy.App;
import com.example.hirearchy.model.*;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

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
    @FXML
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



    //Register and getting info

    @FXML
    private TextField NameTextField;
    @FXML
    private TextField ContactNoTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField PasswordTextField;
    @FXML
    private TextField RePasswordTextField;

    RegularCustomer rc;
    CorporateCustomer cc;
    FullTimeWorker ftw;
    PartTimeWorker ptw;

    public void RegisterButton(ActionEvent event){
        try{
            rc = new RegularCustomer(NameTextField.getText(),
                    ContactNoTextField.getText(),
                    EmailTextField.getText(),
                    0,
                    PasswordTextField.getText(),
                    0);

            cc = new CorporateCustomer(NameTextField.getText(),
                    ContactNoTextField.getText(),
                    EmailTextField.getText(),
                    0,
                    PasswordTextField.getText(),
                    0);

            ftw = new FullTimeWorker(NameTextField.getText(),
                    ContactNoTextField.getText(),
                    EmailTextField.getText(),
                    0,
                    PasswordTextField.getText(),
                    0);

            ptw = new PartTimeWorker(NameTextField.getText(),
                    ContactNoTextField.getText(),
                    EmailTextField.getText(),
                    0,
                    PasswordTextField.getText(),
                    0);
            // Check if password is matched with retyped password
//            ---

            boolean done;

            int prof = 0; // change later

            DB_Operations entry = new DB_Operations();
            if(prof == 0)done = entry.insertRecord(rc);
            else if(prof == 1)done = entry.insertRecord(cc);
            else if(prof == 2)done = entry.insertRecord(ftw);
            else done = entry.insertRecord(ptw);

            if(done){
                System.out.println("Done");
            }
            else {
                System.out.println("Error");
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void loginButton(ActionEvent event){
        try{
            DB_Operations obj = DB_Operations.auth(EmailTextField.getText(),
                                                   PasswordTextField.getText());
            if(obj==null){
                //show a popup window/message saying "Wrong Credentials"
            }
            else{
//                return obj;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
