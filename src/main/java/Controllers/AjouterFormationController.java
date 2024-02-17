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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherFormation.fxml"));
        try {
            Parent root = loader.load();
            AfficherFormationController affichercontroller = loader.getController();
            if (affichercontroller != null) {
                affichercontroller.setNomF(NomF.getText());
                affichercontroller.setDuree(Duree.getText());
                affichercontroller.setNomC(NomC.getText());

                // Display the loaded FXML in a new window
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
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


        {
            // Check for empty input fields
            if (NomF.getText().isEmpty() || Description.getText().isEmpty() || Duree.getText().isEmpty() || Prix.getText().isEmpty()
                    || Niveau.getText().isEmpty() || NomC.getText().isEmpty() || DD.getValue() == null || DF.getValue() == null) {
                // Display error message to user
                System.out.println("Please fill in all fields");
                return;
            }

            // Convert date pickers to Date objects
            LocalDate startDate = DD.getValue();
            LocalDate endDate = DF.getValue();
            Date dateDebut = convertToUtilDate(startDate);
            Date dateFin = convertToUtilDate(endDate);

            // Validate the durationText and Prix fields
            String durationText = Duree.getText();
            if (durationText.isEmpty() || !durationText.matches("\\d+")) {
                // Handle invalid input
                System.out.println("Invalid duration input");
                return;
            }
            int duration = Integer.parseInt(durationText);

            String priceText = Prix.getText();
            if (priceText.isEmpty()) {
                // Handle empty price input
                System.out.println("Price input is empty");
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
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


    }
}