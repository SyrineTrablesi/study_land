package controllers;

import entities.Client;
import entities.Message;
import entities.Server;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import services.ServiceMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.sound.sampled.*;

import javax.imageio.IIOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SampleController implements Initializable {

    // Déclarer la liste des emojis


    @FXML
    private Button button_send;
    @FXML
    private TextField tf_message;
    @FXML
    private VBox vbox_messages;
    @FXML
    private ScrollPane sp_main;

    private Client client;
    private Server server;

    ServiceMessage msg = new ServiceMessage();

    int ena=2;
    //int ena = Session.userInfo.id;
    int sahbi=1;

    @FXML
    private Button button_emoji;

    @FXML
    private StackPane rootPane;

    @FXML
    private TextField serchbyname;

    @FXML
    private Button button_audio;





    private List<Bublefriends.Friend> friendsList=  new ArrayList<>();;


    @FXML
    private Label namePersone;

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
    private ListView<String> emojiListView;
    @FXML
    void handleSendButtonAction(MouseEvent event) {
        if (!emojiListView.isVisible()) {
            emojiListView.setVisible(true);

        }else{
            emojiListView.setVisible(false);

        }
    }


    @FXML
    private void handleEmojiClickedList(MouseEvent event) {
        String selectedEmoji = emojiListView.getSelectionModel().getSelectedItem();
        if (selectedEmoji != null) {
            tf_message.setText(tf_message.getText()+selectedEmoji);
            System.out.println(selectedEmoji);
        }
    }



    // Méthode pour envoyer un message à la discussion


    // Gérer l'appui sur la touche "Entrée" pour envoyer le message

    private String recordAudio() {
        String audioFilePath = "message.wav"; // Chemin du fichier audio à enregistrer
        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line not supported");
            return null;
        }

        try {
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            System.out.println("Recording...");

            AudioInputStream ais = new AudioInputStream(line);

            System.out.println("Recording finished.");

            // Sauvegarder l'audio dans un fichier
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, new File(audioFilePath));

            return audioFilePath; // Retourner le chemin du fichier audio enregistré
        } catch (LineUnavailableException | IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void playAudio(String audioFilePath) {
        AudioClip audioClip = new AudioClip(new File(audioFilePath).toURI().toString());
        audioClip.play();
    }

    @FXML
    private void handleAudioButtonAction(ActionEvent event) {
        // Utilisation d'une boîte de dialogue de fichier pour sélectionner le fichier audio à envoyer
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier audio");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers audio", "*.wav", "*.mp3"));
        File audioFile = fileChooser.showOpenDialog(button_audio.getScene().getWindow());

        if (audioFile != null) {
            // L'utilisateur a sélectionné un fichier audio
            // Code pour enregistrer et envoyer le fichier audio vers le serveur

            // Exemple : Lecture du fichier audio sélectionné
            playAudio(audioFile);
        } else {
            // L'utilisateur a annulé la sélection du fichier audio
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun fichier sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Aucun fichier audio sélectionné.");
            alert.showAndWait();
        }
    }

    // Méthode pour lire le fichier audio sélectionné
    private void playAudio(File audioFile) {
        Media media = new Media(audioFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    /* @FXML
    private void handleAudioButtonAction(ActionEvent event) {
        // Utilisation d'une boîte de dialogue de fichier pour sélectionner le fichier audio à envoyer
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier audio");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers audio", "*.wav", "*.mp3"));
        File audioFile = fileChooser.showOpenDialog(button_audio.getScene().getWindow());

        if (audioFile != null) {
            // L'utilisateur a sélectionné un fichier audio
            // Code pour enregistrer et envoyer le fichier audio vers le serveur

            // Exemple : Lecture du fichier audio sélectionné
            playAudio(audioFile);
        } else {
            // L'utilisateur a annulé la sélection du fichier audio
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun fichier sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Aucun fichier audio sélectionné.");
            alert.showAndWait();
        }
    }

    // Méthode pour lire le fichier audio sélectionné
    private void playAudio(File audioFile) {
        Media media = new Media(audioFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Ajouter un écouteur pour détecter quand la lecture est terminée
        mediaPlayer.setOnEndOfMedia(() -> {
            // Ce code sera exécuté lorsque la lecture sera terminée
            System.out.println("Lecture audio terminée");
            // Vous pouvez ajouter ici le code que vous souhaitez exécuter après la fin de la lecture
        });

        // Commencer la lecture
        mediaPlayer.play();
    } */



    @FXML
    private void handleEmojiButtonClick() {
        // Ouvrir une fenêtre contextuelle ou une liste déroulante avec la liste des emojis
        // Gérer la sélection de l'emoji et l'ajouter au champ de texte
        String selectedEmoji = selectEmoji();
        if (selectedEmoji != null) {
            tf_message.appendText(selectedEmoji);
        }
    }



    private String selectEmoji() {
        // Implémenter cette méthode pour afficher une liste d'emojis et permettre à l'utilisateur de sélectionner un emoji
        // Par exemple, vous pouvez utiliser une boîte de dialogue ou une liste déroulante pour afficher les emojis
        // Cette méthode doit retourner l'emoji sélectionné par l'utilisateur
        // Si aucun emoji n'est sélectionné, retourner null
        return null; // Pour l'exemple, retourne null
    }



    @FXML
    private void sendMessage() {


        String messageToSend = tf_message.getText();
        if (!messageToSend.isEmpty()) {
            // Créer un HBox avec le message à envoyer et l'ajouter à la VBox
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5, 5, 5, 10));

            Text text = new Text(messageToSend);
            TextFlow textFlow = new TextFlow(text);
            textFlow.setStyle("-fx-color: rgb(239,242,255);" +
                    "-fx-background-color: rgb(15,125,242);" +
                    "-fx-background-radius: 20px;");
            textFlow.setPadding(new Insets(5, 10, 5, 10));
            text.setFill(Color.color(0.934, 0.945, 0.996));

            hBox.getChildren().add(textFlow);
            vbox_messages.getChildren().add(hBox);

            // Envoyer le message au serveur
            client.sendMessageToServer(messageToSend);

            // Effacer le champ de texte après l'envoi du message
            tf_message.clear();
        } else {
            // Afficher une alerte si le champ de texte est vide
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Message vide");
            alert.setHeaderText(null);
            alert.setContentText("Vous ne pouvez pas envoyer un message vide.");
            alert.showAndWait();
        }
    }

    /*@FXML
    public void handleMessageBoxClicked(TextFlow textFlow) {
        // Récupérer le texte du message
        String messageText = "";
        for (Node node : textFlow.getChildren()) {
            if (node instanceof Text) {
                messageText += ((Text) node).getText();
            }
        }

        // Afficher une alerte pour confirmer la suppression du message
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce message?");
        alert.setContentText(messageText);

        // Ajouter les boutons "Oui" et "Non"
        ButtonType buttonYes = new ButtonType("Oui");
        ButtonType buttonNo = new ButtonType("Non");
        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        // Récupérer la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonYes) {
            // Si l'utilisateur clique sur "Oui", supprimer le message
            // Mettez ici votre logique pour supprimer le message
            // Par exemple :
            // supprimerMessage(messageText);
        } else {
            // Si l'utilisateur clique sur "Non" ou ferme la boîte de dialogue, ne rien faire
        }
    }*/


    private final ServiceMessage serviceMessage = new ServiceMessage();

    @FXML
    private void handleSentMessageClicked(MouseEvent event) {
        if (event.getTarget() instanceof Text) {
            Text clickedText = (Text) event.getTarget();
            String messageText = clickedText.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Voulez-vous vraiment supprimer ce message?");
            alert.setContentText(messageText);

            ButtonType buttonYes = new ButtonType("Oui", ButtonBar.ButtonData.YES);
            ButtonType buttonNo = new ButtonType("Non", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(buttonYes, buttonNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonYes) {
                try {
                    Message message = new Message();
                    message.setId_message(extractMessageId(messageText));
                    serviceMessage.supprimer(message);

                    // Trouver le parent HBox qui contient le TextFlow
                    Node parentHBox = clickedText.getParent().getParent();
                    // Supprimer le HBox du conteneur vbox_messages
                    vbox_messages.getChildren().remove(parentHBox);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

}

    // Fonction pour extraire l'ID du message à partir du texte du message


    private int extractMessageId(String messageText) {
        int id = -1; // Valeur par défaut si l'extraction échoue ou si l'identifiant est invalide

        // Vérifier si le texte du message contient un identifiant
        if (!messageText.isEmpty() && messageText.matches("\\d+")) { // Vérifier si la chaîne contient uniquement des chiffres
            id = Integer.parseInt(messageText);
        } else {
            // Gérer le cas où le texte du message ne contient pas un identifiant valide
            System.err.println("Le texte du message ne contient pas un identifiant valide : " + messageText);
        }

        return id;
    }





    // Méthode pour supprimer le message





    /*@FXML
    private void handleSentMessageClicked(MouseEvent event) {
        // Récupérer le message cliqué
        Label clickedLabel = (Label) event.getTarget();
        Message clickedMessage = (Message) clickedLabel.getUserData(); // Assume que vous avez stocké l'objet Message dans la propriété userData du Label

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce message?");
        alert.setContentText(clickedMessage.getMessage());

        // Ajouter les boutons "Oui" et "Non"
        ButtonType buttonYes = new ButtonType("Oui");
        ButtonType buttonNo = new ButtonType("Non");
        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        // Récupérer la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonYes) {
            // Si l'utilisateur clique sur "Oui", supprimer le message
            try {
                supprimerMessage(clickedMessage);
                // Supprimer visuellement le message de l'interface utilisateur
                vbox_messages.getChildren().remove(clickedLabel.getParent());
            } catch (SQLException e) {
                e.printStackTrace();
                // Gérer les erreurs éventuelles lors de la suppression du message
            }
        } else {
            // Si l'utilisateur clique sur "Non" ou ferme la boîte de dialogue, ne rien faire
        }
    }

    private void supprimerMessage(Message message) throws SQLException {
        // Appeler votre méthode de service pour supprimer le message de la base de données
        msg.supprimer(message);
    }*/

    /*public static int findAvailablePort() {
        for (int port = 1024; port <= 65535; port++) {
            try (ServerSocket ignored = new ServerSocket(port)) {
                return port; // Si la création du ServerSocket réussit, le port est disponible
            } catch (IOException e) {
                // Si une exception est levée, le port est déjà utilisé, donc on passe au suivant
            }
        }
        // Si aucun port n'est disponible dans la plage spécifiée, retourner -1
        return -1;
    }*/
    /*public static int findAvailablePort(int startPort) {
        int port = startPort;
        while (port < 65536) { // Ports valides vont de 0 à 65535
            try (ServerSocket ignored = new ServerSocket(port)) {
                // Si la création du ServerSocket réussit, le port est disponible
                return port;
            } catch (IOException e) {
                // Si une exception est levée, le port est déjà utilisé, donc on passe au suivant
                port++;
            }
        }
        // Si aucun port n'est disponible dans la plage spécifiée, retourner -1
        return -1;
    }*/

    public boolean findFriendByAge(int id) {
        for (Bublefriends.Friend friend : friendsList) {
            if (friend.getId() == id) {
                return false; // Friend with specified age found
            }
        }
        return true; // Friend with specified age not found
    }

    void beginmsg(){
        List<Message> list = new ArrayList<>();
        try {
            list=msg.afficher();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(list.toString());

        for (Message message : list) {

            if( (message.getId_sender()==ena) ){
                System.out.println(findFriendByAge(message.getId_diss()));
                if(findFriendByAge(message.getId_diss()))
                    friendsList.add(new Bublefriends.Friend("Farah",message.getId_diss()));
            }else if (message.getId_diss()==ena){
                if(findFriendByAge(message.getId_sender() ))
                    friendsList.add(new Bublefriends.Friend("Ahmed", message.getId_sender() ));
            }



            if( ((message.getId_sender()==ena)&(message.getId_diss()==sahbi)) ||
                    ((message.getId_sender()==sahbi)&(message.getId_diss()==ena)) ) {

                // Do something with each message
                if ((message.getId_sender() == ena))
                {
                    System.out.println(message.getId_sender() + "  " + message.getId_diss());


                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);

                    hBox.setPadding(new Insets(5, 5, 5, 10));
                    Text text = new Text(message.getMessage());
                    TextFlow textFlow = new TextFlow(text);

                    textFlow.setStyle("-fx-color: rgb(239,242,255);" +
                            "-fx-background-color: rgb(15,125,242);" +
                            "-fx-background-radius: 20px;");

                    textFlow.setPadding(new Insets(5, 10, 5, 10));
                    text.setFill(Color.color(0.934, 0.945, 0.996));

                    hBox.getChildren().add(textFlow);
                    vbox_messages.getChildren().add(hBox);
                }
                else if (message.getId_sender() == sahbi ){
                    System.out.println(message.getId_sender() + "  " + message.getId_diss());
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    hBox.setPadding(new Insets(5,5,5,10));

                    Text text = new Text(message.getMessage());
                    TextFlow textFlow = new TextFlow(text);
                    textFlow.setStyle("-fx-color: rgb(239,242,255);" +
                            "-fx-background-color: rgb(240, 240, 240);" + // Gris encore plus clair
                            "-fx-background-radius: 20px;");

                    textFlow.setPadding(new Insets(5,10,5,10));
                    hBox.getChildren().add(textFlow);

                    vbox_messages.getChildren().add(hBox);

                }

            }
            // For example, printing the message
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        emojiListView.getItems().addAll(emojis);


        try {
            server = new Server(new ServerSocket(1234));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating server.");
        }


        /*for (Node node : vbox_messages.getChildren()) {
            if (node instanceof HBox) {
                HBox hBox = (HBox) node;
                for (Node child : hBox.getChildren()) {
                    if (child instanceof TextFlow) {
                        TextFlow textFlow = (TextFlow) child;
                        textFlow.setOnMouseClicked(event -> handleMessageBoxClicked(textFlow));
                    }
                }
            }
        }*/

        tf_message.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // Vérifier si la touche pressée est la touche d'entrée
                if (event.getCode() == KeyCode.ENTER) {
                    sendMessage();
                }
            }
        });


        ////affiche begin
     beginmsg();
        ////aafffchageee end




        /*int port = findAvailablePort();
        if (port != -1) {
            try {
                client = new Client(new Socket("localhost", port));
                System.out.println("Connected to server.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erreur de connexion au serveur. Veuillez vérifier si le serveur est en cours d'exécution et si le port " + port + " est accessible.");
            }
        } else {
            System.out.println("Le port n'est pas disponible.");
        }
        */
        /*int port = findAvailablePort(8888);
        if (port != -1) {
            System.out.println("Port disponible : " + port);
        } else {
            System.out.println("Aucun port disponible dans la plage spécifiée.");
        }*/

        try {
            client = new Client(new Socket("localhost", 1234));
            System.out.println("Connected to server");
        } catch (IOException e) {
            e.printStackTrace();
        }


        vbox_messages.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sp_main.setVvalue((Double) newValue);
            }
        });

        client.receiveMessageFromServer(vbox_messages);

        /*button_send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String messageToSend = tf_message.getText();
                if(!messageToSend.isEmpty())  {
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);

                    hBox.setPadding(new Insets(5,5,5,10));
                    Text text = new Text(messageToSend);
                    TextFlow textFlow = new TextFlow(text);

                    textFlow.setStyle("-fx-color: rgb(239,242,255);" +
                            "-fx-background-color: rgb(15,125,242);" +
                            "-fx-background-radius: 20px;");

                    textFlow.setPadding(new Insets(5, 10, 5, 10));
                    text.setFill(Color.color(0.934, 0.945, 0.996));

                    hBox.getChildren().add(textFlow);
                    vbox_messages.getChildren().add(hBox);
                    msgsend();
                    client.sendMessageToServer(messageToSend);
                    tf_message.clear();
                }
            }
        });*/
        button_send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String messageToSend = tf_message.getText();
                if(!messageToSend.isEmpty())  {
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);

                    hBox.setPadding(new Insets(5,5,5,10));
                    Text text = new Text(messageToSend);
                    TextFlow textFlow = new TextFlow(text);

                    textFlow.setStyle("-fx-color: rgb(239,242,255);" +
                            "-fx-background-color: rgb(15,125,242);" +
                            "-fx-background-radius: 20px;");

                    textFlow.setPadding(new Insets(5, 10, 5, 10));
                    text.setFill(Color.color(0.934, 0.945, 0.996));

                    hBox.getChildren().add(textFlow);
                    vbox_messages.getChildren().add(hBox);
                    msgsend();
                    client.sendMessageToServer(messageToSend);
                    tf_message.clear();
                } else {
                    // Afficher une alerte si le champ de texte est vide
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Message vide");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous ne pouvez pas envoyer un message vide.");
                    alert.showAndWait();
                }
            }
        });


        // Add more friends as needed...

        // Display all friends initially
        displayFriends(friendsList);
    }

    @FXML
    void searchByName(KeyEvent event) {
        String searchTerm = serchbyname.getText().trim().toLowerCase();
        List<Bublefriends.Friend> filteredList = new ArrayList<>();

        // Filter friends based on search term
        for (Bublefriends.Friend friend : friendsList) {
            if (friend.getName().toLowerCase().contains(searchTerm)) {
                filteredList.add(friend);
            }
        }

        // Update displayed friends based on filtered list
        displayFriends(filteredList);
    }

    // Method to display friends in the VBox
    private void displayFriends(List<Bublefriends.Friend> friendList) {
        // Create VBox to hold friends
        VBox containerPane = new VBox();
        containerPane.setSpacing(10); // Set vertical spacing between friend panes
        containerPane.setPrefWidth(100); // Set fixed width
        containerPane.setMinWidth(70); // Set minimum width
        containerPane.setMaxHeight(Double.MAX_VALUE); // Set maximum height
        containerPane.setAlignment(Pos.CENTER); // Center the content vertically

        // Create Label objects for each friend and add them to the containerPane
        for (Bublefriends.Friend friend : friendList) {
            VBox friendPane = new VBox();
            friendPane.setPrefWidth(100);
            friendPane.setPrefHeight(70);
            friendPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 20;");

            Label nameLabel = new Label(friend.getName());
            nameLabel.setFont(Font.font("System Bold", 13)); // Set font to bold
            nameLabel.setStyle("-fx-font-weight: bold;"); // Alternate way to set font to bold

            Label ageLabel = new Label("Id: " + friend.getId());

            friendPane.setOnMouseClicked(event -> {
                namePersone.setText(friend.getName()+" "+ friend.getId());
                System.out.println("Clicked on: " + friend.getName());
                vbox_messages.getChildren().clear();
                sahbi=friend.getId();
                beginmsg();

            });

            friendPane.getChildren().addAll(nameLabel, ageLabel);
            friendPane.setAlignment(Pos.CENTER); // Center the content vertically
            containerPane.getChildren().add(friendPane);
        }

        // Create ScrollPane and add the containerPane to it
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(containerPane);
        scrollPane.setFitToWidth(true); // Allow horizontal scrolling if needed

        // Set fixed size for ScrollPane
        scrollPane.setPrefSize(100, Double.MAX_VALUE);
        scrollPane.setMinSize(70, 0);

        // Add the ScrollPane to the rootPane (centered)
        rootPane.getChildren().clear(); // Clear previous content
        rootPane.getChildren().add(scrollPane);
        StackPane.setAlignment(scrollPane, javafx.geometry.Pos.CENTER);
    }



    public static void addLabel(String msgFromServer, VBox vBox) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));

        Text text = new Text(msgFromServer);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: rgb(233,233,235);" +
                "-fx-background-radious: 20px;");
        textFlow.setPadding(new Insets(5,10,5,10));
        hBox.getChildren().add(textFlow);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });
    }



    void msgsend()  {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date utilDate = null;
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
                msg.ajouter(new Message(ena, sahbi, tf_message.getText(), sqlDate));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }


}

