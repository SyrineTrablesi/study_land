package services;

import entities.Favoris;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceFavoris implements IFavoris<Favoris> {

    private Connection connection;

    public ServiceFavoris(){
        connection= MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouterFavoris(Favoris favoris) throws SQLException {
        String req = "INSERT INTO favoris (id_favoris, id_panier) VALUES ('" + favoris.getId_favoris() + "','" + favoris.getId_panier() + "')";

        Statement ste= connection.createStatement();

        ste.executeUpdate(req);


    }

    @Override
    public void modifieFavoris(Favoris favoris) throws SQLException {
        String req = "update favoris set  id_panier=? where id_favoris=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1,favoris.getId_favoris());
        pre.setInt(2,favoris.getId_panier());

        pre.executeUpdate();

    }

    @Override
    public void supprimerFavoris(Favoris favoris) throws SQLException {
        String req = " delete from favoris where id_favoris=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1,favoris.getId_favoris());
        pre.executeUpdate();



    }

    @Override
    public List<Favoris> afficherFavoris() throws SQLException {

        String req = "select * from favoris";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<Favoris> list =new ArrayList<>();
        while (res.next()){
            Favoris f = new Favoris();
            f.setId_favoris(res.getInt(1));
            f.setId_panier(res.getInt(2));

            list.add(f);
        }
        return list;
    }


}

