package services;

import entities.Achat;
import entities.User;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceAchat implements IService<Achat> {

    private final Connection connection;

    public ServiceAchat() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Achat achat) throws SQLException {
        String req = "INSERT INTO achat (id_user, idFormation, dateAjout) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, achat.getId_user());
        pstmt.setInt(2, achat.getIdFormation());

        // Définir la date actuelle pour le paramètre dateAjout
        pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        pstmt.executeUpdate();
    }

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

}