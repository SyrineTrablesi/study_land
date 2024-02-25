package services;

import entities.Favoris;
import utils.MyDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceFavoris implements IFavoris {
    private final Connection connection;

    public ServiceFavoris() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouterFavoris(Favoris favoris) throws SQLException {
        String req = "INSERT INTO favoris (id_user, id_formation, date_ajout) VALUES (?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(req);

        pstmt.setInt(1, favoris.getId_user());
        pstmt.setInt(2, favoris.getId_formation());
        pstmt.setDate(3, Date.valueOf(favoris.getDate_ajout()));

        pstmt.executeUpdate();
    }

    @Override
    public void modifierFavoris(Favoris favoris) throws SQLException {
        String req = "UPDATE favoris SET id_user=?, id_formation=?, date_ajout=? WHERE id_favoris=?";
        PreparedStatement pstmt = connection.prepareStatement(req);

        pstmt.setInt(1, favoris.getId_user());
        pstmt.setInt(2, favoris.getId_formation());
        pstmt.setDate(3, Date.valueOf(favoris.getDate_ajout()));
        pstmt.setInt(4, favoris.getId_favoris());

        pstmt.executeUpdate();
    }

    @Override
    public void supprimerFavoris(Favoris favoris) throws SQLException {
        String req = "DELETE FROM favoris WHERE id_favoris=?";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, favoris.getId_favoris());
        pstmt.executeUpdate();
    }

    @Override
    public List<Favoris> afficherFavorisAdmin() throws SQLException {
        String req = "SELECT * FROM favoris INNER JOIN user ON favoris.id_user = user.id_user INNER JOIN formation ON favoris.id_formation = formation.id_formation";
        return fetchFavoris(req);
    }

    @Override
    public List<Favoris> afficherFavorisUser(int idUser) throws SQLException {
        String req = "SELECT * FROM favoris INNER JOIN formation ON favoris.id_formation = formation.id_formation WHERE favoris.id_user=?";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, idUser);
        return fetchFavoris(pstmt.toString());
    }

    private List<Favoris> fetchFavoris(String req) throws SQLException {
        List<Favoris> list = new ArrayList<>();
        try (Statement ste = connection.createStatement();
             ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Favoris f = new Favoris();
                f.setId_favoris(res.getInt("id_favoris"));
                f.setId_user(res.getInt("id_user"));
                f.setId_formation(res.getInt("id_formation"));
                f.setDate_ajout(res.getDate("date_ajout").toLocalDate());
                list.add(f);
            }
        }
        return list;
    }
}