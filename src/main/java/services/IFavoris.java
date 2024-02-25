package services;

import entities.Favoris;

import java.sql.SQLException;
import java.util.List;

public interface IFavoris {
    void ajouterFavoris(Favoris favoris) throws SQLException;
    void modifierFavoris(Favoris favoris) throws SQLException;
    void supprimerFavoris(Favoris favoris) throws SQLException;
    List<Favoris> afficherFavorisAdmin() throws SQLException;
    List<Favoris> afficherFavorisUser(int idUser) throws SQLException;
}