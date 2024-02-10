package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {
    private final String URL= "jdbc:mysql://localhost:3306/studyland";
    private final String USER = "root";
    private final String PWD = "";
    private Connection connection ;


    //3 variable pour stocker l'instance
    private static MyDB instance;



    // 1 rendre le constructeur privee
    public MyDB(){
        try {
            connection = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("connected to database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // 2methode static pour utiliser le constructeur
    public static MyDB getInstance(){
        if (instance==null){
            instance = new MyDB();
        }return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

