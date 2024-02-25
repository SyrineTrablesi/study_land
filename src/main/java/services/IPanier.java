package services;

import entities.Panier;

import java.sql.SQLException;
import java.util.List;

public interface IPanier<P> {
    void ajouterPanier(Panier panier) throws SQLException;
    void modifierPanier(Panier panier) throws SQLException;
    void supprimerPanier(Panier panier) throws SQLException;
    List<Panier> afficherPanierAdmin() throws SQLException;
    List<Panier> afficherPanierUser(int idUser) throws SQLException;
}