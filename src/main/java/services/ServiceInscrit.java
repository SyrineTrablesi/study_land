package services;

import entities.Inscrit;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceInscrit implements IInscrit<Inscrit>{

    private Connection connection;

    public ServiceInscrit(){
        connection= MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouterInscrit(Inscrit inscrit) throws SQLException {
        String req ="insert into inscrit (id_user,id_formation,id_achat)"+
                "values('"+inscrit.getId_user()+"','"+inscrit.getId_formation()+"',"+inscrit.getId_achat()+")";
        Statement ste= connection.createStatement();

        ste.executeUpdate(req);

    }

    @Override
    public void modifierInscrit(Inscrit inscrit) throws SQLException {
        String req = "update inscrit set id_user=? , id_formation=? , id_achat=? where id_inscrit=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1,inscrit.getId_user());
        pre.setInt(2,inscrit.getId_formation());
        pre.setInt(3,inscrit.getId_achat());
        pre.setInt(4,inscrit.getId_inscrit());

        pre.executeUpdate();

    }

    @Override
    public void supprimerInscrit(Inscrit inscrit) throws SQLException {

        String req = " delete from inscrit where id_inscrit=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1,inscrit.getId_inscrit());
        pre.executeUpdate();


    }

    @Override
    public List<Inscrit> afficherInscrit() throws SQLException {


        String req = "select * from inscrit";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<Inscrit> list =new ArrayList<>();
        while (res.next()){
            Inscrit i = new Inscrit();
            i.setId_inscrit(res.getInt(1));
            i.setId_user(res.getInt(2));
            i.setId_formation(res.getInt(3));
            i.setId_achat(res.getInt(4));

            list.add(i);
        }
        return list;
    }
}
