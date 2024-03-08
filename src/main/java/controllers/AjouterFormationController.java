package controllers;

import entities.Formation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.ServiceCategorie;
import services.ServiceFormation;
import entities.Categorie;
import services.ServiceCategorie;



import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


public class AjouterFormationController {
    @FXML
    private DatePicker DD;

    @FXML
    private DatePicker DF;

    @FXML
    private TextField Description;

    @FXML
    private TextField Duree;

    @FXML
    private TextField Niveau;

    @FXML
    private TextField NomC;

    @FXML
    private TextField NomF;

    @FXML
    private TextField Prix;

    @FXML
    private Button toggleDarkModeButton;
    @FXML
    private ComboBox<Categorie> NomCategorie;

    @FXML
    private void toggleDarkMode(ActionEvent event) {
        Scene scene = toggleDarkModeButton.getScene();
        if (scene != null) {
            if (scene.getStylesheets().contains("/dark-mode.css")) {
                scene.getStylesheets().remove("/dark-mode.css");
                scene.getStylesheets().add("/light-mode.css");
            } else {
                scene.getStylesheets().remove("/light-mode.css");
                scene.getStylesheets().add("/dark-mode.css");
            }
        }
    }
     ServiceCategorie CS= new ServiceCategorie();

    public void initialize() {
        initializeComboBox();
    }

    private void initializeComboBox() {
        try {
            // Fetch categories from the database
            List<Categorie> categories = CS.afficher();

            // Clear existing items in the ComboBox
            NomCategorie.getItems().clear();

            // Add fetched categories' names to the ComboBox
            for (Categorie categorie : categories) {
                // Add the name of each category to the ComboBox
                NomCategorie.getItems().add(categorie);
            }




            // Optionally, set a default selection if needed
            // NomCategorie.getSelectionModel().selectFirst();
        } catch (SQLException e) {
            // Handle database exception
            e.printStackTrace();
            // Optionally, show an error message to the user
            // You can use a dialog or label to display the error message
            // errorMessageLabel.setText("Failed to fetch categories. Please try again later.");
        }
    }




    @FXML
    void AfficherF(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherFormation.fxml"));
            Parent root = loader.load();
            AfficherFormationController affichercontroller = loader.getController();
            if (affichercontroller != null) {
                affichercontroller.setNomF(NomF.getText());
                affichercontroller.setDuree(Duree.getText());
//                affichercontroller.setNomC(NomC.getText());

                // Replace the content of the current scene with the loaded FXML
                Scene scene = NomF.getScene(); // Assuming NomF is a TextField in your current scene
                scene.setRoot(root);
            } else {
                System.out.println("Error: AfficherFormationController is null");
            }
        } catch (IOException e) {
            System.out.println("Error loading AfficherFormation.fxml: " + e.getMessage());
        }
    }
    private final ServiceFormation FS= new ServiceFormation();

    private Date convertToUtilDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }





    public void AjouterFormation(ActionEvent actionEvent) throws SQLException {
        // Check for empty input fields
        if (NomF.getText().isEmpty() || Description.getText().isEmpty() || Duree.getText().isEmpty() || Prix.getText().isEmpty()
                || Niveau.getText().isEmpty() || DD.getValue() == null || DF.getValue() == null || NomCategorie.getValue() == null) {
            // Display error message to user
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Please fill in all fields");
            errorAlert.showAndWait();
            return;
        }

        // Convert date pickers to Date objects
        LocalDate startDate = DD.getValue();
        LocalDate endDate = DF.getValue();

        // Check if the selected dates are from today onwards
        LocalDate today = LocalDate.now();
        if (startDate.isBefore(today) || endDate.isBefore(today)) {
            // Display error message to user
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Please select dates from today onwards");
            errorAlert.showAndWait();
            return;
        }

        // Check if the start date is after the end date
        if (startDate.isAfter(endDate)) {
            // Display error message to user
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Start date cannot be after end date");
            errorAlert.showAndWait();
            return;
        }

        Date dateDebut = convertToUtilDate(startDate);
        Date dateFin = convertToUtilDate(endDate);

        // Validate the durationText and Prix fields
        String durationText = Duree.getText();
        if (durationText.isEmpty() || !durationText.matches("\\d+")) {
            // Handle invalid input
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Invalid duration input");
            errorAlert.showAndWait();
            return;
        }
        int duration = Integer.parseInt(durationText);

        String priceText = Prix.getText();
        if (priceText.isEmpty()) {
            // Handle empty price input
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Price input is empty");
            errorAlert.showAndWait();
            return;
        }


            float price = Float.parseFloat(priceText);
            // Create a new Formation object with the input values
            Formation formation = new Formation(
                    NomF.getText(),
                    Description.getText(),
                    duration,
                    dateDebut,
                    dateFin,
                    Float.parseFloat(priceText),
                    Niveau.getText(),
                    NomCategorie.getValue().getNomCategorie()
            );

            // Add the new Formation object to the database
            ServiceFormation sf = new ServiceFormation();
            sf.ajouter(formation);

            // Clear the input fields
            NomF.clear();
            Description.clear();
            Duree.clear();
            Prix.clear();
            Niveau.clear();
            DD.getEditor().clear();
            DF.getEditor().clear();
            NomCategorie.getSelectionModel().clearSelection();

            // Display success message to user
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText("Formation added successfully");
            successAlert.showAndWait();
        }
    @FXML
    void AccederCours(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Cours.fxml"));
        try {
            Parent root = loader1.load();
            CoursController controller = loader1.getController();
            Niveau.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



}