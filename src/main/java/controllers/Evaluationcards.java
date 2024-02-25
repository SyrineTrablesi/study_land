package controllers;

import entities.evaluation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import services.EvalService;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Evaluationcards {
    @FXML
    private Button idbutton;

    @FXML
    private TextField textrecherche;

    @FXML
    private FlowPane cardsFlowPane;

    EvalService eval = new EvalService();

    public void initialize() {
        try {
            List<evaluation> evaluations = eval.afficher();

            for (evaluation eval : evaluations) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/evaluationcard.fxml"));
                Parent cardNode = loader.load();

                // Access the controller of the Evaluationcard
                Evaluationcard cardController = loader.getController();
                cardController.setIdtitle(eval.getTitre_evaluation());
                cardController.setIdtext(eval.getDescription());

                // Add the card to the FlowPane
                cardsFlowPane.getChildren().add(cardNode);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }


    public void recherche(ActionEvent actionEvent) {
            try {
                String caractereRecherche = textrecherche.getText().trim();

                // Clear existing cards before displaying new search results
                cardsFlowPane.getChildren().clear();

                if (!caractereRecherche.isEmpty()) {
                    List<evaluation> evaluationsTrouvees = eval.rechercherParCaractere(caractereRecherche);

                    for (evaluation eval : evaluationsTrouvees) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/evaluationcard.fxml"));
                        Parent cardNode = loader.load();

                        // Access the controller of the Evaluationcard
                        Evaluationcard cardController = loader.getController();
                        cardController.setIdtitle(eval.getTitre_evaluation());
                        cardController.setIdtext(eval.getDescription());

                        // Add the card to the FlowPane
                        cardsFlowPane.getChildren().add(cardNode);
                    }
                } else {
                    // If the search field is empty, display all evaluations
                    initialize();
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }

    }

