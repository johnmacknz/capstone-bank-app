package controller;

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

public class WithdrawController implements Initializable {
    @javafx.fxml.FXML
    private ComboBox accountComboBox;
    @javafx.fxml.FXML
    private TextField withdrawAmountTextField;
    @javafx.fxml.FXML
    private Button withdrawButton;
    @javafx.fxml.FXML
    private Button backButton;
    @javafx.fxml.FXML
    private Label errorMessageLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountComboBox.setItems(FXCollections.observableArrayList("Account 1", "Account 2", "Account 3"));
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
    public void handleWithdrawButton(ActionEvent actionEvent) {
        if (accountComboBox.getValue() != null) {
            if (withdrawAmountTextField.getText() != null) {

            }else errorMessageLabel.setText("Please enter a Deposit Amount");
        }else errorMessageLabel.setText("Please select an Account");
    }
}
