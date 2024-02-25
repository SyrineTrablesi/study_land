/*package controllers;

import entities.Panier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.ServicePanier;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class ModifierPanierController {
    

    @FXML
    private TextField tfIdPanier;

    @FXML
    private TextField tfIdFormation;

    @FXML
    private TextField tfIdUtilisateur;

    ServicePanier servicePanier = new ServicePanier();


    @FXML
    private DatePicker date_ajout_Panier;

    @FXML
    private TextField tfId_formation;

    @FXML
    private TextField tfId_user;


    public TextField getTfIdPanier() {
        return tfIdPanier;
    }

    public void setTfIdPanier(TextField tfIdPanier) {
        this.tfIdPanier = tfIdPanier;
    }

    public TextField getTfIdFormation() {
        return tfIdFormation;
    }

    public void setTfIdFormation(TextField tfIdFormation) {
        this.tfIdFormation = tfIdFormation;
    }

    public TextField getTfIdUtilisateur() {
        return tfIdUtilisateur;
    }

    public void setTfIdUtilisateur(TextField tfIdUtilisateur) {
        this.tfIdUtilisateur = tfIdUtilisateur;
    }


    private Panier panierSelectionne;

    public void initData(Panier panierSelectionne) {
        // Initialiser les champs de texte avec les données du panier sélectionné
        tfIdPanier.setText(String.valueOf(panierSelectionne.getId_panier()));
        tfIdFormation.setText(String.valueOf(panierSelectionne.getId_formation()));
        tfIdUtilisateur.setText(String.valueOf(panierSelectionne.getId_user()));
    }




    /*
        // Afficher une boîte de dialogue de confirmation
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation de la modification");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir valider les modifications ?");

        // Ajouter les boutons "Oui" et "Non"
        ButtonType ouiButton = new ButtonType("Oui");
        ButtonType nonButton = new ButtonType("Non");
        confirmationDialog.getButtonTypes().setAll(ouiButton, nonButton);

        // Attendre la réponse de l'utilisateur

        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ouiButton) {
            // Valider les modifications et afficher un message de succès
            validerModifications();
            afficherMessage("Modification validée avec succès !");
            // Fermer la fenêtre de modification
            Stage stage = (Stage) tfIdPanier.getScene().getWindow();
            stage.close();
        } else {
            // Annuler les modifications et afficher un message d'annulation
            afficherMessage("Modification annulée.");
            Stage stage = (Stage) tfIdPanier.getScene().getWindow();
            stage.close();
        }



 }

 */


