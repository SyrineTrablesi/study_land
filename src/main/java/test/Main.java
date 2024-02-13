package test;

import entities.Message;
import services.ServiceMessage;
import utils.MyDB;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        /* MyDB db1 = new MyDB(); */
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        java.util.Date utilDate = null;
        java.sql.Date sqlDate = null;

        try {
            // Convertir la chaîne de caractères en un objet Date
            utilDate = dateFormat.parse("10-20-2020");
            // Convertir java.util.Date en java.sql.Date
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            System.out.println("Erreur lors de la conversion de la date: " + e.getMessage());
        }

        if (sqlDate != null) {
        Message m1 = new Message(1,3,"Bonjour",sqlDate);


        ServiceMessage services = new ServiceMessage();

        try {
            services.ajouter(m1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
        }
}
