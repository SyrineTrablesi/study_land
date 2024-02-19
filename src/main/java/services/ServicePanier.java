package services;

import entities.Panier;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static entities.Panier.type_formation;

public class ServicePanier implements IPanier<Panier> {

    private final Connection connection;

    public ServicePanier(){
        connection= MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouterPanier(Panier panier) throws SQLException {


        String req = "INSERT INTO panier (id_user, id_formation, date_ajout, TypeFormation) VALUES (?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(req);

// Remplacement des paramètres de l'instruction préparée avec les valeurs réelles
        pstmt.setInt(1, panier.getId_user());
        pstmt.setInt(2, panier.getId_formation());
        pstmt.setDate(3, panier.getDate_ajout());
        pstmt.setString(4, panier.getTypeFormation().toString());
        pstmt.setString(4, String.valueOf(panier.getTypeFormation()));
        //pstmt.setArray(5, (Array) panier.getFavorisList());
       // pstmt.setArray(5, (Array) panier.getInscritList());

// Exécution de la requête
        pstmt.executeUpdate();
    }

    @Override
    public void modifierPanier(Panier panier) throws SQLException {
        String req = "update panier set id_user=?, id_formation=?, date_ajout=? ,typeFormation=? where id_panier=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, panier.getId_user());
        pre.setInt(2, panier.getId_formation());
        pre.setDate(3, new java.sql.Date(panier.getDate_ajout().getTime()));
        pre.setString(4, String.valueOf(panier.getTypeFormation()));

        //pre.setArray(5, (Array) panier.getFavorisList());
        //pre.setArray(6, (Array) panier.getInscritList());
        pre.setInt(5, panier.getId_panier()); // Définition du sixième paramètre

        pre.executeUpdate();

        }



    @Override
    public void supprimerPanier(Panier panier) throws SQLException {

        String req = " delete from panier where id_panier=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1,panier.getId_panier());
        pre.executeUpdate();

    }


    @Override
    public List<Panier> afficherPanier() throws SQLException {
        String req = "select * from panier";
        List<Panier> list = new ArrayList<>();

        try (Statement ste = connection.createStatement();
             ResultSet res = ste.executeQuery(req)) {
            while (res.next()) {
                Panier p = new Panier();
                p.setId_panier(res.getInt("id_panier"));
                p.setId_user(res.getInt("id_user"));
                p.setId_formation(res.getInt("id_formation"));
                p.setDate_ajout(res.getDate("date_ajout"));
                p.setTypeFormation(type_formation);

                //p.setFavorisList((List<Favoris>) res.getArray(5));
                //p.setInscritList((List<Inscrit>) res.getArray(6));
                list.add(p);
            }

        } // Les ressources seront fermées automatiquement ici

        return list;
    }

}
