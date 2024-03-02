package test;

import entities.Favoris;
import entities.Formation;
import entities.Inscrit;
import services.ServiceFavoris;
import services.ServiceFormation;
import services.ServiceInscrit;

import java.sql.SQLException;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        // Création de deux objets Inscrit
        Inscrit inscrit1 = new Inscrit(1, 6, new Date());
        Inscrit inscrit2 = new Inscrit(7);



        // Création de deux objets Favori
        //      Favoris favoris1 = new Favoris(2, 6, new Date());
       //       Favoris favoris2 = new Favoris(7);



        ServiceInscrit services = new ServiceInscrit();

        // ajouter
        try {
            services.ajouter(inscrit1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }




        //Affichage

        try {
            System.out.println(services.afficher());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

/*
        // modification
        try {
            services.modifier(inscrit1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

 */


        //supprimer

//        try {
//            services.supprimer(favoris1);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }


    }
}
