package test;

import entities.evaluation;
import entities.question;
import entities.response;
import services.EvalService;
import services.Reponseservice;
import services.quesservice;
import utils.MyDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//evaluation test1 =new evaluation("evlaution reseau","test de certificat ccna ","facille",50,1000,0,new Date(2023 , 5, 6),"adel bettaieb",20);
        List<response> allResponses = new ArrayList<>();
        List<question> allQuestions = new ArrayList<>();

        evaluation test2 =new evaluation(4,"tesssssssssssssssst java","test de certificat javascript ","facille",50,new Time(1000),0,new Date(2023 , 5, 6),"adel bettaieb",20,"java");
question q1=new question(1,"cest quoi le protcol dtp ","reseau");
        question q2=new question(2,"cest quoi jdk ","java");

        question q3=new question(3,"cest quoi le api ","web");


        response rep1 = new response("Le JDK est un ensemble d'outils logiciels que vous pouvez utiliser pour" +
                " développer les applications Java. Vous pouvez configurer le JDK dans votre environnement de développement " +
                "en le téléchargeant et en l'installant.", 9, response.status .ONE);

        ;
        EvalService servicetest =new EvalService();
        quesservice servicequestion =new quesservice();
        Reponseservice resservice =new Reponseservice();

         Connection connection = MyDB.getInstance().getConnection();
        ;

        // Display responses for the question
        //q2.loadResponses(connection);
        //q2.displayResponses();

      //ajouter evaluation
//test2.loadQuestionsAndResponsesFromDatabase(connection);
//test2.displayQuestionsAndResponses();
        System.out.println(servicetest.getEvaluationByName("tesssssssssssssssst java"));



        //try {
         //  servicetest.ajouter(test2);
           // System.out.println("hiii");
         //} catch (SQLException e) {
           // System.out.println(e.getMessage());        }


        //   afficher evaluation
         // try {
           //  System.out.println(servicetest.afficher());
         //} catch (SQLException e) {
          //System.out.println(e.getMessage());        }



        //modifier evaluation
        // try {
        //  servicetest.modifier(test2);
        // }catch(SQLException e){
        //  System.out.println(e.getMessage());
        //}

    //delete evaluation

        //  try {
        // servicetest.supprimer(test2);
        //  }catch(SQLException e){
        //   System.out.println(e.getMessage());
        // }


        //jouter question

       // try{

        //servicequestion.ajouter(q2);

         //}catch (SQLException e){
          //System.out.println(e.getMessage());
         //}


        //delete question
      //  try
        //{
          //  servicequestion.supprimer(q1);
            //servicequestion.supprimer(q2);
            //servicequestion.supprimer(q3);
        //}catch (SQLException e){
         //   System.out.println(e.getMessage());
        //}


        //modifier question
        //  try {
        //    servicequestion.modifier(q1);
        //  }catch(SQLException e){
        // System.out.println(e.getMessage());
        // }

       // affichage de question
        //[entities.question@10d59286] kif yji probleme hatha 5atar na9As tostring

        // try {
         // System.out.println(servicequestion.afficher());
         //} catch (SQLException e) {
         //System.out.println(e.getMessage());
        // }


        // ajout reponce

        //try {
         //  resservice.ajouter(rep1);
         //} catch (SQLException e) {
        //System.out.println(e.getMessage());        }



    // affichage les reponce

     //try {
    // System.out.println(resservice.afficher());
    //} catch (SQLException e) {
    //System.out.println(e.getMessage());
    //}



}}
