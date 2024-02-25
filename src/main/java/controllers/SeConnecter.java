package controllers;

import entities.EmailSender;
import entities.ValidationFormuaire;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import security.Session;
import security.UserInfo;

import java.io.IOException;

public class SeConnecter {
    @FXML
    private Label errorMdpLabel;

    @FXML
    private TextField id_email;

    @FXML
    private TextField id_mdp;
    @FXML
    public TextField getId_email() {
        return id_email;
    }

    public TextField getId_mdp() {
        return id_mdp;
    }
    public void setId_email(TextField id_email) {
        this.id_email = id_email;
    }

    public void setId_mdp(TextField id_mdp) {
        this.id_mdp = id_mdp;
    }
    @FXML
    private Hyperlink id_mdp_oublier;

    @FXML
    void mdpOublier(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/mdpOublie.fxml"));
        try {
            Parent root = loader1.load();
            MdpOublie controller = loader1.getController();
            id_email.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
    @FXML
    void seConnecter(ActionEvent event) {
        String email = id_email.getText();
        String mdp = id_mdp.getText();
        Session session = Session.getInstance();
        session.login(id_email.getText(), id_mdp.getText());
        UserInfo userinfo = Session.getInstance().userInfo;

        if (email.isEmpty() || mdp.isEmpty()) {
            errorMdpLabel.setText("Veuillez remplir tous les champs.");
        } else if (!ValidationFormuaire.isEmail(email) && !mdp.isEmpty()) {
            errorMdpLabel.setText("Email invalide !");
        } else if (userinfo == null) {
            errorMdpLabel.setText("Veuillez vérifier vos informations.");
            return;
        }
        if (userinfo.role.equals("Apprenant")|| userinfo.role.equals("Formateur")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProfileUser.fxml"));
            try {
                Parent root = loader.load();
                ProfileApprenant controller = loader.getController();
                controller.getId_nom().setText(userinfo.nom.toString());
                id_email.getScene().setRoot(root);
            } catch (IOException e) {
            }
        } else if (userinfo.role.equals("Admin")) {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/DashboardAdmin.fxml"));
            try {
                Parent root = loader1.load();
                DashboardAdmin controller = loader1.getController();
                id_email.getScene().setRoot(root);
            } catch (IOException e) {
            }
        }
    }

    @FXML
    void pageInscription(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/InscriptionApprenant.fxml"));
        try {
            Parent root = loader1.load();
            InscriptionApprenant controller = loader1.getController();
            id_email.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    }



