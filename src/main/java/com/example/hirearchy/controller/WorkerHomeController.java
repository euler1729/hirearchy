package com.example.hirearchy.controller;

import com.example.hirearchy.App;
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

public class WorkerHomeController implements Initializable{

    mainController controller = new mainController();

    //table
    @FXML
    private TableView<TableEntry> kkTable;
    @FXML
    private TableColumn<TableEntry, String> name;
    @FXML
    private TableColumn<TableEntry, String> contact_no;
    @FXML
    private TableColumn<TableEntry, String> email;
    @FXML
    private TableColumn<TableEntry, String> locat;

    ObservableList<TableEntry> kkList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // table
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        contact_no.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        locat.setCellValueFactory(new PropertyValueFactory<>("location"));

        kkTable.setItems(kkList);
    }

    @FXML
    public void seeOfferClick(ActionEvent event){
        kkList.clear();
        DB_Operations db = new DB_Operations();
        System.out.println(workerUser.getEmail());
        ArrayList<DB_Operations> list = db.get_worker_history(workerUser.getEmail());
        for(DB_Operations w: list){
            kkList.add(new TableEntry(w.getName(),w.getEmail(),"01553635206",1,3,0,0));
        }
    }

    @FXML
    private void onOffersButtonClick (ActionEvent event){
        controller.loadOption("Worker", event);
    }

    @FXML
    private void onProfileButtonClick (ActionEvent event){
        controller.loadOption("workerprofile", event);
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
