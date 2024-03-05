package services;

import entities.Inscrit;
import entities.User;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceInscrit implements IService<Inscrit> {

    private final Connection connection;

    public ServiceInscrit() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Inscrit inscrit) throws SQLException {
        String req = "INSERT INTO inscrit (id_user, idFormation, dateAjout) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, inscrit.getId_user());
        pstmt.setInt(2, inscrit.getIdFormation());

        // Définir la date actuelle pour le paramètre dateAjout
        pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        pstmt.executeUpdate();
    }

    @Override
    public void modifier(Inscrit inscrit) throws SQLException {
        String req = "UPDATE inscrit SET id_user=?, idFormation=?, type=?, dateAjout=? WHERE idInscrit=?";
        PreparedStatement pstmt = connection.prepareStatement(req);

        pstmt.setInt(1, inscrit.getId_user());
        pstmt.setInt(2, inscrit.getIdFormation());
        pstmt.setString(3, inscrit.getType());
        pstmt.setDate(4, new java.sql.Date(inscrit.getDateAjout().getTime()));
        pstmt.setInt(5, inscrit.getIdInscrit());

        pstmt.executeUpdate();
    }

    @Override
    public void supprimer(Inscrit inscrit) throws SQLException {
        String req = "DELETE FROM inscrit WHERE idInscrit=?";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, inscrit.getIdInscrit());
        pstmt.executeUpdate();
    }

    @Override
    public List<Inscrit> afficher() throws SQLException {
        String req = "SELECT i.idInscrit, i.id_user, i.idFormation, i.type, i.dateAjout, f.* " +
                "FROM inscrit i INNER JOIN formation f ON i.idFormation = f.idFormation";
        PreparedStatement pre = connection.prepareStatement(req);
        ResultSet resultSet = pre.executeQuery();
        List<Inscrit> inscrits = new ArrayList<>();
        while (resultSet.next()) {
            Inscrit inscrit = new Inscrit();
            inscrit.setIdInscrit(resultSet.getInt("idInscrit"));
            inscrit.setId_user(resultSet.getInt("id_user"));
            inscrit.setIdFormation(resultSet.getInt("idFormation"));
            inscrit.setType(resultSet.getString("type"));
            inscrit.setDateAjout(resultSet.getDate("dateAjout"));

            // Récupération des données de la formation
            inscrit.setTitre(resultSet.getString("titre"));
            inscrit.setDescription(resultSet.getString("description"));
            inscrit.setDuree(resultSet.getInt("duree"));
            inscrit.setDateDebut(resultSet.getDate("dateDebut"));
            inscrit.setDateFin(resultSet.getDate("dateFin"));
            inscrit.setPrix(resultSet.getFloat("prix"));
            inscrit.setNiveau(resultSet.getString("niveau"));
            inscrit.setNomCategorie(resultSet.getString("nomCategorie"));

            inscrits.add(inscrit);
        }
        return inscrits;
    }

    public List<Inscrit> afficherbyUser(User user) throws SQLException {
        String req = "SELECT i.idInscrit, i.id_user, i.idFormation, i.type, i.dateAjout, f.* " +
                "FROM inscrit i INNER JOIN formation f ON i.idFormation = f.idFormation " +
                "WHERE i.id_user = ?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, user.getId()); // Remplacer user.getId() par la méthode appropriée pour obtenir l'ID de l'utilisateur
        ResultSet resultSet = pre.executeQuery();
        List<Inscrit> inscrits = new ArrayList<>();
        while (resultSet.next()) {
            Inscrit inscrit = new Inscrit();
            inscrit.setIdInscrit(resultSet.getInt("idInscrit"));
            inscrit.setId_user(resultSet.getInt("id_user"));
            inscrit.setIdFormation(resultSet.getInt("idFormation"));
            inscrit.setType(resultSet.getString("type"));
            inscrit.setDateAjout(resultSet.getDate("dateAjout"));

            // Récupération des données de la formation
            inscrit.setTitre(resultSet.getString("titre"));
            inscrit.setDescription(resultSet.getString("description"));
            inscrit.setDuree(resultSet.getInt("duree"));
            inscrit.setDateDebut(resultSet.getDate("dateDebut"));
            inscrit.setDateFin(resultSet.getDate("dateFin"));
            inscrit.setPrix(resultSet.getFloat("prix"));
            inscrit.setNiveau(resultSet.getString("niveau"));
            inscrit.setNomCategorie(resultSet.getString("nomCategorie"));

            inscrits.add(inscrit);
        }
        return inscrits;
    }

}