package controllers;
import entities.MessageVocal;
import entities.MessageVocal1;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDateTime;

import static controllers.ChatGPTAPIExample.chatGPT;

public class MessageVocal1Controller {
    @FXML
    private Button sendAudioButton;

    private String currentSender = "User"; // Remplacez par le nom de l'utilisateur actuel

    @FXML
    public void initialize() {
        sendAudioButton.setOnAction(event -> {

           /* FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir un fichier audio");
            File audioFile = fileChooser.showOpenDialog(sendAudioButton.getScene().getWindow());
            if (audioFile != null) {
                // Vous pouvez enregistrer le fichier audio dans le dossier de l'application
                String audioFilePath = audioFile.getAbsolutePath();
                LocalDateTime timestamp = LocalDateTime.now();
                MessageVocal1 message = new MessageVocal1(currentSender, null, audioFilePath, timestamp);
                // Enregistrer le message dans la base de données ou l'envoyer à l'utilisateur distant, etc.
                // Vous pouvez également lire le fichier audio ici si nécessaire
                Media audio = new Media(audioFile.toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(audio);
                mediaPlayer.play();
            }*/
        });

    }
}
