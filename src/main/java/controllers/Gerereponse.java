package controllers;

import entities.question;
import entities.response;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Reponseservice;

import java.io.IOException;
import java.sql.SQLException;

public class Gerereponse {
    Reponseservice reponseservice =new Reponseservice();


    @FXML
    private TextField iddelete;

    @FXML
    private TextField idques;

    @FXML
    private TextField idques1;

    @FXML
    private TextField idtep1;

    @FXML
    private TextField idtep12;

    @FXML
    private TextField idtep121;

    @FXML
    private CheckBox non;

    @FXML
    private CheckBox non1;

    @FXML
    private CheckBox oui;

    @FXML
    private CheckBox oui1;
    public void btnsupprimer(ActionEvent actionEvent) {
        try{
            reponseservice.supprimer(new response(Integer.parseInt(iddelete.getText())));

        }catch (SQLException e ){
            throw new RuntimeException(e);

        }
    }

    public void btnajouter(ActionEvent actionEvent) {
        try {
            response updatedResponse = new response();
            updatedResponse.setContenu((idtep12.getText()));
            updatedResponse.setIdQuestion(Integer.parseInt(idques.getText()));
            updatedResponse.setStatus(response.status.valueOf(oui.isSelected() ? "ONE" : "ZERO"));

            // Modifier la response dans la base de données
            reponseservice.ajouter(updatedResponse);
            System.out.println(updatedResponse);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnmodifier(ActionEvent actionEvent) {

        try {
            response updatedResponse = new response();
            updatedResponse.setContenu(idtep121.getText());
            updatedResponse.setIdQuestion(Integer.parseInt(idques1.getText()));
            boolean isSelected = oui1.isSelected();
            String statusString = isSelected ? "ONE" : "ZERO";
            System.out.println("isSelected: " + isSelected + ", statusString: " + statusString);

            updatedResponse.setStatus(response.status.valueOf(statusString));
            updatedResponse.setIdReponse(Integer.parseInt(idtep1.getText()));


            // Modifier la response dans la base de données
            reponseservice.modifier(updatedResponse);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void afficherrepense(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reponce.fxml"));
            Parent root = loader.load();

            // Obtenez le contrôleur après avoir chargé le fichier FXML
            Reponce affichepre = loader.getController();

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
