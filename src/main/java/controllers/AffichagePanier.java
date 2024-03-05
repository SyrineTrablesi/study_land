package controllers;

import entities.Inscrit;
import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import services.ServiceInscrit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AffichagePanier {

    @FXML
    private VBox idListeInscription;

    @FXML
    private FlowPane id_inscrit;

    public void initialize() {
        ServiceInscrit serviceInscrit = new ServiceInscrit();
        List<Inscrit> listInscrit = null;
        User user = new User();
        user.setId(2); // L'ID de l'utilisateur, remplacez-le par la valeur correcte

        try {
            listInscrit = serviceInscrit.afficherbyUser(user);
            for (Inscrit inscrit : listInscrit) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardPanierInscrit.fxml"));
                Parent cardNode = loader.load();
                CardPanierInscrit cardController = loader.getController();
                cardController.setData(inscrit.getTitre(), inscrit.getDescription(), inscrit.getNiveau(), inscrit.getDuree(), inscrit.getPrix(), inscrit.getNomCategorie(), inscrit.getDateAjout());
                // Définir l'inscrit associé à cette carte
                cardController.setInscrit(inscrit);

                // Passer cardsFlowPane au contrôleur de carte
                cardController.setCardsFlowPane(id_inscrit);

                id_inscrit.getChildren().add(cardNode);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Gérer l'erreur de manière appropriée (affichage de message d'erreur, journalisation, etc.)
        }
    }
}
