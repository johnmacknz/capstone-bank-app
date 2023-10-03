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
    private Text accountCreation;
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
    private Label titleLabel;
  
    private Bank bank;


    public void initialize() {
        if (SignUpController.ApplicationContext.isAccountCreated()) {
            accountCreation.setText("Account Successfully Created");
        }
        bank = BankFactory.getBank();
    }

    @javafx.fxml.FXML
    public void handleLoginButton(ActionEvent actionEvent) {
        if(bank.getCustomerDataHashMap().containsKey(userNameTextField.getText())) {
            Customer customer = bank.getCustomerDataHashMap().get(userNameTextField.getText());
            if (passwordFieldLogin.getText().equals(customer.getPassword())) {
                // TODO Progress to next scene
            } else {
                accountCreation.setText("Incorrect password entered!"); //TODO- make this red colour
            }
        } else {
            accountCreation.setText("No user with this username exists!"); //TODO- make this red colour
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
