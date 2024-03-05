package controllers;

import entities.Formation;
import entities.Inscrit;
import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import services.ServiceFormation;
import services.ServiceInscrit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeFormation {
    @FXML
    private FlowPane cardsFlowPane;

    public void initialize() {
        ServiceFormation serviceFormation = new ServiceFormation();
        List<Formation> listFormation = null;
//        User user = new User();
//        user.setId(2);
        try {
            listFormation = serviceFormation.afficher();
            for (Formation formation : listFormation) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardHomeFormation.fxml"));
                Parent cardNode = loader.load();
                CardHomeFormation cardController = loader.getController();
                cardController.setData(formation.getTitre(), formation.getDescription(), formation.getNiveau(), formation.getDuree(), formation.getPrix(), formation.getNomCategorie());
                cardsFlowPane.getChildren().add(cardNode);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

