package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class EmojiController {

    @FXML
    private Button button_emoji;

    private final List<String> emojis = Arrays.asList(
            "😀", "😃", "😄", "😁", "😆", "😅", "😂", "🤣", "😊",
            "😇", "🙂", "🙃", "😉", "😌", "😍", "🥰", "😘", "😗",
            "😙", "😚", "😋", "😛", "😜", "🤪", "😝", "🤑", "🤗",
            "🤭", "🤫", "🤔", "🤐", "🤨", "😐", "😑", "😶", "😏",
            "😒", "🙄", "😬", "🤥", "😌", "😔", "😪", "🤤", "😴",
            "😷", "🤒", "🤕", "🤢", "🤮", "🤧", "🥵", "🥶", "🥴",
            "😵", "🤯", "🤠", "🥳", "😎", "🤓", "🧐", "😕", "😟",
            "🙁", "☹️", "😮", "😯", "😲", "😳", "🥺", "😦", "😧",
            "😨", "😰", "😥", "😢", "😭", "😱", "😖", "😣", "😞",
            "😓", "😩", "😫", "🥱", "😤", "😡", "😠", "🤬", "😈",
            "👿", "💀", "☠️", "💩", "🤡", "👹", "👺", "👻", "👽",
            "👾", "🤖", "😺", "😸", "😹", "😻", "😼", "😽", "🙀",
            "😿", "😾",
            "❤️", "💛", "💚", "💙", "💜", "🖤", "💔", "❣️", "💕",
            "💞", "💓", "💗", "💖", "💘", "💝", "💟", "☮️", "✝️",
            "☪️", "🕉️", "☸️", "✡️", "🔯", "🕎", "☯️", "☦️", "🛐"
    );


    @FXML
    private void handleEmojiButtonClick() {
        // Afficher une alerte contenant la liste des emojis
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Choisir un Emoji");
        alert.setHeaderText("Sélectionnez un Emoji");
        alert.setContentText(String.join("\n", emojis));
        alert.showAndWait();
    }
}
