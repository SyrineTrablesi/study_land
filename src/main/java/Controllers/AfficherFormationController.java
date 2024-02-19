package Controllers;

import entities.Categorie;
import entities.Formation;
import javafx.application.Platform;
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
import javafx.scene.layout.VBox;
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
    @FXML
    void modifierFormation(ActionEvent event) {
        // Get the selected Formation object from the ListView
        String selectedItem = formationListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Parse the ID from the selected item
            int id = parseIdFromSelectedItem(selectedItem);

            try {
                // Retrieve the existing Formation object from the database based on its ID
                Formation existingFormation = FS.rechercherParId(id);
                if (existingFormation != null) {
                    // Create a dialog with text input fields pre-filled with existing details
                    Dialog<Formation> dialog = new Dialog<>();
                    dialog.setTitle("Modifier Formation");
                    dialog.setHeaderText("Modifier les détails de la formation");

                    // Set the button types
                    ButtonType modifierButtonType = new ButtonType("Modifier", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().addAll(modifierButtonType, ButtonType.CANCEL);

                    // Create text input fields pre-filled with existing details
                    TextField nomField = new TextField(existingFormation.getTitre());
                    TextField descriptionField = new TextField(existingFormation.getDescription());
                    TextField dureeField = new TextField(String.valueOf(existingFormation.getDuree()));
                    TextField prixField = new TextField(String.valueOf(existingFormation.getPrix()));
                    TextField niveauField = new TextField(existingFormation.getNiveau());

                    // Set the content of the dialog pane
                    dialog.getDialogPane().setContent(new VBox(10,
                            new Label("Nouveau nom:"),
                            nomField,
                            new Label("Nouvelle description:"),
                            descriptionField,
                            new Label("Nouvelle durée:"),
                            dureeField,
                            new Label("Nouveau prix:"),
                            prixField,
                            new Label("Nouveau niveau:"),
                            niveauField
                    ));

                    // Request focus on the nom field by default
                    Platform.runLater(nomField::requestFocus);

                    // Convert the result to a Formation object when the modifier button is clicked
                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == modifierButtonType) {
                            try {
                                // Create a new Formation object with the updated details
                                Formation updatedFormation = new Formation(
                                        id,
                                        existingFormation.getNomCategorie(), // Retain the existing value for nomCategorie
                                        nomField.getText(),
                                        descriptionField.getText(),
                                        Integer.parseInt(dureeField.getText()),
                                        existingFormation.getDateDebut(), // Retain the existing value for dateDebut
                                        existingFormation.getDateFin(), // Retain the existing value for dateFin
                                        Float.parseFloat(prixField.getText()),
                                        niveauField.getText()
                                );
                                return updatedFormation;
                            } catch (NumberFormatException e) {
                                // Handle invalid input (e.g., non-numeric values for duree or prix)
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText(null);
                                alert.setContentText("Veuillez entrer des valeurs valides pour la durée et le prix.");
                                alert.showAndWait();
                                return null;
                            }
                        }
                        return null;
                    });

                    Optional<Formation> result = dialog.showAndWait();
                    result.ifPresent(updatedFormation -> {
                        try {
                            // Call the modifier method in your ServiceFormation class
                            FS.modifier(updatedFormation);
                            // Refresh the ListView
                            AfficherDB(event);
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                            // Handle the exception appropriately
                        }
                    });
                } else {
                    System.out.println("Formation with ID " + id + " not found.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                // Handle the exception appropriately
            }
        } else {
            System.out.println("No item selected.");
        }}}


