package controllers;

import entities.Favoris;
import entities.Inscrit;
import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import services.ServiceFavoris;
import services.ServiceInscrit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PanierController {
    @FXML
    private FlowPane cardsFlowPane;
    @FXML
    private AnchorPane cardsContainer;

    @FXML
    private FlowPane cardsFlowPane2;

    @FXML
    private AnchorPane idListFavoris;

    @FXML
    private AnchorPane idListInscite;
    public void initialize() {
        ServiceInscrit serviceInscrit = new ServiceInscrit();
        ServiceFavoris serviceFavoris = new ServiceFavoris();
        List<Inscrit> listInscrit = null;
        List<Favoris> listFavoris = null;
        User user = new User();
        user.setId(2);

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
                cardController.setCardsFlowPane(cardsFlowPane);

                cardsFlowPane.getChildren().add(cardNode);
            }

            listFavoris = serviceFavoris.afficherbyUser(user);
            for (Favoris favoris : listFavoris) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardPanierFavoris.fxml"));
                Parent cardNode = loader.load();
                CardPanierFavoris cardController = loader.getController();
                cardController.setData(favoris.getTitre(), favoris.getDescription(), favoris.getNiveau(), favoris.getDuree(), favoris.getPrix(), favoris.getNomCategorie(), favoris.getDateAjout());
                // Définir le favoris associé à cette carte
                cardController.setFavoris(favoris);

                // Passer cardsFlowPane2 au contrôleur de carte
                cardController.setCardsFlowPane2(cardsFlowPane2);

                cardsFlowPane2.getChildren().add(cardNode);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


//    public void initialize() {
//        ServiceInscrit serviceInscrit = new ServiceInscrit();
//        ServiceFavoris serviceFavoris = new ServiceFavoris();
//        List<Inscrit> listInscrit = null;
//        List<Favoris> listFavoris = null;
//        User user = new User();
//        user.setId(2);
//        try {
//            listInscrit = serviceInscrit.afficherbyUser(user);
//            for (Inscrit inscrit : listInscrit) {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardPanierInscrit.fxml"));
//                Parent cardNode = loader.load();
//                CardPanierInscrit cardController = loader.getController();
//                cardController.setData(inscrit.getTitre(), inscrit.getDescription(), inscrit.getNiveau(), inscrit.getDuree(), inscrit.getPrix(), inscrit.getNomCategorie());
//                // Définir l'inscrit associé à cette carte
//                cardController.setInscrit(inscrit);
//
//                // Passer cardsFlowPane au contrôleur de carte
//                cardController.setCardsFlowPane(cardsFlowPane);
//
//                cardsFlowPane.getChildren().add(cardNode);
//
//            }
//
//        } catch (SQLException | IOException e) {
//            throw new RuntimeException(e);
//
//        }


    }}
