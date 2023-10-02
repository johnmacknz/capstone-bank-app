module capstonebankapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens capstonebankapp to javafx.fxml;
    exports capstonebankapp;
    opens controller to javafx.fxml;
    exports controller;
}