package controllers;

import entities.Apprenant;
import entities.Formateur;
import entities.User;
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
        UserInfo userInfo = Session.userInfo;
        User user1 = new User(userInfo.id, userInfo.nom, userInfo.prenom, userInfo.email, userInfo.mdp, userInfo.role);
        user1.setNom(id_nom.getText());
        user1.setPrenom(id_prenom.getText());
        user1.setEmail(id_email.getText());
        user1.setPassword(id_password.getText());

        if(user1.getRole().equals("Apprenant")) {
            try {
                Apprenant apprenant = new Apprenant(userInfo.id, userInfo.nom, userInfo.prenom, userInfo.email, userInfo.mdp);
                apprenant.setNom(user1.getNom());
                apprenant.setPrenom(user1.getPrenom());
                apprenant.setEmail(user1.getEmail());
                apprenant.setPassword(user1.getPassword());

                ServiceApprenant serviceApprenant = new ServiceApprenant();
                serviceApprenant.modifier(apprenant);
                showAlert2("Succès", "Les informations de l'utilisateur ont été modifiées avec succès.");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
            }
        } else if(user1.getRole().equals("Formateur")) {
            try {
                Formateur formateur = new Formateur(userInfo.id, userInfo.nom, userInfo.prenom, userInfo.email, userInfo.mdp);
                formateur.setNom(user1.getNom());
                formateur.setPrenom(user1.getPrenom());
                formateur.setEmail(user1.getEmail());
                formateur.setPassword(user1.getPassword());

                ServiceFormateur serviceFormateur = new ServiceFormateur();
                serviceFormateur.modifier(formateur);
                showAlert2("Succès", "Les informations de l'utilisateur ont été modifiées avec succès.");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
            }
        }
    }

    private void showAlert2(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
