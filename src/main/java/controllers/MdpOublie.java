package controllers;

import entities.EmailSender;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import services.ServiceUser;

import java.io.IOException;
import java.sql.SQLException;


public class MdpOublie {
    @FXML
    private Button btn_verifier_mdp;

    @FXML
    private TextField id_code;

    @FXML
    private TextField id_email;
    @FXML
    private Button id_envoyerEmail;
    public TextField getId_email() {
        return id_email;
    }
    @FXML
    private Text loadingLabel;

    @FXML
    void mdpOblieur(ActionEvent event) {
        EmailSender.sendVerificationCode(getId_email().getText());
        loadingLabel.setVisible(false);
    }

    @FXML
    void verificationMdp(ActionEvent event) {
        if(EmailSender.verificationCode.equals(id_code.getText())){
            User user = new User();
            ServiceUser serviceUser= new ServiceUser();
            try {user=serviceUser.rechercheUserParEmail(getId_email().getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/changerPassword.fxml"));
            try {
                Parent root = loader1.load();
                ChangerPassword controller = loader1.getController();
                ChangerPassword.user=user;
                System.out.println(user+"from oublier");
                id_email.getScene().setRoot(root);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("valide");
        }else
        {
            System.out.println("incalide");
        }
    }

}
