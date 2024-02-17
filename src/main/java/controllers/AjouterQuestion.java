package controllers;

import entities.question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.quesservice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AjouterQuestion {
    @FXML
    quesservice sq =new quesservice();
    @FXML
    private TableView<question> tab;

    @FXML
    private TextField tfdomaine;

    @FXML
    private TextField tfenonce;
    @FXML
    private TextField tfidquestion;
    @FXML
    private TableColumn<question, Integer> columidq;

    @FXML
    private TableColumn<question, String> columndom;

    @FXML
    private TableColumn<question,String> columnenonce;





    public void btnmodifier(javafx.event.ActionEvent actionEvent) throws SQLException {

       try {
            sq.modifier(new question(Integer.parseInt(tfidquestion.getText()),tfenonce.getText(),tfdomaine.getText()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnsupprimer(ActionEvent actionEvent) {
        try{
            sq.supprimer(new question(Integer.parseInt(tfidquestion.getText())));
        }catch (SQLException e ){
            throw new RuntimeException(e);

        }
    }

    public void btnajouter(ActionEvent actionEvent) throws SQLException {
        try {
            sq.ajouter(new question(tfenonce.getText(),tfdomaine.getText()));
            initTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnafficher(ActionEvent actionEvent) {
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

    @FXML
    public void initialize() {
        // Initialisez les colonnes ici
        columidq.setCellValueFactory(new PropertyValueFactory<>("idQuestion"));
        columndom.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        columnenonce.setCellValueFactory(new PropertyValueFactory<>("enonce"));

        // Appelez la méthode pour charger les données dans le tableau
        initTable();
    }

    private void initTable() {
        try {
            // Appelez la méthode afficher de votre service pour récupérer les questions
            List<question> questions = sq.afficher();

            // Convertissez la liste en une liste observable pour le TableView
            ObservableList<question> observableList = FXCollections.observableArrayList(questions);

            // Associez la liste observable au TableView
            tab.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez l'exception de manière appropriée
        }
    }
}

