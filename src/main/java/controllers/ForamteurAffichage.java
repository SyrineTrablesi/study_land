package controllers;

import entities.Apprenant;
import entities.EmailSender;
import entities.User;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import entities.Formateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;
import services.ServiceFormateur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ForamteurAffichage {
    @FXML
    private Button btn_ajouter;

    @FXML
    private Label errorEmailLabel;

    @FXML
    private TextField id_email;

    @FXML
    private TextField id_mdp;

    @FXML
    private TextField id_nom;

    @FXML
    private TextField id_prenom;


    @FXML
    private TableColumn<Formateur, String> email_user;

    @FXML
    private TableColumn<Formateur, String> id_user;

    @FXML
    private TableColumn<Formateur, String> mdp_user;

    @FXML
    private TableColumn<Formateur, String> nom_user;

    @FXML
    private TableColumn<Formateur, String> pre_user;

    @FXML
    private TableColumn<Formateur, Void> supprimer_user;

    @FXML
    private TableView<Formateur> tab_formateur;

    private final ServiceFormateur serviceFormateur = new ServiceFormateur();

    @FXML
    public void initialize() {
        id_user.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_user.setCellValueFactory(new PropertyValueFactory<>("nom"));
        pre_user.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email_user.setCellValueFactory(new PropertyValueFactory<>("email"));
        addActionColumn();
        initTable();
    }

    private void addActionColumn() {
        supprimer_user.setCellFactory(param -> new TableCell<>() {
            private final Button button = new Button("Supprimer");

            {
                button.setOnAction(event -> {
                    Formateur formateur = getTableView().getItems().get(getIndex());
                    Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
                    confirmationAlert.setTitle("Confirmation de suppression");
                    confirmationAlert.setHeaderText("Supprimer formateur ?");
                    confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer le formateur " + formateur.getNom() + " ?");
                    confirmationAlert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            try {
                                serviceFormateur.supprimer(formateur);
                                System.out.println("Supprimer le formateur : " + formateur.getNom());
                                tab_formateur.getItems().remove(formateur);
                                showAlert("Suppression réussie", "Le formateur a été supprimé avec succès.");
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });
    }

    // Méthode pour afficher une alerte
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void initTable() {
        try {
            List<Formateur> formateurs = serviceFormateur.afficher();
            ObservableList<Formateur> observableList = FXCollections.observableArrayList(formateurs);
            tab_formateur.setItems(observableList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void Ajouter(ActionEvent event) {
        try {
            Formateur formateur = new Formateur(id_nom.getText(), id_prenom.getText(), id_email.getText(), id_mdp.getText());
             try {
                    if (id_nom.getText().isEmpty() || id_prenom.getText().isEmpty() || id_email.getText().isEmpty() || id_mdp.getText().isEmpty()) {
                        showAlert2("Erreuur","Veuillez remplir tous les champs.");}
                    else {
                    serviceFormateur.ajouter(formateur);
                    EmailSender.sendInfoFormateur(formateur.getEmail(),formateur);
                    id_nom.clear();
                    id_prenom.clear();
                    id_email.clear();
                    id_mdp.clear();
                    showAlert2("Succès", "Le formateur a été ajouté avec succès.");
                } }catch (SQLException e) {
                    System.out.println(e.getMessage());
                    showAlert2("Erreur", "Une erreur s'est produite lors de l'ajout du formateur. Veuillez réessayer.");
                }

        } catch (Exception e) {
            // Gérer toute autre exception de manière appropriée
            System.out.println(e.getMessage());
            showAlert("Erreur", "Une erreur s'est produite lors de l'ajout du formateur. Veuillez réessayer.");
        }
    }

    // Méthode pour afficher une alerte
    private void showAlert2(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}