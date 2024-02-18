package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class DashboardAdmin {
    @FXML
    private Button btn_App;

    @FXML
    private BorderPane rootPane;

    @FXML
    private StackPane centerPane;

    @FXML
    void afficherApprenants(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AffichageApprenant.fxml"));
        try {
            Parent affichageApprenantRoot = loader.load();
            centerPane.getChildren().clear();
            centerPane.getChildren().add(affichageApprenantRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void afficherFormateur(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ForamteurAffichage.fxml"));
        try {
            Parent affichageFormateurRoot = loader.load();
            centerPane.getChildren().clear();
            centerPane.getChildren().add(affichageFormateurRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
