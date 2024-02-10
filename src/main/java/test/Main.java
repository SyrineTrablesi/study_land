package test;
import entities.Project;
import services.ServiceProject;
import utils.MyDB;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        MyDB db1 = MyDB.getInstance();
        System.out.println(db1.hashCode());
Project project1= new Project("Projet java 17","Projet Java a terminer selon blablabla", new Date(2023 , 9, 9), new Date(2025 , 10, 1), 7);

        ServiceProject serviceProject= new ServiceProject();

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
        // modification du projet
        try {
            serviceProject.modifier(project1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //supprimer

        try {
            serviceProject.supprimer(project1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
