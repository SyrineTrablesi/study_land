package Controllers;

import entities.Categorie;
import entities.Formation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import entities.Formation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import Controllers.AjouterFormationController;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import services.ServiceFormation;


public class AfficherFormationController {

    @FXML
    private Label DD;

    @FXML
    private Label DF;

    @FXML
    private Label Description;

    @FXML
    private Label Duree;

    @FXML
    private Label Niveau;

    @FXML
    private Label NomC;

    @FXML
    private Label NomF;

    @FXML
    private Label Prix;

    @FXML
    private Label FromDB;

    public String getDescription() {
        return Description.getText();
    }

    public void setDescription(String description) {
        Description.setText(description);
    }

    public String getDuree() {
        return Duree.getText();
    }

    public void setDuree(String duree) {
        Duree.setText(duree);
    }

    public String getNiveau() {
        return Niveau.getText();
    }

    public void setNiveau(String niveau) {
        Niveau.setText(niveau);
    }

    public String getNomF() {
        return NomF.getText();
    }

    public void setNomF(String nomF) {
        NomF.setText(nomF);
    }

    public String getPrix() {
        return Prix.getText();
    }

    public void setPrix(String prix) {
        Prix.setText(prix);
    }

    public String getNomC() {
        return NomC.getText();
    }

    public void setNomC(String nomC) {
        NomC.setText(nomC);
    }

    public LocalDate getDD() {
        return LocalDate.parse(DD.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setDD(LocalDate dd) {
        DD.setText(dd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public LocalDate getDF() {
        return LocalDate.parse(DF.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setDF(LocalDate df) {
        DF.setText(df.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }


    private ServiceFormation FS = new ServiceFormation();


    @FXML
    private ListView<String> formationListView;
    private List<Formation> formations;






    @FXML
    void AfficherDB(ActionEvent event) {
        try {
            List<Formation> formations = FS.afficher();

            // Clear any existing items in the ListView
            formationListView.getItems().clear();

            // Add each Formation object to the ListView
            for (Formation formation : formations) {
                formationListView.getItems().add(formation.toString());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFormation(ActionEvent actionEvent) {
        // Get the selected Formation object from the ListView
        String selectedItem = formationListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Parse the ID from the selected item
            int id = parseIdFromSelectedItem(selectedItem);

            try {
                // Delete the Formation from the database
                FS.supprimer(new Formation(id, null, null, null, 0, null, null, 0, null)); // Create a temporary Formation object with only the ID
                // Refresh the ListView
                AfficherDB(actionEvent);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                // Handle the exception appropriately
            }
        } else {
            System.out.println("No item selected.");
        }
    }

    // Helper method to parse the ID from the string representation of a Formation object
    private int parseIdFromSelectedItem(String selectedItem) {
        // Assuming your string representation is in the format "Formation{idFormation=<id>, ...}"
        int startIndex = selectedItem.indexOf("idFormation=") + "idFormation=".length();
        int endIndex = selectedItem.indexOf(",", startIndex);
        return Integer.parseInt(selectedItem.substring(startIndex, endIndex));
    }

    public void modifierFormation(ActionEvent actionEvent) {
    }
}


