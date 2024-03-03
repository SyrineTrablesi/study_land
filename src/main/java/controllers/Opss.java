package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Opss {

    @FXML
    private Text resultat;
 private   double score;
    public void setScore(double newScore) {
        this.score = newScore;
    }
    public  double scoreTest;
  @FXML
    public void initialize() throws SQLException {
      String formattedScore = String.format("%.2f%%", scoreTest);
      resultat.setText(formattedScore);
  }
    @FXML
    public void updateScore(double newScore) {
        resultat.setText(String.valueOf(scoreTest));
         // Mettez à jour le texte lors de la mise à jour du score
    }    @FXML
    void reessayer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Evaluationcards.fxml"));
            Parent root = loader.load();

            Evaluationcards affichepre = loader.getController();

            Scene scene = new Scene(root);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();


        }


    }}
