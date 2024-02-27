package controllers;

import entities.*;
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
    private Button btn_ajouter;


    @FXML
    public void initialize() {
        id_user.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_user.setCellValueFactory(new PropertyValueFactory<>("nom"));
        pre_user.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email_user.setCellValueFactory(new PropertyValueFactory<>("email"));
        addActionColumn();
        id_mdp.setDisable(true);
        id_mdp.setOpacity(0.5);

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
        id_mdp.setText(EmailSender.getPasswordGenerte());
        try {
            Admin admin = new Admin(id_nom.getText(), id_prenom.getText(), id_email.getText(), id_mdp.getText());

            if (admin == null) {
                errorEmailLabel.setText("Une erreur s'est produite lors de l'ajout du Admin. Veuillez réessayer.");
            } else if (id_nom.getText().isEmpty() || id_prenom.getText().isEmpty() || id_email.getText().isEmpty() || id_mdp.getText().isEmpty() || !ValidationFormuaire.isEmail(id_email.getText())) {
                showAlert2("Erreur", "Veuillez remplir tous les champs correctement.");
            } else {
                // Ajouter l'admin
                serviceAdmin.ajouter(admin);
                EmailSender.sendInfoAdmin(id_email.getText(), admin);
                id_nom.clear();
                id_prenom.clear();
                id_email.clear();


                showAlert("Succès", "L'admin a été ajouté avec succès.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            showAlert("Erreur", "Une erreur s'est produite lors de l'ajout d'un Admin. Veuillez réessayer.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            showAlert("Erreur", "Une erreur s'est produite lors de l'ajout d'un Admin. Veuillez réessayer.");
        }
    }


    // Méthode pour afficher une alerte
    private void showAlert2(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}