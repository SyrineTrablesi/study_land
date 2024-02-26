package Controllers;

import entities.Categorie;
import entities.Cours;
import entities.Formation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceCours;
import services.ServiceFormation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class CoursController {
    @FXML
    private TextField NomCours;

    @FXML
    private ComboBox<Formation> idFormation;
    @FXML
    private HBox affichagecoursvbox;


    @FXML
    private WebView pdf;
    @FXML
    private ListView<Cours> CoursListView;
    @FXML
    private VBox afficherpdf;
    @FXML
    private Label nomCategorie;

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
        pdf = new WebView(); // Initialize the WebView object

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
            // Display error message to user
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("cours vide");
            successAlert.setHeaderText("Veuillez remplir le formulaire pour ajouter un cours !!");
            successAlert.showAndWait();
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
    void AfficherCours(ActionEvent event) {
        try {
            // Clear the existing content in the VBox
            affichagecoursvbox.getChildren().clear();

            // Retrieve the list of courses from the database
            List<Cours> coursList = serviceC.afficher();

            if (coursList.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Information", "No Courses", "There are no courses to display.");
                return;
            }

            // Create a HBox to hold courses in a row
            HBox rowBox = new HBox();
            rowBox.setSpacing(10); // Adjust spacing between courses

            int count = 0; // Counter for courses in the current row

            // Loop through the list of courses
            for (Cours cours : coursList) {
                // Create labels to display course details
                Label coursLabel = new Label("Nom: " + cours.getNom_Cours());

                // Decode the description from byte array to string
                String description = new String(cours.getDescription_Cours());
                Label descriptionLabel = new Label("Description: " + description);

                // Create a WebView to display the PDF content
                WebView webView = new WebView();
                webView.setPrefSize(800, 600); // Set WebView size
                loadPDFContent(cours.getDescription_Cours(), webView);

                // Optionally, you can add an image to represent the course
                ImageView imageView = new ImageView(new Image("/src/cours.png"));
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);

                // Create a VBox to hold course details and WebView
                VBox courseBox = new VBox(imageView, coursLabel, descriptionLabel, webView);
                courseBox.setSpacing(5); // Adjust spacing between elements

                // Create the "Supprimer" button
                Button deleteButton = new Button("supprimer");
                ImageView deleteIcon = new ImageView(new Image("/src/supprimer.png"));
                deleteIcon.setFitWidth(20);
                deleteIcon.setPreserveRatio(true);

                deleteButton.setGraphic(deleteIcon);
                deleteButton.setOnAction(e -> supprimerCours(cours)); // Attach the event handler

                // Optionally, you can add a button for editing
                Button modifierButton = new Button("Modifier");
                modifierButton.setOnAction(e -> ModiferButton(cours));

                // Add buttons to the VBox
                courseBox.getChildren().addAll(deleteButton, modifierButton);

                // Add the courseBox to the current row
                rowBox.getChildren().add(courseBox);
                count++;

                // Check if the maximum number of courses per line is reached
                if (count == 4) {
                    // Add the current row to the main VBox
                    affichagecoursvbox.getChildren().add(rowBox);
                    rowBox = new HBox(); // Create a new row
                    rowBox.setSpacing(10); // Adjust spacing between courses
                    count = 0; // Reset the counter for the next row
                }
            }

            // Add the last row if it contains fewer than four courses
            if (!rowBox.getChildren().isEmpty()) {
                affichagecoursvbox.getChildren().add(rowBox);
            }
        } catch (SQLException e) {
            // Handle the exception appropriately
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Database Error", "An error occurred while retrieving courses from the database.");
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    // Helper method to convert byte array to Base64 and embed it into HTML for WebView
    private String getPDFContent(byte[] pdfData) {
        try {
            // Check if the provided PDF data is null or empty
            if (pdfData == null || pdfData.length == 0) {
                // Handle the case of empty or null PDF data
                return "<p>No PDF content available</p>";
            }

            // Convert byte array to Base64 encoding
            String base64Encoded = Base64.getEncoder().encodeToString(pdfData);

            // Construct HTML content to embed the PDF
            String htmlContent = "<embed width='100%' height='100%' name='plugin' type='application/pdf' src='data:application/pdf;base64," + base64Encoded + "' />";

            return htmlContent;
        } catch (Exception e) {
            e.printStackTrace();
            return "<p>Error loading PDF content</p>";
        }
    }


    private void loadPDFContent(byte[] pdfData, WebView webView) {
        try {
            // Check if the provided PDF data is null or empty
            if (pdfData == null || pdfData.length == 0) {
                // Handle the case of empty or null PDF data
                showAlert(Alert.AlertType.WARNING, "Warning", "Empty PDF", "The PDF content is empty.");
                return;
            }

            // Load the PDF content into the WebView
            webView.getEngine().loadContent(getPDFContent(pdfData), "application/pdf");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "PDF Loading Error", "An error occurred while loading PDF content.");
        }
    }




    @FXML
    void ModiferButton(ActionEvent event) {
        // Get the selected course from the ListView
        Cours selectedCours = CoursListView.getSelectionModel().getSelectedItem();

        if (selectedCours != null) {
            // Open a dialog box or a form to allow the user to modify the course details

            // For example, you can use TextInputDialog to get the new course name
            TextInputDialog dialog = new TextInputDialog(selectedCours.getNom_Cours());
            dialog.setTitle("Modify Course");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter the new course name:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(newName -> {
                // Update the course name
                selectedCours.setNom_Cours(newName);

                try {
                    // Call the modifier method to update the course in the database
                    serviceC.modifier(selectedCours);

                    // Refresh the view
                    AfficherCours(new ActionEvent());

                    // Show a success message
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Course Modified", "Course details have been updated successfully.");
                } catch (SQLException e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Database Error", "An error occurred while modifying the course: " + e.getMessage());
                }
            });
        } else {
            showAlert(Alert.AlertType.WARNING, "Warning", "No Course Selected", "Please select a course to modify.");
        }
    }


    @FXML
    void RetournerFormation(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/AjouterFormation.fxml"));
        try {
            Parent root = loader1.load();
            AjouterFormationController controller = loader1.getController();
            NomCours.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }
    private void ModiferButton(Cours cours) {
        try {
            // Call the modifier method in your service class to update the course
            serviceC.modifier(cours);

            // Refresh the ListView
            AfficherCours(new ActionEvent());
        } catch (SQLException e) {
            System.out.println("Error modifying course: " + e.getMessage());
        }
    }
    private void supprimerCours(Cours cours) {
        try {
            // Delete related records in the cour_formation table
            serviceC.deleteCourById(cours.getIdCour());

            // Delete the Formation from the database
            serviceC.supprimer(cours);

            // Refresh the ListView
            AfficherCours(new ActionEvent());
        } catch (SQLException e) {
            System.out.println("Error deleting formation: " + e.getMessage());
        }
    }
        public void supprimerCours(ActionEvent actionEvent) {
            // Get the selected Cours object from the ListView
            Cours selectedCours = CoursListView.getSelectionModel().getSelectedItem();

            if (selectedCours != null) {
                try {
                    // Delete the selected course from the database
                    serviceC.supprimer(selectedCours);

                    // Remove the deleted course from the ListView
                    CoursListView.getItems().remove(selectedCours);

                } catch (SQLException e) {
                    System.out.println("Error deleting course: " + e.getMessage());
                    // Handle the exception appropriately
                }
            } else {
                System.out.println("No item selected.");
            }}
}
