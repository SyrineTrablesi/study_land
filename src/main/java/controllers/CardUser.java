package controllers;

import entities.Apprenant;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.print.PrinterJob;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class CardUser{
    @FXML
    private Label email;
    @FXML
     private VBox cardsContainer;

    @FXML
    private Label nom_user;
    @FXML
    private ImageView id_image_User;

    @FXML
    private Label role;
    @FXML
    private Label prenom_user;


    public void initData(User user) {
        nom_user.setText(user.getNom());
        prenom_user.setText(user.getPrenom());
        email.setText(user.getEmail());
        role.setText(user.getRole());
        Image image = new Image("file:" + user.getImage());
        if (image != null) {
            id_image_User.setImage(image);
        } else {
            Image defaultImage = new Image("D:\\syrine_3A26\\pidev\\StudyLand\\src\\main\\resources\\src\\77.png");
            id_image_User.setImage(defaultImage);
        }
    }

    public Label getEmail() {
        return email;
    }

    public Label getNom_user() {
        return nom_user;
    }

    public Label getPrenom_user() {
        return prenom_user;
    }
    @FXML
    private Button id_telecharger;
    public void setEmail(Label email) {
        this.email = email;
    }

    public void setNom_user(Label nom_user) {
        this.nom_user = nom_user;
    }

    public void setPrenom_user(Label prenom_user) {
        this.prenom_user = prenom_user;
    }

    public Label getRole() {
        return role;
    }

    public void setRole(Label role) {
        this.role = role;
    }
    private final String templatePath = "template.pdf"; // Chemin vers votre modèle PDF

    @FXML
    void telecharger(ActionEvent event) {
        try {
            // Créer un document PDF
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            // Début de l'écriture dans le document
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Nom: " + nom_user.getText());
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();

            contentStream.showText("Prénom: " + prenom_user.getText());
            contentStream.newLine();
            contentStream.showText("Email: " + email.getText());
            contentStream.newLine();
            contentStream.showText("Rôle: " + role.getText());
            contentStream.endText();
            contentStream.close();

            // Utilisation de FileChooser pour sélectionner l'emplacement de sauvegarde
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("user_card.pdf");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            File file = fileChooser.showSaveDialog(stage);

            // Enregistrer le document PDF
            if (file != null) {
                document.save(file);
                document.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}



