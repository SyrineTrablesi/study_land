package controllers;

import entities.Achat;
import entities.Favoris;
import java.text.SimpleDateFormat;

import entities.Inscrit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import services.ServiceAchat;
import services.ServiceFavoris;


import java.sql.SQLException;
import java.util.Date;

public class CardAchat {


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

    private Achat achat;
    private FlowPane cardsFlowPane;


    public void setCardsFlowPane(FlowPane cardsFlowPane) {
        this.cardsFlowPane = cardsFlowPane;
    }

    private ServiceAchat serviceAchat = new ServiceAchat();
    public void setAchat(Achat achat) {
        this.achat = achat;

    }

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
}
