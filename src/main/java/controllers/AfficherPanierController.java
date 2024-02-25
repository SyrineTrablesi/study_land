/*package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import entities.Panier;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import services.ServicePanier;
import javafx.collections.FXCollections;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;

public class AfficherPanierController {

    @FXML
    private TableView<Panier> tableViewPanier;

    @FXML
    private TableColumn<Panier, Integer> columnIdPanier;

    @FXML
    private TableColumn<Panier, Integer> columnIdUser;

    @FXML
    private TableColumn<Panier, Integer> columnIdFormation;

    @FXML
    private TableColumn<Panier, String> columnDateAjout;

    @FXML
    private TableColumn<Panier, String> columnTypeFormation;
    @FXML
    private TextField tfIdPanier;

    @FXML
    private TextField tfIdFormation;

    @FXML
    private TextField tfIdUtilisateur;

    ServicePanier servicePanier = new ServicePanier();


    ServicePanier sp = new ServicePanier();
    private TableView<Panier> table;

    public AfficherPanierController() {
        servicePanier = new ServicePanier();
    }

    @FXML
    void initialize() {
        columnIdPanier.setCellValueFactory(new PropertyValueFactory<>("idPanier"));
        columnIdUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        columnIdFormation.setCellValueFactory(new PropertyValueFactory<>("idFormation"));
        columnDateAjout.setCellValueFactory(new PropertyValueFactory<>("dateAjout"));
        columnTypeFormation.setCellValueFactory(new PropertyValueFactory<>("typeFormation"));
        loadPanierData();
        afficherContenuPanier();

    }
    private void loadPanierData() {
        try {
            ObservableList<Panier> paniers = FXCollections.observableArrayList(servicePanier.afficherPanier());
            tableViewPanier.setItems(paniers);
        } catch (SQLException e) {
            afficherAlerte("Erreur", "Impossible d'afficher le contenu du panier : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void afficherContenuPanier() {
        try {
            ObservableList<Panier> paniers = FXCollections.observableArrayList(servicePanier.afficherPanier());
            tableViewPanier.setItems(paniers);
        } catch (SQLException e) {
            afficherAlerte("Erreur", "Impossible d'afficher le contenu du panier : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void supprimerLigne(ActionEvent event) {
        // Obtenir la ligne sélectionnée dans le TableView
        Panier panierSelectionne = tableViewPanier.getSelectionModel().getSelectedItem();

        if (panierSelectionne != null) {
            // Afficher une boîte de dialogue de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Supprimer la ligne sélectionnée ?");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette ligne ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Supprimer la ligne du TableView
                tableViewPanier.getItems().remove(panierSelectionne);
                // Supprimer la ligne de la base de données
                try {
                    sp.supprimerPanier(panierSelectionne);
                } catch (SQLException e) {
                    System.out.println("Impossible de suppimer");
                }
            }
        } else {
            // Afficher un message d'avertissement si aucune ligne n'est sélectionnée
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucune ligne sélectionnée");
            alert.setContentText("Veuillez sélectionner une ligne à supprimer.");
            alert.showAndWait();
        }
    }

    // Méthode pour gérer l'action du bouton "Modifier"
    @FXML
    private void modifierLigne(ActionEvent event) {
        // Obtenir la ligne sélectionnée dans le TableView
        Panier panierSelectionne = tableViewPanier.getSelectionModel().getSelectedItem();

        if (panierSelectionne != null) {
            // Afficher une nouvelle fenêtre de modification avec les données de la ligne sélectionnée (à implémenter)
            afficherFenetreModification(panierSelectionne);
        } else {
            // Afficher un message d'avertissement si aucune ligne n'est sélectionnée
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucune ligne sélectionnée");
            alert.setContentText("Veuillez sélectionner une ligne à modifier.");
            alert.showAndWait();
        }
    }

    // Méthode pour afficher la fenêtre de modification avec les données de la ligne sélectionnée
    private void afficherFenetreModification(Panier panierSelectionne) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fenetre_modification.fxml"));
            Parent root = loader.load();

            // Passer les données de 'panierSelectionne' au contrôleur de la fenêtre de modification
            ModifierPanierController modifierPanierController = loader.getController();
            modifierPanierController.initData(panierSelectionne);

            // Afficher la fenêtre de modification
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modification du Panier");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void validerModification() {
        try {
            // Récupérer les valeurs des champs
            int idPanier = Integer.parseInt(tfIdPanier.getText());
            int idFormation = Integer.parseInt(tfIdFormation.getText());
            int idUtilisateur = Integer.parseInt(tfIdUtilisateur.getText());

            // Obtenir la date de la sélection précédente
            Panier panierSelectionne = tableViewPanier.getSelectionModel().getSelectedItem();
            Date dateAjout = Date.valueOf(panierSelectionne.getDate_ajout());
            String typeFormation = String.valueOf(panierSelectionne.getTypeFormation());

            // Mettre à jour la ligne correspondante dans la base de données
            Panier nouveauPanier = new Panier();
            servicePanier.modifierPanier(nouveauPanier);

            // Afficher un message de confirmation à l'utilisateur
            afficherMessage("Modification réussie !");
        } catch (NumberFormatException e) {
            // Gérer les erreurs de format des champs (par exemple, si l'utilisateur entre du texte au lieu de nombres)
            System.err.println("Veuillez entrer des valeurs numériques valides.");
        } catch (SQLException e) {
            // Gérer les erreurs liées à l'accès à la base de données
            System.err.println("Erreur lors de la modification du panier : " + e.getMessage());
        }
    }
    private void afficherMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void retournerAInterfaceAjout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterPanier.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
 */

