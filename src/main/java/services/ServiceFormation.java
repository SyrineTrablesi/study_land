package services;

import entities.Formation;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ServiceFormation implements IService<Formation> {

    private Connection connection ;
    public ServiceFormation(){
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Formation formation) throws SQLException {
        String req = "INSERT INTO formation(nomCategorie,titre, description, duree, dateDebut, dateFin, prix, niveau) VALUES (?, ?, ?, ?, ?, ?,?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1, formation.getNomCategorie());
        preparedStatement.setString(2, formation.getTitre());
        preparedStatement.setString(3, formation.getDescription());
        preparedStatement.setInt(4, formation.getDuree());
        preparedStatement.setDate(5, new java.sql.Date(formation.getDateDebut().getTime()));
        preparedStatement.setDate(6, new java.sql.Date(formation.getDateFin().getTime()));
        preparedStatement.setFloat(7, formation.getPrix());
        preparedStatement.setString(8, formation.getNiveau());

        preparedStatement.executeUpdate();
    }

    @Override
    public void modifier(Formation formation) throws SQLException {
        String req = "UPDATE formation SET titre=?, description=?, duree=?, dateDebut=?, dateFin=?, prix=?, niveau=? WHERE idFormation=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setString(1, formation.getTitre());
            pre.setString(2, formation.getDescription());
            pre.setInt(3, formation.getDuree());
            pre.setDate(4, new java.sql.Date(formation.getDateDebut().getTime()));
            pre.setDate(5, new java.sql.Date(formation.getDateFin().getTime()));
            pre.setFloat(6, formation.getPrix());
            pre.setString(7, formation.getNiveau());
            pre.setInt(8, formation.getIdFormation());
            pre.executeUpdate();
        }
    }

    @Override
    public void supprimer(Formation formation) throws SQLException {
        String req ="delete from formation where idFormation=?";
        PreparedStatement pre =connection.prepareStatement(req);
        pre.setInt(1,formation.getIdFormation());
        pre.executeUpdate();
    }

    @Override
    public List<Formation> afficher() throws SQLException {
        String req = "SELECT * FROM formation";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<Formation> list = new ArrayList<>();

        int rowCount = 0; // Variable to track the number of rows processed

        while (res.next()) {
            Formation f = new Formation();
            f.setIdFormation(res.getInt("idFormation"));
            f.setTitre(res.getString("titre"));
            f.setDescription(res.getString("description"));
            f.setDuree(res.getInt("duree"));
            f.setDateDebut(res.getDate("dateDebut"));
            f.setDateFin(res.getDate("dateFin"));
            f.setPrix(res.getFloat("prix"));
            f.setNiveau(res.getString("niveau"));
            list.add(f);

            // Print the current row being processed
            System.out.println("Row " + (++rowCount) + ": " + f.toString() + "\n");
        }

        System.out.println("Total rows processed: " + rowCount); // Print total rows processed
        return list;
    }


    public static class TitreComparator implements Comparator<Formation> {
        @Override
        public int compare(Formation f1, Formation f2) {
            return f1.getTitre().compareTo(f2.getTitre());
        }
    }
    public Formation rechercherParId(int id) throws SQLException {
        String req = "SELECT * FROM formation WHERE idFormation = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // If a result is found, construct a Formation object and return it
            Formation formation = new Formation();
            formation.setIdFormation(resultSet.getInt("idFormation"));
            formation.setTitre(resultSet.getString("titre"));
            formation.setDescription(resultSet.getString("description"));
            formation.setDuree(resultSet.getInt("duree"));
            formation.setDateDebut(resultSet.getDate("dateDebut"));
            formation.setDateFin(resultSet.getDate("dateFin"));
            formation.setPrix(resultSet.getFloat("prix"));
            formation.setNiveau(resultSet.getString("niveau"));
            return formation;
        } else {
            // If no result is found, return null or throw an exception
            return null;
            // Or throw new IllegalArgumentException("Formation with ID " + id + " not found");
        }
    }

}
