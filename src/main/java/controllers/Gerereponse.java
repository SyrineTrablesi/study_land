package controllers;

import entities.evaluation;
import entities.question;
import entities.response;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.Reponseservice;
import services.quesservice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Gerereponse {
    Reponseservice reponseservice =new Reponseservice();
    @FXML
    private TableColumn<response, Void> supprimer;
    @FXML
    private TextArea idtep12;

    @FXML
    private TextArea idtep121;

    @FXML
    private TableColumn<response, String> colreponse;
    @FXML
    private TableView<response> tabreponse;
    @FXML
    private TableColumn<question, String> colquestion;
    @FXML
    private TableView<question> tabquestion;

    @FXML
    private TextField iddelete;

    @FXML
    private TextField idques;

    @FXML
    private TextField idques1;

    @FXML
    private TextField idtep1;



    @FXML
    private CheckBox non;

    @FXML
    private CheckBox non1;

    @FXML
    private CheckBox oui;

    @FXML
    private CheckBox oui1;
    private int selectedQuestionId;
    private int selectedReponseId;


    Reponseservice res = new Reponseservice();
Reponseservice rep = new Reponseservice();
quesservice ques= new quesservice();


    @FXML
    public void initialize() {
        colreponse.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        colquestion.setCellValueFactory(new PropertyValueFactory<>("enonce"));
        initTable();


        }
    private void initTable() {
        tabreponse.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Lorsqu'une réponse est sélectionnée, mettez à jour selectedReponseId
                selectedReponseId = newSelection.getIdReponse();
                System.out.println("Réponse ID: " + selectedReponseId);
            }
        });

        tabquestion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Lorsqu'une question est sélectionnée, récupérez son ID
                selectedQuestionId = newSelection.getIdQuestion();
                System.out.println("Question ID: " + selectedQuestionId);
            }
        });
        tabreponse.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Lorsqu'une réponse est sélectionnée, récupérez ses données à partir du service
                int selectedReponseId = newSelection.getIdReponse();
                try {
                    List<response> responseList = reponseservice.afficher();
                    Optional<response> selectedResponse = responseList.stream()
                            .filter(response -> response.getIdReponse() == selectedReponseId)
                            .findFirst();

                    if (selectedResponse.isPresent()) {
                        response selectedResp = selectedResponse.get();

                        // Affichez les données dans les champs correspondants
                        idtep121.setText(selectedResp.getContenu());

                        // Mettez à jour les cases à cocher en fonction du statut
                        switch (selectedResp.getStatus()) {
                            case ONE:
                                oui1.setSelected(true);
                                non1.setSelected(false);
                                break;
                            case ZERO:
                                oui1.setSelected(false);
                                non1.setSelected(true);
                                break;
                            default:
                                // Gérez le cas où le statut n'est ni ONE ni ZERO
                                System.out.println("Statut invalide dans la base de données");
                                oui1.setSelected(false);
                                non1.setSelected(false);
                                break;
                        }
                    } else {
                        System.out.println("Réponse non trouvée avec l'ID : " + selectedReponseId);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Gérez l'exception de manière appropriée dans votre application
                }
            }
        });
        try {
            // Appelez la méthode afficher de votre service pour récupérer les questions
            List<response> reponse = res.afficher();

            // Convertissez la liste en une liste observable pour le TableView
            ObservableList<response> observableList = FXCollections.observableArrayList(reponse);

            // Associez la liste observable au TableView
            tabreponse.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez l'exception de manière appropriée
        }

        try {
            List<question> quest = ques.afficher();
            System.out.println("Nombre de questions récupérées : " + quest.size());

            // Convertissez la liste en une liste observable pour le TableView
            ObservableList<question> observableList = FXCollections.observableArrayList(quest);

            // Associez la liste observable au TableView
            tabquestion.setItems(observableList);
        } catch (SQLException e) {
        e.printStackTrace(); // Gérez l'exception de manière appropriée
        System.err.println("Erreur lors de la récupération des questions : " + e.getMessage());
    }
    }


// Vous devez compléter la méthode btnsup (la fin du nom de la méthode est tronquée dans votre question)
// Ajoutez la suite de votre méthode btnsup ici...

    public void btnsupprimer(ActionEvent actionEvent) {



    }

    public void btnajouter(ActionEvent actionEvent) {
        try {
            // Vérifier que les champs ne sont pas vides
            if (idtep12.getText().isEmpty() && (!oui.isSelected() && !non.isSelected())) {
                afficherAlerte("Champs vides", "Les champs réponse et statut sont vides. Veuillez les remplir.");
                return;
            }

            if (idtep12.getText().isEmpty()) {
                afficherAlerte("Champ réponse vide", "Le champ réponse est vide. Veuillez le remplir.");
                return;
            }

            if (!oui.isSelected() && !non.isSelected()) {
                afficherAlerte("Champ statut vide", "Le champ statut est vide. Veuillez le remplir.");
                return;
            }
            response updatedResponse = new response();
            updatedResponse.setContenu((idtep12.getText()));

            updatedResponse.setIdQuestion((selectedQuestionId));
            updatedResponse.setStatus(response.status.valueOf(oui.isSelected() ? "ONE" : "ZERO"));

            // Modifier la response dans la base de données
            reponseservice.ajouter(updatedResponse);
            System.out.println(updatedResponse);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(titre);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void btnmodifier(ActionEvent actionEvent) {

        try {
            response updatedResponse = new response();
            updatedResponse.setContenu(idtep121.getText());
            boolean isSelected = oui1.isSelected();
            String statusString = isSelected ? "ONE" : "ZERO";
            System.out.println("isSelected: " + isSelected + ", statusString: " + statusString);

            updatedResponse.setIdReponse(selectedReponseId);
            System.out.println("aa"+selectedReponseId);

            // Modifier la response dans la base de données
            reponseservice.modifier(updatedResponse);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void afficherrepense(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reponce.fxml"));
            Parent root = loader.load();

            // Obtenez le contrôleur après avoir chargé le fichier FXML
            Reponce affichepre = loader.getController();

            // Créez une nouvelle scène avec le Parent chargé
            Scene scene = new Scene(root);

            // Récupérez la scène actuelle à partir du bouton
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Remplacez la scène actuelle par la nouvelle scène
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void récupérer(ActionEvent actionEvent) {


        Reponseservice Reponseservice = new Reponseservice();
        try {
            // Get the evaluation ID from the user input or any other source
            int repIdToRetrieve = Integer.parseInt(idtep1.getText());
            response retrievedResponse = Reponseservice.getResponseById(repIdToRetrieve);

            if (retrievedResponse != null) {
                // Display the retrieved question's data in the modification fields
                idtep121.setText(retrievedResponse.getContenu());
                idques1.setText(String.valueOf(retrievedResponse.getIdQuestion()));
                if (retrievedResponse.getStatus() == response.status.ONE) {
                    oui1.setSelected(true);
                    non1.setSelected(false);
                } else if (retrievedResponse.getStatus() == response.status.ZERO) {
                    oui1.setSelected(false);
                    non1.setSelected(true);
                } else {
                    // Handle the case where the status is neither ONE nor ZERO
                    System.out.println("Invalid status in the database");
                }
            } else {
                System.out.println("Response not found with ID: " + repIdToRetrieve);
                // Optionally, clear the modification fields if the response is not found
                idtep121.clear();
                idques1.clear();
                oui1.setSelected(false);
                non1.setSelected(false);
            }




        }catch (NumberFormatException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}

    public void recherche(ActionEvent actionEvent) {
    }

    public void getquestion(ListView.EditEvent<?> editEvent) {
    }

    public void getreponse(ListView.EditEvent<?> editEvent) {
    }
}
