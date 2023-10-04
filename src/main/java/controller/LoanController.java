package controller;

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
    private ChoiceBox <String> loanTypeChoiceBox;
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
    private TextField homeLoanDurationTextField;
    @javafx.fxml.FXML
    private Label errorMessageLabel;

    @javafx.fxml.FXML
    private void onChoiceBoxSelectionChanged() {
        String selectedOption = loanTypeChoiceBox.getValue();
        if ("Home Loan".equals(selectedOption)) {
            loanOutputLabel.setText("The maximum amount you\ncan request is 2,000,000");
        }else if ("Car Loan".equals(selectedOption)){
            loanOutputLabel.setText("The maximum amount you \ncan request is 50,000");
        }
    else if ("Personal Loan".equals(selectedOption)){
            loanOutputLabel.setText("The maximum amount you\ncan request is 45,000");
        }
        else {
            loanOutputLabel.setText("");
        }
        loanDurationChoiceBox.getItems().clear();
        if ("Home Loan".equals(selectedOption)) {
            loanDurationChoiceBox.setItems(FXCollections.observableArrayList( 15, 20, 30));
        } else if ("Car Loan".equals(selectedOption)) {
            loanDurationChoiceBox.setItems(FXCollections.observableArrayList( 3, 4, 5));
        } else {
            loanDurationChoiceBox.setItems(FXCollections.emptyObservableList());
        }
        if ("Personal Loan".equals(selectedOption)) {
            loanDurationChoiceBox.setDisable(true);
            homeLoanDurationTextField.setVisible(true);
        } else {
            loanDurationChoiceBox.setDisable(false);
            homeLoanDurationTextField.setVisible(false);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    public void handleRequestButton(ActionEvent actionEvent) {
        if (loanTypeChoiceBox.getValue() == null || loanAmountTextField.getText() == null ||
                loanDurationChoiceBox.getValue() == null || homeLoanDurationTextField.getText() == null){
            errorMessageLabel.setText("Please fill in all of the boxes!");
        }else {
            //TODO: Code to take you to the next scene that confirms creation of the account
        }
    }
}

