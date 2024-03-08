package services;

import entities.Favoris;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class ServiceFavoris implements IService<Favoris> {

    private final Connection connection;

    public ServiceFavoris() {
        connection = MyDB.getInstance().getConnection();
    }


    @Override
    public void ajouter(Favoris favoris) throws SQLException {
        String req = "INSERT INTO favoris (id_user, idFormation,dateAjout) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, favoris.getId_user());
        pstmt.setInt(2, favoris.getIdFormation());

        // Définir la date actuelle pour le paramètre dateAjout
        pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

        pstmt.executeUpdate();
    }

    @Override
    public void modifier(Favoris favoris) throws SQLException {
        String req = "UPDATE favoris SET id_user=?, idFormation=?, type=?, dateAjout=? WHERE idFavoris=?";
        PreparedStatement pstmt = connection.prepareStatement(req);

        pstmt.setInt(1, favoris.getId_user());
        pstmt.setInt(2, favoris.getIdFormation());
        pstmt.setString(3, favoris.getType());
        pstmt.setDate(4, new java.sql.Date(favoris.getDateAjout().getTime()));
        pstmt.setInt(5, favoris.getIdFavoris());

        pstmt.executeUpdate();
    }

    @Override
    public void supprimer(Favoris favoris) throws SQLException {
        String req = "DELETE FROM favoris WHERE idFavoris=?";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, favoris.getIdFavoris());
        pstmt.executeUpdate();
    }

    @Override
    public List<Favoris> afficher() throws SQLException {
        String req = "SELECT i.idFavoris, i.id_user, i.idFormation, i.type, i.dateAjout, f.* " +
                "FROM favoris i INNER JOIN formation f ON i.idFormation = f.idFormation";
        PreparedStatement pre = connection.prepareStatement(req);
        ResultSet resultSet = pre.executeQuery();
        List<Favoris> favorisList = new ArrayList<>();
        while (resultSet.next()) {
            Favoris favoris = new Favoris();
            favoris.setIdFavoris(resultSet.getInt("idFavoris"));
            favoris.setId_user(resultSet.getInt("id_user"));
            favoris.setIdFormation(resultSet.getInt("idFormation"));
            favoris.setType(resultSet.getString("type"));
            favoris.setDateAjout(resultSet.getDate("dateAjout"));

            // Récupération des données de la formation
            favoris.setTitre(resultSet.getString("titre"));
            favoris.setDescription(resultSet.getString("description"));
            favoris.setDuree(resultSet.getInt("duree"));
            favoris.setDateDebut(resultSet.getDate("dateDebut"));
            favoris.setDateFin(resultSet.getDate("dateFin"));
            favoris.setPrix(resultSet.getFloat("prix"));
            favoris.setNiveau(resultSet.getString("niveau"));
            favoris.setNomCategorie(resultSet.getString("nomCategorie"));

            favorisList.add(favoris);
        }
        return favorisList;
    }

    public List<Favoris> afficherbyUser(User user) throws SQLException {
        String req = "SELECT i.idFavoris, i.id_user, i.idFormation, i.type, i.dateAjout, f.* " +
                "FROM favoris i INNER JOIN formation f ON i.idFormation = f.idFormation " +
                "WHERE i.id_user = ?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, user.getId()); // Remplacer user.getId() par la méthode appropriée pour obtenir l'ID de l'utilisateur
        ResultSet resultSet = pre.executeQuery();
        List<Favoris> favorisList = new ArrayList<>();
        while (resultSet.next()) {
            Favoris favoris = new Favoris();
            favoris.setIdFavoris(resultSet.getInt("idFavoris"));
            favoris.setId_user(resultSet.getInt("id_user"));
            favoris.setIdFormation(resultSet.getInt("idFormation"));
            favoris.setType(resultSet.getString("type"));
            favoris.setDateAjout(resultSet.getDate("dateAjout"));

            // Récupération des données de la formation
            favoris.setTitre(resultSet.getString("titre"));
            favoris.setDescription(resultSet.getString("description"));
            favoris.setDuree(resultSet.getInt("duree"));
            favoris.setDateDebut(resultSet.getDate("dateDebut"));
            favoris.setDateFin(resultSet.getDate("dateFin"));
            favoris.setPrix(resultSet.getFloat("prix"));
            favoris.setNiveau(resultSet.getString("niveau"));
            favoris.setNomCategorie(resultSet.getString("nomCategorie"));

            favorisList.add(favoris);
        }
        return favorisList;
    }


    public List<Favoris> rechercherParTitre(String titre) throws SQLException {
        String req = "SELECT i.idFavoris, i.id_user, i.idFormation, i.type, i.dateAjout, f.* " +
                "FROM favoris i INNER JOIN formation f ON i.idFormation = f.idFormation " +
                "WHERE f.titre LIKE ?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, "%" + titre + "%"); // Utilisez le titre fourni avec des jokers % pour rechercher des correspondances partielles
        ResultSet resultSet = pre.executeQuery();
        List<Favoris> favorisList = new ArrayList<>();
        while (resultSet.next()) {
            Favoris favoris = new Favoris();
            favoris.setIdFavoris(resultSet.getInt("idFavoris"));
            favoris.setId_user(resultSet.getInt("id_user"));
            favoris.setIdFormation(resultSet.getInt("idFormation"));
            favoris.setType(resultSet.getString("type"));
            favoris.setDateAjout(resultSet.getDate("dateAjout"));

            // Récupération des données de la formation
            favoris.setTitre(resultSet.getString("titre"));
            favoris.setDescription(resultSet.getString("description"));
            favoris.setDuree(resultSet.getInt("duree"));
            favoris.setDateDebut(resultSet.getDate("dateDebut"));
            favoris.setDateFin(resultSet.getDate("dateFin"));
            favoris.setPrix(resultSet.getFloat("prix"));
            favoris.setNiveau(resultSet.getString("niveau"));
            favoris.setNomCategorie(resultSet.getString("nomCategorie"));

            favorisList.add(favoris);
        }
        return favorisList;
    }


    public boolean formationExisteDeja(Favoris favoris) throws SQLException {
        String req = "SELECT COUNT(*) AS count FROM favoris WHERE id_user = ? AND idFormation = ?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, favoris.getId_user());
        pre.setInt(2, favoris.getIdFormation());
        ResultSet resultSet = pre.executeQuery();

        // Si le résultat de la requête retourne un nombre supérieur à zéro, cela signifie que la formation existe déjà dans les favoris
        if (resultSet.next()) {
            int count = resultSet.getInt("count");
            return count > 0;
        } else {
            return false;
        }
    }



}