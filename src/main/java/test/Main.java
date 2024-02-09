package test;

import entities.Admin;
import entities.Formateur;
import entities.User;
import services.ServiceAdmin;
import services.ServiceFormateur;
import utils.MyBD;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        MyBD db = MyBD.getInstance();
       /* Admin adminUser= new Admin(3,"trabelsi", "syrine", "syrinet6@gmail.com", "hama123");
        Formateur formateurUser = new Formateur(12,"5", "henda", "syrinet6@gmail.com", "henda");
        ServiceAdmin serviceAdmin = new ServiceAdmin();
        ServiceFormateur serviceFormateur= new ServiceFormateur();

        /*try {
            serviceFormateur.ajouter(formateurUser);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

        /* try{
            System.out.println(serviceFormateur.afficher());
            }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }*/


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