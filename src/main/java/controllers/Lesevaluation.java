    package controllers;

    import entities.evaluation;
    import entities.response;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Node;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.layout.AnchorPane;
    import javafx.stage.Stage;
    import services.EvalService;

    import java.io.IOException;
    import java.sql.SQLException;
    import java.util.List;

    public class Lesevaluation {
        EvalService evalService = new EvalService();
        @FXML
        private TableView<evaluation> tableevaluation;

        @FXML
        private TableColumn<evaluation, String> titre;
        @FXML
        private ListView<String> evaluationListView;
        @FXML
        private TableColumn<evaluation,Void> supprimer;
        @FXML
        private ScrollPane Scroll;
        @FXML
        private TextField idsup;
        @FXML
        public void initialize() {
            titre.setCellValueFactory(new PropertyValueFactory<>("titre_evaluation"));

            // Configure the "Supprimer" column to use a button cell


            try {
                List<evaluation> evaluations = evalService.afficher();
                displayEvaluationsInTable(evaluations);
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
            addActionColumn();
        }
        private void displayEvaluationsInTable(List<evaluation> evaluations) {
            // Clear existing items
            tableevaluation.getItems().clear();

            // Add evaluations to the TableView
            tableevaluation.getItems().addAll(evaluations);
        }


        private void addActionColumn() {
            supprimer.setCellFactory(param -> new TableCell<>() {
                private final Button button = new Button("Supprimer");

                {
                    button.setOnAction(event -> {
                         evaluation evaluation = getTableView().getItems().get(getIndex());
                        try {
                            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                            confirmationAlert.setTitle("Confirmation de suppression");
                            confirmationAlert.setHeaderText("Supprimer Évaluations ?");
                            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer Évaluations " + evaluation.getTitre_evaluation() + " ?");

                            confirmationAlert.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.OK) {
                                    try {
                                        evalService.supprimer(evaluation);
                                        tableevaluation.getItems().remove(evaluation);
                                        showAlert("Suppression réussie", " Évaluations a été supprimé avec succès.");
                                    } catch (SQLException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            });
                        } finally {

                        }
                    });
                }

                private void showAlert(String title, String message) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(title);
                    alert.setHeaderText(null);
                    alert.setContentText(message);
                    alert.showAndWait();
                }


                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(button);
                    }
                }
            });
        }


        private void displayEvaluations(List<evaluation> evaluations) {
            for (evaluation eval : evaluations) {
                String evaluationText = "ID de l'evaluation " + eval.getId_evaluation() + "                   Le Titre de evaluation : " + eval.getTitre_evaluation() ;
                evaluationListView.getItems().add(evaluationText);
            }
        }

        public void ajouterevaluation(ActionEvent actionEvent) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajoutevaluation.fxml"));
                Parent root = loader.load();

                // Obtenez le contrôleur après avoir chargé le fichier FXML
                Ajoutevaluation affichepre = loader.getController();

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

        public void modifierevaluation(ActionEvent actionEvent) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifierevaluation.fxml"));
                Parent root = loader.load();

                // Obtenez le contrôleur après avoir chargé le fichier FXML
                Modifierevaluation affichepre = loader.getController();

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

        public void gererquestion(ActionEvent actionEvent) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterQuestion.fxml"));
                Parent root = loader.load();

                // Obtenez le contrôleur après avoir chargé le fichier FXML
                AjouterQuestion affichepre = loader.getController();

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

        public void supprimer(ActionEvent actionEvent) {

        }

    }
