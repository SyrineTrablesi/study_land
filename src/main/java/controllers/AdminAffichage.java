package controllers;

import entities.Admin;
import entities.Apprenant;
import entities.Formateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceAdmin;
import services.ServiceApprenant;

import java.sql.SQLException;
import java.util.List;

public class AdminAffichage {


    @FXML
    private TableColumn<Admin, String> email_user;
    @FXML
    private TableColumn<Admin,Integer> id_user;
    @FXML
    private TableColumn<Admin, String> mdp_user;
    @FXML
    private TableColumn<Admin, String> nom_user;
    @FXML
    private TableColumn<Admin, String> pre_user;
    @FXML
    private TableColumn<Admin, Void> supprimer_user;
    @FXML
    private TableView<Admin> tab_Admin;

    @FXML
    private Label errorEmailLabel;
    @FXML
    private Label errorEmailLabel1;
    @FXML
    private TextField id_email;
    @FXML
    private TextField id_mdp;
    @FXML
    private TextField id_nom;
    @FXML
    private TextField id_prenom;
    private ServiceAdmin serviceAdmin;
    public AdminAffichage() {
        serviceAdmin = new ServiceAdmin();
    }

    @FXML
    public void initialize() {
        id_user.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_user.setCellValueFactory(new PropertyValueFactory<>("nom"));
        pre_user.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email_user.setCellValueFactory(new PropertyValueFactory<>("email"));
        mdp_user.setCellValueFactory(new PropertyValueFactory<>("password"));
        addActionColumn();
        initTable();
    }

    private void addActionColumn() {
        supprimer_user.setCellFactory(param -> new TableCell<>() {
            private final Button button = new Button("Supprimer");

            {
                button.setOnAction(event -> {
                    Admin admin = getTableView().getItems().get(getIndex());
                    try {
                        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmationAlert.setTitle("Confirmation de suppression");
                        confirmationAlert.setHeaderText("Supprimer l'admin ?");
                        confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer l'admin " + admin.getNom() + " ?");

                        confirmationAlert.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.OK) {
                                try {
                                    serviceAdmin.supprimer(admin);
                                    System.out.println("Supprimer l'admin : " + admin.getNom());
                                    tab_Admin.getItems().remove(admin);
                                    showAlert("Suppression réussie", "L'admin a été supprimé avec succès.");
                                } catch (SQLException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        });
                    } finally {

                    }
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void initTable() {

        try {
            List<Admin> admins =serviceAdmin .afficher();
            ObservableList<Admin> observableList = FXCollections.observableArrayList(admins);
            tab_Admin.setItems(observableList);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void Ajouter(ActionEvent event) {
        try {
            // Créer un nouvel objet Formateur avec le  infos de la crd
            Admin admin = new Admin(id_nom.getText(), id_prenom.getText(), id_email.getText(), id_mdp.getText());
            // Ajouter le formateur
            try {
                if (admin==null){
                    showAlert2("Erreur", "Une erreur s'est produite lors de l'ajout du Admin. Veuillez réessayer.");
                }
                 if (id_nom.getText().isEmpty() || id_prenom.getText().isEmpty() || id_email.getText().isEmpty() || id_mdp.getText().isEmpty()) {
                     showAlert2("Erreuur","Veuillez remplir tous les champs.");
                 }else {
                     serviceAdmin.ajouter(admin);
                     id_nom.clear();
                     id_prenom.clear();
                     id_email.clear();
                     id_mdp.clear();

                     showAlert2("Succès", " l'admin a été ajouté avec succès.");
                 }} catch (SQLException e) {
                System.out.println(e.getMessage());
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            showAlert("Erreur", "Une erreur s'est produite lors de l'ajout d'un Admin. Veuillez réessayer.");
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