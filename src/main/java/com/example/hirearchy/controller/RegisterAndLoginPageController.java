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
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterAndLoginPageController implements Initializable {

    // Scene transition
    private Stage stage;
    private Parent root;
    public static String [] professionArr = {
            "Corporate Customer",
            "Regular Customer",
            "Driver",
            "Electrician",
            "Mechanic",
            "Plumber",
            "Painter"
    };
    public static String [] locationArr = {
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
    };

    public static HashMap<String, Integer> professionMap = new HashMap<String, Integer>();
    public static HashMap<String, Integer> locationMap = new HashMap<String, Integer>();
    static{
        for(int i=0; i<professionArr.length; ++i){
            professionMap.put(professionArr[i],i);
        }
        for(int i=0; i<locationArr.length; ++i){
            locationMap.put(locationArr[i],i);
        }
    }

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
    public static RegularCustomer regularCustomer = null;
    public static CorporateCustomer corporateCustomer = null;
    public static Worker workerUser = null;

    //Controller Object
//    public RegularCustomerController regularCustomerController = null;
//    public CorporateCustomerController corporateCustomerController = null;
//    public WorkerController workerController = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RegisterAsOptionsList.addAll(Arrays.asList(professionArr));
        LocationsList.addAll(Arrays.asList(locationArr));
        RegisterAsDropdown.setItems(RegisterAsOptionsList);
        LocationDropdown.setItems(LocationsList);
    }

    //Routing to Login Page
    @FXML
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
    @FXML
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
    public static void createAlert(String [] message){
        //Alert Popup creator
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(message[0]);
        alert.setContentText(message[1]);
        alert.show();
    }
    //Form Validation
    boolean validateForm(String mail, String password){
        if(mail==null || password==null)return true;
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
        return !matcher.find();
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
        if(validateForm(EmailTextField.getText(), PasswordTextField.getText())){
            createAlert(new String[]{"Invalid Input", "Invalid Email/Password Format!"});
            return false;
        };
        return true;
    }

    //Button for Requesting Registration
    @FXML
    public void onRegisterButtonClick(ActionEvent event){
        try{
            boolean valid = validateForm();
            if(valid){
                boolean done;
                DB_Operations entry = new DB_Operations();
                String name = NameTextField.getText();
                String contact = ContactNoTextField.getText();
                String email = EmailTextField.getText().toLowerCase();
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
                    workerUser = new Worker(name, contact, email, profession, password, location);
                    done = entry.insertRecord(workerUser);
                }
                if(done){
                    System.out.println("Done");
                    if(profession<2){
                        HomePageController homeForCustomer = new HomePageController();
                        homeForCustomer.showCustomerHomePage(event, profession + 1);
                    }
                    else {
                        HomePageController homeForWorker = new HomePageController();
                        homeForWorker.showWorkerHomePage(event);
                    }
                }
                else {
                    createAlert(new String[]{"Invalid Registration.","User may already exist."});
                }
            }
            else{
                createAlert(new String[]{"Invalid Input.","Please Check Input and Try Again!"});
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    //Button for request to Login
    @FXML
    public void onLoginButtonClick(ActionEvent event){
        try{
            if(validateForm(EmailTextField.getText(), PasswordTextField.getText())){
                createAlert(new String[]{"Error!","Wrong Credentials!\nPlease enter valid email & password."});
                return;
            }
            DB_Operations db = new DB_Operations();
            db = db.auth(EmailTextField.getText().toLowerCase(), PasswordTextField.getText());
            if(db.getName()==null || db.getEmail()==null){
                createAlert(new String[]{"Login Failed!", "Wrong Credentials!"});
                System.out.println("wrong credentials");
            }
            else{
                System.out.println("working");
                if(db.getProfession()==0){//For Corporate Customer
                    corporateCustomer = new CorporateCustomer(db.getName(),db.getContact(),db.getEmail(),db.getProfession(),"",db.getLocation());
                    HomePageController homeForCustomer = new HomePageController();
                    homeForCustomer.showCustomerHomePage(event, 1);
                }
                else if(db.getProfession()==1){//For Regular Customer
                    regularCustomer = new RegularCustomer(db.getName(),db.getContact(),db.getEmail(),db.getProfession(),"",db.getLocation());
                    HomePageController homeForCustomer = new HomePageController();
                    homeForCustomer.showCustomerHomePage(event, 2);
                }
                else{//For Worker
                    workerUser = new Worker(db.getName(),db.getContact(),db.getEmail(),db.getProfession(),"",db.getLocation());
                    HomePageController homeForWorker = new HomePageController();
                    homeForWorker.showWorkerHomePage(event);
                }

            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
