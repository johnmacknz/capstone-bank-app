package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class LoginController {
    @javafx.fxml.FXML
    private Label usenameLabel;
    @javafx.fxml.FXML
    private Label passwordLabel;
    @javafx.fxml.FXML
    private TextField userNameTextField;
    @javafx.fxml.FXML
    private TextField passwordTextField;
    @javafx.fxml.FXML
    private Button loginButton;
    @javafx.fxml.FXML
    private Button backButton;

    @javafx.fxml.FXML
    public void handleLoginButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleBackButton(@NotNull ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/app-start-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        currentStage.close();
        newStage.show();
    }
}
