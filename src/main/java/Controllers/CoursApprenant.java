package Controllers;

import entities.Cours;
import entities.Formation;
import entities.YouTubeAPIExample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceCours;
import services.ServiceFormation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CoursApprenant {
    @FXML
    private HBox affichagecoursvbox;

    @FXML
    private VBox afficherpdf;

    @FXML
    private Label nomCategorie;

    @FXML
    private Hyperlink pdfLink;
    @FXML
    private StackPane centerPane;
    private ServiceCours serviceC = new ServiceCours();
    private int currentPageIndex = 0;
    private int itemsPerPage = 4;
    private List<Cours> allData;

    private ServiceFormation serviceFormation = new ServiceFormation();
    @FXML
    private void initialize() {
        // Call the method to display courses when the controller is initialized
        AfficherCours(null);
        loadPage(0);
    }
    public void AfficherCours(ActionEvent actionEvent) {
        try {
            // Clear the existing content in the VBox
            affichagecoursvbox.getChildren().clear();

            // Retrieve the list of courses from the database
            allData = serviceC.afficher(); // Initialize allData here

            if (allData.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Information", "No Courses", "There are no courses to display.");
                return;
            }

            // Loop through the list of courses
            for (Cours cours : allData) {
                // Create labels to display course details
                Label coursLabel = new Label("Nom: " + cours.getNom_Cours());

                // Decode the description from byte array to string
                String description = new String(cours.getDescription_Cours());
                Label descriptionLabel = new Label("Description: " + description);

                // Create a Hyperlink to download/open the PDF
                Hyperlink pdfLink = new Hyperlink("Download PDF");
                pdfLink.setOnAction(e -> {
                    // Handle the action to download/open the PDF
                    try {
                        // Logic to download the PDF file associated with the selected course
                        String fileName = cours.getNom_Cours() + ".pdf"; // Construct the file name
                        byte[] pdfData = cours.getDescription_Cours(); // Get the PDF data from the course
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Save PDF File");
                        fileChooser.setInitialFileName(fileName);
                        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                        fileChooser.getExtensionFilters().add(extFilter);
                        File file = fileChooser.showSaveDialog(new Stage());
                        if (file != null) {
                            try (FileOutputStream fos = new FileOutputStream(file)) {
                                fos.write(pdfData); // Write the PDF data to the file
                                showAlert(Alert.AlertType.INFORMATION, "Download Successful", "PDF downloaded successfully", "File saved as: " + file.getAbsolutePath());
                            } catch (IOException ex) {
                                showAlert(Alert.AlertType.ERROR, "Error", "File Save Error", "An error occurred while saving the PDF file: " + ex.getMessage());
                            }
                        }
                    } catch (Exception ex) {
                        showAlert(Alert.AlertType.ERROR, "Error", "PDF Download Error", "An error occurred while downloading the PDF: " + ex.getMessage());
                    }
                });

                // Optionally, you can add an image to represent the course
                ImageView imageView = new ImageView(new Image("/src/cours.png"));
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);

                // Create a VBox to hold course details and WebView
                VBox courseBox = new VBox(imageView, coursLabel, pdfLink);
                courseBox.setSpacing(5); // Adjust spacing between elements
                // Add the courseBox to the main VBox
                affichagecoursvbox.getChildren().add(courseBox);
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

    @FXML
    void consultervideo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/youtube_viewer.fxml"));

        Parent root = loader.load();
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);
        YouTubeAPIExample controller = loader.getController();

    }

    public void loadNextPage(ActionEvent actionEvent) {
        int totalPages = getTotalPages();
        if (currentPageIndex < totalPages - 1) {
            currentPageIndex++;
            loadPage(currentPageIndex);
        }
    }

    public void loadPreviousPage(ActionEvent actionEvent) {
        if (currentPageIndex > 0) {
            currentPageIndex--;
            loadPage(currentPageIndex);
        }
    }
    private void loadPage(int pageIndex) {
        int fromIndex = pageIndex * itemsPerPage;
        int toIndex = Math.min(fromIndex + itemsPerPage, allData.size());
        affichagecoursvbox.getChildren().clear();

        try {
            // Loop through the sublist of courses to display only 4 courses per page
            HBox rowBox = new HBox(); // Create a new HBox for each row
            rowBox.setSpacing(20); // Adjust spacing between courses in a row

            // Loop through the list of courses
            for (int i = fromIndex; i < toIndex; i++) {
                Cours cours = allData.get(i);

                // Create labels to display course details
                Label nomCategorie = new Label("Nom: " + cours.getNom_Cours());
                Hyperlink pdfLink = new Hyperlink("Download PDF");

                // Handle the action to download/open the PDF
                pdfLink.setOnAction(e -> {
                    try {
                        // Logic to download the PDF file associated with the selected course
                        String fileName = cours.getNom_Cours() + ".pdf"; // Construct the file name
                        byte[] pdfData = cours.getDescription_Cours(); // Get the PDF data from the course
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Save PDF File");
                        fileChooser.setInitialFileName(fileName);
                        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                        fileChooser.getExtensionFilters().add(extFilter);
                        File file = fileChooser.showSaveDialog(new Stage());
                        if (file != null) {
                            try (FileOutputStream fos = new FileOutputStream(file)) {
                                fos.write(pdfData); // Write the PDF data to the file
                                showAlert(Alert.AlertType.INFORMATION, "Download Successful", "PDF downloaded successfully", "File saved as: " + file.getAbsolutePath());
                            } catch (IOException ex) {
                                showAlert(Alert.AlertType.ERROR, "Error", "File Save Error", "An error occurred while saving the PDF file: " + ex.getMessage());
                            }
                        }
                    } catch (Exception ex) {
                        showAlert(Alert.AlertType.ERROR, "Error", "PDF Download Error", "An error occurred while downloading the PDF: " + ex.getMessage());
                    }
                });

                // Optionally, you can add an image to represent the course
                ImageView imageView = new ImageView(new Image("/src/cours.png"));
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);

                // Create a VBox to hold course details
                VBox courseBox = new VBox(imageView, nomCategorie, pdfLink);
                courseBox.setSpacing(5); // Adjust spacing between elements
                rowBox.getChildren().add(courseBox);
            }

            affichagecoursvbox.getChildren().add(rowBox);

        } catch (Exception e) {
            // Handle the exception appropriately
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Error Loading Page", "An error occurred while loading the page: " + e.getMessage());
        }
    }
    private int getTotalPages() {
        return (int) Math.ceil((double) allData.size() / itemsPerPage);
    }

}
