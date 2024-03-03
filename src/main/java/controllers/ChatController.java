/*package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import utils.AudioRecorder;

import java.io.File;

public class ChatController {
    @FXML
    private Button recordButton;

    @FXML
    private TextArea chatTextArea;

    private AudioRecorder audioRecorder; // Déclaration de l'objet audioRecorder

    private File audioFile;

    @FXML
    public void initialize() {
        audioRecorder = new AudioRecorder(); // Initialisation de l'objet audioRecorder
        audioFile = new File("recordedAudio.wav");
    }

    @FXML
    private void startRecording() {
        if (audioRecorder != null) { // Vérifiez si audioRecorder est initialisé avant de l'utiliser
            audioRecorder.startRecording();
        } else {
            System.err.println("Erreur : audioRecorder n'est pas initialisé.");
        }
    }

    @FXML
    private void stopRecording() {
        audioRecorder.stopRecording();
    }

    @FXML
    private void playAudio() {
        if (audioFile.exists()) {
            Media media = new Media(audioFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
    }
}
*/