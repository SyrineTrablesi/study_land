package test;

import entities.Categorie;
import entities.Formation;
//import services.ServiceCategorie;
import services.ServiceFormation;
import utils.MyDB;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.Calendar;
import java.util.List;


public class Main {
    public static void main(String[] args) {
//    MyDB db1= MyDB.getInstance();
        // Date de début spécifique
        Calendar calDebut = Calendar.getInstance();
        calDebut.set(2022, Calendar.JANUARY, 1); // Année, Mois (indexé à partir de 0), Jour
        Date dateDebut = calDebut.getTime();

        // Date de fin spécifique
        Calendar calFin = Calendar.getInstance();
        calFin.set(2023, Calendar.DECEMBER, 31); // Année, Mois (indexé à partir de 0), Jour
        Date dateFin = calFin.getTime();
        Formation f1 = new Formation("java","khouloud",6,dateDebut,dateFin,1650,"bac");
        Formation f2 = new Formation("symfony","chaabi",8,dateDebut,dateFin,1650,"intermediaire");
        Formation f3 = new Formation("java","tanena",3,dateDebut,dateFin,1650,"bac");


        ServiceFormation services =new ServiceFormation();
//        try {
//            services.ajouter(f1);
//            services.ajouter(f2);
//            services.ajouter(f3);
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());}

        //affichage
//        try {
//            System.out.println(services.afficher());
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
        //modifier formation

//        try {
//            services.modifier(f2);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


        //****************************************************
        //trier selon les titres des formations


// obtenir la list des formations a partir de service
//        List<Formation> formations = null;
//        try {
//            formations = services.afficher();
//        } catch (SQLException e) {
//            e.printStackTrace(); // Handle the exception properly
//        }

//trier la liste des formations selon titre avec TitreComparator
//        Collections.sort(formations, new ServiceFormation.TitreComparator());

// afficher les formations trier
//        for (Formation formation : formations) {
//            System.out.println(formation);
//        }


        //**** recherche by id des formations


//        try {
//            // Obtain the Formation with ID 21
//            Formation formationById = services.rechercherParId(20);
//            if (formationById != null) {
//                System.out.println("Formation found: " + formationById);
//            } else {
//                System.out.println("Formation not found.");
//            }
//        } catch (SQLException e) {
        // e.printStackTrace(); // Handle the exception properly
        // }

//************supprimer selon lid
//        try {
//            services.supprimer(f3);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }


        // PARTIIIIIIIIIIIIIIEEEE CATEGORIEEEEEE
//        Categorie c1 = new Categorie(2,"pascal");
//        ServiceCategorie service = new ServiceCategorie();
//        try {
//            service.ajouter(c1); // Use the correct service object and Categorie object
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }

        //***modifier categorie
//        try {
//            service.modifier(c1);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());        }

        //****supprimer un categorie
//        try {
//            service.supprimer(c1);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }


    }
}
