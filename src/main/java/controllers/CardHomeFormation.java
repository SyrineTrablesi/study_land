package controllers;

import entities.Favoris;
import entities.Formation;
import entities.Inscrit;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceFavoris;
import services.ServiceFormation;
import services.ServiceInscrit;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.Format;


public class CardHomeFormation {
    @FXML
    private AnchorPane cardsContainer;

    @FXML
    private FlowPane cardsFlowPane;

    @FXML
    private FlowPane cardsFlowPane2;

    @FXML
    private AnchorPane idListFavoris;

    @FXML
    private AnchorPane idListInscite;

    @FXML
    private Button btn_AjoutFavoris;

    @FXML
    private Button btn_AjoutInscrit;

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
    @FXML
    void btn_AjouterFavoris(ActionEvent event) {
        User user =new User();
        user.setId(2);
        ServiceFormation serviceFormation=new ServiceFormation();
        try {
             Formation formation= serviceFormation.rechercherParTitre(idtitle.getText());
             //instance de formation type favoris
             Favoris favoris = new Favoris( user.getId(),formation.getIdFormation());
             favoris.setDescription(formation.getDescription());
             favoris.setNiveau(formation.getNiveau());
             favoris.setNomCategorie(formation.getNomCategorie());
             favoris.setTitre(formation.getTitre());
             favoris.setDuree(formation.getDuree());
             ServiceFavoris serviceFavoris=new ServiceFavoris();
             System.out.println(favoris);
             serviceFavoris.ajouter(favoris);
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }

    }

    @FXML
    void btn_AjouterInscrit(ActionEvent event) {
        User user = new User();
        user.setId(2);
        ServiceFormation serviceFormation = new ServiceFormation();
        try {
            Formation formation = serviceFormation.rechercherParTitre(idtitle.getText());

            // Vérifier si le prix de la formation est égal à zéro
            if (formation.getPrix() == 0) {
                // Ajouter l'utilisateur à la liste des inscrits
                Inscrit inscrit = new Inscrit(user.getId(), formation.getIdFormation());
                inscrit.setDescription(formation.getDescription());
                inscrit.setNiveau(formation.getNiveau());
                inscrit.setNomCategorie(formation.getNomCategorie());
                inscrit.setTitre(formation.getTitre());
                inscrit.setDuree(formation.getDuree());
                ServiceInscrit serviceInscrit = new ServiceInscrit();
                System.out.println(inscrit);
                serviceInscrit.ajouter(inscrit);
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/TicketPayment.fxml"));
                Parent root = loader.load();
                TicketPaymentController controller = loader.getController();
                controller.setFormation(formation);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (SQLException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de l'inscription.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

//    private void redirectToPaymentProcess(Formation formation) {
//        // Implémentez ici votre processus de paiement
//        // ouvrir une nouvelle fenêtre pour le processus de paiement
//        // ou charger une autre vue qui gère le processus de paiement
//
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketPayment.fxml"));
//            Parent root = loader.load();
//            TicketPaymentController controller = loader.getController();
//            controller.setFormation(formation); // Passer la formation à la vue de paiement
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (IOException e) {
//                e.printStackTrace();
//        }
//
//
//
//    }
}

