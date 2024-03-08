package controllers;

import entities.Achat;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class FactureController {

    @FXML
    private Label idFacture;

    @FXML
    private VBox idFormation;

    @FXML
    private Label idNom;

    @FXML
    private Label idPrenom;
    @FXML
    private Button genererPDF;

    @FXML
    private StackPane centerPane;

    public StackPane getCenterPane() {
        return centerPane;
    }

    public void setCenterPane(StackPane centerPane) {
        this.centerPane = centerPane;
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }


    public void afficherFacture(User user, List<Achat> achats, float totalFacture) {
        // Afficher le nom et le prénom de l'utilisateur
        idNom.setText(user.getNom());
        idPrenom.setText(user.getPrenom());

        // Afficher les titres des formations achetées
        for (Achat achat : achats) {
            Label labelFormation = new Label();
            labelFormation.setText(achat.getTitre());
            idFormation.getChildren().add(labelFormation);
        }

        // Afficher le total de la facture
        DecimalFormat df = new DecimalFormat("#.##");
        idFacture.setText(df.format(totalFacture));
    }


    @FXML
    void genererPDF() {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                // Set line spacing
                contentStream.setLeading(14.5f);

                // Starting y-coordinate
                float y = page.getMediaBox().getHeight() - 50;

                // Write user information
                contentStream.beginText();
                contentStream.newLineAtOffset(50, y);
                contentStream.showText("Facture de " + idNom.getText() + " " + idPrenom.getText());
                contentStream.newLine();
                contentStream.showText("Formations achetées :");
                contentStream.newLine();

                for (Object child : idFormation.getChildren()) {
                    if (child instanceof Label) {
                        Label label = (Label) child;
                        contentStream.showText("- " + label.getText());
                        contentStream.newLine();
                    }
                }

                // Write total
                contentStream.showText("Total : " + idFacture.getText());
                contentStream.endText();
            }

            // Show file chooser and save PDF
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF File");
            fileChooser.setInitialFileName("facture.pdf");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(new Stage());

            if (file != null) {
                document.save(file);
                showAlert(Alert.AlertType.INFORMATION, "PDF Saved", "PDF saved successfully", "File saved as: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


