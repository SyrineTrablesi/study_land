package test;

import entities.Admin;
import entities.User;
import services.ServiceAdmin;
import utils.MyBD;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        MyBD db = MyBD.getInstance();
        User user1 = new User();
        user1.setEmail("syrine@gmail.com");
        Admin adminUser = new Admin(3,"trabelsi", "syrine", "syrinet6@gmail.com", "hama123");
        ServiceAdmin serviceAdmin = new ServiceAdmin();
       /* try {
            serviceAdmin.ajouter(adminUser);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
      /* try {
            serviceAdmin.supprimer(adminUser);
            System.out.println("delete");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }*/
       /* try {
            serviceAdmin.modifier(adminUser);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }*/
      /*  try{
            System.out.println(serviceAdmin.afficher());
            }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }*/
}
}