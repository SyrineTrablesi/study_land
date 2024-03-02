package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Home {
    @FXML
    private Button id_popUp_seconnecter;

    @FXML
    private Button id_popUp_sinscrire;

    @FXML
    private TextField id_recherche;

    @FXML
    void onREcherche(ActionEvent event) {

    }

    @FXML
    void popUpSinscrire(ActionEvent event) {
        try {
            // Chargement du fichier FXML de la page "InscriptionApprenat"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InscriptionApprenant.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Inscription Apprenat");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            // Fermeture de la scène de la page "Home"
            ((Stage) id_popUp_sinscrire.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void popUpseConnecter(ActionEvent event) {
        try {
            // Chargement du fichier FXML de la page "SeConnecter"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SeConnecter.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("SeConnecter");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            // Fermeture de la scène de la page "Home"
            ((Stage) id_popUp_seconnecter.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}