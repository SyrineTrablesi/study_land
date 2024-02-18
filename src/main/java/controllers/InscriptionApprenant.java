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
import services.ServiceApprenant;

import java.io.IOException;
import java.sql.SQLException;

public class InscriptionApprenant {
    User user = new User();




    @FXML
    private Label errorMdpLabel;

    @FXML
    private Label errorEmailLabel;

    @FXML
    private Button btn_ajouter;

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

    public TextField getId_mdp() {
        return id_mdp;
    }

    public TextField getId_nom() {
        return id_nom;
    }

    public TextField getId_prenom() {
        return id_prenom;
    }

    public void setId_confirmer(TextField id_confirmer) {
        this.id_confirmer = id_confirmer;
    }

    public void setId_email(TextField id_email) {
        this.id_email = id_email;
    }

    public void setId_mdp(TextField id_mdp) {
        this.id_mdp = id_mdp;
    }

    public void setId_nom(TextField id_nom) {
        this.id_nom = id_nom;
    }

    public void setId_prenom(TextField id_prenom) {
        this.id_prenom = id_prenom;
    }

    //Sercice Apprenant
    ServiceApprenant SP= new ServiceApprenant();
    private Apprenant apprenant = new Apprenant();

    @FXML
    void Ajouter(ActionEvent event) {
        try {
            Apprenant apprenant=new Apprenant(id_nom.getText(),id_prenom.getText(),id_email.getText(),id_mdp.getText(),id_confirmer.getText());
            int count = SP.existeApprenant(apprenant);
            if (!(apprenant.isEmailValid(id_email.getText()))) {
                errorEmailLabel.setText("Adresse e-mail invalide !");
            }
            if(!(id_confirmer.getText().equals(apprenant.getPassword()))){
                errorMdpLabel.setText("Le mot de passe de confirmation ne correspond pas au mot de passe");

            }else{
                SP.ajouter(apprenant);
              User user =new User(id_nom.getText(), id_prenom.getText(), id_email.getText(),id_mdp.getText(),"Apprenat",id_confirmer.getText());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Seconnecter.fxml"));
                try {
                    Parent root = loader.load();
                    SeConnecter controller = loader.getController();
                    controller.getId_email().setText(apprenant.getEmail());
                    controller.getId_mdp().setText(apprenant.getPassword());

                    id_nom.getScene().setRoot(root);

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void PageHome(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DashboardAdmin.fxml"));
        try {
            Parent root = loader.load();
            DashboardAdmin affichage = loader.getController();
            id_nom.getScene().setRoot(root);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

        public void initData(Apprenant apprenant) {
            this.apprenant = apprenant;
            if (apprenant != null) {
                id_nom.setText(apprenant.getNom());
                id_prenom.setText(apprenant.getPrenom());
                id_email.setText(apprenant.getEmail());
            }
    }
    public User getUser() {
        return new User(id_nom.getText(), id_prenom.getText(), id_email.getText(),id_mdp.getText(),"Apprenat",id_confirmer.getText());
    }

}
