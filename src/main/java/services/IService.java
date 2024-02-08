package services;

import entities.Formation;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    public void ajouter (T t) throws SQLException;
    public void modifier (T t) throws SQLException;
    public void supprimer (T t) throws SQLException;
    public List<Formation> afficher () throws SQLException;


}
