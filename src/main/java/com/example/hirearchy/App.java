package com.example.hirearchy;

import com.example.hirearchy.controller.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();

        String profession;
        Person person;


    }

    public static void main(String[] args) {
        launch();
    }
}