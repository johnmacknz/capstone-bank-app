package controller;

import capstonebankmodel.Bank;
import capstonebankmodel.BankFactory;
import capstonebankmodel.Customer;

import capstonebankmodel.Loan;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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

public class LoanController implements Initializable {
    @javafx.fxml.FXML
    private Label loanTypeLabel;
    @javafx.fxml.FXML
    private ChoiceBox<String> loanTypeChoiceBox;
    @javafx.fxml.FXML
    private Label loanAmountLabel;
    @javafx.fxml.FXML
    private TextField loanAmountTextField;
    @javafx.fxml.FXML
    private Button requestLoanButton;
    @javafx.fxml.FXML
    private Button viewPreviousLoansButton;
    @javafx.fxml.FXML
    private Button backButton;
    @javafx.fxml.FXML
    private Label loanOutputLabel;
    @javafx.fxml.FXML
    private Label signedInAsLabel;
    @javafx.fxml.FXML
    private Label accountNameLabel;
    @javafx.fxml.FXML
    private Label loanDurationLabel;
    @javafx.fxml.FXML
    private ChoiceBox<Integer> loanDurationChoiceBox;
    @javafx.fxml.FXML
    private Label errorMessageLabel;
    @javafx.fxml.FXML
    private Label enterAnIntegerLabel;
    @javafx.fxml.FXML
    private Label durationInYearsLabel;
    @javafx.fxml.FXML
    private TextField personalLoanDurationTextField;
    private Bank bank;
    private String username;
    private Customer customer;

    private Bank bank;

    private String username;

    private Customer customer;

    @javafx.fxml.FXML
    private void onChoiceBoxSelectionChanged() {
        String selectedOption = loanTypeChoiceBox.getValue();
        if ("Home Loan".equals(selectedOption)) {
            loanOutputLabel.setText("The maximum amount you\ncan request is 2,000,000");
        } else if ("Car Loan".equals(selectedOption)) {
            loanOutputLabel.setText("The maximum amount you \ncan request is 50,000");
        } else if ("Personal Loan".equals(selectedOption)) {
            loanOutputLabel.setText("The maximum amount you\ncan request is 45,000");
        } else {
            loanOutputLabel.setText("");
        }
        loanDurationChoiceBox.getItems().clear();
        if ("Home Loan".equals(selectedOption)) {
            loanDurationChoiceBox.setItems(FXCollections.observableArrayList(15, 20, 30));
        } else if ("Car Loan".equals(selectedOption)) {
            loanDurationChoiceBox.setItems(FXCollections.observableArrayList(3, 4, 5));
        } else {
            loanDurationChoiceBox.setItems(FXCollections.emptyObservableList());
        }
        if ("Personal Loan".equals(selectedOption)) {
            loanDurationChoiceBox.setDisable(true);
            personalLoanDurationTextField.setVisible(true);
        } else {
            loanDurationChoiceBox.setDisable(false);
            personalLoanDurationTextField.setVisible(false);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bank = BankFactory.getBank();
        username = LoginController.getUsername();
        customer = bank.getCustomerDataHashMap().get(username);
        accountNameLabel.setText(username);

        loanTypeChoiceBox.setItems(FXCollections.observableArrayList("Home Loan", "Car Loan", "Personal Loan"));
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

    @javafx.fxml.FXML
    public void handleRequestButton(ActionEvent actionEvent) throws IOException {
        errorMessageLabel.setText("");

        String selectedOption = loanTypeChoiceBox.getValue();
        int maxLoanAmount = 0;

        if ("Home Loan".equals(selectedOption)) {
            maxLoanAmount = 2000000;
        } else if ("Car Loan".equals(selectedOption)) {
            maxLoanAmount = 50000;
        } else if ("Personal Loan".equals(selectedOption)) {
            maxLoanAmount = 45000;
        }
        if (loanTypeChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please fill in all of the boxes!");
        } else if ((loanTypeChoiceBox.getValue().equals("Personal Loan") && (loanAmountTextField.getText().isEmpty() || personalLoanDurationTextField.getText().isEmpty())) || ((loanDurationChoiceBox.getValue() == null
                || loanAmountTextField.getText().isEmpty()) && !loanTypeChoiceBox.getValue().equals("Personal Loan"))) {
            errorMessageLabel.setText("Please fill in all of the boxes!");

        } else {
            try {
                int loanAmount = Integer.parseInt(loanAmountTextField.getText());
                if (loanAmount > maxLoanAmount) {
                    errorMessageLabel.setText("Loan amount exceeds the maximum allowed amount for " + selectedOption);
                } else {
                    Loan loan = new Loan(customer.getUserName(), loanAmount, loanDurationChoiceBox.getValue());
                    bank.addLoan(customer, loan);
                    Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/scene-for-successful-loan.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage newStage = new Stage();
                    newStage.setScene(new Scene(root));
                    currentStage.close();
                    newStage.show();
                }
            } catch (NumberFormatException e) {
                errorMessageLabel.setText("Invalid loan amount. Please enter a valid number.");
            }
        }
    }
}

