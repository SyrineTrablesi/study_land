package controllers;
import entities.ValidationFormuaire;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Notifications;
import entities.Apprenant;
import entities.EmailSender;
import entities.User;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.ServiceApprenant;
import services.ServiceUser;

import java.io.IOException;
import java.sql.SQLException;
public class InscriptionApprenant {

    User user = new User();
    @FXML
    private Label errorMdpLabel;
    @FXML
    private Label errorMessage;
    @FXML
    private Label errorEmailLabel;
    @FXML
    private Hyperlink seConnecter;
    @FXML
    private TextField id_confirmer;
    @FXML
    private TextField id_email;
    @FXML
    private TextField id_mdp;
    @FXML
    private TextField id_nom;
    @FXML
    private TextField id_prenom;
    public TextField getId_email() {
        return id_email;
    }

    public void setId_email(TextField id_email) {
        this.id_email = id_email;
    }
    ServiceApprenant SP = new ServiceApprenant();
    private Apprenant apprenant = new Apprenant();
    //notification
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

    //Inscrit
    @FXML
    void Ajouter(ActionEvent event) {
        if (id_nom.getText().isEmpty() || id_prenom.getText().isEmpty() || id_email.getText().isEmpty() || id_mdp.getText().isEmpty() || id_confirmer.getText().isEmpty()) {
            errorMessage.setText("Veuillez remplir tous les champs.");
            return;
        }

        Apprenant apprenant = new Apprenant(id_nom.getText(), id_prenom.getText(), id_email.getText(), id_mdp.getText(), id_confirmer.getText());

        if (!apprenant.isEmailValid(id_email.getText())) {
            errorMessage.setText("Adresse e-mail invalide !");
            return;
        }

        if (!ValidationFormuaire.isValidPassword(id_mdp.getText())) {
            errorMessage.setText("Le mot de passe est faible ");
            return;
        }

        if (!id_mdp.getText().equals(apprenant.getConfirmerPassword())) {
            errorMessage.setText("Le mot de passe de confirmation ne correspond pas au mot de passe");
            return;
        }

        User userRech = new User();
        try {
            ServiceUser serviceUser = new ServiceUser();
            userRech = serviceUser.rechercheUserParEmail(id_email.getText());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (userRech != null) {
            showToastMessage("Ce email est deja utiliser ");
            //redecrtion sans mdp:
            rederctionsansMdp(userRech);

        } else {
            showToastMessage("Veuillez patienter pendant l'envoi de l'e-mail...");
            Task<Void> verificationEmailTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    EmailSender.sendVerficationEmail(id_email.getText(), id_nom.getText());
                    return null;
                }
            };

            verificationEmailTask.setOnSucceeded(e -> {
                showToastMessage("E-mail de vérification envoyé avec succès !");
                try {
                    SP.ajouter(apprenant);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                redirectToSeConnecter(apprenant);
                Task<Void> emailTask = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        EmailSender.sendWelcomeEmailWithSignature(apprenant.getEmail(), apprenant.getNom() + " " + apprenant.getPrenom());
                        return null;
                    }
                };

                emailTask.setOnSucceeded(e1 -> {
                });

                emailTask.setOnFailed(e1 -> {
                });

                new Thread(emailTask).start();
            });

            verificationEmailTask.setOnFailed(e -> {
                Throwable exception = verificationEmailTask.getException();
                showToastMessage("Erreur lors de l'envoi de l'e-mail de vérification : " + exception.getMessage());
            });
            new Thread(verificationEmailTask).start();
        }
    }
    private void redirectToSeConnecter(User apprenant) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Seconnecter.fxml"));
        try {
            Parent root = loader.load();
            SeConnecter controller = loader.getController();
            controller.getId_email().setText(apprenant.getEmail());
            controller.getId_mdp().setText(apprenant.getPassword());
            id_nom.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


    //url se connecter
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
    void rederctionsansMdp(User user){
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Seconnecter.fxml"));
        try {
            Parent root = loader1.load();
            SeConnecter controller = loader1.getController();
            controller.getId_email().setText(user.getEmail());
            id_email.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

