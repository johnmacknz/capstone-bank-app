package controller;

import capstonebankmodel.Bank;
import capstonebankmodel.BankFactory;
import capstonebankmodel.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextFlow;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DashboardController {
    @FXML
    private TextFlow noAccountCreatedTextFlow;
    @FXML
    private Hyperlink hereHyperLink;
    @FXML
    private Button createAccountButton;
    @FXML
    private TextFlow noLoansTextFlow;
    @FXML
    private Hyperlink hereHyperLink2;
    @FXML
    private Button requestLoanButton;
    @FXML
    private Button depositButton;
    @FXML
    private Button withdrawButton;
    @FXML
    private Button transferButton;
    @FXML
    private Label accountType1Label;
    @FXML
    private Label accountNumber1Label;
    @FXML
    private Label balance1Label;
    @FXML
    private Label accountType2Label;
    @FXML
    private Label accountNumber2Label;
    @FXML
    private Label balance2Label;
    @FXML
    private Label accountType3Label;
    @FXML
    private Label accountNumber3Label;
    @FXML
    private Label balance3Label;
    @FXML
    private GridPane accountGrid;
    @FXML
    private GridPane loanGrid;
    @FXML
    private Label loanTypeTitleLabel;
    @FXML
    private Label loanAmountTitleLabel;
    @FXML
    private Label loanDurationTitleLabel;
    @FXML
    private Label loanType1Label;
    @FXML
    private Label loanAmount1Label;
    @FXML
    private Label loanDuration1Label;
    @FXML
    private Label loanType2Label;
    @FXML
    private Label loanAmount2Label;
    @FXML
    private Label loanDuration2Label;
    @FXML
    private Label loanType3Label;
    @FXML
    private Label loanAmount3Label;
    @FXML
    private Label loanDuration3Label;
    @FXML
    private Label signedInAsLabel;
    @FXML
    private Button logOutButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private TitledPane myAccountsTitlePane;
    @FXML
    private TitledPane loansTitlePane;
    @FXML
    private Label time;
    @FXML
    private Button repayLoanButton;

    private Bank bank;

    private Customer customer;

    public void initialize() {
        String username = LoginController.getUsername();
        bank = BankFactory.getBank();
        customer = bank.getCustomerDataHashMap().get(username);
        userNameLabel.setText(username);
        if (!customer.getAccountTypeHashMap().isEmpty()) {
            accountGrid.setVisible(true);
            noAccountCreatedTextFlow.setVisible(false);
            populateAccountGrid();
        } else {
            accountGrid.setVisible(false);
            noAccountCreatedTextFlow.setVisible(true);
        }
        if (!customer.getLoanTypeHashMap().isEmpty()) {
            loanGrid.setVisible(true);
            noLoansTextFlow.setVisible(false);
            populateLoanGrid();
        } else {
            loanGrid.setVisible(false);
            noLoansTextFlow.setVisible(true);
        }
        ControllerLogic.addClock(time);
    }

    private void populateAccountGrid() {
        for (String accountType : customer.getAccountTypeHashMap().keySet()) {
            long accountNumber = customer.getAccountTypeHashMap().get(accountType);
            double balance = bank.getAccountDataHashMap().get(accountNumber).getBalance();
            if (accountType1Label.getText().equals("Label")) {
                ControllerLogic.populateGridRow(accountType1Label, accountNumber1Label, balance1Label, accountType, String.valueOf(accountNumber), String.valueOf(balance));
            } else if (accountType2Label.getText().equals("Label")) {
                ControllerLogic.populateGridRow(accountType2Label, accountNumber2Label, balance2Label, accountType, String.valueOf(accountNumber), String.valueOf(balance));
            } else if (accountType3Label.getText().equals("Label")) {
                ControllerLogic.populateGridRow(accountType3Label, accountNumber3Label, balance3Label, accountType, String.valueOf(accountNumber), String.valueOf(balance));
            }
        }
    }

    @FXML
    public void handleHereHyperLink(@NotNull ActionEvent actionEvent) throws IOException {
        accountGrid.setVisible(true);
        noAccountCreatedTextFlow.setVisible(false);
        ControllerLogic.openNewScene(actionEvent,"/capstonebankapp/create-new-account-scene.fxml");
    }

    @FXML
    public void handleCreateAccountButton(@NotNull ActionEvent actionEvent) throws IOException {
        ControllerLogic.openNewScene(actionEvent,"/capstonebankapp/create-new-account-scene.fxml");
    }

    @FXML
    public void handleHereHyperLink2(@NotNull ActionEvent actionEvent) throws IOException {
        noLoansTextFlow.setVisible(false);
        ControllerLogic.openNewScene(actionEvent,"/capstonebankapp/loan-scene.fxml");
    }

    @FXML
    public void handleRequestLoanButton(@NotNull ActionEvent actionEvent) throws IOException {
        ControllerLogic.openNewScene(actionEvent,"/capstonebankapp/loan-scene.fxml");
    }

    @FXML
    public void handleDepositButton(@NotNull ActionEvent actionEvent) throws IOException {
        ControllerLogic.openNewScene(actionEvent,"/capstonebankapp/deposit-scene.fxml");
    }

    @FXML
    public void handleWithdrawButton(@NotNull ActionEvent actionEvent) throws IOException {
        ControllerLogic.openNewScene(actionEvent, "/capstonebankapp/withdraw-scene.fxml");
    }

    @FXML
    public void handleTransferButton(@NotNull ActionEvent actionEvent) throws IOException {
        ControllerLogic.openNewScene(actionEvent, "/capstonebankapp/transfer-scene.fxml");
    }

    private void populateLoanGrid() {
        for (String loanType : customer.getLoanTypeHashMap().keySet()) {
            long loanAccountId = customer.getLoanTypeHashMap().get(loanType);
            int loanDuration = bank.getLoanDataHashMap().get(loanAccountId).getLoanDuration();
            double loanAmount = bank.getLoanDataHashMap().get(loanAccountId).getOutstandingAmount();
            if (loanType1Label.getText().equals("Label")) {
                ControllerLogic.populateGridRow(loanType1Label, loanAmount1Label, loanDuration1Label, loanType, String.valueOf(loanAmount), String.valueOf(loanDuration));
            } else if (loanType2Label.getText().equals("Label")) {
                ControllerLogic.populateGridRow(loanType2Label, loanAmount2Label, loanDuration2Label, loanType, String.valueOf(loanAmount), String.valueOf(loanDuration));
            } else if (loanType3Label.getText().equals("Label")) {
                ControllerLogic.populateGridRow(loanType3Label, loanAmount3Label, loanDuration3Label, loanType, String.valueOf(loanAmount), String.valueOf(loanDuration));
            }
        }
    }

    @FXML
    public void handleLogOutButton(@NotNull ActionEvent actionEvent) throws IOException {
        ControllerLogic.openNewScene(actionEvent, "/capstonebankapp/app-start-scene.fxml");
    }

    @FXML
    public void handleRepayLoanButton(ActionEvent actionEvent) throws IOException {
        ControllerLogic.openNewScene( actionEvent,"/capstonebankapp/repay-loan-scene.fxml");
    }
}