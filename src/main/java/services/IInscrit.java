package services;

import entities.Inscrit;

import java.sql.SQLException;
import java.util.List;

public interface IInscrit<I> {
    void ajouterInscrit(Inscrit inscrit) throws SQLException;

    void modifierInscrit(Inscrit inscrit) throws SQLException;

    void supprimerInscrit(Inscrit idInscrit) throws SQLException;
    List<Inscrit> afficherInscritAdmin() throws SQLException;
    List<Inscrit> afficherInscritUser(int idUser) throws SQLException;
}