package services;

import entities.Achat;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceAchat implements IAchat<Achat> {


    private Connection connection;
    public ServiceAchat(){
        connection= MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouterAchat(Achat achat) throws SQLException {
        String req = "INSERT INTO achat (id_panier, id_user, facture, date_achat) VALUES (?, ?, ?, ?)";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, achat.getId_panier());
        pre.setInt(2, achat.getId_user());
        pre.setDouble(3, achat.getFacture());
        pre.setDate(4, new java.sql.Date(achat.getDate_achat().getTime())); // Utilisation de setDate pour la date

        pre.executeUpdate();
    }

    @Override
    public void modifierAchat(Achat achat) throws SQLException {

        String req = "update achat set id_user=?, id_panier=?, facture=?,date_achat=? where id_achat=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, achat.getId_user());
        pre.setInt(2, achat.getId_panier());
        pre.setDouble(3, achat.getFacture());
        pre.setDate(4, new java.sql.Date(achat.getDate_achat().getTime())); // Utilisation de setDate pour la date
        pre.setInt(5, achat.getId_achat());

        pre.executeUpdate();
    }

    @Override
    public void supprimerAchat(Achat achat) throws SQLException {

        String req = " delete from achat where id_achat=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1,achat.getId_achat());
        pre.executeUpdate();

    }

    @Override
    public List<Achat> afficherAchat() throws SQLException {

        String req = "select * from achat";
        List<Achat> list = new ArrayList<>();

        try (Statement ste = connection.createStatement();
             ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Achat a = new Achat();
                a.setId_achat(res.getInt("id_achat"));
                a.setId_user(res.getInt("id_user"));
                a.setId_panier(res.getInt("id_panier"));
                a.setFacture(res.getInt("facture"));
                a.setDate_achat(res.getDate("date_achat"));

                list.add(a);
            }
        }

        return list;
    }
// calculer facture
/*
    public void calculerFacture(Achat achat) throws SQLException {
        String req = "SELECT SUM(prix) AS total_facture FROM panier  WHERE id_user = ?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, achat.getId_panier());

        ResultSet res = pre.executeQuery();
        if (res.next()) {
            achat.setFacture(res.getInt("total_facture"));
        }
    }
    */
}
