package services;

import entities.Project;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class ServiceProject implements IService<Project> {
    private Connection connection ;
    public ServiceProject(){
        connection = MyDB.getInstance().getConnection();
    }
    @Override
    public void ajouter(Project project) throws SQLException {
        String req = "INSERT INTO projet(NomProjet, DescProjet, Date_D, Date_F, nb_taches) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1, project.getNomProjet());
        preparedStatement.setString(2, project.getDescProjet());
        preparedStatement.setDate(3, new java.sql.Date(project.getDate_D().getTime()));
        preparedStatement.setDate(4, new java.sql.Date(project.getDate_F().getTime()));
        preparedStatement.setInt(5, project.getNb_taches());


        preparedStatement.executeUpdate();
    }

    @Override
    public void modifier(Project project) throws SQLException {
        String req = "UPDATE projet SET NomProjet=?, DescProjet=?, Date_D=?, Date_F=?, nb_taches=? WHERE id_Projet=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setString(1, project.getNomProjet());
            pre.setString(2, project.getDescProjet());
            pre.setDate(3, new java.sql.Date(project.getDate_D().getTime()));
            pre.setDate(4, new java.sql.Date(project.getDate_F().getTime()));
            pre.setInt(5, project.getNb_taches());
            pre.setInt(6, project.getId_Projet());
            pre.executeUpdate();
        }
    }

    @Override
    public void supprimer(Project project) throws SQLException {
        String req ="delete from projet where id_Projet=?";
        PreparedStatement pre =connection.prepareStatement(req);
        pre.setInt(1,project.getId_Projet());
        pre.executeUpdate();
    }

    @Override
    public List<Project> afficher() throws SQLException {
        String req = "select * from projet";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<Project> list = new ArrayList<>();
        while (res.next()){
            Project p = new Project();
            p.setId_Projet(res.getInt(1));
            p.setNomProjet(res.getString(2));
            p.setDescProjet(res.getString(3));
            p.setDate_D(res.getDate(4));
            p.setDate_F(res.getDate(5));
            p.setNb_taches(res.getInt(6));
            list.add(p);
        }
        return list;
    }



}
