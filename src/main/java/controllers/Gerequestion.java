package controllers;

import entities.question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.quesservice;

import java.io.IOException;
import java.sql.SQLException;

public class Gerequestion {
    quesservice sq =new quesservice();

    @FXML
    private TextField ajoutdomain;

    @FXML
    private TextArea ajouteneonce;

    @FXML
    private TextField iddelete;

    @FXML
    private TextField modfdomaine;

    @FXML
    private TextArea modifeneonce;

    @FXML
    private TextField modifid;





    public void btnsupprimer(javafx.event.ActionEvent actionEvent) {
        try{
            sq.supprimer(new question(Integer.parseInt(iddelete.getText())));
        }catch (SQLException e ){
            throw new RuntimeException(e);

        }
    }

    public void btnajouter(javafx.event.ActionEvent actionEvent) {
        try {
            sq.ajouter(new question(ajouteneonce.getText(),ajoutdomain.getText()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnmodifier(javafx.event.ActionEvent actionEvent)
        {try {
            sq.modifier(new question(Integer.parseInt(modifid.getText()),modifeneonce.getText(),modfdomaine.getText()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void affichagequetion(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterQuestion.fxml"));
            Parent root = loader.load();

            // Obtenez le contrôleur après avoir chargé le fichier FXML
            AjouterQuestion affichepre = loader.getController();

            // Créez une nouvelle scène avec le Parent chargé
            Scene scene = new Scene(root);

            // Récupérez la scène actuelle à partir du bouton
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Remplacez la scène actuelle par la nouvelle scène
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
