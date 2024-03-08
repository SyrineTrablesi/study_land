package controllers;

import entities.Favoris;
import entities.Formation;
import entities.Inscrit;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceFavoris;
import services.ServiceFormation;
import services.ServiceInscrit;

import java.io.IOException;
import java.sql.SQLException;


public class CardHomeFormation {
    @FXML
    private AnchorPane cardsContainer;

    @FXML
    private FlowPane cardsFlowPane;

    @FXML
    private FlowPane cardsFlowPane2;

    @FXML
    private AnchorPane idListFavoris;

    @FXML
    private AnchorPane idListInscite;

    @FXML
    private Button btn_AjoutFavoris;

    @FXML
    private Button btn_AjoutInscrit;

    @FXML
    private Label categorieLabel;

    @FXML
    private Label dureeLabel;

    @FXML
    private Text idtext;

    @FXML
    private Label idtitle;

    @FXML
    private Label niveauLabel;

    @FXML
    private Label prixLabel;



    public void setData(String title, String description, String niveau, int duree, float prix, String categorie) {
        idtitle.setText(title);
        idtext.setText(description);
        niveauLabel.setText(niveau);
        dureeLabel.setText(Integer.toString(duree));
        prixLabel.setText(Float.toString(prix));
        categorieLabel.setText(categorie);
    }
    @FXML
    void btn_AjouterFavoris(ActionEvent event) {
        User user = new User();
        user.setId(2);
        ServiceFormation serviceFormation = new ServiceFormation();
        try {
            Formation formation = serviceFormation.rechercherParTitre(idtitle.getText());

            if (formation != null) { // Vérifier si la formation existe
                Favoris favoris = new Favoris(user.getId(), formation.getIdFormation());
                favoris.setDescription(formation.getDescription());
                favoris.setNiveau(formation.getNiveau());
                favoris.setNomCategorie(formation.getNomCategorie());
                favoris.setTitre(formation.getTitre());
                favoris.setDuree(formation.getDuree());
                ServiceFavoris serviceFavoris = new ServiceFavoris();

                if (serviceFavoris.formationExisteDeja(favoris)) { // Vérifier si la formation est déjà ajoutée aux favoris
                    // Afficher un message d'alerte
                    showAlert(Alert.AlertType.INFORMATION, "Formation déjà ajoutée", "La formation est déjà dans vos favoris.");
                } else {
                    // Ajouter la formation aux favoris
                    serviceFavoris.ajouter(favoris);
                    // Afficher un message de succès
                    showAlert(Alert.AlertType.INFORMATION, "Formation ajoutée", "La formation a été ajoutée à vos favoris avec succès.");
                }
            } else {
                // Afficher un message d'alerte si la formation n'existe pas
                showAlert(Alert.AlertType.ERROR, "Formation introuvable", "La formation que vous essayez d'ajouter n'existe pas.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btn_AjouterInscrit(ActionEvent event) {
        User user = new User();
        user.setId(2);
        ServiceFormation serviceFormation = new ServiceFormation();
        try {
            Formation formation = serviceFormation.rechercherParTitre(idtitle.getText());

            // Vérifier si le prix de la formation est égal à zéro
            if (formation.getPrix() == 0) {
                // Vérifier si l'utilisateur est déjà inscrit à la formation
                Inscrit inscrit = new Inscrit(user.getId(), formation.getIdFormation());
                ServiceInscrit serviceInscrit = new ServiceInscrit();
                if (serviceInscrit.formationExisteDeja(inscrit)) {
                    // Afficher un message d'alerte si l'utilisateur est déjà inscrit à la formation
                    showAlert(Alert.AlertType.INFORMATION, "Déjà inscrit", "Vous êtes déjà inscrit à cette formation.");
                } else {
                    // Ajouter l'utilisateur à la liste des inscrits
                    inscrit.setDescription(formation.getDescription());
                    inscrit.setNiveau(formation.getNiveau());
                    inscrit.setNomCategorie(formation.getNomCategorie());
                    inscrit.setTitre(formation.getTitre());
                    inscrit.setDuree(formation.getDuree());
                    System.out.println(inscrit);
                    serviceInscrit.ajouter(inscrit);
                }
            } else {
                // Afficher l'écran de paiement
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/TicketPayment.fxml"));
                Parent root = loader.load();
                TicketPaymentController controller = loader.getController();
                controller.setFormation(formation);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (SQLException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de l'inscription.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    // Méthode pour afficher une boîte de dialogue
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

