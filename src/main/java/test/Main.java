package test;

import entities.Achat;
import entities.Favoris;
import entities.Inscrit;
import entities.Panier;
import services.ServiceFavoris;
import services.ServiceInscrit;
import services.ServicePanier;
import services.ServiceAchat;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

//Panier
/*
        Panier p1 = new Panier(33, 1,1, LocalDate.of(2023, 1, 1),"inscrite ");
        Panier p2 = new Panier( 2,2, LocalDate.of(2024, 7, 7),"inscrite" );
        Panier p3 = new Panier( 36 );

        ServicePanier services = new ServicePanier();

        // ajouter au panier
        try {
            services.ajouterPanier(p1);
            services.ajouterPanier(p2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }




        //Affichage

        try {
            System.out.println(services.afficherPanierAdmin());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(services.afficherPanierUser(1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        // modification
       try {
            services.modifierPanier(p1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



       //supprimer

        try {
            services.supprimerPanier(p3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

 */



//Favoris
/*
        ServiceFavoris service = new ServiceFavoris();
        Favoris f1 = new Favoris(80);
        Favoris f2 = new Favoris(6,100);


        // ajouter
        try {
            service.ajouterFavoris(f1);
            service.ajouterFavoris(f2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }






        //Affichage

        try{
            System.out.println(service.afficherFavoris());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }


      // modification
        try {
            service.modifieFavoris(f2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }





        //supprimer

        try {
            service.supprimerFavoris(f2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

 */



//Inscrit
        /*
        ServiceInscrit srv = new ServiceInscrit();
        Inscrit i1 = new Inscrit(7,1,2,LocalDate.of(2024, 5, 5));
        Inscrit i2 = new Inscrit(8,2,1,LocalDate.of(2023, 2, 4));


        // ajouter
        try {
            srv.ajouterInscrit(i1);
            srv.ajouterInscrit(i2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



        //Affichage

        try{
            System.out.println(srv.afficherInscritAdmin());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // modification
        try {
            srv.modifierInscrit(i1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



      //supprimer

        try {
            srv.supprimerInscrit(i2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

  */




 //Achat
        Achat a1 = new Achat(1, 2, 20.10, LocalDate.of(2023, 1, 1));
        Achat a2 = new Achat(2, 1, 7.25, LocalDate.of(2023, 5, 5));
        Achat a3 = new Achat(9,2, 1, 200.0, LocalDate.of(2024, 4, 4));

        ServiceAchat services = new ServiceAchat();


        // ajouter achat

 /*       try {
            services.ajouterAchat(a1);
            services.ajouterAchat(a2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

  */

        //Affichage

        try{
            System.out.println(services.afficherAchatAdmin());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        // modification
/*       try {
            services.modifierAchat(a3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

 */



        //supprimer

        try {
            services.supprimerAchat(a3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }

}
