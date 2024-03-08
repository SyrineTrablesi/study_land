package controllers;

import entities.Favoris;
import entities.Inscrit;
import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import services.ServiceFavoris;
import services.ServiceInscrit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PanierController {

    @FXML
    private VBox idListeInscription;

    @FXML
    private FlowPane id_inscrit;

    @FXML
    private VBox idListeFavoris;

    @FXML
    private FlowPane id_favoris;

    @FXML
    private AnchorPane panierAnchorPane;

    @FXML
    private AnchorPane favorisAnchorPane;

    @FXML
    public void showFavorisPage() {
        panierAnchorPane.setVisible(false);
        favorisAnchorPane.setVisible(true);
    }

    @FXML
    public void showPanierPage() {
        favorisAnchorPane.setVisible(false);
        panierAnchorPane.setVisible(true);
    }


    public void initialize() {
        // Partie du contrôleur pour afficher les inscrits
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

        // Partie du contrôleur pour afficher les favoris
        ServiceFavoris serviceFavoris = new ServiceFavoris();
        List<Favoris> listFavoris = null;

        try {
            listFavoris = serviceFavoris.afficherbyUser(user);
            if (listFavoris != null) {
                for (Favoris favoris : listFavoris) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardPanierFavoris.fxml"));
                    Parent cardNode = null;
                    try {
                        cardNode = loader.load();
                        CardPanierFavoris cardController = loader.getController();
                        cardController.setData(favoris.getTitre(), favoris.getDescription(), favoris.getNiveau(), favoris.getDuree(), favoris.getPrix(), favoris.getNomCategorie(), favoris.getDateAjout());
                        cardController.setFavoris(favoris);
                        cardController.setCardsFlowPane2(id_favoris); // Passer la référence au FlowPane
                        id_favoris.getChildren().add(cardNode);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                System.out.println("La liste des favoris est vide.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

