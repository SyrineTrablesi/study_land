package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class MyBD {
    private final String URL = "jdbc:mysql://localhost:3306/studyland";
    private final String USER = "root";
    private final String PWD = "";

    private Connection connection;
    private static MyBD instance;
    //Méthode de sagloton
    //Rendre le constructeure privee
    private MyBD() {
        try {
            connection = DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Creer une methode staticpour utiliserr bd
    public static MyBD getInstance() {
        if (instance == null) {
            instance = new MyBD();
            System.out.println("connecteed");
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
