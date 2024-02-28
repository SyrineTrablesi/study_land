package services;

import entities.Admin;
import entities.Apprenant;
import entities.User;
import utils.MyBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ServiceUser {
    private Connection connection;
    ServiceAdmin serviceAdmin=new ServiceAdmin();
    ServiceFormateur serviceFormateur=new ServiceFormateur();
    ServiceApprenant serviceApprenant=new ServiceApprenant();
    public ServiceUser() {
        connection = MyBD.getInstance().getConnection();
    }


    //Modifier User

    //RecherUser by email
    public User rechercheUserParEmail(String email) throws SQLException {
        String req = "SELECT * FROM user WHERE  email = ?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, email);
        ResultSet result = pre.executeQuery();
        if (result.next()) {
            int id = result.getInt("id_user");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String adresseEmail = result.getString("email");
            String password = result.getString("password");
            String role = result.getString("Role");
            return new User(id, nom, prenom, adresseEmail, password,role);
        } else {
            return null;
        }
    }

//Update
public  User UpdateMdp(User user,String password) throws  Exception{
    String req = "UPDATE user SET password=?, confirmer_password=? WHERE id_user=?";
    PreparedStatement pre = connection.prepareStatement(req);
    pre.setString(1, password);
    pre.setString(2, password);
    pre.setInt(3, user.getId());
    pre.executeUpdate();
    return new User(user.getId(), user.getNom(), user.getPrenom(), user.getEmail(), password, user.getRole(),password);
    }

    public User connexion(String email,String password)throws  Exception{
        String req = "SELECT * FROM user WHERE email=? and password=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, email);
        pre.setString(2, password);
        ResultSet resultSet = pre.executeQuery();
        while (resultSet.next()){
            return new User(resultSet.getInt("id_user"),resultSet.getString("nom"),resultSet.getString("prenom"),resultSet.getString("email"),resultSet.getString("password"),resultSet.getString("role"));
        }
        return  null;
    }

}