package controllers;

import entities.Achat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.ServiceAchat;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class AffichageAchat {

    @FXML
    private FlowPane id_Achat;

    @FXML
    private StackPane centerPane;

    public StackPane getCenterPane() {
        return centerPane;
    }

    public void setCenterPane(StackPane centerPane) {
        this.centerPane = centerPane;
    }

    public void initialize() {
        ServiceAchat serviceAchat = new ServiceAchat();
        List<Achat> listAchat = null;
        User user = new User();
        user.setId(2); // L'ID de l'utilisateur, remplacez-le par la valeur correcte

        try {
            listAchat = serviceAchat.afficherbyUser(user);
            for (Achat achat : listAchat) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardAchat.fxml"));
                Parent cardNode = loader.load();
                CardAchat cardController = loader.getController();
                cardController.setData(achat.getTitre(), achat.getDescription(), achat.getNiveau(), achat.getDuree(), achat.getPrix(), achat.getNomCategorie(), achat.getDateAjout());
                // Définir l'inscrit associé à cette carte
                cardController.setAchat(achat);

                // Passer cardsFlowPane au contrôleur de carte
                cardController.setCardsFlowPane(id_Achat);

                id_Achat.getChildren().add(cardNode);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Gérer l'erreur de manière appropriée (affichage de message d'erreur, journalisation, etc.)
        }

    }
    @FXML
    void btnFacture(ActionEvent event) {
        try {
            // Charger la nouvelle fenêtre FactureUtilisateur.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Facture.fxml"));
            Parent root = loader.load();

            // Accéder au contrôleur de la nouvelle fenêtre
            FactureController controller = loader.getController();

            // Définir les détails de la facture dans le contrôleur
            ServiceAchat serviceAchat = new ServiceAchat();
            User user = new User();
            user.setId(2); // Remplacez par l'ID de l'utilisateur connecté
            List<Achat> achats = serviceAchat.afficherbyUser(user); // Récupérer les achats de l'utilisateur
            float facture = serviceAchat.calculerFactureUtilisateur(user);
            controller.afficherFacture(user, achats, facture); // Utiliser la méthode afficherFacture

            // Afficher la nouvelle fenêtre
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }




}
