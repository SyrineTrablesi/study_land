package Controllers;

import entities.Formation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceFormation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.control.Alert;



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
    void AfficherF(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherFormation.fxml"));
            Parent root = loader.load();
            AfficherFormationController affichercontroller = loader.getController();
            if (affichercontroller != null) {
                affichercontroller.setNomF(NomF.getText());
                affichercontroller.setDuree(Duree.getText());
                affichercontroller.setNomC(NomC.getText());

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

    public void AjouterFormation(ActionEvent actionEvent) {
        {// Check for empty input fields
            if (NomF.getText().isEmpty() || Description.getText().isEmpty() || Duree.getText().isEmpty() || Prix.getText().isEmpty()
                    || Niveau.getText().isEmpty() || NomC.getText().isEmpty() || DD.getValue() == null || DF.getValue() == null) {
                // Display error message to user
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Formulaire vide");
                successAlert.setHeaderText("Veuillez remplir le formulaire de formation");
                successAlert.showAndWait();
                return;
            }

            // Convert date pickers to Date objects
            LocalDate startDate = DD.getValue();
            LocalDate endDate = DF.getValue();

            // Check if the selected dates are from today onwards
            LocalDate today = LocalDate.now();
            if (startDate.isBefore(today) || endDate.isBefore(today)) {
                // Display error message to user
                // Display success message to user
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Wrong date");
                successAlert.setHeaderText("Please select dates from today onwards");
                successAlert.showAndWait();
                return;
            }

            // Check if the start date is after the end date
            if (startDate.isAfter(endDate)) {
                // Display error message to user
                System.out.println("Start date cannot be after end date");
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Wrong date");
                successAlert.setHeaderText("Start date cannot be after end date");
                successAlert.showAndWait();
                return;
            }

            Date dateDebut = convertToUtilDate(startDate);
            Date dateFin = convertToUtilDate(endDate);

            // Validate the durationText and Prix fields
            String durationText = Duree.getText();
            if (durationText.isEmpty() || !durationText.matches("\\d+")) {
                // Handle invalid input
                System.out.println("Invalid duration input");
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Wrong duration");
                successAlert.setHeaderText("Invalid duration input");
                successAlert.showAndWait();
                return;
            }
            int duration = Integer.parseInt(durationText);

            String priceText = Prix.getText();
            if (priceText.isEmpty()) {
                // Handle empty price input
                System.out.println("Price input is empty");
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Wrong price");
                successAlert.setHeaderText("Price input is empty");
                successAlert.showAndWait();
                return;
            }

            try {
                float price = Float.parseFloat(priceText);

                FS.ajouter(new Formation(
                        NomF.getText(),
                        Description.getText(),
                        duration,
                        dateDebut,
                        dateFin,
                        price,
                        Niveau.getText(),
                        NomC.getText()
                ));

                // Display success message to user
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText("Formation added successfully");
                successAlert.showAndWait();

            } catch (NumberFormatException e) {
                // Handle invalid price input
                System.out.println("Invalid price input: " + e.getMessage());
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Invalid price input");
                successAlert.setHeaderText("Put a price");
                successAlert.showAndWait();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }}

    public void accederCategorie(ActionEvent actionEvent) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Categorie.fxml"));
        try {
            Parent root = loader1.load();
            CategorieController controller = loader1.getController();
            NomC.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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