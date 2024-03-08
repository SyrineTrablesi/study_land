package controllers;

import entities.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import services.ServiceMessage;

import java.sql.SQLException;
import java.util.List;

public class ListeController {

    @FXML
    private TextField tf_search; // Déclaration de tf_search

    @FXML
    private ListView<String> listViewMessages;

    private ServiceMessage serviceMessage;

    public void initialize() {
        serviceMessage = new ServiceMessage();
        afficherListeMessages();
    }

    private void afficherListeMessages() {
        try {
            List<Message> messages = serviceMessage.afficher();
            ObservableList<String> messageStrings = FXCollections.observableArrayList();
            for (Message message : messages) {
                String messageString = String.format("ID: %d | Message: %s | Date: %s | Sender ID: %d | Diss ID: %d",
                        message.getId_message(), message.getMessage(), message.getDate().toString(),
                        message.getId_sender(), message.getId_diss());
                messageStrings.add(messageString);
            }
            listViewMessages.setItems(messageStrings);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchMessages(ActionEvent event) {
        // Obtenir le terme de recherche depuis l'interface utilisateur
        String searchTerm = tf_search.getText().trim().toLowerCase();
        if (!searchTerm.isEmpty()) {
            // Créer une liste temporaire pour stocker les résultats de la recherche
            ObservableList<String> searchResults = FXCollections.observableArrayList();
            // Parcourir la liste actuelle des messages
            for (String message : listViewMessages.getItems()) {
                // Vérifier si le message contient le terme de recherche
                if (message.toLowerCase().contains(searchTerm)) {
                    // Ajouter le message correspondant aux résultats de la recherche
                    searchResults.add(message);
                }
            }
            // Afficher les résultats de la recherche dans la ListView
            listViewMessages.setItems(searchResults);
        } else {
            // Si le champ de recherche est vide, afficher tous les messages
            afficherListeMessages();
        }
    }

}
