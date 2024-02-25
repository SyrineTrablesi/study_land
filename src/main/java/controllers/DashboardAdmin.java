package controllers;
import entities.Apprenant;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import security.Session;
import security.UserInfo;
import services.ServiceApprenant;
import services.ServiceUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DashboardAdmin {


    @FXML
    private Button btn_Admin;

    @FXML
    private Button btn_App;

    @FXML
    private Button btn_formateur;

    @FXML
    private Button btn_parameter;

    @FXML
    private Button btn_profile;

    @FXML
    private StackPane centerPane;

    @FXML
    private Button id_home;

    @FXML
    private Label id_nom1;

    @FXML
    private Button id_projet;

    @FXML
    private TextField id_recherche;

    @FXML
    private VBox id_side_bar;

    @FXML
    private BorderPane rootPane;
    @FXML
    void afficherApprenants(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AffichageApprenant.fxml"));
        try {
            Parent affichageApprenantRoot = loader.load();
            centerPane.getChildren().clear();
            centerPane.getChildren().add(affichageApprenantRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void afficherFormateur(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ForamteurAffichage.fxml"));
        try {
            Parent affichageFormateurRoot = loader.load();
            centerPane.getChildren().clear();
            centerPane.getChildren().add(affichageFormateurRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void afficherAdmin(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminAffichage.fxml"));
        try {
            Parent affichageAdminRoot = loader.load();
            centerPane.getChildren().clear();
            centerPane.getChildren().add(affichageAdminRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void afficherParametre(ActionEvent event) {
        UserInfo userInfo = Session.getInstance().userInfo;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GererProfile.fxml"));
        try {
            Parent root = loader.load();
            centerPane.getChildren().clear();
            centerPane.getChildren().add(root);
            GererProfile controller = loader.getController();
            controller.getId_nom().setText(userInfo.nom);
            controller.getId_prenom().setText(userInfo.prenom);
            controller.getId_email().setText(userInfo.email);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void home(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Seconnecter.fxml"));
        try {
            Parent root = loader1.load();
            SeConnecter controller = loader1.getController();
          btn_App.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void onREcherche(ActionEvent event) {
        String email = id_recherche.getText(); // Supprimez les espaces inutiles
        System.out.println(email);

        try {
            ServiceUser  serviceUser = new ServiceUser();

            try {
                User user = serviceUser.rechercheUserParEmail(email);
                if (user != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardUser.fxml"));
                    Parent root = loader.load();
                    centerPane.getChildren().clear();
                    centerPane.getChildren().add(root);
                    CardUser cardUserController = loader.getController();
                    cardUserController.getEmail().setText(user.getEmail());
                    cardUserController.getNom_user().setText(user.getNom());
                    cardUserController.getPrenom_user().setText(user.getPrenom());
                    cardUserController.getRole().setText(user.getRole());
                } else {
                    System.out.println("Aucun utilisateur trouvé pour l'email spécifié.");
                }
            } catch (SQLException e) {
                // Gérer l'exception SQL
                e.printStackTrace();
                System.out.println("Erreur lors de la recherche de l'utilisateur.");
            }
        } catch (IOException e) {
            // Gérer l'exception d'entrée/sortie
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de l'interface utilisateur.");
        }
    }


}


