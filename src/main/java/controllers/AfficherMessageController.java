package controllers;

import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import services.ServiceMessage;

import java.sql.SQLException;


public class AfficherMessageController {

    @FXML
    private Label datedb;

    @FXML
    private Label dissdb;

    @FXML
    private Label labelfromdb;

    @FXML
    private Label messagedb;

    @FXML
    private Label senderdb;

    public Label getDatedb() {
        return datedb;
    }

    public void setDatedb(String datedb) {
        this.datedb.setText(datedb);
    }

    public Label getDissdb() {
        return dissdb;
    }

    public void setDissdb(String dissdb) {
        this.dissdb.setText(dissdb);
    }


    public Label getMessagedb() {
        return messagedb;
    }

    public void setMessagedb(String messagedb) {
        this.messagedb.setText(messagedb);
    }

    public Label getSenderdb() {
        return senderdb;
    }

    public void setSenderdb(String senderdb) {
        this.senderdb.setText(senderdb);
    }

    ServiceMessage msg = new ServiceMessage();

    @FXML
    void afficherdb(ActionEvent event) {
        try {
            labelfromdb.setText(msg.afficher().toString());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



}
