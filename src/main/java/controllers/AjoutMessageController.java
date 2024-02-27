package controllers;

import entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.ServiceMessage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import java.time.LocalDate;


public class AjoutMessageController {

    ServiceMessage msg = new ServiceMessage();

    @FXML
    private TextField dateFx;

    @FXML
    private TextField dissFx;

    @FXML
    private TextField messageFx;

    @FXML
    private TextField senderFx;

    @FXML
    void afficherdb(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherMessage.fxml"));
        try {
            Parent root = loader.load();
            AfficherMessageController afficherController = loader.getController();
            afficherController.setMessagedb(messageFx.getText());
            afficherController.setDatedb(dateFx.getText());
            afficherController.setSenderdb(senderFx.getText());
            afficherController.setDissdb(dissFx.getText());

            senderFx.getScene().setRoot(root);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }

    @FXML
    void ajoutermessage(ActionEvent event) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        java.util.Date utilDate = null;
        java.sql.Date sqlDate = null;

        try {
            // Convertir la chaîne de caractères en un objet Date
            utilDate = dateFormat.parse("10-20-2020");
            // Convertir java.util.Date en java.sql.Date
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            System.out.println("Erreur lors de la conversion de la date: " + e.getMessage());
        }

        if (sqlDate != null) {

            try {
                msg.ajouter(new Message(Integer.parseInt(senderFx.getText()), Integer.parseInt(dissFx.getText()), messageFx.getText(), sqlDate));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
