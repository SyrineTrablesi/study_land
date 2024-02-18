package controllers;

import entities.Apprenant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import security.Session;
import security.UserInfo;

import java.io.IOException;

public class ProfileApprenant {
    Apprenant apprenant=new Apprenant();

    @FXML
    private Button btn_achat;

    @FXML
    private Button btn_certif;

    @FXML
    private Button btn_parameter;

    @FXML
    private StackPane centerPane;

    @FXML
    private Button id_btn_panier;

    @FXML
    private Label id_nom;
    // get et set
    public void setId_nom(Label id_nom) {
        this.id_nom = id_nom;
    }

    public Label getId_nom() {
        return id_nom;
    }

    @FXML
    private BorderPane rootPane;
    @FXML
    void afficherParametre(ActionEvent event) {
        UserInfo userInfo = Session.userInfo;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GererProfile.fxml"));
        try {
            Parent root = loader.load();
            centerPane.getChildren().clear();
            centerPane.getChildren().add(root);
            GererProfile controller = loader.getController();
            controller.getId_nom().setText(userInfo.nom);
            controller.getId_prenom().setText(userInfo.prenom);
            controller.getId_email().setText(userInfo.email);
            controller.getId_password().setText(userInfo.mdp);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void homePage(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Seconnecter.fxml"));
        try {
            Parent root = loader1.load();
            SeConnecter controller = loader1.getController();
            id_nom.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}


