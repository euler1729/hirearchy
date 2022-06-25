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
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.hirearchy.model.Person.*;

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

    //User Object
    public RegularCustomer regularCustomer;
    public CorporateCustomer corporateCustomer;
    public FullTimeWorker fullTimeWorker;
    public PartTimeWorker partTimeWorker;

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

    //Created Alert for Error Handling
    void createAlert(String [] message){
        //Alert Popup creator
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(message[0]);
        alert.setContentText(message[1]);
        alert.show();
    }
    //Form Validation
    boolean validateForm(String mail, String password){
        if(mail==null || password==null)return false;
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
        return matcher.find();
    }
    boolean validateForm(){
        if(!PasswordTextField.getText().equals(RePasswordTextField.getText())){
            createAlert(new String[]{"Invalid Input","Passwords doesn't matches!"});
            return false;
        }
        if(NameTextField.getText().length()<3 || ContactNoTextField.getText().length()<3 || EmailTextField.getText().length()<3){
            createAlert(new String[]{"Invalid Input","No Input Field Can be empty!"});
            return false;
        }
        if(RegisterAsDropdown.getValue()==null || LocationDropdown.getValue()==null){
            createAlert(new String[]{"Invalid Input","RegisterAs/Location can't be empty."});
            return false;
        }
        if(!validateForm(EmailTextField.getText(),PasswordTextField.getText())){
            createAlert(new String[]{"Invalid Input", "Invalid Email/Password Format!"});
            return false;
        };
        return true;
    }

    //Button for Requesting Registration
    public void onRegisterButtonClick(ActionEvent event){
        try{
            boolean valid = validateForm();
            boolean done = true;
            if(valid){
                DB_Operations entry = new DB_Operations();
                String name = NameTextField.getText();
                String contact = ContactNoTextField.getText();
                String email = EmailTextField.getText();
                int profession = professionMap.get(RegisterAsDropdown.getValue());
                int location = locationMap.get(LocationDropdown.getValue());
                String password = PasswordTextField.getText();

                if(profession==0){
                    corporateCustomer = new CorporateCustomer(name,contact,email,profession,password,location);
                    done = entry.insertRecord(corporateCustomer);
                }
                else if(profession==1){
                    regularCustomer = new RegularCustomer(name,contact,email,profession,password,location);
                    done = entry.insertRecord(regularCustomer);
                }
                else {
                    fullTimeWorker = new FullTimeWorker(name, contact, email, profession, password, location);
                    done = entry.insertRecord(fullTimeWorker);
                }
                if(done){
                    System.out.println("Done");
                    if(profession<2){
                        HomePageController homeForCustomer = new HomePageController();
                        homeForCustomer.showCustomerHomePage(event);
                    }
                    else {
                        HomePageController homeForCustomer = new HomePageController();
                        homeForCustomer.showWorkerHomePage(event);
                    }
                }
                else {
                    createAlert(new String[]{"Invalid Registration.","User may already exist."});
                }
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
