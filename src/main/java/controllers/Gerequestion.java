package controllers;

import entities.question;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.quesservice;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class Gerequestion {
    quesservice sq =new quesservice();

    @FXML
    private TextField ajoutdomain;

    @FXML
    private TextArea ajouteneonce;

    @FXML
    private TextField iddelete;

    @FXML
    private TextField modfdomaine;

    @FXML
    private TextArea modifeneonce;

    @FXML
    private TextField modifid;





    public void btnsupprimer(javafx.event.ActionEvent actionEvent) {
        try{
            sq.supprimer(new question(Integer.parseInt(iddelete.getText())));
        }catch (SQLException e ){
            throw new RuntimeException(e);

        }
    }

    public void btnajouter(javafx.event.ActionEvent actionEvent) {
        try {
            sq.ajouter(new question(ajouteneonce.getText(),ajoutdomain.getText()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnmodifier(javafx.event.ActionEvent actionEvent)
        {try {
            sq.modifier(new question(Integer.parseInt(modifid.getText()),modifeneonce.getText(),modfdomaine.getText()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
