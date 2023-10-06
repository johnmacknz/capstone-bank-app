package controller;


import capstonebankmodel.Bank;
import capstonebankmodel.BankFactory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @javafx.fxml.FXML
    private Label newUserNameLabel;
    @javafx.fxml.FXML
    private Label enterPasswordLabel;
    @javafx.fxml.FXML
    private Label reEnterPasswordLabel;
    @javafx.fxml.FXML
    private TextField newUserNameTextFiend;
    @javafx.fxml.FXML
    private TextField enterPasswordTextField;
    @javafx.fxml.FXML
    private TextField reEnterPasswordTextField;
    @javafx.fxml.FXML
    private Button createAccountButton;
    @javafx.fxml.FXML
    private Button backButton;
    @javafx.fxml.FXML
    private Label createAccountErrorMessage;
    @javafx.fxml.FXML
    private Label successMessage;
    @javafx.fxml.FXML
    private Label passwordRequirementsLabel;
    @javafx.fxml.FXML
    private Label firstNameLabel;
    @javafx.fxml.FXML
    private Label lastNameLabel;
    @javafx.fxml.FXML
    private TextField lastNameUserTextField;
    @javafx.fxml.FXML
    private TextField firstNameUserTextField;
    @javafx.fxml.FXML
    private Label titleLabel;
    @javafx.fxml.FXML
    private Label time;

    private Bank bank;

    private static boolean checkString(@NotNull String password) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for (int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if (numberFlag && capitalFlag && lowerCaseFlag)
                return true;
        }
        return false;
    }

    @javafx.fxml.FXML
    public void handleCreateAccountButton(ActionEvent actionEvent) throws IOException {
        String username = newUserNameTextFiend.getText();
        String password = enterPasswordTextField.getText();
        String passwordCheck = reEnterPasswordTextField.getText();
        if (!bank.getCustomerDataHashMap().containsKey(username)){
            if (password.equals(passwordCheck)) {
                boolean passwordConditions = checkString(password);
                if (passwordConditions) {
                    bank.addNewCustomer(username, firstNameUserTextField.getText(),
                            lastNameUserTextField.getText(), password);
                    ApplicationContext.setAccountCreated(true);
                    Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/login-scene.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage newStage = new Stage();
                    newStage.setScene(new Scene(root));
                    newStage.setTitle("Barclava Bank");
                    currentStage.close();
                    newStage.show();
                } else createAccountErrorMessage.setText("Password conditions not met!");
            } else createAccountErrorMessage.setText("Passwords do not match!");
        } else createAccountErrorMessage.setText("Username taken!");
    }

    public class ApplicationContext {
        private static boolean accountCreated = false;

        public static boolean isAccountCreated() {
            return accountCreated;
        }

        public static void setAccountCreated(boolean created) {
            accountCreated = created;
        }
    }

    @javafx.fxml.FXML
    public void handleBackButton(@NotNull ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/app-start-scene.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Barclava Bank");
        currentStage.close();
        newStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String text = """
                Password needs to have at least:
                    - 1 uppercase letter 
                    - 1 lowercase letter
                    - 1 number""";
        passwordRequirementsLabel.setText(text);
        bank = BankFactory.getBank();
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e->
                time.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
