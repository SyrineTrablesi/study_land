package test;

import entities.evaluation;
import services.EvalService;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class Main {
    public static void main(String[] args) {

//evaluation test1 =new evaluation("evlaution reseau","test de certificat ccna ","facille",50,1000,0,new Date(2023 , 5, 6),"adel bettaieb",20);
        evaluation test2 =new evaluation(2,"tesssssssssssssssst java","test de certificat javascript ","facille",50,new Time(1000),0,new Date(2023 , 5, 6),"adel bettaieb",20);

          EvalService servicetest =new EvalService();

      //ajouter


        //try {
        //    servicetest.ajouter(test2);
        //  } catch (SQLException e) {
        //    System.out.println(e.getMessage());        }


        //   afficher
          try {
             System.out.println(servicetest.afficher());
         } catch (SQLException e) {
          System.out.println(e.getMessage());        }



        //modifier
    try {
        servicetest.modifier(test2);
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }

    //delete

        try {
            servicetest.supprimer(test2);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }}
