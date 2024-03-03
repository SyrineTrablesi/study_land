package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import entities.MessageVocal;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;

public class MessageVocalController {

    @FXML
    private Button recordButton;

    @FXML
    private Button sendButton;

    private String audioFilePath;

    @FXML
    public void initialize() {
        // Initial setup
        recordButton.setOnAction(event -> recordAudio());
        sendButton.setOnAction(event -> sendAudioMessage());
    }

    private void recordAudio() {
        // Logic to record audio and save it to audioFilePath
    }

    private void sendAudioMessage() {
        // Logic to send audio message
        MessageVocal message = new MessageVocal();
        message.setSender("Me");
        message.setReceiver("Recipient");
        message.setAudioFilePath(audioFilePath);
        message.setTimestamp(new Date());
        // Code to send message to recipient
    }

    // Optional: Method to play audio message
    private void playAudio(String filePath) {
        try {
            Media audio = new Media(new File(filePath).toURI().toURL().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
