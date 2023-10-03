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
    private Label errorMessageLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        accountTypeChoiceBox.setItems(FXCollections.observableArrayList("Savings Account", "Checking Account", "CD Account"));
    }

    @javafx.fxml.FXML
    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/dashboard-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        currentStage.close();
        newStage.show();
    }

    @javafx.fxml.FXML
    public void handleCreateAccountButton(ActionEvent actionEvent) throws IOException {
        if (accountTypeChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please select an account type!");
        } else {
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/dashboard-scene.fxml"));
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            currentStage.close();
            newStage.show();
        }
    }
}
