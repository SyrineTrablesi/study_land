package services;

import entities.Message;

import java.sql.SQLException;
import java.util.List;

public interface IService <T> {

    public void ajouter (T t) throws SQLException;
    public void modifier (T t) throws SQLException;
    public void supprimer (T t) throws SQLException;

    List<Message> afficher() throws SQLException;
}
