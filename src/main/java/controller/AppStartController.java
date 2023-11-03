package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppStartController implements Initializable {
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    @FXML
    public Label time;
    @FXML
    private ImageView barclavaImageView1;

    @FXML
    public void handleLoginButton(@NotNull ActionEvent actionEvent) throws IOException {
        ControllerLogic.openNewScene(actionEvent, "/capstonebankapp/login-scene.fxml");
    }

    @FXML
    public void handleSignUpButton(@NotNull ActionEvent actionEvent) throws IOException {
        ControllerLogic.openNewScene(actionEvent, "/capstonebankapp/sign-up-scene.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerLogic.addClock(time);
    }

}
