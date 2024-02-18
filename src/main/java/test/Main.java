package test;

import entities.Achat;
import entities.Favoris;
import entities.Panier;
import services.ServiceFavoris;
import services.ServicePanier;
import services.ServiceAchat;

import java.sql.SQLException;
import java.util.Date;

public class Main {

    public static void main(String[] args){

//Panier
        /*
        Panier p1 = new Panier(1, 1, new Date(2023, 1, 1), "favoris");
        Panier p2 = new Panier(3,7, 7,new Date(2024, 7, 7), "inscrite");

        ServicePanier services = new ServicePanier();

        // ajouter au panier

        try {
            services.ajouterPanier(p1);
            services.ajouterPanier(p2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



        //Affichage

        try{
            System.out.println(services.afficherPanier());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        // modification
       try {
            services.modifierPanier(p2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



        //supprimer

        try {
            services.supprimerPanier(p2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

 */






//Favoris

   /*   ServiceFavoris service = new ServiceFavoris();
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
/*      ServiceInscrit srv = new ServiceInscrit();
        Inscrit i1 = new Inscrit(10,10,10,10,new Date(2024-1900, 2, 1));
        Inscrit i2 = new Inscrit(4,40,40,30,30,new Date(2024-1900, 3, 14));


        // ajouter
        try {
            srv.ajouterInscrit(i1);
            srv.ajouterInscrit(i2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }







        //Affichage

        try{
            System.out.println(srv.afficherInscrit());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }


       // modification
        try {
            srv.modifierInscrit(i2);
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
        Achat a1 = new Achat(55, 11, 20.10, new Date(2023-1900, 1-1, 1)); // 1er janvier 2023
        Achat a2 = new Achat(8,30, 70, 7.25, new Date(2024-1900, 9-1, 9));    // 7 juillet 2024

        ServiceAchat services = new ServiceAchat();
        // ajouter achat
/*
        try {
            services.ajouterAchat(a1);
            services.ajouterAchat(a2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

 */



        //Affichage

        try{
            System.out.println(services.afficherAchat());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        // modification
   /*     try {
            services.modifierAchat(a2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    */


        //supprimer

        try {
            services.supprimerAchat(a2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }








    }



}
