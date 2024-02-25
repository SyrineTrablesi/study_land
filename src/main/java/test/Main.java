package test;

import entities.Categorie;
import entities.Formation;
import entities.Cours;
//import services.ServiceCategorie;
import services.ServiceCategorie;
import services.ServiceFormation;
import services.ServiceCours;

import utils.MyDB;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
//    MyDB db1= MyDB.getInstance();
        // Date de début spécifique
        Calendar calDebut = Calendar.getInstance();
        calDebut.set(2022, Calendar.JANUARY, 1); // Année, Mois (indexé à partir de 0), Jour
        Date dateDebut = calDebut.getTime();

        // Date de fin spécifique
        Calendar calFin = Calendar.getInstance();
        calFin.set(2023, Calendar.DECEMBER, 31); // Année, Mois (indexé à partir de 0), Jour
        Date dateFin = calFin.getTime();
//        Formation f1 = new Formation(9,"pascal", "cours", 3,dateDebut, dateFin, 1650,"intermidiaire", "Pascal");
//        Formation f2 = new Formation("symfony","mehdi",8,dateDebut,dateFin,1650,"intermediaire");
//        Formation f3 = new Formation("java","sami",3,dateDebut,dateFin,1650,"bac");


        ServiceFormation services = new ServiceFormation();
//        try {
//            services.ajouter(f1);
////            services.ajouter(f2);
////            services.ajouter(f3);
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }

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
        Categorie c1 = new Categorie(2, "pascal");
        ServiceCategorie service = new ServiceCategorie();
//        try {
//            service.ajouter(c1); // Use the correct service object and Categorie object
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
        // ***modifier categorie
        // try {
        // service.modifier(c1);
        // } catch (SQLException e) {
        // System.out.println(e.getMessage()); }

        // ****supprimer un categorie
        // try {
        // service.supprimer(c1);
        // } catch (SQLException e) {
        // System.out.println(e.getMessage());
        // }
//        try {
//            System.out.println(service.afficher());
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }

        //********************cours***************************************
        Connection connection = null; // Initialize your connection object here

        // Read the PDF file and convert it to a byte array for the course description
        byte[] descriptionBytes = null;
        try {
            File pdfFile = new File("C:\\Users\\mohamed salah bedoui\\Desktop\\poly2013.pdf"); // Update with the actual path to your PDF file
            descriptionBytes = Files.readAllBytes(pdfFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return; // Exit the method if an error occurs
        }

        // Create a new course instance
        Cours cr = new Cours(5, "salah", descriptionBytes, 9);
        ServiceCours serviceC = new ServiceCours();

        // Call the method to add the course to the database
//        try {
//            serviceC.ajouter(cr);
//            System.out.println("Course added successfully!");
//            } catch (SQLException e) {
//            System.out.println("Error adding course: " + e.getMessage());
//        }
        // afficher cours
//        try {
//            List<Cours> coursList = serviceC.afficher();
//            System.out.println("Cours list:");
//            for (Cours cours : coursList) {
//                System.out.println(cours);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error retrieving courses: " + e.getMessage());
//        }
        //supprimer cours
//        try {
//            // Retrieve a course from the database
//            List<Cours> coursList = serviceC.afficher();
//            Cours coursToDelete = coursList.get(0); // Assuming there is at least one course in the database
//
//            // Delete the course
//            serviceC.supprimer(cr);
//            System.out.println("Course deleted successfully!");
//
//            // Verify that the course has been deleted
//            coursList = serviceC.afficher();
//            boolean courseDeleted = true;
//            for (Cours cours : coursList) {
//                if (cours.getIdCour() == coursToDelete.getIdCour()) {
//                    courseDeleted = false;
//                    break;
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error: " + e.getMessage());
//        }


//modifier cours

// Modify the course in the database
        try {
            serviceC.modifier(cr);
            System.out.println("Course modified successfully!");
        } catch (SQLException e) {
            System.out.println("Error modifying course: " + e.getMessage());
        }

        // Verify that the course was modified
//        try {
//            List<Cours> coursList = serviceC.afficher();
//            boolean courseModified = false;
//            for (Cours cours : coursList) {
//                if (cours.getIdCour() == cr.getIdCour()) {
//                    if (cours.getNom_Cours().equals(cr.getNom_Cours()) &&
//                            Arrays.equals(cours.getDescription_Cours(), cr.getDescription_Cours()) &&
//                            cours.getIdFormation() == cr.getIdFormation()) {
//                        courseModified = true;
//                        break;
//                    }
//                }
//            }
//            if (courseModified) {
//                System.out.println("Course modified successfully!");
//            } else {
//                System.out.println("Error: Course not modified.");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error retrieving courses: " + e.getMessage());
//        }
    }


}