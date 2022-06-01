module com.example.hirearchy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires org.jetbrains.annotations;

    opens com.example.hirearchy to javafx.fxml;
    exports com.example.hirearchy;
    exports com.example.hirearchy.model;
    opens com.example.hirearchy.model to javafx.fxml;
    exports com.example.hirearchy.controller;
    opens com.example.hirearchy.controller to javafx.fxml;
}