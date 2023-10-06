package controller;

import capstonebankmodel.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RepayLoanController {
    @javafx.fxml.FXML
    private ComboBox accountComboBox;
    @javafx.fxml.FXML
    private TextField depositAmountTextField;
    @javafx.fxml.FXML
    private Button repayLoanButton;
    @javafx.fxml.FXML
    private Button backButton;
    @javafx.fxml.FXML
    private Label errorMessageLabel;
    @javafx.fxml.FXML
    private Label signedInAsLabel;
    @javafx.fxml.FXML
    private Label userNameLabel;
    @javafx.fxml.FXML
    private Button logOutButton;
    @javafx.fxml.FXML
    private Label selectLoanLabel;
    @javafx.fxml.FXML
    private ComboBox selectLoanComboBox;

    private Bank bank;

    private String username;

    private Customer customer;

    public void initialize() {
        bank = BankFactory.getBank();
        username = LoginController.getUsername();
        customer = bank.getCustomerDataHashMap().get(username);
        userNameLabel.setText(username);
        accountComboBox.setItems(FXCollections.observableArrayList(customer.getAccountTypeHashMap().keySet()));
        selectLoanComboBox.setItems(FXCollections.observableArrayList(customer.getLoanTypeHashMap().keySet()));
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

    @javafx.fxml.FXML
    public void handleRepayButton(ActionEvent actionEvent) {
        try {
            long accountId = customer.getAccountTypeHashMap().get(accountComboBox.getValue());
            Account account = bank.getAccountDataHashMap().get(accountId);
            long loanId = customer.getLoanTypeHashMap().get(selectLoanComboBox.getValue());
            Loan loan = bank.getLoanDataHashMap().get(loanId);
            double amount = Double.parseDouble(depositAmountTextField.getText());
            if (account.getBalance() >= amount){
                bank.repayLoan(customer, account, loan, amount);
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/successful-repayment-scene.fxml"));
                Parent root = fxmlLoader.load();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.setTitle("Barclava Bank");
                currentStage.close();
                newStage.show();
            } else {
                errorMessageLabel.setText("Insufficient funds in this account!");
            }
        } catch (NumberFormatException e) {
            errorMessageLabel.setText("Please enter a valid amount of money.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
