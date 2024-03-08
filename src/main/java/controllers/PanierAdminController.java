package controllers;

import entities.Achat;
import entities.Favoris;
import entities.Inscrit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import services.ServiceAchat;
import services.ServiceFavoris;
import services.ServiceInscrit;

import java.sql.SQLException;
import java.util.List;

public class PanierAdminController {
    @FXML
    private ListView<Achat> ListeA;

    @FXML
    private ListView<Favoris> ListeF;

    @FXML
    private ListView<Inscrit> ListeI;

    @FXML
    private TextField tfTitre;

    @FXML
    private TextField tfTitreA;

    @FXML
    private TextField tfTitreI;
    @FXML
    void Abtn(ActionEvent event) {
        ServiceAchat serviceAchat = new ServiceAchat();
        try {
            List<Achat> listAchat = serviceAchat.afficher();

            // Afficher les données dans le ListView
            ObservableList<Achat> achats = FXCollections.observableArrayList(listAchat);
            ListeA.setItems(achats);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database Error");
            alert.setContentText("An error occurred while fetching data from the database.");
            alert.showAndWait();
        }

    }

    @FXML
    void Fbtn(ActionEvent event) {
        ServiceFavoris serviceFavoris = new ServiceFavoris();
        try {
            List<Favoris> listFavoris = serviceFavoris.afficher();

            // Afficher les données dans le ListView
            ObservableList<Favoris> favorises = FXCollections.observableArrayList(listFavoris);
            ListeF.setItems(favorises);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database Error");
            alert.setContentText("An error occurred while fetching data from the database.");
            alert.showAndWait();
        }


    }

    @FXML
    void Ibtn(ActionEvent event) {
        ServiceInscrit serviceInscrit = new ServiceInscrit();
        try {
            List<Inscrit> listInscrit = serviceInscrit.afficher();

            // Afficher les données dans le ListView
            ObservableList<Inscrit> inscrits = FXCollections.observableArrayList(listInscrit);
            ListeI.setItems(inscrits);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database Error");
            alert.setContentText("An error occurred while fetching data from the database.");
            alert.showAndWait();
        }


    }



    @FXML
    public void rechTitre(ActionEvent actionEvent) {
        ServiceFavoris serviceFavoris = new ServiceFavoris();
        // Récupérer le titre saisi dans le champ de recherche
        String titre = tfTitre.getText();

        // Vérifier si le champ de recherche n'est pas vide
        if (!titre.isEmpty()) {
            try {
                // Appeler le service pour effectuer la recherche par titre
                List<Favoris> results = serviceFavoris.rechercherParTitre(titre);

                // Vérifier si des résultats ont été trouvés
                if (!results.isEmpty()) {
                    // Afficher les résultats dans le ListView
                    ObservableList<Favoris> resultList = FXCollections.observableArrayList(results);
                    ListeF.setItems(resultList);
                } else {
                    // Afficher un message si aucun résultat n'a été trouvé
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Aucun résultat");
                    alert.setHeaderText(null);
                    alert.setContentText("Aucun favori trouvé avec le titre " + titre);
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                // Gérer les erreurs SQL
                e.printStackTrace(); // Affichez l'erreur dans la console pour le débogage
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur s'est produite lors de la recherche.");
                alert.showAndWait();
            }
        } else {
            // Afficher un message si le champ de recherche est vide
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champ de recherche vide");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un titre à rechercher.");
            alert.showAndWait();
        }
    }
    @FXML
    void rechTitreA(ActionEvent event) {
        ServiceAchat serviceAchat = new ServiceAchat();
        // Récupérer le titre saisi dans le champ de recherche
        String titre = tfTitreA.getText();

        // Vérifier si le champ de recherche n'est pas vide
        if (!titre.isEmpty()) {
            try {
                // Appeler le service pour effectuer la recherche par titre
                List<Achat> results = serviceAchat.rechercherParTitre(titre);

                // Vérifier si des résultats ont été trouvés
                if (!results.isEmpty()) {
                    // Afficher les résultats dans le ListView
                    ObservableList<Achat> resultList = FXCollections.observableArrayList(results);
                    ListeA.setItems(resultList);
                } else {
                    // Afficher un message si aucun résultat n'a été trouvé
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Aucun résultat");
                    alert.setHeaderText(null);
                    alert.setContentText("Aucun favori trouvé avec le titre " + titre);
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                // Gérer les erreurs SQL
                e.printStackTrace(); // Affichez l'erreur dans la console pour le débogage
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur s'est produite lors de la recherche.");
                alert.showAndWait();
            }
        } else {
            // Afficher un message si le champ de recherche est vide
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champ de recherche vide");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un titre à rechercher.");
            alert.showAndWait();
        }

    }

    @FXML
    void rechTitreI(ActionEvent event) {
        ServiceInscrit serviceInscrit = new ServiceInscrit();
        // Récupérer le titre saisi dans le champ de recherche
        String titre = tfTitreI.getText();

        // Vérifier si le champ de recherche n'est pas vide
        if (!titre.isEmpty()) {
            try {
                // Appeler le service pour effectuer la recherche par titre
                List<Inscrit> results = serviceInscrit.rechercherParTitre(titre);

                // Vérifier si des résultats ont été trouvés
                if (!results.isEmpty()) {
                    // Afficher les résultats dans le ListView
                    ObservableList<Inscrit> resultList = FXCollections.observableArrayList(results);
                    ListeI.setItems(resultList);
                } else {
                    // Afficher un message si aucun résultat n'a été trouvé
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Aucun résultat");
                    alert.setHeaderText(null);
                    alert.setContentText("Aucun favori trouvé avec le titre " + titre);
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                // Gérer les erreurs SQL
                e.printStackTrace(); // Affichez l'erreur dans la console pour le débogage
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur s'est produite lors de la recherche.");
                alert.showAndWait();
            }
        } else {
            // Afficher un message si le champ de recherche est vide
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champ de recherche vide");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un titre à rechercher.");
            alert.showAndWait();
        }

    }

}

