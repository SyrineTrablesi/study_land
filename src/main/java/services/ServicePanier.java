package services;

import entities.Panier;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePanier implements IPanier<Panier>{

    private final Connection connection;

    public ServicePanier() {
        connection = MyDB.getInstance().getConnection();
    }

    public void ajouterPanier(Panier panier) throws SQLException {
        String req = "INSERT INTO panier (id_user, idFormation, date_ajout, typeFormation) VALUES (?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(req);

        pstmt.setInt(1, panier.getId_user());
        pstmt.setInt(2, panier.getId_formation());
        pstmt.setDate(3, Date.valueOf(panier.getDate_ajout()));
        pstmt.setString(4, panier.getTypeFormation());

        pstmt.executeUpdate();
    }

    public void modifierPanier(Panier panier) throws SQLException {
        String req = "UPDATE panier SET id_user=?, idFormation=?, date_ajout=?, typeFormation=? WHERE id_panier=?";
        PreparedStatement pstmt = connection.prepareStatement(req);

        pstmt.setInt(1, panier.getId_user());
        pstmt.setInt(2, panier.getId_formation());
        pstmt.setDate(3, Date.valueOf(panier.getDate_ajout()));
        pstmt.setString(4, panier.getTypeFormation());
        pstmt.setInt(5, panier.getId_panier());

        pstmt.executeUpdate();
    }

    public void supprimerPanier(Panier panier) throws SQLException {
        String req = "DELETE FROM panier WHERE id_panier=?";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, panier.getId_panier());
        pstmt.executeUpdate();
    }

    public List<Panier> afficherPanierAdmin() throws SQLException {
        String req = "SELECT * FROM panier INNER JOIN user ON panier.id_user = user.id_user INNER JOIN formation ON panier.idFormation = formation.idFormation";
        return fetchPaniers(req);
    }

    public List<Panier> afficherPanierUser(int idUser) throws SQLException {
        String req = "SELECT * FROM panier INNER JOIN formation ON panier.idFormation = formation.idFormation WHERE id_user=?";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, idUser);
        return fetchPaniers(pstmt.toString());
    }

    private List<Panier> fetchPaniers(String req) throws SQLException {
        List<Panier> list = new ArrayList<>();
        try (Statement ste = connection.createStatement();
             ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Panier p = new Panier();
                p.setId_panier(res.getInt("id_panier"));
                p.setId_user(res.getInt("id_user"));
                p.setId_formation(res.getInt("idFormation"));
                p.setDate_ajout(res.getDate("date_ajout").toLocalDate());
                p.setTypeFormation(res.getString("typeFormation"));

                // Informations héritées de la table Formation
                p.setIdFormation(res.getInt("idFormation"));
                p.setTitre(res.getString("titre"));
                p.setDescription(res.getString("description"));
                p.setDuree(res.getInt("duree"));
                p.setDateDebut(res.getDate("dateDebut").toLocalDate());
                p.setDateFin(res.getDate("dateFin").toLocalDate());
                p.setPrix(res.getDouble("prix"));
                p.setNiveau(res.getString("niveau"));
                p.setNomCategorie(res.getString("nomCategorie"));

                list.add(p);
            }
        }
        return list;
    }
}