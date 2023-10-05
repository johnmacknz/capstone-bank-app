module capstonebankapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.csv;
    requires jdk.compiler;
    requires org.jetbrains.annotations;
    requires javafx.graphics;


    exports capstonebankapp;
    opens capstonebankapp to javafx.fxml;
    opens controller to javafx.fxml;
}