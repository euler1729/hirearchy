package com.example.hirearchy.controller;

import com.example.hirearchy.model.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
    // table
    @FXML
    private TableView<TableEntry> rcTable;
    @FXML
    private TableColumn<TableEntry, String> name;
    @FXML
    private TableColumn<TableEntry, String> contact_no;
    @FXML
    private TableColumn<TableEntry, String> email;
    @FXML
    private TableColumn<TableEntry, String> locat;
    @FXML
    private TableColumn<TableEntry, Double> monthly_rate;
    @FXML
    private TableColumn<TableEntry,Double> hourly_rate;
    @FXML
    private TableColumn<TableEntry,Button>req_btn;


    ObservableList<TableEntry> rcList = FXCollections.observableArrayList();

//    public RegularCustomerController(TableColumn<Worker, Button> action) {
//        this.action = action;
//    }

    @FXML
    public void onRegularCustomerSearchButtonClick(ActionEvent event) {
        if(ProfessionDropDown1.getValue()==null && LocationDropDown1.getValue()==null){
            createAlert(new String[]{"Invalid Input","Please Select Both Location and Profession."});
            return;
        }
        rcList.clear();
        DB_Operations db = new DB_Operations();
        ArrayList<Worker> workers = db.search_custom(professionMap.get(ProfessionDropDown1.getValue()),locationMap.get(LocationDropDown1.getValue()));
        for(Worker w:workers){
            rcList.add(new TableEntry(w.getName(),w.getEmail(),w.getContact_no(), w.getLocation(), w.getProfession(),w.getMonthly_rate(),w.getHourly_rate()));
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
        name.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("name"));
        contact_no.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        locat.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthly_rate.setCellValueFactory(new PropertyValueFactory<>("monthly_rate"));
        hourly_rate.setCellValueFactory(new PropertyValueFactory<>("hourly_rate"));
//        action.setCellValueFactory(new PropertyValueFactory<>("button"));
        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<TableEntry, String>, TableCell<TableEntry, String>> cellFactory
                = //
                new Callback<TableColumn<TableEntry, String>, TableCell<TableEntry, String>>() {
                    @Override
                    public TableCell call(final TableColumn<TableEntry, String> param) {
                        final TableCell<TableEntry, String> cell = new TableCell<TableEntry, String>() {

                            final Button btn = new Button("Just Do It");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(btn);
                                    setText("yes");
                                } else {
                                    btn.setOnAction(event -> {
//                                        Person person = getTableView().getItems().get(getIndex());
//                                        System.out.println(person.getFirstName()
//                                                + "   " + person.getLastName());
                                        System.out.println("button clicked");
                                    });
                                    setGraphic(btn);
                                    setText("yes");
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory(cellFactory);

        rcTable.setItems(rcList);
        rcTable.getColumns().add(actionCol);

//        rcTable.setItems(rcList);
    }


    @FXML
    private void onHireButtonClick (ActionEvent event){
        controller.loadOption("regularCustomerHire", event);
    }

    @FXML
    private void onProfileButtonClick (ActionEvent event){
        controller.loadOption("regularcustomerprofile", event);
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


