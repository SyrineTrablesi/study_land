package test;
import entities.Project;
import entities.StatutTache;
import entities.taches_projet;
import services.ServiceProject;
import services.ServiceTaches;
import utils.MyDB;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

// ajout du projet

       /* try{
            serviceProject.ajouter(project1);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
*/

//Affichage

   /*     try {
            System.out.println(serviceProject.afficher());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
*/
      /*  // modification du projet
        try {
            serviceProject.modifier(project1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

        //supprimer

       /* try {
            serviceProject.supprimer(project1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/



// pour la tache
    //    taches_projet tp1 = new taches_projet("tache 1","tache numero 1 pour java ",new Date(2023 , 9, 9),new Date(2025 , 10, 9),StatutTache.afaire,"yassine",1);
      //  taches_projet tp2 = new taches_projet("tache 2","tache numero 2 pour java ",new Date(2023 , 9, 9),new Date(2025 , 10, 9),StatutTache.terminee,"yassine",1);

        //ServiceTaches service = new ServiceTaches();
//        try {
//           service.ajouter(tp2); //
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//      }

        //***modifier tache
      /*  try {
            service.modifier(tp2);
       } catch (SQLException e) {
            System.out.println(e.getMessage());        }*/

        //supprimer une tache
       /* try {
            service.supprimer(tp2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Categorie c1 = new Categorie(2,"pascal");
        ServiceCategorie service = new ServiceCategorie();
        try {
            service.ajouter(c1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
       /*  //modifier tache
         try {
         service.modifier(tp2);
         } catch (SQLException e) {
         System.out.println(e.getMessage()); }*/

         //supprimer un tache
       /*  try {
         service.supprimer(tp2);
         } catch (SQLException e) {
         System.out.println(e.getMessage());
         }*/

    }
}
