package controllers;

import entities.Formation;
import entities.Inscrit;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceFormation;
import services.ServiceInscrit;

import java.sql.SQLException;

public class CardPanierFormation {
    @FXML
    private Button btn_supprimerInscrit;

    @FXML
    private Label categorieLabel;

    @FXML
    private Label dureeLabel;

    @FXML
    private Text idtext;

    @FXML
    private Label idtitle;

    @FXML
    private Label niveauLabel;

    @FXML
    private Label prixLabel;
    private Inscrit inscrit;
    private FlowPane cardsFlowPane;
    @FXML
    private AnchorPane cardsContainer;

    public void setCardsFlowPane(FlowPane cardsFlowPane) {
        this.cardsFlowPane = cardsFlowPane;
    }

    

    private ServiceInscrit serviceInscrit = new ServiceInscrit();
    public void setData(String title, String description, String niveau, int duree, float prix, String categorie) {
        idtitle.setText(title);
        idtext.setText(description);
        niveauLabel.setText(niveau);
        dureeLabel.setText(Integer.toString(duree));
        prixLabel.setText(Float.toString(prix));
        categorieLabel.setText(categorie);
    }


    @FXML
    void SupprimerFormationInscrit(ActionEvent event) {
        try {
            serviceInscrit.supprimer(inscrit);

            if (cardsFlowPane != null) {
                cardsFlowPane.getChildren().remove(cardsContainer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setInscrit(Inscrit inscrit) {
            this.inscrit = inscrit;

    }

}






// Ajouter inscrit
    /*@FXML
    void AjouterInscrit(ActionEvent event) {
        User user=new User();
        user.setId(1);
        ServiceInscrit serviceInscrit=new ServiceInscrit();
        Inscrit inscritAjouter=new Inscrit()

    }*/

