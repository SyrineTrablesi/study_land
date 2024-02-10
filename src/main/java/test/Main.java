package test;

import entities.Favoris;
import entities.Panier;
import services.ServicePanier;
import utils.MyDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args){




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





    }

}
