package controllers;

import entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import security.Session;
import security.UserInfo;
import services.ServiceAdmin;
import services.ServiceApprenant;
import services.ServiceFormateur;
import services.ServiceUser;

import java.io.IOException;
import java.sql.SQLException;

public class GererProfile {
    @FXML
    private Button btn_Modifier;

    @FXML
    private Label erroMessage;

    @FXML
    private TextField id_email;

    @FXML
    private TextField id_nom;

    @FXML
    private Label id_nom_gerer;

    @FXML
    private TextField id_password;

    @FXML
    private TextField id_prenom;

    @FXML
    private Label id_prenom_gerer;

    //get et set

    public void setErroMessage(Label erroMessage) {
        this.erroMessage = erroMessage;
    }

    public void setId_email(TextField id_email) {
        this.id_email = id_email;
    }

    public void setId_nom(TextField id_nom) {
        this.id_nom = id_nom;
    }

    public void setId_nom_gerer(Label id_nom_gerer) {
        this.id_nom_gerer = id_nom_gerer;
    }

    public void setId_password(TextField id_password) {
        this.id_password = id_password;
    }

    public void setId_prenom(TextField id_prenom) {
        this.id_prenom = id_prenom;
    }

    public void setId_prenom_gerer(Label id_prenom_gerer) {
        this.id_prenom_gerer = id_prenom_gerer;
    }
//get

    public Label getErroMessage() {
        return erroMessage;
    }

    public TextField getId_email() {
        return id_email;
    }

    public TextField getId_nom() {
        return id_nom;
    }

    public Label getId_nom_gerer() {
        return id_nom_gerer;
    }

    public TextField getId_password() {
        return id_password;
    }

    public TextField getId_prenom() {
        return id_prenom;
    }

    public Label getId_prenom_gerer() {
        return id_prenom_gerer;
    }


    @FXML
    void ModifierUser(ActionEvent event) {
        UserInfo userInfo = Session.getInstance().userInfo;
        String nouveauNom = id_nom.getText().trim();
        String nouveauPrenom = id_prenom.getText().trim();
        String nouvelEmail = id_email.getText().trim();

        if (nouveauNom.isEmpty() || nouveauPrenom.isEmpty() || nouvelEmail.isEmpty()) {
            showAlert("Erreur", "Veuillez remplir tous les champs.");
            return; // Sortir de la méthode si un champ est vide
        }

        if (ValidationFormuaire.isEmail(nouvelEmail)) {
            showAlert("Erreur", "Veuillez saisir une adresse email valide.");
            return; // Sortir de la méthode si l'email est invalide
        }

        User user1 = new User(userInfo.nom, userInfo.prenom, userInfo.email, userInfo.role, userInfo.id);
        user1.setNom(nouveauNom);
        user1.setPrenom(nouveauPrenom);
        user1.setEmail(nouvelEmail);

        try {
            if (user1.getRole().equals("Apprenant")) {
                Apprenant apprenant = new Apprenant(userInfo.nom, userInfo.prenom, userInfo.email, userInfo.role, userInfo.id);
                apprenant.setNom(user1.getNom());
                apprenant.setPrenom(user1.getPrenom());
                apprenant.setEmail(user1.getEmail());
                ServiceApprenant serviceApprenant = new ServiceApprenant();
                serviceApprenant.modifier(apprenant);
            } else if (user1.getRole().equals("Formateur")) {
                Formateur formateur = new Formateur(userInfo.nom, userInfo.prenom, userInfo.email, userInfo.role, userInfo.id);
                formateur.setNom(user1.getNom());
                formateur.setPrenom(user1.getPrenom());
                formateur.setEmail(user1.getEmail());
                ServiceFormateur serviceFormateur = new ServiceFormateur();
                serviceFormateur.modifier(formateur);
            } else {
                Admin admin = new Admin(userInfo.nom, userInfo.prenom, userInfo.email, userInfo.id);
                admin.setNom(user1.getNom());
                admin.setPrenom(user1.getPrenom());
                admin.setEmail(user1.getEmail());
                admin.setPassword(user1.getPassword());
                ServiceAdmin serviceAdmin = new ServiceAdmin();
                serviceAdmin.modifier(admin);
            }
            showAlert2("Succès", "Les informations de l'utilisateur ont été modifiées avec succès.");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
        }
    }
    // Méthode pour afficher une alerte
    private void showAlert(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    private void showAlert2(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
