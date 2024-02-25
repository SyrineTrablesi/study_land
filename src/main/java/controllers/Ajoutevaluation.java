package controllers;
import entities.evaluation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.EvalService;
import entities.question;
import services.quesservice;
import utils.MyDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Ajoutevaluation {
    @FXML
    private TextField recherchequestion;
    public Button ajouterevaluation;
    EvalService eval =new EvalService();
    evaluation evaluation =new evaluation();
    @FXML
    private TextField createur;

    @FXML
    private DatePicker date;

    @FXML
    private TextField description;

    @FXML
    private TextField dificulter;

    @FXML
    private TextField domaine;

    @FXML
    private TextField duree;

    @FXML
    private TextField nbquestion;

    @FXML
    private TextField prix;

    @FXML
    private ListView<question> questionListView;
    @FXML
    private ListView<question> questionselectioner;

    @FXML
    private TextField titre;
    @FXML
    private TextField resultat;



    private quesservice questionService = new quesservice();
    EvalService  EvalService =new EvalService();
    public Connection connection;

    public Ajoutevaluation(Connection connection) {
        this.connection = connection;
    }
    public Ajoutevaluation() {
        // Utilisez la connexion par défaut ou tout autre mécanisme que vous utilisez pour obtenir une connexion
        this.connection = MyDB.getInstance().getConnection();
    }

    @FXML
    public void initialize() {
        chargerQuestions();
        // Configurer la ListView pour permettre la sélection multiple
        questionListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void chargerQuestions() {
        try {
            List<question> questions = questionService.afficher();
            ObservableList<question> observableQuestions = FXCollections.observableArrayList(questions);

            // Remplir la ListView avec les questions
            questionListView.setItems(observableQuestions);
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée dans votre application
        }
    }

    public void ajouter(javafx.event.ActionEvent actionEvent) {
        // Obtenir les éléments sélectionnés
        ObservableList<question> selectedQuestions = questionListView.getSelectionModel().getSelectedItems();

        // Afficher les IDs des questions sélectionnées
        System.out.println("IDs des questions sélectionnées :");
        for (question selectedQuestion : selectedQuestions) {
            System.out.println(selectedQuestion.getIdQuestion()); // Remplacez getId() par la méthode réelle pour obtenir l'ID de la question

            // Ajouter l'ID à la questionselectioner
            questionselectioner.getItems().add(new question(selectedQuestion.getIdQuestion()));
    }}

    public void ajouterevaluation(ActionEvent actionEvent) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

            // Validation des champs obligatoires
            if (titre.getText().isEmpty()) {
                System.out.println("Le champ 'Titre' ne peut pas être vide.");
                return;
            }
            if (description.getText().isEmpty()) {
                System.out.println("Le champ 'Description' ne peut pas être vide.");
                return;
            }
            if (dificulter.getText().isEmpty()) {
                System.out.println("Le champ 'Difficulté' ne peut pas être vide.");
                return;
            }
            if (nbquestion.getText().isEmpty()) {
                System.out.println("Le champ 'Nombre de questions' ne peut pas être vide.");
                return;
            }
            if (duree.getText().isEmpty()) {
                System.out.println("Le champ 'Durée' ne peut pas être vide.");
                return;
            }
            if (resultat.getText().isEmpty()) {
                System.out.println("Le champ 'Résultat' ne peut pas être vide.");
                return;
            }
            if (date.getValue() == null) {
                System.out.println("Veuillez sélectionner une date.");
                return;
            }
            if (createur.getText().isEmpty()) {
                System.out.println("Le champ 'Créateur' ne peut pas être vide.");
                return;
            }
            if (prix.getText().isEmpty()) {
                System.out.println("Le champ 'Prix' ne peut pas être vide.");
                return;
            }
            if (domaine.getText().isEmpty()) {
                System.out.println("Le champ 'Domaine' ne peut pas être vide.");
                return;
            }

            // Validation des formats de saisie
            try {
                Integer.parseInt(nbquestion.getText());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez saisir un nombre valide pour le champ 'Nombre de questions'.");
                return;
            }
            try {
                Float.parseFloat(resultat.getText());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez saisir un nombre décimal valide pour le champ 'Résultat'.");
                return;
            }
            try {
                Float.parseFloat(prix.getText());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez saisir un nombre décimal valide pour le champ 'Prix'.");
                return;
            }

            // Validation de la date
            if (date.getValue().isBefore(LocalDate.now())) {
                System.out.println("La date de l'évaluation ne peut pas être antérieure à la date actuelle.");
                return;
            }

            Date parsedDate = dateFormat.parse(duree.getText());

            // Créer un objet evaluation à partir des valeurs dans l'interface utilisateur
            evaluation.setTitre_evaluation(titre.getText());
            evaluation.setDescription(description.getText());
            evaluation.setDifficulte(dificulter.getText());
            evaluation.setNb_questions(Integer.parseInt(nbquestion.getText()));
            evaluation.setDuree(new Time(parsedDate.getTime()));
            evaluation.setResultat(Float.parseFloat(resultat.getText()));
            evaluation.setTestDate(java.sql.Date.valueOf(date.getValue()));
            evaluation.setCreateur(createur.getText());
            evaluation.setPrix(Float.parseFloat(prix.getText()));
            evaluation.setDomaine(domaine.getText());

            // Récupérer l'ID de l'évaluation ajoutée
            int idEvaluation = EvalService.ajouter(evaluation);
            System.out.println(idEvaluation + " idEvaluation est");

            // Ajouter les IDs des questions sélectionnées dans la table d'association
            for (question selectedQuestion : questionselectioner.getItems()) {
                int idQuestion = selectedQuestion.getIdQuestion();
                System.out.println(idQuestion);
                ajoutIdEvaluationQuestion(idEvaluation, idQuestion);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private void ajoutIdEvaluationQuestion(int idEvaluation, int idQuestion) throws SQLException {
        String req = "INSERT INTO evaluationquestion (id_evaluation, id_question) VALUES (?, ?)";
        try (PreparedStatement st = connection.prepareStatement(req)) {
            st.setInt(1, idEvaluation);
            st.setInt(2, idQuestion);
            st.executeUpdate();
        }
    }


    public void lesevaluation(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lesevaluation.fxml"));
            Parent root = loader.load();

            // Obtenez le contrôleur après avoir chargé le fichier FXML
            Lesevaluation affichepre = loader.getController();

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

    public void recherche(ActionEvent actionEvent) {
    }
}
