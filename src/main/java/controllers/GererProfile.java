package controllers;

import entities.Apprenant;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import security.Session;
import security.UserInfo;
import services.ServiceApprenant;
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
        Apprenant ap1=new Apprenant();
        Apprenant ap =new Apprenant(userInfo.id,userInfo.nom,userInfo.prenom,userInfo.email,userInfo.mdp);
        System.out.println(ap);
       ap1.setNom(id_nom.getText());
        ap1.setPrenom(id_prenom.getText());
        ap1.setEmail(id_email.getText());
        ap1.setPassword(id_password.getText());
        ap1.setId(userInfo.id);
        System.out.println(ap1);
        ap1.setEmail(id_email.getText());
        ServiceApprenant serviceApprenant = new ServiceApprenant();
        try {
            serviceApprenant.modifier(ap1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
