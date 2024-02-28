    package controllers;
    import entities.question;
    import entities.evaluation;
    import javafx.application.Platform;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.scene.Node;
    import javafx.scene.control.CheckBox;
    import javafx.scene.control.Label;
    import javafx.scene.control.TextArea;
    import javafx.scene.control.TextField;
    import javafx.scene.layout.VBox;
    import javafx.scene.text.Text;
    import services.EvalService;
    import services.Reponseservice;
    import services.quesservice;
    import entities.response;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;
    import java.text.DecimalFormat;
    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.List;
    import javafx.scene.text.Text;

    public class Teste {

        private List<response> selectedResponses = new ArrayList<>();
        @FXML
        private Label titre;

        @FXML
        private Text date;

        @FXML
        private Text descriptionTextArea;

        @FXML
        private Text domaine;

        @FXML
        private Text duree;



        @FXML
        private Text nbquestion;

        @FXML
        private Text resultat;



        @FXML
        private VBox vbxquesrep;
        @FXML
        private List<CheckBox> responseCheckBoxes = new ArrayList<>();

static int  ids;

        List<response> responses =new ArrayList<>();

        quesservice ques=new quesservice();
        EvalService EvalService = new EvalService();
        quesservice quesservice = new quesservice(); // Instantiate quesservice
    Reponseservice reponseservice=new Reponseservice();
int getNb_questions;


        private int retrievedId;  // Attribute to store the retrieved ID

        // ... other fields and methods ...

        public int getRetrievedId() {
            return retrievedId;
        }

        @FXML
        public void initialize() throws SQLException {
            try {
                System.out.println("adoula clicked for Evaluation ID: " + ids);
                evaluation eval = getEvaluationWithQuestionsById(ids);
                System.out.println("Retrieved ID: " + ids);

                System.out.println("Evaluation Object: " + eval);  // Check if evaluation is retrieved successfully

                if (eval != null) {
                    getNb_questions = eval.getNb_questions();

                    System.out.println("getNb_questions " + getNb_questions);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    // Set the values in the UI fields
                    titre.setText(eval.getTitre_evaluation());
                    descriptionTextArea.setText(eval.getDescription());
                    domaine.setText(eval.getDomaine());
                    duree.setText(String.valueOf(eval.getDuree()));
                    resultat.setText(String.valueOf(eval.getResultat()));
                    date.setText(dateFormat.format(eval.getTestDate()));
                    nbquestion.setText(String.valueOf(eval.getNb_questions()));
                    System.out.println(domaine + "domaine aaaaaa");
                    // Clear existing content in the VBox
                    vbxquesrep.getChildren().clear();

                    vbxquesrep.getStyleClass().add("vbxquesrep");

                    for (question q : eval.getQuestion()) {
                        VBox questionVBox = new VBox();

                        // Appliquer le style à la questionLabel
                        Label questionLabel = new Label(q.getIdQuestion() + ". " + q.getEnonce());
                        questionLabel.getStyleClass().add("question-label");

                        // Fetch and display responses for the question using CheckBox
                        List<response> responses = reponseservice.getResponsesByQuestionId(q.getIdQuestion());
                        for (response r : responses) {
                            CheckBox responseCheckBox = new CheckBox(r.getContenu());

                            // Appliquer le style à la case à cocher
                            responseCheckBox.getStyleClass().add("checkbox");

                            // Ajouter un gestionnaire d'événements pour la sélection de la case à cocher
                            responseCheckBox.setOnAction(event -> handleCheckBoxSelection(responseCheckBox, r));
                            questionVBox.getChildren().add(responseCheckBox);
                        }

                        vbxquesrep.getChildren().add(questionLabel);
                        vbxquesrep.getChildren().add(questionVBox);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }




        private void handleCheckBoxSelection(CheckBox checkBox, response r) {
            System.out.println("ID de la réponse choisie : " + r.getIdReponse());
            System.out.println("Statut de la réponse : " + r.getStatus());

            // Check if the checkbox is selected
            if (checkBox.isSelected()) {
                // Add the response to the list when it is selected
                selectedResponses.add(r);
            } else {
                // Remove the response from the list when it is deselected
                selectedResponses.remove(r);
            }
        }




        public evaluation getEvaluationWithQuestionsById(int id) throws SQLException {
            retrievedId = ids;  // Set the retrieved ID

            evaluation eval = EvalService.getEvaluationById(id);

            if (eval != null) {
                List<question> questions = quesservice.getQuestionsByEvaluationId(id); // Call the method on the instance
                eval.setQuestion(questions);
                ids= eval.getId_evaluation();
                // Print evaluation details
                System.out.println("Evaluation ID: " + eval.getId_evaluation());
                System.out.println("Title: " + eval.getTitre_evaluation());
                System.out.println("Description: " + eval.getDescription());
                System.out.println("Domaine: " + eval.getDomaine());
                System.out.println("Duree: " + eval.getDuree());
                System.out.println("Number of Questions: " + eval.getNb_questions());
                System.out.println("Resultat: " + eval.getResultat());
                System.out.println("Test Date: " + eval.getTestDate());
                // Determine and print the number of questions
                int numberOfQuestions = questions.size();
                // Print questions
                System.out.println("Questions:");
                for (question q : questions) {
                    System.out.println("Question ID: " + q.getIdQuestion());
                    System.out.println("Enonce: " + q.getEnonce());
                    System.out.println("Domaine: " + q.getDomaine());
                    System.out.println("--------------------");
                }
            }

            return eval;
        }



        @FXML
        public void validerAction(ActionEvent actionEvent) throws SQLException {
            double score = calculateScore();
            System.out.println("Score: " + score + "/100");
            resultat.setText(String.valueOf(score));

        }

        private double calculateScore() {
            int totalQuestions = 3; // Total number of questions
            int correctResponses = 0; // Number of correct responses

            // Iterate through the selected responses and count the correct ones
            for (response r : selectedResponses) {
                // Assuming 'ONE' is the correct status
                if (r.getStatus() == response.status.ONE) {
                    // If the status is ONE, the response is correct
                    correctResponses++;

                }

            }
            System.out.println(correctResponses);
            System.out.println(totalQuestions);
            // Calculate the percentage
            double percentage = (double) correctResponses / totalQuestions * 100;
// Format the percentage to have two digits after the decimal point
            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            String formattedPercentage = decimalFormat.format(percentage);

            // Print or return the formatted percentage
            System.out.println(formattedPercentage);
            return Double.parseDouble(formattedPercentage); // I
        }



    }







