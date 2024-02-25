package Controllers;

import entities.Categorie;
import entities.Formation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import entities.Formation;

import java.time.format.DateTimeParseException;
import java.util.*;


import Controllers.AjouterFormationController;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
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
    @FXML
    private WebView affichageWeb;

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
    private VBox affichageformationvbox;





    @FXML
    void AfficherDB(ActionEvent event){
        try {
            List<Formation> formations = FS.afficher();

            // Clear any existing items in the VBox
            affichageformationvbox.getChildren().clear();

            // Loop through each Formation object and add its details to the VBox
            for (int i = 0; i < formations.size(); i += 4) {
                // Create a new HBox for each row
                HBox rowBox = new HBox();
                rowBox.setSpacing(20); // Adjust spacing between formations in a row

                // Loop through 4 formations and add them to the current row
                for (int j = i; j < Math.min(i + 4, formations.size()); j++) {
                    Formation formation = formations.get(j);

                    // Create labels for each property of the Formation object
                    Label titreLabel = new Label("Titre: " + formation.getTitre());
                    Label descriptionLabel = new Label("Description: " + formation.getDescription());
                    Label dureeLabel = new Label("Durée: " + formation.getDuree() + " heures");
                    Label dateDebutLabel = new Label("Date Début: " + formation.getDateDebut());
                    Label dateFinLabel = new Label("Date Fin: " + formation.getDateFin());
                    Label prixLabel = new Label("Prix: " + formation.getPrix() + " DT");
                    Label niveauLabel = new Label("Niveau: " + formation.getNiveau());

                    // Optionally, you can add an image to the formation
                    ImageView imageView = new ImageView(new Image("/src/cours.png"));
                    imageView.setFitWidth(100);
                    imageView.setPreserveRatio(true);

                    // Add labels and image to the current VBox
                    VBox formationBox = new VBox(imageView, titreLabel, descriptionLabel, dureeLabel, dateDebutLabel, dateFinLabel, prixLabel, niveauLabel);
                    formationBox.setSpacing(5); // Adjust spacing between labels in a formation

                    // Create the "Supprimer" button
                    Button supprimerButton = new Button("Supprimer");

                    // Optionally, you can add a picture next to the "Supprimer" button
                    ImageView deleteIcon = new ImageView(new Image("/src/supprimer.png"));
                    deleteIcon.setFitWidth(20);
                    deleteIcon.setPreserveRatio(true);

                    supprimerButton.setGraphic(deleteIcon);

                    supprimerButton.setOnAction(e -> supprimerFormation(formation)); // Attach the event handler
                    // Create the "Modifier" button
                    Button modifierButton = new Button("Modifier");

                    rowBox.setOnMouseClicked(e -> modifier(new ActionEvent()));

                    // Optionally, you can add a picture next to the "Modifier" button
                    ImageView modifyIcon = new ImageView(new Image("/src/modifier.png"));
                    modifyIcon.setFitWidth(20);
                    modifyIcon.setPreserveRatio(true);
                    modifierButton.setGraphic(modifyIcon);

                    // Add labels, image, and buttons to the current VBox
                    VBox.setMargin(supprimerButton, new Insets(5, 0, 0, 0)); // Add margin to the button
                    VBox.setMargin(modifierButton, new Insets(5, 0, 0, 0)); // Add margin to the button
                    formationBox.getChildren().addAll(supprimerButton, modifierButton, deleteIcon, modifyIcon);


                    // Add the VBox for the current formation to the row
                    rowBox.getChildren().add(formationBox);
                }

                // Add the current row to the VBox
                affichageformationvbox.getChildren().add(rowBox);

                // Add spacing between rows
                Region spacer = new Region();
                spacer.setPrefHeight(40); // Adjust the height to increase or decrease the spacing between rows
                affichageformationvbox.getChildren().add(spacer);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }}

    // Method to handle formation deletion
    private void supprimerFormation(Formation formation) {
        try {
            // Delete related records in the cour_formation table
            FS.deleteCourFormationByFormationId(formation.getIdFormation());

            // Delete the Formation from the database
            FS.supprimer(formation);

            // Refresh the ListView
            AfficherDB(new ActionEvent());
        } catch (SQLException e) {
            System.out.println("Error deleting formation: " + e.getMessage());
        }
    }
    @FXML
    void supprimer(ActionEvent event) {
// Get the selected Formation object from the ListView
        String selectedItem = formationListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Parse the ID from the selected item
            int id = parseIdFromSelectedItem(selectedItem);

            try {
                // Delete the Formation from the database
                FS.supprimer(new Formation(id, null, null, null, 0, null, null, 0, null)); // Create a temporary Formation object with only the ID
                // Refresh the ListView
                AfficherDB(event);
            } catch (SQLException e) {
                System.out.println("Error deleting formation: " + e.getMessage());
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


    public void modifier(ActionEvent actionEvent) {
        try {
            // Get the source of the event
            Object source = actionEvent.getSource();

            if (source instanceof Button) {
                Button modifierButton = (Button) source;

                // Retrieve the Formation object from the user data of the modifier button
                Formation formation = (Formation) modifierButton.getUserData();

                if (formation != null) {
                    // Create TextFields for each attribute to allow the user to input new values
                    TextField descriptionField = new TextField();
                    TextField dureeField = new TextField();
                    TextField dateDebutField = new TextField();
                    TextField dateFinField = new TextField();
                    TextField prixField = new TextField();
                    TextField niveauField = new TextField();

                    // Create a GridPane to organize the TextFields
                    GridPane gridPane = new GridPane();
                    gridPane.setHgap(10);
                    gridPane.setVgap(10);
                    gridPane.addRow(0, new Label("Description:"), descriptionField);
                    gridPane.addRow(1, new Label("Durée:"), dureeField);
                    gridPane.addRow(2, new Label("Date Début (dd/MM/yyyy):"), dateDebutField);
                    gridPane.addRow(3, new Label("Date Fin (dd/MM/yyyy):"), dateFinField);
                    gridPane.addRow(4, new Label("Prix:"), prixField);
                    gridPane.addRow(5, new Label("Niveau:"), niveauField);

                    // Create an Alert with a custom dialog pane containing the GridPane
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Modifier Formation");
                    alert.setHeaderText(null);
                    alert.getDialogPane().setContent(gridPane);

                    // Show the alert and wait for user input
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        // Parse the input values
                        String description = descriptionField.getText().trim();
                        int duree = Integer.parseInt(dureeField.getText().trim());
                        LocalDate dateDebut = LocalDate.parse(dateDebutField.getText().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        LocalDate dateFin = LocalDate.parse(dateFinField.getText().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        float prix = Float.parseFloat(prixField.getText().trim());
                        String niveau = niveauField.getText().trim();

                        // Update the Formation object with the new values
                        formation.setDescription(description);
                        formation.setDuree(duree);
                        formation.setDateDebut(java.sql.Date.valueOf(dateDebut));
                        formation.setDateFin(java.sql.Date.valueOf(dateFin));
                        formation.setPrix(prix);
                        formation.setNiveau(niveau);

                        // Call the modifier method in your service class to update the formation in the database
                        FS.modifier(formation);

                        // Refresh the VBox
                        AfficherDB(actionEvent);
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "No item selected", "Please select a Formation to modify.");
                }
            }
        } catch (SQLException | NumberFormatException | DateTimeParseException e) {
            e.printStackTrace(); // Handle the exception appropriately
            // Show an error message to the user
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input", "Please enter valid values for the Formation.");
        }
    }
    // Helper method to show an alert dialog
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    public static class TitreComparator implements Comparator<Formation> {
        @Override
        public int compare(Formation f1, Formation f2) {
            return f1.getTitre().compareTo(f2.getTitre());
        }
    }
    public void trierParTitre() {
        try {
            List<Formation> formations = FS.afficher();

            // Sort formations using TitreComparator
            Collections.sort(formations, new TitreComparator());

            // Clear any existing items in the VBox
            affichageformationvbox.getChildren().clear();

            // Loop through each Formation object and add its details to the VBox
            for (int i = 0; i < formations.size(); i += 4) {
                // Create a new HBox for each row
                HBox rowBox = new HBox();
                rowBox.setSpacing(20); // Adjust spacing between formations in a row

                // Loop through 4 formations and add them to the current row
                for (int j = i; j < Math.min(i + 4, formations.size()); j++) {
                    Formation formation = formations.get(j);

                    // Create labels for each property of the Formation object
                    Label titreLabel = new Label("Titre: " + formation.getTitre());
                    Label descriptionLabel = new Label("Description: " + formation.getDescription());
                    Label dureeLabel = new Label("Durée: " + formation.getDuree() + " heures");
                    Label dateDebutLabel = new Label("Date Début: " + formation.getDateDebut());
                    Label dateFinLabel = new Label("Date Fin: " + formation.getDateFin());
                    Label prixLabel = new Label("Prix: " + formation.getPrix() + " DT");
                    Label niveauLabel = new Label("Niveau: " + formation.getNiveau());

                    // Optionally, you can add an image to the formation
                    ImageView imageView = new ImageView(new Image("/src/cours.png"));
                    imageView.setFitWidth(100);
                    imageView.setPreserveRatio(true);

                    // Add labels and image to the current VBox
                    VBox formationBox = new VBox(imageView, titreLabel, descriptionLabel, dureeLabel, dateDebutLabel, dateFinLabel, prixLabel, niveauLabel);
                    formationBox.setSpacing(5); // Adjust spacing between labels in a formation

                    // Create the "Supprimer" button
                    Button supprimerButton = new Button("Supprimer");

                    // Optionally, you can add a picture next to the "Supprimer" button
                    ImageView deleteIcon = new ImageView(new Image("/src/supprimer.png"));
                    deleteIcon.setFitWidth(20);
                    deleteIcon.setPreserveRatio(true);

                    supprimerButton.setGraphic(deleteIcon);

                    supprimerButton.setOnAction(e -> supprimerFormation(formation)); // Attach the event handler
                    // Create the "Modifier" button
                    Button modifierButton = new Button("Modifier");

                    rowBox.setOnMouseClicked(e -> modifier(new ActionEvent()));

                    // Optionally, you can add a picture next to the "Modifier" button
                    ImageView modifyIcon = new ImageView(new Image("/src/modifier.png"));
                    modifyIcon.setFitWidth(20);
                    modifyIcon.setPreserveRatio(true);
                    modifierButton.setGraphic(modifyIcon);

                    // Add labels, image, and buttons to the current VBox
                    VBox.setMargin(supprimerButton, new Insets(5, 0, 0, 0)); // Add margin to the button
                    VBox.setMargin(modifierButton, new Insets(5, 0, 0, 0)); // Add margin to the button
                    formationBox.getChildren().addAll(supprimerButton, modifierButton, deleteIcon, modifyIcon);

                    // Add the VBox for the current formation to the row
                    rowBox.getChildren().add(formationBox);
                }

                // Add the current row to the VBox
                affichageformationvbox.getChildren().add(rowBox);

                // Add spacing between rows
                Region spacer = new Region();
                spacer.setPrefHeight(40); // Adjust the height to increase or decrease the spacing between rows
                affichageformationvbox.getChildren().add(spacer);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    }


