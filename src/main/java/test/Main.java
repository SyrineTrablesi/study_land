package test;

import entities.Admin;
import entities.Apprenant;
import entities.Formateur;
import entities.User;
import services.ServiceAdmin;
import services.ServiceApprenant;
import services.ServiceFormateur;
import utils.MyBD;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        MyBD db = MyBD.getInstance();
       /* Admin adminUser= new Admin(3,"trabelsi", "syrine", "syrinet6@gmail.com", "hama123");
        Formateur formateurUser = new Formateur(12,"5", "henda", "syrinet6@gmail.com", "henda");
        ServiceFormateur serviceFormateur= new ServiceFormateur();
        ServiceApprenant serviceApprenant=new ServiceApprenant();
         ServiceAdmin serviceAdmin =new ServiceAdmin();


        */
      /*
      AJOUTER
      try {
            serviceApprenant.ajouter(ApprenantUser);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

        /*
        AFFICHAGE

        try{
            System.out.println(serviceFormateur.afficher());
            }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }*/

      /*
      SUPPRIMER
      try {
            serviceAdmin.supprimer(adminUser);
            System.out.println("delete");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }*/
       /*
       MODIFIER
       try {
            serviceAdmin.modifier(adminUser);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }*/
        //RechercheMétode par Email
       /* Apprenant apprenantUser = new Apprenant(19, "sara", "saroura", "Sara@gmail.com", "degla");
        ServiceApprenant serviceApprenant = new ServiceApprenant();
        String email = "Sara@gmail.com";
        try {
            Apprenant apprenant = serviceApprenant.rechercheApprenantParEmail(email);
            if (apprenant != null) {
                System.out.println("Apprenant trouvé : " + apprenant);
            } else {
                System.out.println("Aucun apprenant trouvé avec l'e-mail : " + email);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        ServiceFormateur serviceFormateur = new ServiceFormateur();
        String emailFormateur = "syrinet6@gmail.com";
        try {
            Formateur formateur = serviceFormateur.rechercheFormateurParEmail(emailFormateur);
            if (formateur != null) {
                System.out.println("Apprenant trouvé : " + formateur);
            } else {
                System.out.println("Aucun apprenant trouvé avec l'e-mail : " + email);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/
     /*   ServiceApprenant serviceApprenant= new ServiceApprenant();
        Apprenant ApprenantUser= new Apprenant("hmemch","hamaa","hama@gmail.com","degla","degla");
        try {
            serviceApprenant.ajouter(ApprenantUser);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
*/

    }}


