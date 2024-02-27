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
    private Label erroMessage;
    @FXML
    private TextField id_email;
    @FXML
    private TextField id_nom;
    @FXML
    private TextField id_prenom;
    public void setId_email(TextField id_email) {
        this.id_email = id_email;
    }
    public void setId_nom(TextField id_nom) {
        this.id_nom = id_nom;
    }

    public TextField getId_email() {
        return id_email;
    }

    public TextField getId_nom() {
        return id_nom;
    }

    public TextField getId_prenom() {
        return id_prenom;
    }



    @FXML
    void ModifierUser(ActionEvent event) {
        UserInfo userInfo = Session.getInstance().userInfo;
        String nouveauNom = id_nom.getText();
        String nouveauPrenom = id_prenom.getText();
        String nouvelEmail = id_email.getText();

        if (nouveauNom.isEmpty() || nouveauPrenom.isEmpty() || nouvelEmail.isEmpty()) {
            showAlert("Erreur", "Veuillez remplir tous les champs.");
            return;
        }

        if (!ValidationFormuaire.isEmail(nouvelEmail)) {
            erroMessage.setText("Veuillez saisir une adresse email valide.");
            return;
        }

        if (nouveauNom.equals(userInfo.nom) && nouveauPrenom.equals(userInfo.prenom) && nouvelEmail.equals(userInfo.email)) {
            showAlert2("Information", "Aucune modification n'a été apportée.");
            return;
        }

        try {
            User user1 = new User(userInfo.nom, userInfo.prenom, userInfo.email, userInfo.role, userInfo.id);
            if (!nouveauNom.equals(userInfo.nom)) {
                user1.setNom(nouveauNom);
            }
            if (!nouveauPrenom.equals(userInfo.prenom)) {
                user1.setPrenom(nouveauPrenom);
            }
            if (!nouvelEmail.equals(userInfo.email)) {
                user1.setEmail(nouvelEmail);
            }

            if (user1.getRole().equals("Apprenant")) {
                Apprenant apprenant = new Apprenant(userInfo.nom, userInfo.prenom, userInfo.email, userInfo.role, userInfo.id);
                if (!nouveauNom.equals(userInfo.nom)) {
                    apprenant.setNom(user1.getNom());
                }
                if (!nouveauPrenom.equals(userInfo.prenom)) {
                    apprenant.setPrenom(user1.getPrenom());
                }
                if (!nouvelEmail.equals(userInfo.email)) {
                    apprenant.setEmail(user1.getEmail());
                }
                ServiceApprenant serviceApprenant = new ServiceApprenant();
                serviceApprenant.modifier(apprenant);
            } else if (user1.getRole().equals("Formateur")) {
                Formateur formateur = new Formateur(userInfo.nom, userInfo.prenom, userInfo.email, userInfo.role, userInfo.id);
                if (!nouveauNom.equals(userInfo.nom)) {
                    formateur.setNom(user1.getNom());
                }
                if (!nouveauPrenom.equals(userInfo.prenom)) {
                    formateur.setPrenom(user1.getPrenom());
                }
                if (!nouvelEmail.equals(userInfo.email)) {
                    formateur.setEmail(user1.getEmail());
                }
                ServiceFormateur serviceFormateur = new ServiceFormateur();
                serviceFormateur.modifier(formateur);

            } else {
                Admin admin = new Admin(userInfo.nom, userInfo.prenom, userInfo.email, userInfo.role,userInfo.id);
                if (!nouveauNom.equals(userInfo.nom)) {
                    admin.setNom(user1.getNom());
                }
                if (!nouveauPrenom.equals(userInfo.prenom)) {
                    admin.setPrenom(user1.getPrenom());
                }
                if (!nouvelEmail.equals(userInfo.email)) {
                    admin.setEmail(user1.getEmail());
                }
                ServiceAdmin serviceAdmin = new ServiceAdmin();
                serviceAdmin.modifier(admin);

            }
            showAlert2("Succès", "Les informations de l'utilisateur ont été modifiées avec succès.");
        } catch (SQLException e) {
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
