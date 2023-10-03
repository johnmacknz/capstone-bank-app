package controller;

import capstonebankmodel.Bank;
import capstonebankmodel.Customer;
import javafx.event.ActionEvent;
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

public class LoginController{
    @javafx.fxml.FXML
    private Label usenameLabel;
    @javafx.fxml.FXML
    private Label passwordLabel;
    @javafx.fxml.FXML
    private TextField userNameTextField;
    @javafx.fxml.FXML
    private Button loginButton;
    @javafx.fxml.FXML
    private Button backButton;
    @javafx.fxml.FXML
      
    private PasswordField passwordFieldLogin;
    Bank bank = new Bank();
  
    private Text accountCreation;
    public LoginController(Bank bank){
        this.bank = bank;
    }
    public void initialize() {
        if (SignUpController.ApplicationContext.isAccountCreated()) {
            accountCreation.setText("Account Successfully Created");
        }
    }

    @javafx.fxml.FXML
    public void handleLoginButton(ActionEvent actionEvent) {
        if(bank.getCustomerDataHashMap().containsKey(userNameTextField.getText())) {
            Customer customer = bank.getCustomerDataHashMap().get(userNameTextField.getText());
            if (passwordFieldLogin.getText().equals(customer.getPassword())) {
                //TODO- username and password validated, progress to next scene 3
            } else {
                accountCreation.setText("Incorrect password entered!"); //TODO- make this red colour

            }
        }
        else{
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
