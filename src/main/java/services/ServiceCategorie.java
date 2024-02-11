package services;

import entities.Categorie;
import entities.Formation;
import utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServiceCategorie implements IService<Categorie> {

    private Connection connection ;
    public ServiceCategorie(){
        connection = MyDB.getInstance().getConnection();
    }
    @Override
    public void ajouter(Categorie categorie) throws SQLException {
        String req = "INSERT INTO categorie(nomCategorie) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1, categorie.getNomCategorie());


        preparedStatement.executeUpdate();
    }

    @Override
    public void modifier(Categorie categorie) throws SQLException {

    }

    @Override
    public void supprimer(Categorie categorie) throws SQLException {

    }

    @Override
    public List<Formation> afficher() throws SQLException {
        return null;
    }
}
