package controllers;

import entities.EmailSender;
import entities.User;
import entities.ValidationFormuaire;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;
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
    private Hyperlink seConnecter;

    private void showToastMessage(String message) {
        ImageView imageView=new  ImageView("src/attendre.png");
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        Notifications.create()
                .title("Inscription StudyLand")
                .text(message)
                .graphic(imageView)
                .position(Pos.CENTER)
                .show();
    }
    @FXML
    void mdpOblieur(ActionEvent event) {
        if(ValidationFormuaire.isEmail(id_email.getText())){
            ServiceUser serviceUser=new ServiceUser();
            try {
                 User user= serviceUser.rechercheUserParEmail(id_email.getText());
                 if(user!= null){
                     showToastMessage("Veuillez patienter pendant l'envoi de l'e-mail...");
                     EmailSender.sendVerificationCode(getId_email().getText());
                     showToastMessage("E-mail de vérification envoyé avec succès !");
                 }
                 else {
                     showToastMessage("pas d'inscrit avec ce email  !");
                 }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            showToastMessage("Enter un email valide  !");
        }


    }

    @FXML
    void verificationMdp(ActionEvent event) {
        if(EmailSender.verificationCode.equals(id_code.getText())){
            User user =new User();
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
        }else
        {
            showToastMessage("Code invalide   !");
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Seconnecter.fxml"));
            try {
                Parent root = loader1.load();
                SeConnecter controller = loader1.getController();
                id_email.getScene().setRoot(root);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    @FXML
    void seConnecter(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Seconnecter.fxml"));
        try {
            Parent root = loader1.load();
            SeConnecter controller = loader1.getController();
            id_email.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
