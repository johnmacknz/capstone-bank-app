package capstonebankapptest;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.TestFx;

import java.util.Objects;

class BankAppTest extends ApplicationTest {

    @Override
    public void start (@NotNull Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/capstonebankapp/app-start-scene.fxml")));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Before
    public void setUp () {
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }



    @TestFx
    public void testEnglishInput () {
        clickOn("#loginButton");
        write("This is a test!");
    }
}