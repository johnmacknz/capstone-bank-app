package controller;

import capstonebankmodel.Bank;
import capstonebankmodel.BankFactory;
import capstonebankmodel.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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

    private Bank bank;
    @FXML
    private Text errorMessage;
    @FXML
    private Text accountCreation;

    public static String getUsername() {
        return username;
    }

    private static String username;


    public void initialize() {
        if (SignUpController.ApplicationContext.isAccountCreated()) {
            accountCreation.setText("Account Successfully Created");
        }
        bank = BankFactory.getBank();
    }

    @javafx.fxml.FXML
    public void handleLoginButton(ActionEvent actionEvent) throws IOException {
        username = userNameTextField.getText();
        if(bank.getCustomerDataHashMap().containsKey(username)) {
            Customer customer = bank.getCustomerDataHashMap().get(username);
            if (passwordFieldLogin.getText().equals(customer.getPassword())) {
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/dashboard-scene.fxml"));
                Parent root = fxmlLoader.load();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                currentStage.close();
                newStage.show();
            } else {
                errorMessage.setText("Incorrect password entered!");
            }
        } else {
            errorMessage.setText("No user with this username exists!");
        }
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
