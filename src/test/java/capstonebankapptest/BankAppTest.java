package capstonebankapptest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.TestFx;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextMatchers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.isVisible;

class BankAppTest extends ApplicationTest {
    private Stage primaryStage;
    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Parent mainNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/capstonebankapp/app-start-scene.fxml")));
        primaryStage.setScene(new Scene(mainNode));
        primaryStage.show();
        primaryStage.toFront();
    }

    private void deleteUserFromCSV(String username) {
        String csvFile = "src/main/resources/data/customer-data.csv";
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains(username)) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteAccountFromCSV(String username) {
        String csvFile = "src/main/resources/data/account-data.csv";
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains(username)) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setUp () {
        String usernameToDelete = "newUser";
        String accountToDelete = "test";
        deleteUserFromCSV(usernameToDelete);
        deleteAccountFromCSV(accountToDelete);
    }

    @AfterEach
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
        String usernameToDelete = "newUser";
        String accountToDelete = "test";
        deleteUserFromCSV(usernameToDelete);
        deleteAccountFromCSV(accountToDelete);
    }

    @TestFx
    public void testLoginButton () {
        clickOn("#loginButton");

        sleep(1000);

        Assertions.assertFalse(primaryStage.isShowing());
        verifyThat("#passwordFieldLogin", NodeMatchers.isVisible());
    }

    @TestFx
    public void testSignUpButton () {
        clickOn("#signUpButton");

        sleep(1000);

        Assertions.assertFalse(primaryStage.isShowing());
        verifyThat("#passwordRequirementsLabel", NodeMatchers.isVisible());
    }

    @TestFx
    public void testUnknownUser () {
        clickOn("#loginButton");
        clickOn("#userNameTextField");
        write("UnknownUser");
        clickOn("#passwordFieldLogin");
        write("UnknownPassword");
        clickOn("#loginButton");

        sleep(1000);

        verifyThat("#errorMessage", isVisible());
        verifyThat("#errorMessage", TextMatchers.hasText("No user with this username exists!"));
    }

    @TestFx
    public void testUnknownPassword () {
        clickOn("#loginButton");
        clickOn("#userNameTextField");
        write("0");
        clickOn("#passwordFieldLogin");
        write("UnknownPassword");
        clickOn("#loginButton");

        sleep(1000);

        verifyThat("#errorMessage", isVisible());
        verifyThat("#errorMessage", TextMatchers.hasText("Incorrect password entered!"));
    }

    @TestFx
    public void testExistingUser() {
        clickOn("#signUpButton");
        clickOn("#firstNameUserTextField");
        write("test");
        clickOn("#lastNameUserTextField");
        write("test");
        clickOn("#newUserNameTextFiend");
        write("test");
        clickOn("#enterPasswordTextField");
        write("Password123");
        clickOn("#reEnterPasswordTextField");
        write("Password123");
        clickOn("#createAccountButton");

        sleep(1000);

        verifyThat("#createAccountErrorMessage", isVisible());
        verifyThat("#createAccountErrorMessage", LabeledMatchers.hasText("Username taken!"));
    }

    @TestFx
    public void testPasswordConditions() {
        clickOn("#signUpButton");
        clickOn("#firstNameUserTextField");
        write("0");
        clickOn("#lastNameUserTextField");
        write("0");
        clickOn("#newUserNameTextFiend");
        write("newUser123");
        clickOn("#enterPasswordTextField");
        write("0");
        clickOn("#reEnterPasswordTextField");
        write("0");
        clickOn("#createAccountButton");

        sleep(1000);

        verifyThat("#createAccountErrorMessage", isVisible());
        verifyThat("#createAccountErrorMessage", LabeledMatchers.hasText("Password conditions not met!"));
    }

    @TestFx
    public void testPasswordNotMatching() {
        clickOn("#signUpButton");
        clickOn("#firstNameUserTextField");
        write("0");
        clickOn("#lastNameUserTextField");
        write("0");
        clickOn("#newUserNameTextFiend");
        write("newUser1234");
        clickOn("#enterPasswordTextField");
        write("Password123");
        clickOn("#reEnterPasswordTextField");
        write("Password1234");
        clickOn("#createAccountButton");

        sleep(1000);

        verifyThat("#createAccountErrorMessage", isVisible());
        verifyThat("#createAccountErrorMessage", LabeledMatchers.hasText("Passwords do not match!"));
    }

    @TestFx
    public void testSuccessfulLogin () {
        clickOn("#loginButton");
        clickOn("#userNameTextField");
        write("test");
        clickOn("#passwordFieldLogin");
        write("Password123");
        clickOn("#loginButton");

        sleep(1000);

        verifyThat("#createAccountButton", NodeMatchers.isVisible());
    }

    @TestFx
    public void testCreateAccount() {
        clickOn("#signUpButton");
        clickOn("#firstNameUserTextField");
        write("0");
        clickOn("#lastNameUserTextField");
        write("0");
        clickOn("#newUserNameTextFiend");
        write("newUser");
        clickOn("#enterPasswordTextField");
        write("Password123");
        clickOn("#reEnterPasswordTextField");
        write("Password123");
        clickOn("#createAccountButton");

        sleep(1000);

        verifyThat("#accountCreation", NodeMatchers.isVisible());
        verifyThat("#accountCreation", TextMatchers.hasText("Account Successfully Created"));
    }

    @TestFx
    public void testCreateSavingsAccount () {
        clickOn("#loginButton");
        clickOn("#userNameTextField");
        write("test");
        clickOn("#passwordFieldLogin");
        write("Password123");
        clickOn("#loginButton");
        clickOn("#createAccountButton");
        clickOn("#accountTypeChoiceBox");
        clickOn("Savings Account");
        clickOn("#createNewAccountButton");
        TitledPane titledPane = lookup("My Accounts").query();
        clickOn(titledPane);

        sleep(1000);

        GridPane accountGrid = lookup("#accountGrid").query();
        boolean containsSavingsAccount = accountGrid.getChildren()
                .stream()
                .anyMatch(node -> node instanceof Label && ((Label) node).getText().equals("Savings Account"));

        assertTrue(containsSavingsAccount, "Savings Account not found in the grid.");
    }

    @TestFx
    public void testCreateCheckingAccount () {
        clickOn("#loginButton");
        clickOn("#userNameTextField");
        write("test");
        clickOn("#passwordFieldLogin");
        write("Password123");
        clickOn("#loginButton");
        clickOn("#createAccountButton");
        clickOn("#accountTypeChoiceBox");
        clickOn("Checking Account");
        clickOn("#createNewAccountButton");
        TitledPane titledPane = lookup("My Accounts").query();
        clickOn(titledPane);

        sleep(1000);

        GridPane accountGrid = lookup("#accountGrid").query();
        boolean containsSavingsAccount = accountGrid.getChildren()
                .stream()
                .anyMatch(node -> node instanceof Label && ((Label) node).getText().equals("Checking Account"));

        assertTrue(containsSavingsAccount, "Checking Account not found in the grid.");
    }

    @TestFx
    public void testCreateCDAccount () {
        clickOn("#loginButton");
        clickOn("#userNameTextField");
        write("test");
        clickOn("#passwordFieldLogin");
        write("Password123");
        clickOn("#loginButton");
        clickOn("#createAccountButton");
        clickOn("#accountTypeChoiceBox");
        clickOn("CD Account");
        clickOn("#createNewAccountButton");
        TitledPane titledPane = lookup("My Accounts").query();
        clickOn(titledPane);

        sleep(1000);

        GridPane accountGrid = lookup("#accountGrid").query();
        boolean containsSavingsAccount = accountGrid.getChildren()
                .stream()
                .anyMatch(node -> node instanceof Label && ((Label) node).getText().equals("CD Account"));

        assertTrue(containsSavingsAccount, "Checking Account not found in the grid.");
    }

    @TestFx
    public void testCreatingSameAccountType () {
        clickOn("#loginButton");
        clickOn("#userNameTextField");
        write("test");
        clickOn("#passwordFieldLogin");
        write("Password123");
        clickOn("#loginButton");
        clickOn("#createAccountButton");
        clickOn("#accountTypeChoiceBox");
        clickOn("Savings Account");
        clickOn("#createNewAccountButton");
        clickOn("#createAccountButton");
        clickOn("#accountTypeChoiceBox");
        clickOn("Savings Account");
        clickOn("#createNewAccountButton");

        verifyThat("#emptyErrorMessageLabel", NodeMatchers.isVisible());
        verifyThat("#emptyErrorMessageLabel", LabeledMatchers.hasText("Account of this type already exists!"));
    }

 }
