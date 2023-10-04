package controller;

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
import javafx.scene.layout.HBox;
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

    public void initialize() {
        //TODO boolean expresion returns true if user has an account
        if (false) {

        } else {
            accountGrid.setVisible(false);
            noAccountCreatedTextFlow.setVisible(true);


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
}
