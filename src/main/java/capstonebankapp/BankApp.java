package capstonebankapp;

import controller.AppStartController;
import controller.DashboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.net.URL;

public class BankApp extends Application {
    public static void style(Parent root) {
        URL cssUrl = DashboardController.class.getResource("/capstonebankapp/styles.css");
        if (cssUrl != null) {
            root.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.err.println("CSS file not found: /capstonebankapp/styles.css");
        }
    }

    @Override
    public void start(@NotNull Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource("/capstonebankapp/app-start-scene.fxml"));
        Parent root = fxmlLoader.load();
        style(root);
        stage.setScene(new Scene(root));
        stage.setTitle("Barclava Bank");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
