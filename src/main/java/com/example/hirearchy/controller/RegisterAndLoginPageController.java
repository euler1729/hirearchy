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
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import static com.example.hirearchy.controller.HomePageController.showCustomerHomePage;
//import static com.example.hirearchy.controller.HomePageController.showWorkerHomePage;
import static com.example.hirearchy.model.Person.locationArr;
import static com.example.hirearchy.model.Person.professionArr;

public class RegisterAndLoginPageController implements Initializable {
    // Scene transition
    private Stage stage;
    private Parent root;

    // dropdown options for register as
    @FXML
    public ComboBox<String> RegisterAsDropdown = new ComboBox<>();
    @FXML
    public ComboBox<String> LocationDropdown = new ComboBox<>();
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

    //DropDown Lists
    ObservableList<String> RegisterAsOptionsList = FXCollections.observableArrayList();
    ObservableList<String> LocationsList = FXCollections.observableArrayList();

    RegularCustomer rc;
    CorporateCustomer cc;
    FullTimeWorker ftw;
    PartTimeWorker ptw;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RegisterAsOptionsList.addAll(Arrays.asList(professionArr));
        LocationsList.addAll(Arrays.asList(locationArr));
        RegisterAsDropdown.setItems(RegisterAsOptionsList);
        LocationDropdown.setItems(LocationsList);
    }
    //Routing to Login Page
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
    //Routing to Register Page
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
    //Form Validation
    boolean validateForm(){
        return true;
    }
    boolean validateForm(String mail, String password){
        if(mail==null || password==null)return false;
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
        return matcher.find();
    }
    //Alert Popup creator
    void createAlert(String [] message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(message[0]);
        alert.setContentText(message[1]);
        alert.show();
    }

    //Button for Requesting Registration
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
                    HomePageController homeForCustomer = new HomePageController();
                    homeForCustomer.showCustomerHomePage(event);
                }
                else {
                    HomePageController homeForCustomer = new HomePageController();
                    homeForCustomer.showCustomerHomePage(event);
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
    //Button for request to Login
    public void onLoginButtonClick(ActionEvent event){
        try{
            if(validateForm(EmailTextField.getText(), PasswordTextField.getText())==false){
                createAlert(new String[]{"Login Failed!", "Wrong Credentials! or wrong format of email/empty password field."});
                return;
            }
            DB_Operations obj = new DB_Operations();
            obj = obj.auth(EmailTextField.getText(),
                    PasswordTextField.getText());
            if(obj.getName()==null || obj.getEmail()==null){
                //show a popup window/message saying "Wrong Credentials"
                createAlert(new String[]{"Login Failed!", "Wrong Credentials!"});
                System.out.println("wrong credentials");
            }
            else{
                System.out.println("working");
                if(obj.getProfession()==0){
                    //Create Object for profession 0(maybe Corporate Customer)
                    //And show suitable page for that user
                    HomePageController homeForCustomer = new HomePageController();
                    homeForCustomer.showCustomerHomePage(event);
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
//                showCustomerHomePage(event);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
