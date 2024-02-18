package controllers;
import entities.evaluation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import services.EvalService;
import entities.question;
import services.quesservice;
import utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Ajoutevaluation {
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
            Date parsedDate = dateFormat.parse(duree.getText());
            // Créer un objet evaluation à partir des valeurs dans l'interface utilisateur
            evaluation.setTitre_evaluation(titre.getText());
            evaluation.setDescription(description.getText());
            evaluation.setDifficulte(dificulter.getText());
            evaluation.setNb_questions(Integer.parseInt(nbquestion.getText()));
            evaluation.setDuree(new Time(parsedDate.getTime()));
            evaluation.setResultat(Float.parseFloat(resultat.getText()));
            evaluation.setTestDate(java.sql.Date.valueOf(date.getValue())); // Assurez-vous que votre champ date est un DatePicker
            evaluation.setCreateur(createur.getText());
            evaluation.setPrix(Float.parseFloat(prix.getText()));

            evaluation.setDomaine(domaine.getText());

            // Récupérer l'ID de l'évaluation ajoutée
            int idEvaluation = EvalService.ajouter(evaluation);
            System.out.println(idEvaluation +"idEvaluation est");

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


}
