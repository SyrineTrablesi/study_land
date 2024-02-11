package services;

import entities.Cours;
import entities.Formation;
import utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServiceCours implements IService<Cours>{
    private Connection connection ;
    public ServiceCours(){
        connection = MyDB.getInstance().getConnection();
    }
    @Override
    public void ajouter(Cours cours) throws SQLException {
// Préparer la requête SQL
        String sql = "INSERT INTO cour_formation (Nom_Cours, Description_Cours) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        // Définir les valeurs des paramètres
        statement.setString(1, cours.getNomCours());
        statement.setBytes(2, cours.getDescription_Cours());

        // Exécuter la requête
        statement.executeUpdate();
    }

    @Override
    public void modifier(Cours cours) throws SQLException {

    }

    @Override
    public void supprimer(Cours cours) throws SQLException {

    }

    @Override
    public List<Formation> afficher() throws SQLException {
        return null;
    }
}
