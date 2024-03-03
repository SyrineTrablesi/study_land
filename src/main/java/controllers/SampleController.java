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
import services.ServiceMessage;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    int sahbi=1;

    @FXML
    private Button button_emoji;

    @FXML
    private StackPane rootPane;

    @FXML
    private TextField serchbyname;

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

    public boolean findFriendByAge(int age) {
        for (Bublefriends.Friend friend : friendsList) {
            if (friend.getAge() == age) {
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
                    friendsList.add(new Bublefriends.Friend("Alice",message.getId_diss()));
            }else if (message.getId_diss()==ena){
                if(findFriendByAge(message.getId_sender() ))
                    friendsList.add(new Bublefriends.Friend("Alice", message.getId_sender() ));
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
            server = new Server(new ServerSocket(8888));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating server.");
        }


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
            client = new Client(new Socket("localhost", 8888));
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

            Label ageLabel = new Label("Age: " + friend.getAge());

            friendPane.setOnMouseClicked(event -> {
                namePersone.setText(friend.getName()+" "+ friend.getAge());
                System.out.println("Clicked on: " + friend.getName());
                vbox_messages.getChildren().clear();
                sahbi=friend.getAge();
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

