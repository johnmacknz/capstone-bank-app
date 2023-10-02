module com.example.capstonebankapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.capstonebankapp to javafx.fxml;
    exports com.example.capstonebankapp;
}