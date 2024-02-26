package Controllers;

import entities.Categorie;
import entities.Formation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLOutput;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import services.ServiceFormation;


public class AfficherFormationController implements Initializable {

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
    private String selectedItem;
    private Formation selectedFormation;


    public AfficherFormationController() {
        this.formationService = new ServiceFormation();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the "FromDB" Label here
        FromDB = new Label();
    }

    @FXML
    private void handleLabelClick(MouseEvent event) {
        Node source = (Node) event.getSource();

        if (source instanceof Label) {
            Label clickedLabel = (Label) source;

            // Retrieve the Formation object associated with the clicked label
            Formation clickedFormation = (Formation) clickedLabel.getUserData();

            if (clickedFormation != null) {
                // Set the selectedFormation to the clickedFormation
                selectedFormation = clickedFormation;

                // Display both the title and the ID of the selected formation
                String message = "Titre label clicked for formation: " + clickedFormation.getTitre() + "\n";
                message += "Formation ID: " + clickedFormation.getIdFormation();
                FromDB.setText(message);
            } else {
                System.out.println("No formation associated with the clicked label.");
            }
        }
    }
    @FXML
    void AfficherDB(ActionEvent event) {
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
                    // Set the ID of the label to the ID of the formation
                    titreLabel.setId(String.valueOf(formation.getIdFormation()));

                    // Set the Formation object as the user data for the label
                    titreLabel.setUserData(formation);

                    // Add event handler to titreLabel
                    titreLabel.setOnMouseClicked(this::handleLabelClick);

                    // Create other labels for the remaining properties of the Formation object
                    // (Description, Durée, Date Début, Date Fin, Prix, Niveau)
                    Label descriptionLabel = new Label("Description: " + formation.getDescription());
                    Label dureeLabel = new Label("Durée: " + formation.getDuree() + " heures");
                    Label dateDebutLabel = new Label("Date Début: " + formation.getDateDebut());
                    dateDebutLabel.setPrefWidth(120); // Set the preferred width as needed
                    Label dateFinLabel = new Label("Date Fin: " + formation.getDateFin());
                    dateFinLabel.setPrefWidth(120); // Set the preferred width as needed
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
        }
    }

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
        System.out.println(selectedItem);
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

    //test syrine
    private Formation parseIdFromSelecte(String selectedItem) {
        ServiceFormation serviceFormation= new ServiceFormation() ;
        int startIndex = selectedItem.indexOf("idFormation=") + "idFormation=".length();
        int endIndex = selectedItem.indexOf(",", startIndex);
        try {
            System.out.println(serviceFormation.rechercherParId(startIndex));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            return serviceFormation.rechercherParId(startIndex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Helper method to convert Date to LocalDate
    private LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void modifier(ActionEvent actionEvent) {
        if (selectedFormation != null) {
            // Show a dialog to prompt the user for the new title
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Modifier formation");
            dialog.setHeaderText("Modifier le titre de la formation");
            dialog.setContentText("Nouveau titre:");

            // Get user input
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(newTitre -> {
                if (!newTitre.isEmpty()) {
                    try {
                        // Create a new Formation object with the updated title and existing attributes
                        Formation updatedFormation = new Formation(selectedFormation.getIdFormation(), newTitre);
                        System.out.println(selectedFormation.getIdFormation()+"form syrineee");

                        // Call the modifier method in your ServiceFormation class to update the formation title in the database
                        formationService.modifier(updatedFormation);

                        // Provide feedback to the user
                        showAlert("Success", "Formation title updated successfully.", Alert.AlertType.INFORMATION);
                    } catch (SQLException e) {
                        System.out.println("Error updating formation title: " + e.getMessage());
                        showAlert("Error", "Failed to update formation title: " + e.getMessage(), Alert.AlertType.ERROR);
                    }
                } else {
                    showAlert("Error", "New title cannot be empty.", Alert.AlertType.ERROR);
                }
            });
        } else {
            showAlert("Error", "No formation selected.", Alert.AlertType.ERROR);
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


