package com.example.hirearchy.controller;

import com.example.hirearchy.App;
import com.example.hirearchy.model.RegularCustomer;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.ResourceBundle;

import static com.example.hirearchy.controller.RegisterAndLoginPageController.locationArr;
import static com.example.hirearchy.controller.RegisterAndLoginPageController.professionArr;

public class RegularCustomerController implements Initializable {

//    public void onRegularCustomerSearchButtonClick(ActionEvent event){
//        DB_Operations db = new DB_Operations();
//        ArrayList<Worker> workers = db.search_custom(0,0);
//    }

    mainController controller = new mainController();

    @FXML
    public ComboBox<String> LocationDropDown1 = new ComboBox<>();
    @FXML
    public ComboBox<String> ProfessionDropDown1 = new ComboBox<>();
    //Query condition field
    ObservableList<String> LocationsList1 = FXCollections.observableArrayList();
    ObservableList<String> ProfessionList1 = FXCollections.observableArrayList();

    @FXML
    private TableView<RegularCustomer> rcTable;


    //table

    @FXML
    private TableColumn<RegularCustomer, String> name;

    @FXML
    private TableColumn<RegularCustomer, String> contact_no;

    @FXML
    private TableColumn<RegularCustomer, String> email;

    @FXML
    private TableColumn<RegularCustomer, String> locat;

    ObservableList<RegularCustomer> rcList = FXCollections.observableArrayList(
            new RegularCustomer("nafi", "01915311111", "n@yahoo.com", 4, "", 4),
            new RegularCustomer("hafi", "01951541112", "a@yahoo.com", 1, "", 6),
            new RegularCustomer("kafi", "01944111122", "b@yahoo.com", 0, "", 5)
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i=2; i<professionArr.length; ++i) {
            ProfessionList1.add(professionArr[i]);
        }
        LocationsList1.addAll(Arrays.asList(locationArr));
        LocationDropDown1.setItems(LocationsList1);
        ProfessionDropDown1.setItems(ProfessionList1);

        //table
        name.setCellValueFactory(new PropertyValueFactory<RegularCustomer, String>("name"));
        contact_no.setCellValueFactory(new PropertyValueFactory<RegularCustomer, String>("contact_no"));
        email.setCellValueFactory(new PropertyValueFactory<RegularCustomer, String>("email"));
        locat.setCellValueFactory(new PropertyValueFactory<RegularCustomer, String>("location"));

        rcTable.setItems(rcList);
    }


    @FXML
    private void onHireButtonClick (ActionEvent event){
        controller.loadOption("Worker", event);
    }

    @FXML
    private void onProfileButtonClick (ActionEvent event){
        controller.loadOption("workerprofile", event);
    }

    @FXML
    public void onFaqButtonClick(ActionEvent event){
        controller.loadOption("faq", event);
    }

    @FXML
    private void onAboutButtonClick (ActionEvent event){
        controller.loadOption("about", event);
    }

    @FXML
    private void onLogoutButtonClick(ActionEvent event){
        controller.loadOption("LoginPage", event);
    }

//    public void onRegularCustomerSearchButtonClick(ActionEvent event) {
//        DB_Operations db = new DB_Operations();
//        ArrayList<Worker> workers = db.search_custom(0,0);
//    }


}
