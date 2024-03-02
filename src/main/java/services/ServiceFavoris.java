package services;

import entities.Favoris;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceFavoris implements IService<Favoris> {

    private final Connection connection;

    public ServiceFavoris() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Favoris favoris) throws SQLException {
        String req = "INSERT INTO favoris (id_user, idFormation, type, dateAjout) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(req);

        pstmt.setInt(1, favoris.getId_user());
        pstmt.setInt(2, favoris.getIdFormation());
        pstmt.setString(3, favoris.getType());
        pstmt.setDate(4, new java.sql.Date(favoris.getDateAjout().getTime()));

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
        String req = "SELECT f.idFavoris, f.id_user, f.idFormation, f.type, f.dateAjout, formation.* " +
                "FROM favoris f INNER JOIN formation ON f.idFormation = formation.idFormation";
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
}
