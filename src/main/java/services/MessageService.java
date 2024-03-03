package services;

import entities.Message;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageService {

    // Méthode pour récupérer tous les messages depuis la base de données
    public List<Message> getAllMessages() throws SQLException {
        List<Message> messages = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Établir la connexion à la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studyland", "votre_utilisateur", "votre_mot_de_passe");

            // Créer l'instruction SQL pour sélectionner tous les messages
            String sql = "SELECT * FROM message";

            // Créer l'objet Statement
            statement = connection.createStatement();

            // Exécuter la requête SQL
            resultSet = statement.executeQuery(sql);

            // Parcourir le résultat et ajouter chaque message à la liste
            while (resultSet.next()) {
                int id_message = resultSet.getInt("id_message");
                int id_sender = resultSet.getInt("id_sender");
                int id_diss = resultSet.getInt("id_diss");
                String messageText = resultSet.getString("message");
                Date date = resultSet.getDate("date");

                // Créer un objet Message et l'ajouter à la liste
                Message message = new Message(id_message, id_sender, id_diss, messageText, date);
                messages.add(message);
            }
        } finally {
            // Fermer les ressources JDBC dans un bloc finally pour s'assurer que la connexion est toujours fermée
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return messages;
    }
}
