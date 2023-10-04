package controller;

import capstonebankmodel.Bank;
import capstonebankmodel.BankFactory;
import capstonebankmodel.Customer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;

public class TransferController implements Initializable {
    @javafx.fxml.FXML
    private TextField transferAmountTextField;
    @javafx.fxml.FXML
    private Button transferButton;
    @javafx.fxml.FXML
    private Button backButton;
    @javafx.fxml.FXML
    private Label errorMessageLabel;
    @javafx.fxml.FXML
    private ComboBox accountComboBox1;
    @javafx.fxml.FXML
    private ComboBox accountComboBox2;

    private Bank bank;

    private String username;

    private Customer customer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bank = BankFactory.getBank();
        username = LoginController.getUsername();
        customer = bank.getCustomerDataHashMap().get(username);
        accountComboBox1.setItems(FXCollections.observableArrayList(customer.getAccountTypeHashMap().keySet()));
        accountComboBox2.setItems(FXCollections.observableArrayList(customer.getAccountTypeHashMap().keySet()));
    }

    @javafx.fxml.FXML
    public void handleTransferButton(ActionEvent actionEvent) {
        if (accountComboBox1.getValue() != null) {
            if (accountComboBox2.getValue() != null) {
                if (transferAmountTextField.getText() != null) {
                    long accountNumber1 = customer.getAccountTypeHashMap().get(accountComboBox1.getValue());
                    long accountNumber2 = customer.getAccountTypeHashMap().get(accountComboBox2.getValue());
                    double amount = Double.parseDouble(transferAmountTextField.getText());
                    bank.transfer(bank.getAccountDataHashMap().get(accountNumber1),
                            bank.getAccountDataHashMap().get(accountNumber2), amount);
                    //TODO transfer success text
                }else errorMessageLabel.setText("Please enter a Deposit Amount");
            }else errorMessageLabel.setText("Please select an Account to Transfer to");
        }else errorMessageLabel.setText("Please select an Account to Transfer from");
    }

    @javafx.fxml.FXML
    public void handleBackButton(@NotNull ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/dashboard-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        currentStage.close();
        newStage.show();
    }
}
