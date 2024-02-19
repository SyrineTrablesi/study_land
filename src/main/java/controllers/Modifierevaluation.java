package controllers;

import entities.evaluation;
import entities.question;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Modifierevaluation {
    @FXML
    private Button ajouterevaluation;

    @FXML
    private Button button;

    @FXML
    private Button button1;

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
    private TextField resultat;

    @FXML
    private TextField titre;
    @FXML
    private TextField ideval;
    EvalService eval =new EvalService();
    public Connection connection;

    public Modifierevaluation(quesservice questionService) {
        this.questionService = questionService;
    }
    List<question> selectedQuestions = new ArrayList<>();

    public Modifierevaluation() {
        this.connection = MyDB.getInstance().getConnection();
    }

    private quesservice questionService = new quesservice();
    public void ajouter(ActionEvent actionEvent) {
        // Obtenir les éléments sélectionnés
        ObservableList<question> selectedQuestions = questionListView.getSelectionModel().getSelectedItems();

        // Afficher les IDs des questions sélectionnées
        System.out.println("IDs des questions sélectionnées :");
        for (question selectedQuestion : selectedQuestions) {
            System.out.println(selectedQuestion.getIdQuestion()); // Remplacez getId() par la méthode réelle pour obtenir l'ID de la question

            // Ajouter l'ID à la questionselectioner
            questionselectioner.getItems().add(new question(selectedQuestion.getIdQuestion()));
    }}
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
    public void ajouterevaluation(ActionEvent actionEvent) throws ParseException {
        // Créer un objet evaluation à partir des valeurs dans l'interface utilisateur
        evaluation evaluation = new evaluation();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        // Parse the date string from the TextField
        Date parsedDate = dateFormat.parse(duree.getText());

        // Set the parsedDate in the evaluation object
        evaluation.setDuree(new Time(parsedDate.getTime()));
        evaluation.setId_evaluation(Integer.parseInt(ideval.getText()));
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

        try {
            // Appel de la méthode de modification dans le service
            eval.modifier(evaluation);

            // Fetch question IDs associated with the evaluation
            List<Integer> existingQuestionIds = eval.getQuestionsByEvaluationId(evaluation.getId_evaluation());

            // Fetch corresponding questions using the getQuestionById method
            List<Integer> selectedQuestionIds = new ArrayList<>();
            for (question selectedQuestion : selectedQuestions) {
                selectedQuestionIds.add(selectedQuestion.getIdQuestion());
            }


            // Ajouter uniquement les nouvelles questions sélectionnées à la table evaluationquestion
            for (int questionId : selectedQuestionIds) {
                // Vérifier si la questionId n'existe pas déjà dans la table
                if (!existingQuestionIds.contains(questionId)) {
                    ajoutIdEvaluationQuestion(evaluation.getId_evaluation(), questionId);
                }
            }

            System.out.println("Modification réussie avec succès");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public void supprimer(ActionEvent actionEvent) throws SQLException {
        evaluation evaluation = new evaluation();
        int idEvaluation = Integer.parseInt(ideval.getText());
        List<Integer> questionIds = eval.getQuestionsByEvaluationId(idEvaluation);

        // Fetch corresponding questions using the getQuestionById method
        for (int questionId : questionIds) {
            question q = questionService.getQuestionById(questionId);
            if (q != null) {
                selectedQuestions.add(q);
            }
        }

        // Display questions in the questionselectioner ListView
        ObservableList<question> observableSelectedQuestions = FXCollections.observableArrayList(selectedQuestions);
        questionselectioner.setItems(observableSelectedQuestions);
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

    public void recuperer(ActionEvent actionEvent) {
    EvalService eval = new EvalService();
        try {
            // Get the evaluation ID from the user input or any other source
            int evaluationIdToRetrieve = Integer.parseInt(ideval.getText());

            // Call the getEvaluationById method to retrieve the evaluation
            evaluation retrievedEvaluation = eval.getEvaluationById(evaluationIdToRetrieve);

            if (retrievedEvaluation != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                // Populate UI fields with the retrieved evaluation data
                titre.setText(retrievedEvaluation.getTitre_evaluation());
                description.setText(retrievedEvaluation.getDescription());
                dificulter.setText(retrievedEvaluation.getDifficulte());
                nbquestion.setText(String.valueOf(retrievedEvaluation.getNb_questions()));
                duree.setText(retrievedEvaluation.getDuree().toString());
                resultat.setText(String.valueOf(retrievedEvaluation.getResultat()));
                date.setValue(LocalDate.parse(dateFormat.format(retrievedEvaluation.getTestDate())));
                createur.setText(retrievedEvaluation.getCreateur());
                prix.setText(String.valueOf(retrievedEvaluation.getPrix()));
                domaine.setText(retrievedEvaluation.getDomaine());

                // You may also need to handle the question-related parts if needed

                System.out.println("Evaluation retrieved: " + retrievedEvaluation);
            } else {
                System.out.println("Evaluation not found with ID: " + evaluationIdToRetrieve);
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }


    }
}
