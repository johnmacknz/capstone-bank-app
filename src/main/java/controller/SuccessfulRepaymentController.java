package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SuccessfulRepaymentController {
    @javafx.fxml.FXML
    private Label successfulWithdrawLabel;
    @javafx.fxml.FXML
    private Button backToDashboardButton;
    @javafx.fxml.FXML
    private Label signedInAsLabel;
    @javafx.fxml.FXML
    private Label userNameLabel;
    @javafx.fxml.FXML
    private Button logOutButton;

    public void initialize() {
        String username = LoginController.getUsername();
        userNameLabel.setText(username);
    }

    @javafx.fxml.FXML
    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/dashboard-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Barclava Bank");
        currentStage.close();
        newStage.show();
    }

    @javafx.fxml.FXML
    public void handleLogOutButton(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/app-start-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Barclava Bank");
        currentStage.close();
        newStage.show();
    }
}
