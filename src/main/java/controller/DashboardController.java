package controller;

import capstonebankmodel.Bank;
import capstonebankmodel.BankFactory;
import capstonebankmodel.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DashboardController {
    @javafx.fxml.FXML
    private ImageView barclavaImageView;
    @javafx.fxml.FXML
    private TextFlow noAccountCreatedTextFlow;
    @javafx.fxml.FXML
    private Hyperlink hereHyperLink;
    @javafx.fxml.FXML
    private Button createAccountButton;
    @javafx.fxml.FXML
    private TextFlow noLoansTextFlow;
    @javafx.fxml.FXML
    private Hyperlink hereHyperLink2;
    @javafx.fxml.FXML
    private Button requestLoanButton;
    @javafx.fxml.FXML
    private Button depositButton;
    @javafx.fxml.FXML
    private Button withdrawButton;
    @javafx.fxml.FXML
    private Button transferButton;
    @javafx.fxml.FXML
    private Label accountType1Label;
    @javafx.fxml.FXML
    private Label accountNumber1Label;
    @javafx.fxml.FXML
    private Label balance1Label;
    @javafx.fxml.FXML
    private Label accountType2Label;
    @javafx.fxml.FXML
    private Label accountNumber2Label;
    @javafx.fxml.FXML
    private Label balance2Label;
    @javafx.fxml.FXML
    private Label accountType3Label;
    @javafx.fxml.FXML
    private Label accountNumber3Label;
    @javafx.fxml.FXML
    private Label balance3Label;
    @javafx.fxml.FXML
    private GridPane accountGrid;
    @javafx.fxml.FXML
    private GridPane loanGrid;
    @javafx.fxml.FXML
    private Label loanTypeTitleLabel;
    @javafx.fxml.FXML
    private Label loanAmountTitleLabel;
    @javafx.fxml.FXML
    private Label loanDurationTitleLabel;
    @javafx.fxml.FXML
    private Label loanType1Label;
    @javafx.fxml.FXML
    private Label loanAmount1Label;
    @javafx.fxml.FXML
    private Label loanDuration1Label;
    @javafx.fxml.FXML
    private Label loanType2Label;
    @javafx.fxml.FXML
    private Label loanAmount2Label;
    @javafx.fxml.FXML
    private Label loanDuration2Label;
    @javafx.fxml.FXML
    private Label loanType3Label;
    @javafx.fxml.FXML
    private Label loanAmount3Label;
    @javafx.fxml.FXML
    private Label loanDuration3Label;

    private Bank bank;

    private String username;

    private Customer customer;
    @javafx.fxml.FXML
    private Label signedInAsLabel;
    @javafx.fxml.FXML
    private Button logOutButton;
    @javafx.fxml.FXML
    private Label userNameLabel;

    public void initialize() {
        username = LoginController.getUsername();
        bank = BankFactory.getBank();
        customer = bank.getCustomerDataHashMap().get(username);
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
    }

    private void populateAccountGrid() {
        for (String accountType : customer.getAccountTypeHashMap().keySet()) {
            long accountNumber = customer.getAccountTypeHashMap().get(accountType);
            double balance = bank.getAccountDataHashMap().get(accountNumber).getBalance();
            if (accountType1Label.getText().equals("Label")) {
                accountType1Label.setVisible(true);
                accountNumber1Label.setVisible(true);
                balance1Label.setVisible(true);
                accountType1Label.setText(accountType);
                accountNumber1Label.setText(String.valueOf(accountNumber));
                balance1Label.setText(String.valueOf(balance));
            } else if (accountType2Label.getText().equals("Label")) {
                accountType2Label.setVisible(true);
                accountNumber2Label.setVisible(true);
                balance2Label.setVisible(true);
                accountType2Label.setText(accountType);
                accountNumber2Label.setText(String.valueOf(accountNumber));
                balance2Label.setText(String.valueOf(balance));
            } else if (accountType3Label.getText().equals("Label")) {
                accountType3Label.setVisible(true);
                accountNumber3Label.setVisible(true);
                balance3Label.setVisible(true);
                accountType3Label.setText(accountType);
                accountNumber3Label.setText(String.valueOf(accountNumber));
                balance3Label.setText(String.valueOf(balance));
            }
        }
    }

    @javafx.fxml.FXML
    public void handleHereHyperLink(@NotNull ActionEvent actionEvent) throws IOException {
        accountGrid.setVisible(true);
        noAccountCreatedTextFlow.setVisible(false);

        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/create-new-account-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        currentStage.close();
        newStage.show();
    }

    @javafx.fxml.FXML
    public void handleCreateAccountButton(@NotNull ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/create-new-account-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        currentStage.close();
        newStage.show();
    }

    @javafx.fxml.FXML
    public void handleHereHyperLink2(@NotNull ActionEvent actionEvent) throws IOException {
        noLoansTextFlow.setVisible(false);

        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/loan-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        currentStage.close();
        newStage.show();
    }

    @javafx.fxml.FXML
    public void handleRequestLoanButton(@NotNull ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/loan-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        currentStage.close();
        newStage.show();
    }

    @javafx.fxml.FXML
    public void handleDepositButton(@NotNull ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/deposit-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        currentStage.close();
        newStage.show();
    }

    @javafx.fxml.FXML
    public void handleWithdrawButton(@NotNull ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/withdraw-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        currentStage.close();
        newStage.show();
    }

    @javafx.fxml.FXML
    public void handleTransferButton(@NotNull ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/transfer-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        currentStage.close();
        newStage.show();
    }

    private void populateLoanGrid() {
        for (String loanType : customer.getLoanTypeHashMap().keySet()) {
            long loanAccountId = customer.getLoanTypeHashMap().get(loanType);
            int loanDuration = bank.getLoanDataHashMap().get(loanAccountId).getLoanDuration();
            double loanAmount = bank.getLoanDataHashMap().get(loanAccountId).getLoanAmount();
            if (loanType1Label.getText().equals("Label")) {
                loanType1Label.setVisible(true);
                loanAmount1Label.setVisible(true);
                loanDuration1Label.setVisible(true);
                loanType1Label.setText(loanType);
                loanAmount1Label.setText(String.valueOf(loanAmount));
                loanDuration1Label.setText(String.valueOf(loanDuration));
            } else if (loanType2Label.getText().equals("Label")) {
                loanType2Label.setVisible(true);
                loanAmount2Label.setVisible(true);
                loanDuration2Label.setVisible(true);
                loanType2Label.setText(loanType);
                loanAmount2Label.setText(String.valueOf(loanAmount));
                loanDuration2Label.setText(String.valueOf(loanDuration));
            } else if (loanType3Label.getText().equals("Label")) {
                loanType3Label.setVisible(true);
                loanAmount3Label.setVisible(true);
                loanDuration3Label.setVisible(true);
                loanType3Label.setText(loanType);
                loanAmount3Label.setText(String.valueOf(loanAmount));
                loanDuration3Label.setText(String.valueOf(loanDuration));
            }
        }
    }

    @javafx.fxml.FXML
    public void handleLogOutButton(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/app-start-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        currentStage.close();
        newStage.show();
    }
}