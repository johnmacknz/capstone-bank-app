package controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ControllerLogic {
    static void openNewScene(@NotNull ActionEvent actionEvent, String filePath) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(AppStartController.class.getResource(filePath));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Barclava Bank");
        currentStage.close();
        newStage.show();
    }

    public static void addClock(Label timeLabel) {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e->
               timeLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public static void accountCreatedCheck() {
        if (SignUpController.ApplicationContext.isAccountCreated() && !LoginController.accountCreatedShown) {
            LoginController.accountCreation.setText("Account Successfully Created");
            LoginController.accountCreatedShown = true;
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), LoginController.accountCreation);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            fadeOut.setOnFinished(event->{
                LoginController.accountCreation.setVisible(false);
            });

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(3), event->{
                        fadeOut.play();
                    })
            );
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    public static void populateGridRow(@NotNull Label label1, @NotNull Label label2, @NotNull Label label3, String value1, String value2, String value3) {
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label1.setText(value1);
        label2.setText(value2);
        label3.setText(value3);
    }
}
