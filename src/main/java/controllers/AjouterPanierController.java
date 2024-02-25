/*package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import services.ServicePanier;
import entities.Panier;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class AjouterPanierController {

    @FXML
    private DatePicker date_ajout;

    @FXML
    private TextField tfId_formation;

    @FXML
    private TextField tfId_user;

    @FXML
    private ChoiceBox<String> tfTypeFormation;

    private ServicePanier servicePanier;

    public AjouterPanierController() {
        servicePanier = new ServicePanier();
    }

    @FXML
    void initialize() {
        // Ajouter les choix "favoris" et "inscrite" à la ChoiceBox
        tfTypeFormation.getItems().addAll("favoris", "inscrite");

    }

    @FXML
    void ajouterPanier() {
        try {
            // Récupérer les valeurs des champs en tant qu'entiers
            int id_user = Integer.parseInt(tfId_user.getText());
            int id_formation = Integer.parseInt(tfId_formation.getText());
            String typeFormation = tfTypeFormation.getValue();

            if (id_user <= 0 || id_formation <= 0 || typeFormation == null) {
                afficherAlerte("Erreur", "Veuillez remplir tous les champs obligatoires.");
                return;
            }

            LocalDate date_ajout = getDate_ajout();

            if (date_ajout == null) {
                afficherAlerte("Erreur", "Veuillez sélectionner une date valide.");
                return; // Sortir de la méthode si la date est null
            }

            // Créer une instance de Panier avec les valeurs récupérées
            Panier nouveauPanier = new Panier();
            nouveauPanier.setId_user(id_user);
            nouveauPanier.setId_formation(id_formation);
            nouveauPanier.setDate_ajout(date_ajout);
            nouveauPanier.setTypeFormation(Panier.TypeFormation.valueOf(typeFormation));

            // Appeler la méthode ajouterPanier avec la nouvelle instance de Panier
            boolean ajoutReussi = false;
            try {
                servicePanier.ajouterPanier(nouveauPanier);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (ajoutReussi) {
                afficherMessageAjoutReussi();
                // Effacer les champs après l'ajout réussi
                tfId_user.clear();
                tfId_formation.clear();
                tfTypeFormation.getSelectionModel().clearSelection();
            } else {
                afficherAlerte("Erreur", "Formation déjà ajoutée.");
            }

        } catch (NumberFormatException e) {
            afficherAlerte("Erreur", "Veuillez saisir des valeurs numériques valides pour les champs 'id_user' et 'id_formation'.");
            e.printStackTrace();
        }
    }
     LocalDate getDate_ajout() {
        return date_ajout.getValue();
    }

    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void afficherMessageAjoutReussi() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajout_reussi.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Ajout réussi");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void afficherMonPanier(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML de la scène d'affichage du panier
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherPanier.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir le stage actuel (celui du bouton "Mon Panier")
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Changer la scène du stage actuel pour afficher le contenu du panier
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
                e.printStackTrace();
            }
    }

}

 */


