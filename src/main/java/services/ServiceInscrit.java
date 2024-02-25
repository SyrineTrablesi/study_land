package services;

import entities.Inscrit;
import entities.Panier;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceInscrit implements IInscrit< Inscrit> {
    private  Connection connection;

    public ServiceInscrit() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouterInscrit(Inscrit inscrit) throws SQLException {
        String req = "INSERT INTO inscrit (id_user, idFormation, inscription_date) VALUES (?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(req);

        pstmt.setInt(1, inscrit.getId_user());
        pstmt.setInt(2, inscrit.getIdFormation());
        pstmt.setDate(3, Date.valueOf(inscrit.getInscription_date()));

        pstmt.executeUpdate();
    }

    @Override
    public void modifierInscrit(Inscrit inscrit) throws SQLException {
        String req = "UPDATE inscrit SET id_user=?, idFormation=?, inscription_date=? WHERE id_inscrit=?";
        PreparedStatement pstmt = connection.prepareStatement(req);

        pstmt.setInt(1, inscrit.getId_user());
        pstmt.setInt(2, inscrit.getIdFormation());
        pstmt.setDate(3, Date.valueOf(inscrit.getInscription_date()));
        pstmt.setInt(4, inscrit.getId_inscrit());

        pstmt.executeUpdate();
    }

    public void supprimerInscrit(Inscrit inscrit) throws SQLException {
        String req = "DELETE FROM inscrit WHERE id_inscrit=?";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, inscrit.getId_inscrit());
        pstmt.executeUpdate();
    }

    @Override
    public List<Inscrit> afficherInscritAdmin() throws SQLException {
        String req = "SELECT * FROM inscrit INNER JOIN formation ON inscrit.idFormation = formation.idFormation INNER JOIN user ON inscrit.id_user = user.id_user";
        return fetchInscrit(req);
    }

    @Override
    public List<Inscrit> afficherInscritUser(int id_user) throws SQLException {
        String req = "SELECT * FROM inscrit INNER JOIN formation ON inscrit.idFormation = formation.idFormation WHERE id_user=?";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, id_user);
        return fetchInscrit(pstmt.toString());
    }

    private List<Inscrit> fetchInscrit(String req) throws SQLException {
        List<Inscrit> list = new ArrayList<>();
        try (Statement ste = connection.createStatement();
             ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Inscrit inscrit = new Inscrit();
                inscrit.setId_inscrit(res.getInt("id_inscrit"));
                inscrit.setId_user(res.getInt("id_user"));
                inscrit.setIdFormation(res.getInt("idFormation"));
                inscrit.setInscription_date(res.getDate("inscription_date").toLocalDate());
                // Ajouter les autres attributs hérités de la table Formation ici
                list.add(inscrit);
            }
        }
        return list;
    }
}