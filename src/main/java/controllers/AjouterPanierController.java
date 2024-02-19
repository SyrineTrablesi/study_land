package controllers;

import entities.Panier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.ServicePanier;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AjouterPanierController implements Initializable {
    ServicePanier sp = new ServicePanier();

    @FXML
    private DatePicker date_ajout_Panier;

    @FXML
    private TextField tfId_formation;

    @FXML
    private TextField tfId_user;

    @FXML
    private ChoiceBox<String> tfTypeFormation;
    private String[] type_formation = {"favoris", "inscrite"};

    @FXML
    void afficherPanier(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherPanier.fxml"));
        try {
            Parent root = loader.load();
            AfficherPanierController afficherController = loader.getController();
            afficherController.setLabelId_user(tfId_user.getText());
            afficherController.setLabelId_formation(tfId_formation.getText());
            afficherController.setLabelDate_ajout(date_ajout_Panier.getValue().toString());
            afficherController.setLabelType_formation(tfTypeFormation.getValue());

            tfId_user.getScene().setRoot(root);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void ajouterPanier(ActionEvent event) {
        try {
            int id_user = Integer.parseInt(tfId_user.getText());
            int id_formation = Integer.parseInt(tfId_formation.getText());
            String type_formation = tfTypeFormation.getValue();

            if (id_user <= 0 || id_formation <= 0 || type_formation == null) {
                System.err.println("Veuillez remplir tous les champs obligatoires.");
                return;
            }

            LocalDate date_ajout = getDate_ajout();

            if (date_ajout == null) {
                System.err.println("Veuillez sélectionner une date valide.");
                return;
            }

            try {
                sp.ajouterPanier(new Panier(id_user, id_formation, date_ajout, type_formation));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (NumberFormatException e) {
            System.err.println("Veuillez saisir des valeurs numériques valides pour les champs 'id_user' et 'id_formation'.");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfTypeFormation.getItems().addAll(type_formation);
    }

    // Méthode pour obtenir la date d'ajout du DatePicker
    private LocalDate getDate_ajout() {
        return date_ajout_Panier.getValue();
    }
}

