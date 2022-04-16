package com.example.hirearchy.controller;

import com.example.hirearchy.App;
import com.example.hirearchy.model.DB_Operations;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterAndLoginPageController implements Initializable {

    // Scene transition
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void RegisterToLoginPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
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

    Person info1;

    public void RegisterButton(ActionEvent event){
        try{
            info1 = new Person( NameTextField.getText(),
                                ContactNoTextField.getText(),
                                EmailTextField.getText(),
                                0,
                                PasswordTextField.getText(),
                                0);

            // Check if password is matched with retyped password
//            ---

            boolean done;

            int occupation = 0; // change later
            DB_Operations entry = new DB_Operations();
            if(occupation == 0)done = entry.insertRecord((Customer) info1);
            else done = entry.insertRecord((Worker) info1);

            if(done == true){
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
}
