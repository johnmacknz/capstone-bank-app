package controller;

import capstonebankmodel.Bank;
import capstonebankmodel.BankFactory;
import capstonebankmodel.Customer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    @javafx.fxml.FXML
    private ComboBox <String> accountComboBox;
    @javafx.fxml.FXML
    private Button depositButton;
    @javafx.fxml.FXML
    private Button backButton;
    @javafx.fxml.FXML
    private TextField depositAmountTextField;
    @javafx.fxml.FXML
    private Label errorMessageLabel;

    private Bank bank;

    private String username;

    private Customer customer;
    @javafx.fxml.FXML
    private Label signedInAsLabel;
    @javafx.fxml.FXML
    private Label userNameLabel;
    @javafx.fxml.FXML
    private Button logOutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bank = BankFactory.getBank();
        username = LoginController.getUsername();
        customer = bank.getCustomerDataHashMap().get(username);
        accountComboBox.setItems(FXCollections.observableArrayList(customer.getAccountTypeHashMap().keySet()));
        userNameLabel.setText(username);
    }

    @javafx.fxml.FXML
    public void handleDepositButton(ActionEvent actionEvent) throws IOException {
        if (accountComboBox.getValue() != null) {
            if (depositAmountTextField.getText() != null) {
                long accountNumber = customer.getAccountTypeHashMap().get(accountComboBox.getValue());
                double amount = Double.parseDouble(depositAmountTextField.getText());
                bank.deposit(bank.getAccountDataHashMap().get(accountNumber), amount);
                ControllerLogic.openNewScene(actionEvent, "/capstonebankapp/scene-for-successful-deposit.fxml");
            }else errorMessageLabel.setText("Please enter a Deposit Amount");
        }else errorMessageLabel.setText("Please select an Account");
    }

    @javafx.fxml.FXML
    public void handleBackButton(@NotNull ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/dashboard-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Barclava Bank");
        currentStage.close();
        newStage.show();
    }

    @FXML
    public void handleLogOutButton(@NotNull ActionEvent actionEvent) throws IOException {
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
