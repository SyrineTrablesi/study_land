package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CardPanierFormation {


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
    public void setData(String title, String description, String niveau, int duree, float prix, String categorie) {
        idtitle.setText(title);
        idtext.setText(description);
        niveauLabel.setText(niveau);
        dureeLabel.setText(Integer.toString(duree));
        prixLabel.setText(Float.toString(prix));
        categorieLabel.setText(categorie);
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

