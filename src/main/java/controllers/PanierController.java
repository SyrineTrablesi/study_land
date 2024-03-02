package controllers;

import entities.Inscrit;
import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import services.ServiceInscrit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PanierController {
    @FXML
    private FlowPane cardsFlowPane;
    public void initialize() {
        ServiceInscrit serviceInscrit=new ServiceInscrit();
        List<Inscrit> listInscrit = null;
        User user=new User();
        user.setId(2);
        try {
            listInscrit = serviceInscrit.afficherbyUser(user);
            for (Inscrit inscrit : listInscrit) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardPanierFormation.fxml"));
                Parent cardNode = loader.load();
                CardPanierFormation cardController = loader.getController();
                cardController.setData(inscrit.getTitre(),inscrit.getDescription(),inscrit.getNiveau(),inscrit.getDuree(),inscrit.getPrix(),inscrit.getNomCategorie());
                cardsFlowPane.getChildren().add(cardNode);            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
