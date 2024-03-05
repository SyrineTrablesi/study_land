package controllers;

import entities.Favoris;
import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import services.ServiceFavoris;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AffichageFavoris {
    @FXML
    private VBox idListeFavoris;

    @FXML
    private FlowPane id_favoris;

    public void initialize() {
        ServiceFavoris serviceFavoris = new ServiceFavoris();
        List<Favoris> listFavoris = null;
        User user = new User();
        user.setId(2);

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
