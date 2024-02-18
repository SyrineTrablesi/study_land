package controllers;

import entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.ServiceMessage;

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
    private DatePicker tfdate;

    @FXML
    private TextField tfdiss;

    @FXML
    private TextField tfmessage;

    @FXML
    private TextField tfsender;

    @FXML
    void affichermessage(ActionEvent event) {

    }

    @FXML
    void ajoutmessage(ActionEvent event) {
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
                msg.ajouter(new Message(Integer.parseInt(tfsender.getText()), Integer.parseInt(tfdiss.getText()), tfmessage.getText(), sqlDate));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
