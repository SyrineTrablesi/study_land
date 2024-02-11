package test;

import entities.Favoris;
import entities.Inscrit;
import entities.Panier;
import services.ServiceFavoris;
import services.ServiceInscrit;
import services.ServicePanier;
import utils.MyDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args){



/*
       Panier p1 = new Panier(1,1,4,new Date(2023 , 5, 6));
       Panier p2 = new Panier(2,6,9,new Date(2000 , 11, 11));

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

/*
        //Favoris
        ServiceFavoris service = new ServiceFavoris();
        Favoris f1 = new Favoris(3);
        Favoris f2 = new Favoris(2,5);


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
        ServiceInscrit srv = new ServiceInscrit();
        Inscrit i1 = new Inscrit(1,6,10,2);
        Inscrit i2 = new Inscrit(2,5,20,3);

/*
        // ajouter
        try {
            srv.ajouterInscrit(i1);
            srv.ajouterInscrit(i2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

 */




        //Affichage

        try{
            System.out.println(srv.afficherInscrit());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }


      /*  // modification
        try {
            srv.modifierInscrit(i2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

       */



      //supprimer

        try {
            srv.supprimerInscrit(i2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }






    }

}
