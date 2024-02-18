package controllers;

import entities.evaluation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.EvalService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Lesevaluation {
    EvalService evalService = new EvalService();

    @FXML
    private ListView<String> evaluationListView;

    @FXML
    private ScrollPane Scroll;

    @FXML
    public void initialize() {
        try {
            List<evaluation> evaluations = evalService.afficher();
            displayEvaluations(evaluations);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private void displayEvaluations(List<evaluation> evaluations) {
        for (evaluation eval : evaluations) {
            String evaluationText = "ID de l'evaluation " + eval.getId_evaluation() + "                   Le Titre de evaluation : " + eval.getTitre_evaluation() ;
            evaluationListView.getItems().add(evaluationText);
        }
    }

    public void ajouterevaluation(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajoutevaluation.fxml"));
            Parent root = loader.load();

            // Obtenez le contrôleur après avoir chargé le fichier FXML
            Ajoutevaluation affichepre = loader.getController();

            // Créez une nouvelle scène avec le Parent chargé
            Scene scene = new Scene(root);

            // Récupérez la scène actuelle à partir du bouton
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Remplacez la scène actuelle par la nouvelle scène
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifierevaluation(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifierevaluation.fxml"));
            Parent root = loader.load();

            // Obtenez le contrôleur après avoir chargé le fichier FXML
            Modifierevaluation affichepre = loader.getController();

            // Créez une nouvelle scène avec le Parent chargé
            Scene scene = new Scene(root);

            // Récupérez la scène actuelle à partir du bouton
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Remplacez la scène actuelle par la nouvelle scène
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
