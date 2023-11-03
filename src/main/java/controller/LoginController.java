package controller;

import capstonebankmodel.Bank;
import capstonebankmodel.BankFactory;
import capstonebankmodel.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public class LoginController {


    @FXML
    private Label usenameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField userNameTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button backButton;
    @FXML
    private PasswordField passwordFieldLogin;
    @FXML
    private Text errorMessage;
    @FXML
    public static Text accountCreation;
    @FXML
    private ImageView barclavaImageView1;
    @FXML
    private Label time;

    public static String getUsername() {
        return username;
    }

    private Bank bank;
    private static String username;
    public static boolean accountCreatedShown = false;

    public void initialize() {
        ControllerLogic.accountCreatedCheck();
        bank = BankFactory.getBank();
        ControllerLogic.addClock(time);
    }

    @javafx.fxml.FXML
    public void handleLoginButton(ActionEvent actionEvent) throws IOException {
        SignUpController.ApplicationContext.setAccountCreated(false);
        username = userNameTextField.getText();
        if (bank.getCustomerDataHashMap().containsKey(username)) {
            Customer customer = bank.getCustomerDataHashMap().get(username);
            if (passwordFieldLogin.getText().equals(customer.getPassword())) {
                ControllerLogic.openNewScene(actionEvent, "/capstonebankapp/dashboard-scene.fxml");
            } else {
                errorMessage.setText("Incorrect password entered!");
            }
        } else {
            errorMessage.setText("No user with this username exists!");
        }
    }

    @javafx.fxml.FXML
    public void handleBackButton(@NotNull ActionEvent actionEvent) throws IOException {
        ControllerLogic.openNewScene(actionEvent, "/capstonebankapp/app-start-scene.fxml");
    }
}
