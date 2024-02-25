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
import java.time.ZoneId;
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
    private TextField titreFormationTextField; // Assuming you have a TextField to input the ID
    private ServiceFormation formationService; // Instance of your service


    public AfficherFormationController() {
        this.formationService = new ServiceFormation();
    }

    @FXML
    private void handleLabelClick() {
        // Handle label click event
        System.out.println("Label clicked");
    }

    @FXML
    private void handleImageViewClick() {
        // Handle ImageView click event
        System.out.println("ImageView clicked");
    }


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
                    // Add event handler to titreLabel
                    titreLabel.setOnMouseClicked(mouseEvent -> {
                        // Handle label click event
                        System.out.println("Titre label clicked for formation: " + formation.getTitre());
                    });
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
                    // Attach the event handler to the modifierButton, not the rowBox
                    modifierButton.setOnAction(e -> modifier(new ActionEvent()));

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




    // Helper method to convert Date to LocalDate
    private LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void modifier(ActionEvent actionEvent) {
        // Get the selected Formation object from the VBox
        Node source = (Node) actionEvent.getSource();
        VBox parentVBox = (VBox) source.getParent();
        Formation formation = (Formation) parentVBox.getUserData();

        // Display a TextInputDialog to modify the titre
        TextInputDialog dialog = new TextInputDialog(formation.getTitre());
        dialog.setTitle("Modifier Titre");
        dialog.setHeaderText("Modifier Titre de la Formation");
        dialog.setContentText("Nouveau Titre:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newTitre -> {
            try {
                // Update the titre of the Formation object
                formation.setTitre(newTitre);
                // Call the modifier method in your service class to update the formation in the database
                FS.modifier(formation);
                // Optionally, provide feedback to the user that the modification was successful
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Modification Réussie");
                successAlert.setHeaderText(null);
                successAlert.setContentText("La formation a été modifiée avec succès.");
                successAlert.showAndWait();
                // Update the UI elements directly with the new titre
                Label titleLabel = (Label) parentVBox.lookup("#titleLabel");
                titleLabel.setText(newTitre);
            } catch (SQLException e) {
                System.out.println("Error modifying formation: " + e.getMessage());
                // Provide feedback to the user about the error
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur");
                errorAlert.setHeaderText("Erreur lors de la modification de la formation");
                errorAlert.setContentText("Une erreur s'est produite lors de la modification de la formation. Veuillez réessayer plus tard.");
                errorAlert.showAndWait();
                // Handle the exception appropriately
            }
        });

    }

    // Method to update the UI with the modified Formation object

    // Method to handle modification for a specific formation
    private void handleModificationForFormation(String formationId) {
        // Implement your logic to handle modification for the formation with the given ID
        System.out.println("Handling modification for formation with ID: " + formationId);
    }
    // Helper method to show an alert dialog
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void rechercherFormation(ActionEvent actionEvent) {
        String titre = titreFormationTextField.getText(); // Assuming you have a text field for the title input
        if (titre.isEmpty()) {
            // Show an alert if the title field is empty
            showAlert("Error", "Title field is empty", Alert.AlertType.ERROR);
            return;
        }

        try {
            Formation formation = formationService.rechercherParTitre(titre);
            if (formation != null) {
                // If formation is found, you can display its details or perform any actions
                // For example, you can set the details in other fields or display them in a dialog
                showAlert("Success", "Formation found: " + formation.getTitre(), Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Formation with title '" + titre + "' not found", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            showAlert("Error", "Error occurred while searching for formation", Alert.AlertType.ERROR);
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }

    }
    // Helper method to show an alert dialog
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
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


