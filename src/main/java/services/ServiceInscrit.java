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
        String req = "INSERT INTO inscrit (id_user, id_panier, id_formation, id_achat, inscription_date) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1, inscrit.getId_user());
        pstmt.setInt(2, inscrit.getId_panier());
        pstmt.setInt(3, inscrit.getId_formation());
        pstmt.setInt(4, inscrit.getId_achat());
        pstmt.setDate(5, new java.sql.Date(inscrit.getInscription_date().getTime()));
        pstmt.executeUpdate();

    }

    @Override
    public void modifierInscrit(Inscrit inscrit) throws SQLException {

        // 1. Construct a secure and parameterized SQL query:
        String sql = "UPDATE inscrit SET id_panier=?, id_user=?, id_formation=?, id_achat=?, inscription_date=? WHERE id_inscrit=?";

        // 2. Prepare the statement with placeholders for values:
        PreparedStatement statement = connection.prepareStatement(sql);

        // 3. Bind values to placeholders using appropriate methods:
        statement.setInt(1, inscrit.getId_panier());
        statement.setInt(2, inscrit.getId_user());
        statement.setInt(3, inscrit.getId_formation());
        statement.setInt(4, inscrit.getId_achat());
        statement.setDate(5, new java.sql.Date(inscrit.getInscription_date().getTime())); // Convert to SQL-compatible date
        statement.setInt(6, inscrit.getId_inscrit()); // Use getInt() for id retrieval

        // 4. Execute the update query:
        statement.executeUpdate();

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
            i.setId_inscrit(res.getInt("id_inscrit"));
            i.setId_user(res.getInt("id_user"));
            i.setId_panier(res.getInt("id_panier"));
            i.setId_formation(res.getInt("id_formation"));
            i.setId_achat(res.getInt("id_achat"));
            i.setInscription_date(res.getDate("inscription_date"));

            list.add(i);
        }
        return list;
    }
}
