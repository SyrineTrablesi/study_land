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
        String req = "INSERT INTO favoris (id_panier, id_user, facture, date_achat  ) VALUES ('" + achat.getId_panier() + "','" + achat.getId_user() + "','" + achat.getFacture() +"','" + achat.getDate_achat() + "')";

        Statement ste= connection.createStatement();

        ste.executeUpdate(req);

    }

    @Override
    public void modifierAchat(Achat achat) throws SQLException {

        String req = "update achat set id_user=?, id_panier=?, facture=?,date_achat=? where id_achat=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, achat.getId_user());
        pre.setInt(2, achat.getId_panier());
        pre.setInt(3, achat.getFacture());
        pre.setDate(4, new java.sql.Date(achat.getDate_achat().getTime()));
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
                a.setId_achat(res.getInt(1));
                a.setId_user(res.getInt(2));
                a.setId_panier(res.getInt(3));
                a.setFacture(res.getInt(4));
                a.setDate_achat(res.getDate(5));

                list.add(a);
            }
        }

        return list;
    }
}
