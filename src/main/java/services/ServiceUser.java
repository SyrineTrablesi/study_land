package services;

import entities.Admin;
import entities.Apprenant;
import entities.Formateur;
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
    public void modifier(User user) throws SQLException {
        if (user.getRole()=="Admin"){
            serviceAdmin.modifier((Admin) user);
        }
        if(user.getRole()=="Apprenant"){
            serviceApprenant.modifier((Apprenant) user);
        }
        if (user.getRole()=="Formateur"){
            serviceFormateur.modifier((Formateur) user);
        }

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
