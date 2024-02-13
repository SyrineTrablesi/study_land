package controllers;

import entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.ServiceMessage;


public class AjoutMessageController {
    @FXML
    private Label messageFx;

    @FXML
    private TextField x1;

    @FXML
    private TextField x2;

    private final ServiceMessage msg = new ServiceMessage();

    @FXML
    void ajouterMessage(ActionEvent event) {
        /*msg.ajouter(new Message(messageFx.getText(),x1.getText(),(x2.getText());*/

    }

}
