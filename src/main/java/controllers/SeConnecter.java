package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import security.Session;
import security.UserInfo;

import java.io.IOException;

public class SeConnecter {
    @FXML
    private Button btn_connecter;

    @FXML
    private Label errorEmailLabel;
    @FXML
    private Label errorMdpLabel;

    @FXML
    private TextField id_email;

    @FXML
    private TextField id_mdp;
    @FXML
    private Button btn_inscrit;
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
    void seConnecter(ActionEvent event) {
        Session session = new Session();
        session.login(id_email.getText(), id_mdp.getText());
        UserInfo userinfo = Session.userInfo;
      /*  if (userinfo.email == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez vérifier vos informations.");
            alert.showAndWait();
            return;
        }*/

        if (userinfo.role.equals("Apprenant")|| userinfo.role.equals("Formateur")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProfileUser.fxml"));
            try {
                Parent root = loader.load();
                ProfileApprenant controller = loader.getController();
                controller.getId_nom().setText(userinfo.nom.toString());
                id_email.getScene().setRoot(root);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else if (userinfo.role.equals("Admin")) {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/DashboardAdmin.fxml"));
            try {
                Parent root = loader1.load();
                DashboardAdmin controller = loader1.getController();
                id_email.getScene().setRoot(root);
            } catch (IOException e) {
                System.out.println(e.getMessage());
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

