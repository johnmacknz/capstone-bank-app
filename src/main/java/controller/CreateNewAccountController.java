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
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateNewAccountController implements Initializable {

    @javafx.fxml.FXML
    private Label accountTypeLabel;
    @javafx.fxml.FXML
    private ChoiceBox accountTypeChoiceBox;
    @javafx.fxml.FXML
    private Label titleLabel;
    @javafx.fxml.FXML
    private Label signedInAsLabel;
    @javafx.fxml.FXML
    private Label accountNameLabel;
    @javafx.fxml.FXML
    private Button createNewAccountButton;
    @javafx.fxml.FXML
    private Button backButton;
    @javafx.fxml.FXML
    private Label emptyErrorMessageLabel;
    @FXML
    private Label alreadyExistingTypeErrorLabel;

    private Bank bank;

    private String username;

    private Customer customer;
    @FXML
    private Button logOutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bank = BankFactory.getBank();
        username = LoginController.getUsername();
        customer = bank.getCustomerDataHashMap().get(username);
        accountNameLabel.setText(username);
        accountTypeChoiceBox.setItems(FXCollections.observableArrayList("Savings Account", "Checking Account", "CD Account"));
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
    public void handleCreateAccountButton(ActionEvent actionEvent) throws IOException {
        String accountType = accountTypeChoiceBox.getValue().toString();
        if (accountTypeChoiceBox.getValue() == null) {
            emptyErrorMessageLabel.setText("Please select an account type!");
        } else if (customer.getAccountTypeHashMap().containsKey(accountType)) {
            emptyErrorMessageLabel.setText("Account of this type already exists!");
        } else {
            bank.addAccount(customer, accountType);
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/dashboard-scene.fxml"));
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Barclava Bank");
            currentStage.close();
            newStage.show();
        }
    }

    @FXML
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
