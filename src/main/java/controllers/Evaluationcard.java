package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class Evaluationcard {
    @FXML
    private AnchorPane cardsContainer;
    @FXML
    private Button idbouton;

    @FXML
    private Text idtext;

    @FXML
    private Label idtitle;

    public void consulter(ActionEvent actionEvent) {
    }



    public void setIdtitle(String title) {
        idtitle.setText(title);
    }

    public void setIdtext(String text) {
        idtext.setText(text);
    }



}
