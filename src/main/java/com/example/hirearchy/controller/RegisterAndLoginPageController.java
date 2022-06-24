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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.hirearchy.controller.HomePageController.showCustomerHomePage;
import static com.example.hirearchy.controller.HomePageController.showWorkerHomePage;

public class RegisterAndLoginPageController implements Initializable {

    // Scene transition
    private Stage stage;
    private Parent root;

    public void RegisterToLoginPage(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("LoginPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Login to Hirearchy");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public void LoginToRegisterPage(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("RegisterPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Register to Hirearchy");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }


    // dropdown options for register as
    @FXML
    public ComboBox<String> RegisterAsDropdown = new ComboBox<>();
    @FXML
    public ComboBox<String> LocationDropdown = new ComboBox<>();

    ObservableList<String> RegisterAsOptionsList = FXCollections.observableArrayList(
            "Customer",
            "Driver",
            "Electrician",
            "Mechanic",
            "Plumber",
            "Painter"
    );

    ObservableList<String> LocationsList = FXCollections.observableArrayList(
            "Banani",
            "Banasree",
            "Dhanmondi",
            "Farmgate",
            "Gabtoli",
            "Gulshan",
            "Kamalapur",
            "Khilgaon",
            "Mirpur",
            "Mohammadpur",
            "Nilkhet",
            "Shahbag",
            "Shyamoli"
    );

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
    boolean validateForm(){
        return true;
    }
    boolean validateForm(String mail, String password){
        if(mail==null || password==null)return false;
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
        return matcher.find();
    }

    public void onRegisterButtonClick(ActionEvent event){
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

                if(prof <= 1){
                    showCustomerHomePage(event);
                }
                else {
                    showWorkerHomePage(event);
                }
            }
            else {
                System.out.println("Error");
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    void createAlert(String [] message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(message[0]);
        alert.setContentText(message[1]);
        alert.show();
    }
    public void onLoginButtonClick(ActionEvent event){
        try{
            if(validateForm(EmailTextField.getText(), PasswordTextField.getText())==false){
                createAlert(new String[]{"Login Failed!", "Wrong Credentials!"});
                return;
            }
            DB_Operations obj = new DB_Operations();
            obj = obj.auth(EmailTextField.getText(),
                    PasswordTextField.getText());
            if(obj.getName()==null || obj.getEmail()==null){
                //show a popup window/message saying "Wrong Credentials"

                System.out.println("wrong credentials");
            }
            else{
                System.out.println("working");
                if(obj.getProfession()==0){
                    //Create Object for profession 0(maybe Corporate Customer)
                    //And show suitable page for that user
                }
                else if(obj.getProfession()==1){
                    //Create Object for profession 0(maybe Regular Customer)
                    //And show suitable page for that user
                }
                else if(obj.getProfession()>20 && obj.getProfession()<30){
                    //Create Object for profession 0(maybe Fulltime Worker)
                    //And show suitable page for that user
                }
                else if(obj.getProfession()>=30){
                    //Create Object for profession 0(maybe PartTime Worker)
                    //And show suitable page for that user
                }
                showCustomerHomePage(event);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
