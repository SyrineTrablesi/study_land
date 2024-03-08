package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeFormation {
    @FXML
    private FlowPane cardsFlowPane;

    @FXML
    private StackPane centerPane;

    public StackPane getCenterPane() {
        return centerPane;
    }

    public void setCenterPane(StackPane centerPane) {
        this.centerPane = centerPane;
    }

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

