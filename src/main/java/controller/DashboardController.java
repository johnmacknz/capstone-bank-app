package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;


public class DashboardController {
    @javafx.fxml.FXML
    private ImageView barclavaImageView;
    @javafx.fxml.FXML
    private HBox accountInfoHbox;
    @javafx.fxml.FXML
    private TextFlow noAccountCreatedTextFlow;
    @javafx.fxml.FXML
    private Hyperlink hereHyperLink;

    public void initialize() {
        //TODO boolean expresion returns true if user has an account
        if (true == false) {

        } else {
            accountInfoHbox.setVisible(false);
            noAccountCreatedTextFlow.setVisible(true);

        }
    }



    @javafx.fxml.FXML
    public void handleHereHyperLink(ActionEvent actionEvent) {
        accountInfoHbox.setVisible(true);
        noAccountCreatedTextFlow.setVisible(false);
    }
}
