package controllers;

import entities.Achat;
import entities.Inscrit;
import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import services.ServiceAchat;
import services.ServiceInscrit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class AffichageAchat {

    @FXML
    private FlowPane id_Achat;

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
}
