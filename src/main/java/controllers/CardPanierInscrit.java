package controllers;

import entities.Inscrit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import services.ServiceInscrit;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CardPanierInscrit {
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

    @FXML
    private AnchorPane cardsContainer;

    @FXML
    private Label labelAjoutDate;

    @FXML
    private StackPane centerPane;

    public StackPane getCenterPane() {
        return centerPane;
    }

    public void setCenterPane(StackPane centerPane) {
        this.centerPane = centerPane;
    }

    private Inscrit inscrit;
    private FlowPane cardsFlowPane;


    public void setCardsFlowPane(FlowPane cardsFlowPane) {
        this.cardsFlowPane = cardsFlowPane;
    }

    

    private ServiceInscrit serviceInscrit = new ServiceInscrit();
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

    public void setInscrit(Inscrit inscrit) {
            this.inscrit = inscrit;

    }


    public void supprimerFormationInscrit(ActionEvent actionEvent) {

        if (inscrit != null) {
            try {
                serviceInscrit.supprimer(inscrit);
                System.out.println("Formation supprimee avec succès");

                // Supprimer l'élément visuel correspondant à l'objet supprimé
                cardsFlowPane.getChildren().remove(cardsContainer);

            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la suppression de la formation", e);
            }
        } else {
            System.out.println("Aucune formation sélectionnée pour la suppression");
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

