package controllers;

import entities.Favoris;
import java.text.SimpleDateFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import services.ServiceFavoris;


import java.sql.SQLException;
import java.util.Date;

public class CardPanierFavoris {
    @FXML
    private Button btn_supprimerFavoris;

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

    @FXML
    private Label labelAjoutDate;

    @FXML
    private AnchorPane cardsContainer;
    private Favoris favoris;
    private FlowPane cardsFlowPane;

    public void setCardsFlowPane2(FlowPane cardsFlowPane) {
        this.cardsFlowPane = cardsFlowPane;
    }


    private ServiceFavoris serviceFavoris = new ServiceFavoris();

    public void setData(String title, String description, String niveau, int duree, float prix, String categorie, Date dateAjout) {
        idtitle.setText(title);
        idtext.setText(description);
        niveauLabel.setText(niveau);
        dureeLabel.setText(Integer.toString(duree));
        prixLabel.setText(Float.toString(prix));
        categorieLabel.setText(categorie);

        // Si vous devez formater la date, vous pouvez le faire ici
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        String formattedDate = sdf.format(dateAjout);

        // Affichez la date d'ajout dans votre vue
        labelAjoutDate.setText(formattedDate);

    }


    public void setFavoris(Favoris favoris) {
        this.favoris = favoris;

    }

    @FXML
    public void supprimerFormationFavoris(ActionEvent actionEvent) {
        if (favoris != null) {
            try {
                serviceFavoris.supprimer(favoris); // Utiliser l'objet Favoris pour supprimer
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("formation supprimée");
            if (cardsFlowPane != null) {
                cardsFlowPane.getChildren().remove(cardsContainer);
            } else {
                System.out.println("cardsFlowPane est null");
            }
        }
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

