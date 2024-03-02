package controllers;
package Controllers;

import entities.Categorie;
import entities.Formation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import entities.Formation;

import java.time.format.DateTimeParseException;
import java.util.*;


import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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

//                    supprimerButton.setOnAction(e -> supprimerFormation(formation)); // Attach the event handler
//                    // Create the "Modifier" button
//                    Button modifierButton = new Button("Modifier");
//                    // Attach the event handler to the modifierButton, not the rowBox
//                    modifierButton.setOnAction(e -> modifier(new ActionEvent()));

//                    // Optionally, you can add a picture next to the "Modifier" button
//                    ImageView modifyIcon = new ImageView(new Image("/src/modifier.png"));
//                    modifyIcon.setFitWidth(20);
//                    modifyIcon.setPreserveRatio(true);
//                    modifierButton.setGraphic(modifyIcon);

//                    // Add labels, image, and buttons to the current VBox
//                    VBox.setMargin(supprimerButton, new Insets(5, 0, 0, 0)); // Add margin to the button
//                    VBox.setMargin(modifierButton, new Insets(5, 0, 0, 0)); // Add margin to the button
//                    formationBox.getChildren().addAll(supprimerButton, modifierButton, deleteIcon, modifyIcon);
//

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
    }}