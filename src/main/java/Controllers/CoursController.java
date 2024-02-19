package Controllers;

import entities.Categorie;
import entities.Cours;
import entities.Formation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceCours;
import services.ServiceFormation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CoursController {
    @FXML
    private TextField NomCours;

    @FXML
    private ComboBox<Formation> idFormation;

    @FXML
    private WebView pdf;
    @FXML
    private ListView<Cours> CoursListView;

    private ServiceCours serviceC = new ServiceCours();
    private ServiceFormation serviceFormation = new ServiceFormation();

    @FXML
    private void initialize() {
        // Set up cell factory to display only the ID of the Formation
        idFormation.setCellFactory(param -> new ListCell<Formation>() {
            @Override
            protected void updateItem(Formation item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.valueOf(item.getIdFormation()));
                }
            }
        });

        // Populate the ComboBox with existing formations
        try {
            List<Formation> formations = serviceFormation.afficher();
            idFormation.getItems().addAll(formations);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AjouterCours(ActionEvent event) {
        // Open a file chooser dialog to select the PDF file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDF File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile == null) {
            // No file selected, return
            return;
        }

        // Retrieve the selected formation from the ComboBox
        Formation selectedFormation = idFormation.getValue();
        if (selectedFormation == null) {
            // No formation selected, return
            return;
        }

        // Retrieve other necessary data for the course
        String nomCours = NomCours.getText();
        byte[] descriptionBytes;
        try {
            descriptionBytes = Files.readAllBytes(selectedFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return; // Exit the method if an error occurs
        }

        // Create a new course instance
        Cours cours = new Cours(nomCours, descriptionBytes, selectedFormation.getIdFormation());

        // Call the method to add the course to the database
        try {
            serviceC.ajouter(cours);

            // Display an alert for successful addition
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Course added successfully!");
            alert.showAndWait();

            // Load the selected PDF file into the WebView
            URL url = selectedFile.toURI().toURL();
            pdf.getEngine().load(url.toString());
        } catch (SQLException | MalformedURLException e) {
            // Display an alert for error
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Error adding course: " + e.getMessage());
            errorAlert.showAndWait();
        }
    }

    @FXML
    void supprimerCours(ActionEvent event) {
        // Get the selected Cours object from the ListView
        Cours selectedCours = CoursListView.getSelectionModel().getSelectedItem();

        if (selectedCours != null) {
            try {
                // Delete the course from the database
                serviceC.supprimer(selectedCours);

                // Refresh the ListView
                AfficherCours(event);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                // Handle the exception appropriately
            }
        } else {
            System.out.println("No item selected.");
        }
    }

    @FXML
    void AfficherCours(ActionEvent event) {
        try {
            List<Cours> courses = serviceC.afficher(); // Assuming this method retrieves a list of all courses
            CoursListView.getItems().clear();

            for (Cours Cours : courses) {
                CoursListView.getItems().add(Cours);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void ModifierCours(ActionEvent event) {
    }
}
