module capstonebankapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires org.apache.commons.csv;


    opens capstonebankapp to javafx.fxml;
    exports capstonebankapp;
    opens controller to javafx.fxml;
    exports controller;
}