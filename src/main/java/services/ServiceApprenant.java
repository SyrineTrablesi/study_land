package services;

import entities.Admin;
import entities.Apprenant;
import utils.MyBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceApprenant implements IUserService<Apprenant> {
    private static Connection connection;

    public ServiceApprenant() {
        connection = MyBD.getInstance().getConnection();
    }

    @Override
    public void ajouter(Apprenant apprenant) throws SQLException {
        String req = "INSERT INTO user (nom, prenom, email, password, Role) " +
                "VALUES ('" + apprenant.getNom() + "', '" + apprenant.getPrenom() + "', '" + apprenant.getEmail() + "', '" + apprenant.getPassword() + "', 'Apprenant')";
        Statement ste = connection.createStatement();
        ste.executeUpdate(req);
    }

    @Override
    public void modifier(Apprenant apprenant) throws SQLException {
        String req = "UPDATE user SET nom=?, prenom=?, password=? WHERE id_user=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, apprenant.getNom());
        pre.setString(2, apprenant.getPrenom());
        pre.setString(3, apprenant.getPassword());
        pre.setInt(4, apprenant.getId());
        pre.executeUpdate();
    }

    @Override
    public void supprimer(Apprenant apprenant) throws SQLException {
        String req = "delete from user where id_user=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, apprenant.getId());
        pre.executeUpdate();
    }


    @Override
    public List<Apprenant> afficher() throws SQLException {
        String req = "SELECT * FROM user WHERE Role=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, "Apprenant");
        ResultSet resultSet = pre.executeQuery();

        List<Apprenant> ApprenatList = new ArrayList<>();
        while (resultSet.next()) {
            Apprenant apprenant = new Apprenant();
            apprenant.setId(resultSet.getInt("id_user"));
            apprenant.setNom(resultSet.getString("nom"));
            apprenant.setPrenom(resultSet.getString("prenom"));
            apprenant.setEmail(resultSet.getString("email"));
            apprenant.setRole(resultSet.getString("role"));
            apprenant.setPassword(resultSet.getString("password"));
            ApprenatList.add(apprenant);
        }
        return ApprenatList;
    }
//Rechercher  rechercheApprenantParEmail
    public Apprenant rechercheApprenantParEmail(String email) throws SQLException {
        String req = "SELECT * FROM user WHERE role = 'Apprenant' AND email = ?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, email);
        ResultSet result = pre.executeQuery();
        if (result.next()) {
            int id = result.getInt("id_user");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String adresseEmail = result.getString("email");
            String password=result.getString("password");
            return new Apprenant(id, nom, prenom, adresseEmail,password);
        } else {
            return null;
        }
    }

}