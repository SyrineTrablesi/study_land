package services;

import entities.Achat;
import entities.Favoris;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceAchat implements IAchat {
    private final Connection connection;

    public ServiceAchat() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouterAchat(Achat achat) throws SQLException {
        String req = "INSERT INTO achat (id_user, idFormation, date_achat, facture) VALUES (?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(req);

        pstmt.setInt(1, achat.getId_user());
        pstmt.setInt(2, achat.getId_formation());
        pstmt.setDate(3, Date.valueOf(String.valueOf(achat.getDate_achat())));
        pstmt.setString(4, String.valueOf(achat.getFacture()));

        pstmt.executeUpdate();
    }


    @Override
    public void modifierAchat(Achat achat) throws SQLException {
        String req = "UPDATE achat SET id_user=?, idFormation=?, date_achat=?, facture=? WHERE id_achat=?";
        PreparedStatement pstmt = connection.prepareStatement(req);

        pstmt.setInt(1, achat.getId_user());
        pstmt.setInt(2, achat.getId_formation());
        pstmt.setDate(3, Date.valueOf(achat.getDate_achat()));
        pstmt.setString(4, String.valueOf(achat.getFacture()));
        pstmt.setInt(5, achat.getId_achat());

        pstmt.executeUpdate();
    }

    @Override
    public void supprimerAchat(Achat achat) throws SQLException {
        String req = "DELETE FROM achat WHERE id_achat=?";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, achat.getId_achat());
        pstmt.executeUpdate();
    }
    @Override
    public List<Achat> afficherAchatAdmin() throws SQLException {
        String req = "SELECT * FROM achat INNER JOIN user ON achat.id_user = user.id_user INNER JOIN formation ON achat.idFormation = formation.idFormation";
        return fetchAchat(req);
    }

    @Override
    public List<Achat> afficherAchatUser(int id_user) throws SQLException {
        String req = "SELECT * FROM achat INNER JOIN formation ON achat.idFormation = formation.idFormation WHERE id_user=?";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, id_user);
        return fetchAchat(pstmt.toString());
    }

    private List<Achat> fetchAchat(String req) throws SQLException {
        List<Achat> list = new ArrayList<>();
        try (Statement ste = connection.createStatement();
             ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Achat a = new Achat();
                a.setId_achat(res.getInt("id_achat"));
                a.setId_user(res.getInt("id_user"));
                a.setId_formation(res.getInt("idFormation"));
                a.setDate_achat(res.getDate("date_achat").toLocalDate());
                a.setFacture(Double.parseDouble(res.getString("facture")));
                // Vous pouvez ajouter d'autres attributs hérités de la table Formation ici
                list.add(a);
            }
        }
        return list;
    }
}