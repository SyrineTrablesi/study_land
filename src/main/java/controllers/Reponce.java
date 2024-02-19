package controllers;

import entities.question;
import entities.response;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.Reponseservice;
import entities.response.status;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Reponce {
    Reponseservice reponseservice =new Reponseservice();

    @FXML
    private TextField idques;

    @FXML
    private TableColumn<response, Integer> idquestion;

    @FXML
    private TableColumn<response, Integer> idreponse;

    @FXML
    private TextField idtep;

    @FXML
    private CheckBox non;

    @FXML
    private CheckBox oui;

    @FXML
    private TextArea rep;

    @FXML
    private TableColumn<response, String> reponse;

    @FXML
    private TableColumn<response, String> status;


    @FXML
    private TableView<response> tabreponse;

    @FXML
    void btnajouter(ActionEvent event) {

            try {
                response updatedResponse = new response();
                updatedResponse.setIdReponse(Integer.parseInt(idtep.getText()));
                updatedResponse.setContenu(rep.getText());
                updatedResponse.setIdQuestion(Integer.parseInt(idques.getText()));
                updatedResponse.setStatus(response.status.valueOf(oui.isSelected() ? "ONE" : "ZERO"));

                // Modifier la response dans la base de données
                reponseservice.modifier(updatedResponse);

                initTable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



    @FXML
    void btnmodifier(ActionEvent event) {


            try {
                response updatedResponse = new response();
                updatedResponse.setContenu(rep.getText());
                updatedResponse.setIdQuestion(Integer.parseInt(idques.getText()));
                updatedResponse.setStatus(response.status.valueOf(oui.isSelected() ? "ONE" : "ZERO"));

                updatedResponse.setIdReponse(Integer.parseInt(idtep.getText()));


                // Modifier la response dans la base de données
                reponseservice.modifier(updatedResponse);

                initTable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    @FXML
    void btnsupprimer(ActionEvent event) {
        try{
            reponseservice.supprimer(new response(Integer.parseInt(idtep.getText())));
            initTable();

        }catch (SQLException e ){
            throw new RuntimeException(e);

        }
    }
    @FXML
    public void initialize() {
        idquestion.setCellValueFactory(new PropertyValueFactory<>("idQuestion"));
        idreponse.setCellValueFactory(new PropertyValueFactory<>("idReponse"));
        reponse.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));


        // Appelez la méthode pour charger les données dans le tableau
        initTable();
    }

    private void initTable() {
        try {
            // Appelez la méthode afficher de votre service pour récupérer les questions
            List<response> responses = reponseservice.afficher();

            // Convertissez la liste en une liste observable pour le TableView
            ObservableList<response> observableList = FXCollections.observableArrayList(responses);

            // Associez la liste observable au TableView
            tabreponse.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez l'exception de manière appropriée
        }
    }



    public void gererepense(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gerereponse.fxml"));
            Parent root = loader.load();

            // Obtenez le contrôleur après avoir chargé le fichier FXML
            Gerereponse affichepre = loader.getController();

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

    public void btnafficher(ActionEvent actionEvent) {

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

    public void modifierrep(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifierevaluation.fxml"));
            Parent root = loader.load();

            // Obtenez le contrôleur après avoir chargé le fichier FXML
            Modifierevaluation affichepre = loader.getController();

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
