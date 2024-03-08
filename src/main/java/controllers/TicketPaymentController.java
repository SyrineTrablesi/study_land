package controllers;

import com.stripe.Stripe;
import entities.Achat;
import entities.Favoris;
import entities.Formation;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceAchat;
import services.ServiceFormation;
import services.ServiceFavoris;
import services.ServiceFormation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TicketPaymentController implements Initializable {

    private final ServiceAchat serviceAchat = new ServiceAchat();
    private Achat achat;
    private Formation formation;

    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField exprMonthField;
    @FXML
    private TextField exprYearField;
    @FXML
    private TextField cvvField;
    @FXML
    private Button purchaseBtn;

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // this.achat = new Achat();
        Stripe.apiKey = "sk_test_51OrKaRDOYVI5uEqTE8p6z8idDW3U03DPu84cAFKM3xJ7TadBHNE55nyAe1xrJBORV8saCYHBO1g57iPtJyk3f83c00t4i6bYvJ";
    }

    @FXML
    private void handlePayment(ActionEvent event) {
        String cardNumber = cardNumberField.getText();
        String expMonth = exprMonthField.getText();
        String expYear = exprYearField.getText();
        String cvv = cvvField.getText();

        if (cardNumber.isEmpty() || expMonth.isEmpty() || expYear.isEmpty() || cvv.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        User user = new User();
        user.setId(2);

        try {
            // Utilisation de la formation récupérée
            Achat achat = new Achat(user.getId(), formation.getIdFormation());
            achat.setDescription(formation.getDescription());
            achat.setNiveau(formation.getNiveau());
            achat.setNomCategorie(formation.getNomCategorie());
            achat.setTitre(formation.getTitre());
            achat.setDuree(formation.getDuree());
            achat.setCardNumber(cardNumber);
            achat.setExprMonth(expMonth);
            achat.setExprYear(expYear);
            achat.setCvv(cvv);

            // Ajouter l'achat à la base de données
            serviceAchat.ajouter(achat);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Paiement réussi!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors du traitement du paiement.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    public void setAchat(Achat achat) {
        this.achat = achat;
    }

}