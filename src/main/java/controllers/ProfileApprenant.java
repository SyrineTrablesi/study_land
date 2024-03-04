package controllers;

import entities.Apprenant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import security.Session;
import security.UserInfo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileApprenant {
    Apprenant apprenant=new Apprenant();
    @FXML
    private ImageView img;

    @FXML
    private Button btn_parameter;

    @FXML
    private Button btn_profile;

    @FXML
    private StackPane centerPane;

    @FXML
    private Button id_evaluation;

    @FXML
    private Button id_formation;

    @FXML
    private Button id_home;

    @FXML
    private Label id_nom;
    @FXML
    private Label id_nom1;

    @FXML
    private Button id_panier;

    @FXML
    private Button id_projet;
    @FXML
    private ComboBox<String> combo_login;
    @FXML
    private TextField id_recherche_formation;

    @FXML
    private VBox id_side_bar;

    @FXML
    private BorderPane rootPane;

    public Label getId_nom1() {
        return id_nom1;
    }

    UserInfo userInfo = Session.getInstance().userInfo;

    public void setId_nom1(Label id_nom1) {
        this.id_nom1 = id_nom1;
    }

    @FXML
    void modification(ActionEvent event) {
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
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/home.fxml"));
        try {
            Parent root = loader1.load();
            SeConnecter controller = loader1.getController();
            id_nom1.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    @FXML
    void onComboLoginSelected(ActionEvent event) {
        String selectedOption = combo_login.getValue();
        switch (selectedOption) {
            case "Modifier Email":
                // Pas besoin de se déconnecter de la session ici
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/modifierEmail.fxml"));
                try {
                    Parent defaultRoot = loader2.load();
                    ModifierEmail controller = loader2.getController();
                    controller.setId_email(userInfo.email);
                    centerPane.getChildren().clear();
                    centerPane.getChildren().add(defaultRoot);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "Logout":
                // Pas besoin de se déconnecter de la session ici
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Seconnecter.fxml"));
                try {
                    Parent root = loader1.load();
                    SeConnecter controller = loader1.getController();
                    id_home.getScene().setRoot(root);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "Aide":
                break;
        }
    }

    @FXML
    public void initialize() {
        combo_login.getItems().addAll( "Modifier Email","Logout", "Aide");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DashboardApprenantParDefaut.fxml"));
        try {
            Parent defaultRoot = loader.load();
            centerPane.getChildren().clear();
            centerPane.getChildren().add(defaultRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void PageParDefaut(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DashboardApprenantParDefaut.fxml"));
        try {
            Parent defaultRoot = loader.load();
            centerPane.getChildren().clear();
            centerPane.getChildren().add(defaultRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}