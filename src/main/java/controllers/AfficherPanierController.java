package controllers;

import entities.Panier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import services.ServicePanier;

import java.sql.SQLException;
import java.util.List;

public class AfficherPanierController {

    @FXML
    private Label labelFromDB;

    @FXML
    private Label labelDate_ajout;

    @FXML
    private Label labelId_formation;

    @FXML
    private Label labelId_user;

    @FXML
    private Label labelType_formation;

    public String getLabelDate_ajout() {
        return labelDate_ajout.getText();
    }

    public void setLabelDate_ajout(String labelDate_ajout) {
        this.labelDate_ajout.setText(labelDate_ajout);
    }

    public String getLabelId_formation() {
        return labelId_formation.getText();
    }

    public void setLabelId_formation(String labelId_formation) {
        this.labelId_formation.setText(labelId_formation);
    }

    public String getLabelId_user() {
        return labelId_user.getText();
    }

    public void setLabelId_user(String labelId_user) {
        this.labelId_user.setText(labelId_user);
    }

    public String getLabelType_formation() {
        return labelType_formation.getText();
    }

    public void setLabelType_formation(String labelType_formation) {
        this.labelType_formation.setText(labelType_formation);
    }

    private ServicePanier ps = new ServicePanier();

    @FXML
    private ListView<String> panierListView;

    @FXML
    void afficherDB(ActionEvent event) {
/*
         try {
            List<Panier> paniers = ps.afficherPanier();

            // Clear any existing items in the ListView
            panierListView.getItems().clear();

            // Add each Panier object's information to the ListView
            for (Panier panier : paniers) {
                panierListView.getItems().add(panier.toString());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

 */


            try {
                labelFromDB.setText(ps.afficherPanier().toString());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


    }


}
