package com.example.hirearchy.controller;


import com.example.hirearchy.model.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import static com.example.hirearchy.controller.RegisterAndLoginPageController.*;
import static com.example.hirearchy.controller.RegisterAndLoginPageController.locationMap;

public class CorporateCustomerController implements Initializable {

    mainController controller = new mainController();
    @FXML
    public ComboBox<String> LocationDropDown2 = new ComboBox<>();
    @FXML
    public ComboBox<String> ProfessionDropDown2 = new ComboBox<>();
    //Query condition field
    ObservableList<String> LocationsList2 = FXCollections.observableArrayList();
    ObservableList<String> ProfessionList2 = FXCollections.observableArrayList();
    //table
    @FXML
    private TableView<TableEntry> ccTable;
    @FXML
    private TableColumn<TableEntry, String> name;
    @FXML
    private TableColumn<TableEntry, String> contact_no;
    @FXML
    private TableColumn<TableEntry, String> email;
    @FXML
    private TableColumn<TableEntry, String> locat;
    @FXML
    private TableColumn<TableEntry, Button> req_btn;
    ObservableList<TableEntry> ccList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i=2; i<professionArr.length; ++i) {
            ProfessionList2.add(professionArr[i]);
        }
        LocationsList2.addAll(Arrays.asList(locationArr));
        LocationDropDown2.setItems(LocationsList2);
        ProfessionDropDown2.setItems(ProfessionList2);

        // table
        name.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("name"));
        contact_no.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        locat.setCellValueFactory(new PropertyValueFactory<>("location"));
        req_btn.setCellValueFactory(new PropertyValueFactory<>("Action"));

//        action.setCellValueFactory(new PropertyValueFactory<>("button"));

        ccTable.setItems(ccList);
    }

    @FXML
    public void onCorporateCustomerSearchButtonClick(ActionEvent event) {
        if(ProfessionDropDown2.getValue()==null && LocationDropDown2.getValue()==null){
            createAlert(new String[]{"Invalid Input","Please Select Both Location and Profession."});
            return;
        }
        ccList.clear();
        DB_Operations db = new DB_Operations();
        ArrayList<Worker> workers = db.search_custom(professionMap.get(ProfessionDropDown2.getValue()),locationMap.get(LocationDropDown2.getValue()));
        for(Worker w:workers){
            ccList.add(new TableEntry(w.getName(),w.getEmail(),w.getContact_no(), w.getLocation(), w.getProfession(), (int) w.getMonthly_rate(), (int) w.getHourly_rate()));
        }
    }


    @FXML
    private void onHireButtonClick (ActionEvent event){
        controller.loadOption("corporateCustomerHire", event);
    }

    @FXML
    private void onProfileButtonClick (ActionEvent event){
        controller.loadOption("corporateProfile", event);
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

}
