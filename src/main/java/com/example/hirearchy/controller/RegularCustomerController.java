package com.example.hirearchy.controller;

import com.example.hirearchy.model.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static com.example.hirearchy.controller.RegisterAndLoginPageController.*;


public class RegularCustomerController implements Initializable {

    mainController controller = new mainController();

    @FXML
    public ComboBox<String> LocationDropDown1 = new ComboBox<>();
    @FXML
    public ComboBox<String> ProfessionDropDown1 = new ComboBox<>();
    //Query condition field
    ObservableList<String> LocationsList1 = FXCollections.observableArrayList();
    ObservableList<String> ProfessionList1 = FXCollections.observableArrayList();

    @FXML
    private TableView<TableEntry> rcTable;

    //table

    @FXML
    private TableColumn<Worker, String> name;

    @FXML
    private TableColumn<Worker, String> contact_no;

    @FXML
    private TableColumn<Worker, String> email;

    @FXML
    private TableColumn<Worker, String> locat;

//    @FXML
//    private TableColumn<Worker, Button> action;


    ObservableList<TableEntry> rcList = FXCollections.observableArrayList();

//    public RegularCustomerController(TableColumn<Worker, Button> action) {
//        this.action = action;
//    }

    public void onRegularCustomerSearchButtonClick(ActionEvent event) {
        if(ProfessionDropDown1.getValue()==null && LocationDropDown1.getValue()==null){
            createAlert(new String[]{"Invalid Input","Please Select Both Location and Profession."});
            return;
        }
        rcList.clear();
        DB_Operations db = new DB_Operations();
        ArrayList<Worker> workers = db.search_custom(professionMap.get(ProfessionDropDown1.getValue()),locationMap.get(LocationDropDown1.getValue()));
        for(Worker w:workers){
            rcList.add(new TableEntry(w.getName(),w.getEmail(),w.getContact_no(), w.getLocation(), w.getProfession()));
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i=2; i<professionArr.length; ++i) {
            ProfessionList1.add(professionArr[i]);
        }
        LocationsList1.addAll(Arrays.asList(locationArr));
        LocationDropDown1.setItems(LocationsList1);
        ProfessionDropDown1.setItems(ProfessionList1);

        //table
        name.setCellValueFactory(new PropertyValueFactory<Worker, String>("name"));
        contact_no.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        locat.setCellValueFactory(new PropertyValueFactory<>("location"));
//        action.setCellValueFactory(new PropertyValueFactory<>("button"));

        rcTable.setItems(rcList);
    }


    @FXML
    private void onHireButtonClick (ActionEvent event){
        controller.loadOption("Customer", event);
    }

    @FXML
    private void onProfileButtonClick (ActionEvent event){
        controller.loadOption("Customer", event);
    }

    @FXML
    private void onFaqButtonClick(ActionEvent event){
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

}


