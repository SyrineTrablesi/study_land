package services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import entities.Achat;
import entities.User;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceAchat implements IService<Achat> {

    private final Connection connection;

    public ServiceAchat() {
        connection = MyDB.getInstance().getConnection();
    }


    @Override
    public void ajouter(Achat achat) throws SQLException {
        // Vérifier si l'objet Achat contient une valeur pour idFormation
        String req = "INSERT INTO achat (id_user, idFormation, dateAjout, cardNumber, exprMonth, exprYear, cvv) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, achat.getId_user());
        pstmt.setInt(2, achat.getIdFormation()); // Utilisation de getIdFormation() au lieu de getIdFormation()
        pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        pstmt.setString(4, achat.getCardNumber());
        pstmt.setString(5, achat.getExprMonth());
        pstmt.setString(6, achat.getExprYear());
        pstmt.setString(7, achat.getCvv());
        pstmt.executeUpdate();

        // Effectuer le paiement après l'ajout de l'achat
        effectuerPaiement(achat);
    }
//    @Override
//    public void ajouter(Achat achat) throws SQLException {
//        String req = "INSERT INTO achat (id_user, idFormation, dateAjout) VALUES (?, ?, ?)";
//        PreparedStatement pstmt = connection.prepareStatement(req);
//        pstmt.setInt(1, achat.getId_user());
//        pstmt.setInt(2, achat.getIdFormation());
//
//        // Définir la date actuelle pour le paramètre dateAjout
//        pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
//        pstmt.executeUpdate();
//
//        // Effectuer le paiement après l'ajout de l'achat
//        effectuerPaiement(achat);
//    }

    @Override
    public void modifier(Achat achat) throws SQLException {
        String req = "UPDATE achat SET id_user=?, idFormation=?, dateAjout=? WHERE idAchat=?";
        PreparedStatement pstmt = connection.prepareStatement(req);

        pstmt.setInt(1, achat.getId_user());
        pstmt.setInt(2, achat.getIdFormation());
        pstmt.setDate(3, new java.sql.Date(achat.getDateAjout().getTime()));
        pstmt.setInt(4, achat.getIdAchat());

        pstmt.executeUpdate();
    }

    @Override
    public void supprimer(Achat achat) throws SQLException {
        String req = "DELETE FROM achat WHERE idAchat=?";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, achat.getIdAchat());
        pstmt.executeUpdate();
    }

    @Override
    public List<Achat> afficher() throws SQLException {
        String req = "SELECT i.idAchat, i.id_user, i.idFormation, i.dateAjout, f.* " +
                "FROM achat i INNER JOIN formation f ON i.idFormation = f.idFormation";
        PreparedStatement pre = connection.prepareStatement(req);
        ResultSet resultSet = pre.executeQuery();
        List<Achat> achats = new ArrayList<>();
        while (resultSet.next()) {
            Achat achat = new Achat();
            achat.setIdAchat(resultSet.getInt("idAchat"));
            achat.setId_user(resultSet.getInt("id_user"));
            achat.setIdFormation(resultSet.getInt("idFormation"));
            achat.setDateAjout(resultSet.getDate("dateAjout"));

            // Récupération des données de la formation
            achat.setTitre(resultSet.getString("titre"));
            achat.setDescription(resultSet.getString("description"));
            achat.setDuree(resultSet.getInt("duree"));
            achat.setDateDebut(resultSet.getDate("dateDebut"));
            achat.setDateFin(resultSet.getDate("dateFin"));
            achat.setPrix(resultSet.getFloat("prix"));
            achat.setNiveau(resultSet.getString("niveau"));
            achat.setNomCategorie(resultSet.getString("nomCategorie"));

            achats.add(achat);
        }
        return achats;
    }

    public List<Achat> afficherbyUser(User user) throws SQLException {
        String req = "SELECT i.idAchat, i.id_user, i.idFormation,  i.dateAjout, f.* " +
                "FROM achat i INNER JOIN formation f ON i.idFormation = f.idFormation " +
                "WHERE i.id_user = ?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, user.getId()); // Remplacer user.getId() par la méthode appropriée pour obtenir l'ID de l'utilisateur
        ResultSet resultSet = pre.executeQuery();
        List<Achat> achats = new ArrayList<>();
        while (resultSet.next()) {
            Achat achat = new Achat();
            achat.setIdAchat(resultSet.getInt("idAchat"));
            achat.setId_user(resultSet.getInt("id_user"));
            achat.setIdFormation(resultSet.getInt("idFormation"));
            achat.setDateAjout(resultSet.getDate("dateAjout"));

            // Récupération des données de la formation
            achat.setTitre(resultSet.getString("titre"));
            achat.setDescription(resultSet.getString("description"));
            achat.setDuree(resultSet.getInt("duree"));
            achat.setDateDebut(resultSet.getDate("dateDebut"));
            achat.setDateFin(resultSet.getDate("dateFin"));
            achat.setPrix(resultSet.getFloat("prix"));
            achat.setNiveau(resultSet.getString("niveau"));
            achat.setNomCategorie(resultSet.getString("nomCategorie"));

            achats.add(achat);
        }
        return achats;
    }

    public void effectuerPaiement(Achat achat) throws SQLException {
        // Créez un objet Stripe Charge avec les informations de la carte et le prix total
        Stripe.apiKey = "sk_test_51OrKaRDOYVI5uEqTE8p6z8idDW3U03DPu84cAFKM3xJ7TadBHNE55nyAe1xrJBORV8saCYHBO1g57iPtJyk3f83c00t4i6bYvJ";
        Map<String, Object> chargeParams = new HashMap<>();
        int price = Math.round(achat.getPrix() * 100 / 10) * 10; // convertir en cents
        chargeParams.put("amount", price);
        chargeParams.put("currency", "usd");
        chargeParams.put("description", "Achat de formation");
        chargeParams.put("source", createCardToken(achat.getCardNumber(), achat.getExprMonth(), achat.getExprYear(), achat.getCvv()));
        try {
            Charge.create(chargeParams);
        } catch (StripeException e) {
            throw new SQLException("Erreur lors du paiement.");
        }
    }

    private String createCardToken(String cardNumber, String expMonth, String expYear, String cvv) throws SQLException {
        try {
            Map<String, Object> cardParams = new HashMap<>();
            cardParams.put("number", cardNumber);
            cardParams.put("exp_month", expMonth);
            cardParams.put("exp_year", expYear);
            cardParams.put("cvc", cvv);

            Map<String, Object> tokenParams = new HashMap<>();
            tokenParams.put("card", cardParams);
            Token token = Token.create(tokenParams);
            return token.getId();
        } catch (StripeException e) {
            throw new SQLException("Erreur lors de la création du jeton de carte: " + e.getMessage());
        }
    }
}
